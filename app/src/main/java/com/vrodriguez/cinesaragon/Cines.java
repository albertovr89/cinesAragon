package com.vrodriguez.cinesaragon;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.vrodriguez.cinesaragon.adaptadores.PelisAdapter;
import com.vrodriguez.cinesaragon.adaptadores.PortadasAdapter;
import com.vrodriguez.cinesaragon.apis.GetCines;
import com.vrodriguez.cinesaragon.apis.GetPeliculas;
import com.vrodriguez.cinesaragon.modelos.Edificio;
import com.vrodriguez.cinesaragon.modelos.Pelicula;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class Cines extends AppCompatActivity {

    TextView direccion, telefono, mail, web, nombre;
    ImageView fotocine;

    private ArrayList<Pelicula> peliculas;
    private PortadasAdapter portadasAdapter;

    private GetCines getCines;
    private GetPeliculas getPeliculas;
    private Context context;
    private CinesAragonApplication application;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cines);

        peliculas = new ArrayList<>();
        fotocine = (ImageView) findViewById(R.id.fotoCine);
        direccion = (TextView) findViewById(R.id.direccioncine);
        telefono = findViewById(R.id.telefonocine);
        mail = findViewById(R.id.mailcine);
        web = findViewById(R.id.webcine);
        nombre = findViewById(R.id.nombrecinetoolbar);
        RecyclerView rvportadas = findViewById(R.id.rvportadas);
        portadasAdapter = new PortadasAdapter(this, peliculas);

        application = (CinesAragonApplication )getApplication();

        rvportadas.setAdapter(portadasAdapter);
        rvportadas.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        Toolbar toolbar = findViewById(R.id.toolbarcines);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        Bundle cine = this.getIntent().getExtras();
        String idCine = cine.getString("id");

        getCines = new GetCines(application.getHttpClient());
        String tabla = "cines";

        getCines.pedirCines(tabla, idCine, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                final String miRespuesta = response.body().string();

                Cines.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject json = new JSONObject(miRespuesta);
                            Edificio c = Edificio.fromJSON(json);
                            nombre.setText(c.getNombre());
                            direccion.setText(c.getDireccion());
                            telefono.setText(c.getTelefono());
                            mail.setText(c.getCorreo());
                            web.setText(c.getWeb());

                            Picasso.get()
                                    .load(c.getImagen()).fit().centerCrop()
                                    .error(R.drawable.cines2)
                                    .into(fotocine);

                        } catch (IllegalArgumentException error) {
                            Toast.makeText(getApplicationContext(), "Error:" + error.getMessage(), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

        getPeliculas = new GetPeliculas(application.getHttpClient());

        getPeliculas.pedirPelisDeCine(idCine, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                final String miRespuesta = response.body().string();

                Cines.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject contenedor = new JSONObject(miRespuesta);
                            peliculas.clear();
                            peliculas.addAll(Pelicula.fromJSONArray(contenedor.getJSONArray("elementos")));
                            portadasAdapter.notifyDataSetChanged();
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

}
