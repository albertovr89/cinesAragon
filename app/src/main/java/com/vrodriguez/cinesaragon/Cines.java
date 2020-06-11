package com.vrodriguez.cinesaragon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.vrodriguez.cinesaragon.apis.GetCines;
import com.vrodriguez.cinesaragon.apis.GetPeliculas;
import com.vrodriguez.cinesaragon.apis.LoginClient;
import com.vrodriguez.cinesaragon.modelos.Edificio;
import com.vrodriguez.cinesaragon.modelos.Pelicula;
import com.vrodriguez.cinesaragon.modelos.Persona;

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

    TextView titulo, direccion, telefono, mail, web;
    ImageView fotocine;
    private GetCines getCines;
    private GetPeliculas getPeliculas;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cines);

        titulo = (TextView) findViewById(R.id.titulotxt);
        fotocine = (ImageView) findViewById(R.id.fotoCine);
        direccion = (TextView) findViewById(R.id.direccion);
       RecyclerView rvportadas = findViewById(R.id.rvportadas);



        Bundle cine = this.getIntent().getExtras();
        String idCine = cine.getString("id");
//      Toast.makeText(getApplicationContext(), "Hola " + idCine, Toast.LENGTH_SHORT).show();

        OkHttpClient httpClient = new OkHttpClient();
        getCines = new GetCines(httpClient);
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
                                    titulo.setText(c.getNombre());
                                    direccion.setText(c.getImagen());
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
/*
                //Exp pedir lista de imagenes
        OkHttpClient httpClient = new OkHttpClient();
        getPeliculas = new GetPeliculas(httpClient);

        getPeliculas.pedirPelis2(idCine, new Callback() {
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
                            Pelicula pelicula = Pelicula.fromJSON(json);
                            titulo.setText(c.getNombre());
                            direccion.setText(c.getImagen());
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
        });*/


        }

}
