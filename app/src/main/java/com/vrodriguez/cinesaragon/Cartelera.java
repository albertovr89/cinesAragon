package com.vrodriguez.cinesaragon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vrodriguez.cinesaragon.adaptadores.PelisAdapter;
import com.vrodriguez.cinesaragon.apis.GetPeliculas;
import com.vrodriguez.cinesaragon.modelos.Pelicula;
import com.vrodriguez.cinesaragon.utils.ItemClickSupport;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class Cartelera extends AppCompatActivity {

    private static String TABLA = "peliculas";
    GetPeliculas getPeliculas;
    ArrayList<Pelicula> peliculas;
    PelisAdapter adapter;
    private final String TODOS = "Todas";
    String[] generos = {TODOS, "Drama", "Terror", "Ciencia ficción", "Anime", "Animación"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartelera);

        RecyclerView rvpeliculas = findViewById(R.id.rvpeliculas);

        OkHttpClient httpClient = new OkHttpClient();
        getPeliculas = new GetPeliculas(httpClient);

        peliculas = new ArrayList<Pelicula>();
        adapter = new PelisAdapter(this, peliculas);
        ItemClickSupport.addTo(rvpeliculas).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                irAPeli(peliculas.get(position));
            }
        });

        rvpeliculas.setAdapter(adapter);
        rvpeliculas.setLayoutManager(new LinearLayoutManager(this));


        //Pruebas Spinner Toolbar
        Toolbar toolbar = findViewById(R.id.carteleratoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cartelera");

        Spinner filtroToolbar = findViewById(R.id.filtroToolbar);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getSupportActionBar().getThemedContext(), R.layout.appbar_filter_genero,
                generos);
        adapter.setDropDownViewResource(R.layout.appbar_filter_list);
        filtroToolbar.setAdapter(adapter);

        filtroToolbar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // ???
                String seleccion = generos[position];
                if (seleccion.equals(TODOS)) {
                    seleccion = "";
                }

                pedirPeliculas(seleccion);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //fin experimento

        pedirPeliculas("");

    }

    private void pedirPeliculas(String genero) {
        getPeliculas.pedirPelis(TABLA, genero, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                final String miRespuesta = Objects.requireNonNull(response.body()).string();

                Cartelera.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // JSONObject json = new JSONObject(miRespuesta);
                            // Pelicula p = Pelicula.fromJSON(json);

                            JSONObject contenedor = new JSONObject(miRespuesta);
                            peliculas.clear();
                            peliculas.addAll(Pelicula.fromJSONArray(contenedor.getJSONArray("elementos")));
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

//EXP2

    protected void irAPeli(Pelicula p) {
        Intent pelintent = new Intent(Cartelera.this, DetallePeli.class);
        pelintent.putExtra("pelicula", Parcels.wrap(p));
        startActivityForResult(pelintent, 0);
    }



/*
    private void respuestaDialogo() {
        pedirPeliculas("Drama");
    }*/
}