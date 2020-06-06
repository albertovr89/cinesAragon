package com.vrodriguez.cinesaragon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.vrodriguez.cinesaragon.modelos.Persona;

import org.parceler.Parcels;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Persona persona = (Persona) Parcels.unwrap(getIntent().getParcelableExtra("persona"));
        Toast.makeText(getApplicationContext(), "Hola " + persona.getUsuario(), Toast.LENGTH_SHORT).show();
    }
}
