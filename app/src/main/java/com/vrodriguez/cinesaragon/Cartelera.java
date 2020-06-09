package com.vrodriguez.cinesaragon;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.vrodriguez.cinesaragon.adaptadores.PelisAdapter;
import com.vrodriguez.cinesaragon.apis.GetPeliculas;
import com.vrodriguez.cinesaragon.modelos.Pelicula;
import com.vrodriguez.cinesaragon.modelos.Persona;

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
     String[] generos = {"Drama","Terror","Ciencia ficción", "Anime", "Animación"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartelera);

        RecyclerView rvpeliculas = findViewById(R.id.rvpeliculas);

        OkHttpClient httpClient = new OkHttpClient();
        getPeliculas = new GetPeliculas(httpClient);

        peliculas = new ArrayList<Pelicula>();
        adapter = new PelisAdapter(this, peliculas);

        rvpeliculas.setAdapter(adapter);
        rvpeliculas.setLayoutManager(new LinearLayoutManager(this));


        //Pruebas Spinner Toolbar
        Toolbar toolbar = findViewById(R.id.carteleratoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final Spinner filtroToolbar = findViewById(R.id.filtroToolbar);
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
                pedirPeliculas(seleccion);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

       //fin experimento

        pedirPeliculas("");

    }

    private void setSupportActionBar(Toolbar toolbar) {
    }


    private void pedirPeliculas(String genero) {
        getPeliculas.pedirPelis(TABLA, new Callback() {
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


                            adapter.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    irAPeli(peliculas);
                                }
                            });

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

    protected void irAPeli(ArrayList<Pelicula> p) {
        Intent pelintent = new Intent(Cartelera.this, DetallePeli.class);
        pelintent.putExtra("pelicula", Parcels.wrap(p));
        startActivityForResult(pelintent, 0);
    }



/*
    private void respuestaDialogo() {
        pedirPeliculas("Drama");
    }*/
}