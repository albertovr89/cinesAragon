package com.vrodriguez.cinesaragon.apis;

import com.vrodriguez.cinesaragon.Constantes.Configuracion;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class UpdateClient {

    private OkHttpClient client;

    public UpdateClient(OkHttpClient client) {
        this.client = client;
    }

    public void updateClient(Long id, String password, String nombre , String apellidos, String telefono, String correo, String fecha, String tarjeta, Callback cb) {
        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("id", String.valueOf(id))
                .add("pass", password)
                .add("nombre", nombre)
                .add("apellidos", apellidos)
                .add("telefono", telefono)
                .add("correo", correo)
                .add("fecha", fecha)
                .add("tarjeta", tarjeta)
                .build();

        Request request = new Request.Builder()
                .url(Configuracion.SERVIDOR + Configuracion.FUNCION_UPDATE_USER)
                .post(formBody)
                .build();

        client.newCall(request).enqueue(cb);
    }

}
