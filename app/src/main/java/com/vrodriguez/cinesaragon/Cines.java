package com.vrodriguez.cinesaragon;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vrodriguez.cinesaragon.Constantes.Constantes;

public class Cines extends AppCompatActivity {

    TextView titulo, direccion, telefono, mail, web;
    ImageView fotoCine, peli1, peli2, peli3, peli4;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cines);

        titulo = findViewById(R.id.titulo);
        direccion = findViewById(R.id.direccion);
        telefono = findViewById(R.id.telefono);
        mail = findViewById(R.id.mail);
        web = findViewById(R.id.web);
        fotoCine = findViewById(R.id.fotoCine);
        peli1 = findViewById(R.id.peli1);
        peli2 = findViewById(R.id.peli2);
        peli3 = findViewById(R.id.peli3);
        peli4 = findViewById(R.id.peli4);

        conn = new ConexionSQLiteHelper(getApplicationContext(),"bd_cines",null,1);
        //Extraer id para mostrar los datos del cine desde la base de datos

        Bundle datos = this.getIntent().getExtras();
        assert datos != null;
        int clave = datos.getInt("id");

        if(clave == 1) {
            titulo.setText("Pulsado el boton 1");
        } else if (clave == 2) {

        } else if (clave == 3) {

        } else if (clave == 4) {

        } else if (clave == 5) {

        } else if (clave == 6) {

        }
    }


}
