package com.vrodriguez.cinesaragon;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.vrodriguez.cinesaragon.modelos.Persona;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class Login extends AppCompatActivity {

    private EditText usuariotxt, passtxt;
    String url = "http://192.168.1.129/cinesAragon/loginCine.php";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuariotxt = findViewById(R.id.usuariotxt);
        passtxt = findViewById(R.id.passtxt);
        Button btnlogin = findViewById(R.id.btnlogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = usuariotxt.getText().toString();
                String pass = passtxt.getText().toString();

                final String postUrl = "http://192.168.1.129/cinesAragon/loginCine.php";

                try {
                    postLogin(postUrl,user,pass);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }



            void postLogin (String postUrl, String user, String pass) throws IOException {

                OkHttpClient client = new OkHttpClient();

                RequestBody formBody = new FormBody.Builder()
                        .add("usuario", user)
                        .add("pass", pass)
                        .build();

                Request request = new Request.Builder()
                        .url(postUrl)
                        .post(formBody)
                        .build();

                client.newCall(request).enqueue(new Callback() {
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
                                try{
                                    JSONObject json = new JSONObject(miRespuesta);
                                    Persona p = Persona.fromJSON(json);

                                    Intent logintent = new Intent(Login.this, Menu.class);
                                    startActivityForResult(logintent, 0);
                                } catch (IllegalArgumentException error) {
                                    Toast.makeText(getApplicationContext(), "Error:"+ error.getMessage(), Toast.LENGTH_SHORT).show();
                                } catch (JSONException e){
                                    e.printStackTrace();
                                }
                            }
                        });


                    }
                });
            }
        });

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





