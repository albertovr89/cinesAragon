package com.vrodriguez.cinesaragon;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.vrodriguez.cinesaragon.modelos.Pelicula;

import org.parceler.Parcels;

public class DetallePeli extends AppCompatActivity {

    TextView titulotxt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_peli);

        titulotxt = findViewById(R.id.titulotxt);

        Pelicula pelicula = (Pelicula) Parcels.unwrap(getIntent().getParcelableExtra("pelicula"));
        assert pelicula != null;
        titulotxt.setText(pelicula.getTitulo());
    }
}