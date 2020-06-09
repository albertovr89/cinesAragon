package com.vrodriguez.cinesaragon.apis;

import com.vrodriguez.cinesaragon.Constantes.Configuracion;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class GetPeliculas {

    private OkHttpClient client;

    public GetPeliculas(OkHttpClient client) {
        this.client = client;
    }


    public void pedirPelis(String tabla, Callback cb) {
        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("objeto", tabla)
                .build();

        Request request = new Request.Builder()
                .url(Configuracion.SERVIDOR + Configuracion.FUNCION_EXTRAER)
                .post(formBody)
                .build();

        client.newCall(request).enqueue(cb);
    }

    public void pedirPelis2(String idCine, Callback cb) {
        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("id", idCine)
                .build();

        Request request = new Request.Builder()
                .url(Configuracion.SERVIDOR + Configuracion.FUNCION_SACAR_IMAGEN_PELI)
                .post(formBody)
                .build();

        client.newCall(request).enqueue(cb);
    }
}