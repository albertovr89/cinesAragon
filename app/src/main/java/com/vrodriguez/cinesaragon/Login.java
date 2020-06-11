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

        OkHttpClient httpClient = new OkHttpClient();
        loginClient = new LoginClient(httpClient);

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

                                    irAMenu(p);
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


    protected void irAMenu(Persona p) {
        Intent logintent = new Intent(Login.this, Menu.class);
        logintent.putExtra("persona", Parcels.wrap(p));
        startActivityForResult(logintent, 0);
    }
}











/*
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONObject jsonObject = new JSONObject();

                try{
                    jsonObject.put("usuario", usuariotxt.getText().toString());
                    jsonObject.put("pass", passtxt.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                LoginUser loginUser = new LoginUser();
                loginUser.execute(jsonObject.toString());

            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    private class LoginUser extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog = new ProgressDialog(Login.this);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setTitle("Espere");
            progressDialog.setMessage("Verificando el Login");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... param) {

            JSONObject jsonObject = FuncionesJSON.ObtenerParam(url, param[0]);
            return jsonObject.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();

            try{
                JSONObject j2 = new JSONObject(s);

                String res1 = j2.getString("ok");
                String res2 = j2.getString("reason");

                if(res1.equalsIgnoreCase("true")) {
                    Intent logintent = new Intent(Login.this, Menu.class);
                    startActivityForResult(logintent, 0);
                } else {
                    Toast.makeText(getApplicationContext(), "Error:"+res2, Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


    }

}

*/

 /*
//Funciona
    private void validarUsuario () {

        String url = "http://192.168.1.129/cinesAragon/loginCine.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {


                    if (response.contains("false")) {
                        Toast.makeText(getApplicationContext(), "Error:" , Toast.LENGTH_SHORT).show();
                    } else {

                        Intent logintent = new Intent(Login.this, Menu.class);
                        startActivityForResult(logintent, 0);
                    }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Ocurrió un error: " +error.toString(),Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams () {
                Map<String,String> parametros = new HashMap<>();
                parametros.put("usuario", usuariotxt.getText().toString());
                parametros.put("pass", passtxt.getText().toString());

                return parametros;
            }
        };

        RequestQueue requestQueue =  Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
*/





