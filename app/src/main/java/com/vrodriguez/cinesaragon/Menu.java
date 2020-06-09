package com.vrodriguez.cinesaragon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.vrodriguez.cinesaragon.modelos.Persona;

import org.parceler.Parcels;

public class Menu extends AppCompatActivity implements View.OnClickListener {

    Button btncartelera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Persona persona = (Persona) Parcels.unwrap(getIntent().getParcelableExtra("persona"));
        Toast.makeText(getApplicationContext(), "Hola " + persona.getUsuario(), Toast.LENGTH_SHORT).show();

        btncartelera = findViewById(R.id.btncartelera);
        btncartelera.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btncartelera:
                Intent carteleraIntent = new Intent(Menu.this, Cartelera.class);
                startActivityForResult(carteleraIntent, 0);
                break;

        }
    }
}