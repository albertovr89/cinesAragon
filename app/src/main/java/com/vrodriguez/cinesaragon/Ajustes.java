package com.vrodriguez.cinesaragon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vrodriguez.cinesaragon.apis.GetClient;
import com.vrodriguez.cinesaragon.apis.LoginClient;
import com.vrodriguez.cinesaragon.apis.UpdateClient;
import com.vrodriguez.cinesaragon.modelos.Persona;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class Ajustes extends AppCompatActivity {

    EditText pass, nombre, apell, tlfn, mail, fecha, tarjeta;
    TextView user;
    private GetClient getClient;
    private UpdateClient updateClient;
    private CinesAragonApplication application;
    Persona usuariologeado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        user = findViewById(R.id.userpersonal);
        pass = findViewById(R.id.passpersonal);
        nombre = findViewById(R.id.nombrepersonal);
        apell = findViewById(R.id.apellidospersonal);
        tlfn = findViewById(R.id.tlfnpersonal);
        mail = findViewById(R.id.mailpersonal);
        fecha = findViewById(R.id.fechapersonal);
        tarjeta = findViewById(R.id.tarjetapersonal);
        Toolbar toolbar = findViewById(R.id.toolbarajustes);
        Button btnactualizar = findViewById(R.id.btnactualizar);


        application = (CinesAragonApplication) getApplication();
        updateClient = new UpdateClient(application.getHttpClient());

        usuariologeado = application.getUsuarioLogueado();

        user.setText(usuariologeado.getUsuario());
        pass.setText(usuariologeado.getPass());
        nombre.setText(usuariologeado.getNombre());
        apell.setText(usuariologeado.getApellidos());
        tlfn.setText(usuariologeado.getTelefono());
        mail.setText(usuariologeado.getCorreo());
        fecha.setText(usuariologeado.getFecha());
        tarjeta.setText(usuariologeado.getTarjeta());


        btnactualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarUsuario();
            }
        });
    }


    protected void actualizarUsuario() {
        String passact = pass.getText().toString();
        String nombreact = nombre.getText().toString();
        String apellact = apell.getText().toString();
        String tlfnact = tlfn.getText().toString();
        String mailact = mail.getText().toString();
        String fechaact = fecha.getText().toString();
        String tarjetaact = tarjeta.getText().toString();

        updateClient.updateClient(usuariologeado.getId(), passact, nombreact, apellact, tlfnact, mailact, fechaact, tarjetaact, new Callback() {

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                final String miRespuesta = response.body().string();

                Ajustes.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject json = new JSONObject(miRespuesta);
                            Persona p = Persona.fromJSON(json);

                            application.setUsuarioLogueado(p);
                            Toast.makeText(getApplicationContext(), "Datos actualizados correctamente", Toast.LENGTH_SHORT).show();

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
}