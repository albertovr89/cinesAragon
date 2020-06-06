package com.vrodriguez.cinesaragon;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.vrodriguez.cinesaragon.Constantes.Constantes;

import org.json.JSONObject;


public class Registro extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    EditText nombre, contrasena, telefono, mail, tarjeta;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombre = findViewById(R.id.nombretxt);
        contrasena = findViewById(R.id.passtxt);
        telefono = findViewById(R.id.tlfntxt);
        mail = findViewById(R.id.mailtxt);
        tarjeta = findViewById(R.id.tarjetatxt);

        request= Volley.newRequestQueue(this);
        

    }

    public void registrar (View view) {
        cargarwebservice();
        
        //registrarUsuario();
        //Intent vuelta = new Intent(Registro.this, MainActivity.class);
        //startActivityForResult(vuelta,0);
    }

    private void cargarwebservice() {

        String url = "http://192.168.1.133:4278/cinesAragon/registroJSON.php?nombre="+nombre.getText().toString()+
                "&contrasena="+contrasena.getText().toString()+"&telefono="+telefono.getText().toString()+
                "&correo="+mail.getText().toString()+"&tarjeta="+tarjeta.getText().toString();


        url = url.replace(" ","%20");


        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(Constantes.MY_DEFAULT_TIMEOUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        request.add(jsonObjectRequest);
    }
/*
    private void registrarUsuario (){

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd_usuarios", null, 1);

        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constantes.CAMPO_NOMBRE, nombre.getText().toString());
        values.put(Constantes.CAMPO_CONTRASENA, contrasena.getText().toString());
        values.put(Constantes.CAMPO_TELEFONO, telefono.getText().toString());
        values.put(Constantes.CAMPO_MAIL, mail.getText().toString());
        values.put(Constantes.CAMPO_TARJETA, tarjeta.getText().toString());

        long idResultante = db.insert(Constantes.TABLA_USUARIO, Constantes.CAMPO_NOMBRE, values);

        Toast.makeText(getApplicationContext(),"Id de Registro: "+idResultante, Toast.LENGTH_SHORT).show();
        db.close();
    }*/

    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(getBaseContext(),"No se pudo registrar"+error.toString(), Toast.LENGTH_SHORT).show();
        Log.i("ERROR",error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {

        Toast.makeText(getBaseContext(),"Usuario registrado correctamente", Toast.LENGTH_SHORT).show();

        nombre.setText("");
        contrasena.setText("");
        telefono.setText("");
        mail.setText("");
        tarjeta.setText("");

    }
}
