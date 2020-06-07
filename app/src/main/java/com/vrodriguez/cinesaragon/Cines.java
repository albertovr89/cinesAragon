package com.vrodriguez.cinesaragon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vrodriguez.cinesaragon.apis.GetCines;
import com.vrodriguez.cinesaragon.apis.LoginClient;
import com.vrodriguez.cinesaragon.modelos.Edificio;
import com.vrodriguez.cinesaragon.modelos.Persona;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class Cines extends AppCompatActivity {

    TextView titulo, direccion, telefono, mail, web;
    ImageView fotoCine, peli1, peli2, peli3, peli4;
    private GetCines getCines;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cines);

        titulo = findViewById(R.id.titulotxt);

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
