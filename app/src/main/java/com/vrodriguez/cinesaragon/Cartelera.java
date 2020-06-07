package com.vrodriguez.cinesaragon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.vrodriguez.cinesaragon.adaptadores.PelisAdapter;
import com.vrodriguez.cinesaragon.modelos.Pelicula;

import java.util.ArrayList;

public class Cartelera extends AppCompatActivity {

    ArrayList<Pelicula> peliculas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartelera);

        RecyclerView rvpeliculas = (RecyclerView) findViewById(R.id.rvpeliculas);

        peliculas = Pelicula.fromJSONArray();

        PelisAdapter adapter = new PelisAdapter(peliculas);

        rvpeliculas.setAdapter(adapter);

        rvpeliculas.setLayoutManager(new LinearLayoutManager(this));

    }
}