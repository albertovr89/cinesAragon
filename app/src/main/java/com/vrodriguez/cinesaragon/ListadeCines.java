package com.vrodriguez.cinesaragon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.vrodriguez.cinesaragon.adaptadores.CinesAdapter;
import com.vrodriguez.cinesaragon.adaptadores.PelisAdapter;
import com.vrodriguez.cinesaragon.apis.GetCines;
import com.vrodriguez.cinesaragon.apis.GetPeliculas;
import com.vrodriguez.cinesaragon.modelos.Edificio;
import com.vrodriguez.cinesaragon.modelos.Pelicula;
import com.vrodriguez.cinesaragon.utils.ItemClickSupport;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ListadeCines extends AppCompatActivity {

    private static String TABLA = "cines";
    private static String id = "";
    GetCines getCines;
    ArrayList<Edificio> cines;
    CinesAdapter adapter;
    private CinesAragonApplication application;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listade_cines);

        RecyclerView rvcines = findViewById(R.id.rvcines);

        application = (CinesAragonApplication) getApplication();
        getCines = new GetCines(application.getHttpClient());

        cines = new ArrayList<Edificio>();
        adapter = new CinesAdapter(this, cines);
        ItemClickSupport.addTo(rvcines).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                irACine(cines.get(position));
            }
        });

        rvcines.setAdapter(adapter);
        rvcines.setLayoutManager(new LinearLayoutManager(this));

        Toolbar toolbar = findViewById(R.id.listadecinestoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cines");

        pedirCines("");

    }


    private void pedirCines(String id) {
        getCines.pedirCines(TABLA, id, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                final String miRespuesta = Objects.requireNonNull(response.body()).string();

                ListadeCines.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject contenedor = new JSONObject(miRespuesta);
                            cines.addAll(Edificio.fromJSONArray(contenedor.getJSONArray("elementos")));
                            adapter.notifyDataSetChanged();

                        } catch (IllegalArgumentException error) {
                            Toast.makeText(getApplicationContext(), "Error:" + error.getMessage(), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });


            }
        });
    }


    protected void irACine(Edificio cine) {
        Intent cineintent = new Intent(ListadeCines.this, Cines.class);
        cineintent.putExtra("id", String.valueOf(cine.getId()));
        startActivityForResult(cineintent, 0);
    }
}