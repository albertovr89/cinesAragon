package com.vrodriguez.cinesaragon;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.vrodriguez.cinesaragon.modelos.Pelicula;

import org.parceler.Parcels;

public class DetallePeli extends AppCompatActivity {

    TextView titulotxt;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        titulotxt = findViewById(R.id.titulotxt);

        Pelicula pelicula = (Pelicula) Parcels.unwrap(getIntent().getParcelableExtra("pelicula"));
        assert pelicula != null;
        titulotxt.setText(pelicula.getTitulo());
    }




}