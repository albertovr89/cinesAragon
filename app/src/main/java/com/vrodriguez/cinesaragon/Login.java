package com.vrodriguez.cinesaragon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.vrodriguez.cinesaragon.apis.LoginClient;
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


public class Login extends AppCompatActivity {

    private LoginClient loginClient;
    private EditText usuariotxt, passtxt;
    private CinesAragonApplication application;


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuariotxt = findViewById(R.id.usuariotxt);
        passtxt = findViewById(R.id.passtxt);
        Button btnlogin = findViewById(R.id.btnlogin);
        Toolbar toolbar = findViewById(R.id.toolbarlogin);

        //Acciones de la barra
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Login");

        application = (CinesAragonApplication )getApplication();
        loginClient = new LoginClient(application.getHttpClient());

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = usuariotxt.getText().toString();
                String pass = passtxt.getText().toString();

                loginClient.login(user, pass, new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        call.cancel();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                        final String miRespuesta = response.body().string();

                        Login.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    JSONObject json = new JSONObject(miRespuesta);
                                    Persona p = Persona.fromJSON(json);

                                    application.setUsuarioLogueado(p);
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
        Intent logintent = new Intent(Login.this, MenuyCines.class);
        startActivityForResult(logintent, 0);
    }
}





