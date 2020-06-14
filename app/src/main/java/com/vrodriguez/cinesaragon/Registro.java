package com.vrodriguez.cinesaragon;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.vrodriguez.cinesaragon.apis.LoginClient;
import com.vrodriguez.cinesaragon.apis.RegisterClient;
import com.vrodriguez.cinesaragon.modelos.Persona;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;


public class Registro extends AppCompatActivity  {

    EditText usuario, contrasena, nombre, apellidos, telefono, correo, fecha,  tarjeta;
    Button btnregistro;
    private RegisterClient registerClient;
    private CinesAragonApplication application;


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        usuario = findViewById(R.id.usertxt);
        contrasena = findViewById(R.id.passtxt);
        nombre = findViewById(R.id.nombretxt);
        apellidos = findViewById(R.id.apelltxt);
        telefono = findViewById(R.id.tlfntxt);
        correo = findViewById(R.id.mailtxt);
        fecha = findViewById(R.id.fechatxt);
        tarjeta = findViewById(R.id.tarjetatxt);
        btnregistro = findViewById(R.id.btnregistro);

        Toolbar toolbar = findViewById(R.id.toolbarregistro);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Registro");

        application = (CinesAragonApplication )getApplication();
        registerClient = new RegisterClient(application.getHttpClient());


        btnregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = usuario.getText().toString();
                String pass = contrasena.getText().toString();
                String name = nombre.getText().toString();
                String surname = apellidos.getText().toString();
                String phone = telefono.getText().toString();
                String mail = correo.getText().toString();
                String date = fecha.getText().toString();
                String card = tarjeta.getText().toString();

                registerClient.registro(user, pass, name, surname, phone, mail, date, card, new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        call.cancel();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                        final String miRespuesta = response.body().string();

                        Registro.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    JSONObject json = new JSONObject(miRespuesta);
                                    Persona p = Persona.fromJSON(json);
                                    application.setUsuarioLogueado(p);
                                    vaciarCampos();
                                    irAMenu();
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
        });

    }

    protected void irAMenu() {
        Intent regintent = new Intent(Registro.this, Menu.class);
        startActivityForResult(regintent, 0);
    }

    public void vaciarCampos() {
        usuario.setText("");
        contrasena.setText("");
        nombre.setText("");
        apellidos.setText("");
        telefono.setText("");
        correo.setText("");
        fecha.setText("");
        tarjeta.setText("");
    }




}
