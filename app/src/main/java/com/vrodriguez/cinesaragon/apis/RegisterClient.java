package com.vrodriguez.cinesaragon.apis;

import com.vrodriguez.cinesaragon.Constantes.Configuracion;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class RegisterClient {

    private OkHttpClient client;

    public RegisterClient(OkHttpClient client) {
        this.client = client;
    }

    public void registro(String usuario, String password, String nombre , String apellidos, String telefono, String correo, String fecha, String tarjeta, Callback cb) {
        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("usuario", usuario)
                .add("pass", password)
                .add("nombre", nombre)
                .add("apellidos", apellidos)
                .add("telefono", telefono)
                .add("correo", correo)
                .add("fecha", fecha)
                .add("tarjeta", tarjeta)
                .build();

        Request request = new Request.Builder()
                .url(Configuracion.SERVIDOR + Configuracion.FUNCION_REGISTER)
                .post(formBody)
                .build();

        client.newCall(request).enqueue(cb);
    }
}
