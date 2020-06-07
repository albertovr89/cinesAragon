package com.vrodriguez.cinesaragon.apis;

import com.vrodriguez.cinesaragon.Constantes.Configuracion;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class GetCines {

    private OkHttpClient client;

    public GetCines(OkHttpClient client) {
        this.client = client;
    }


    public void pedirCines(String tabla, String id, Callback cb) {
        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("objeto", tabla)
                .add("id", id)
                .build();

        Request request = new Request.Builder()
                .url(Configuracion.SERVIDOR + Configuracion.FUNCION_EXTRAER)
                .post(formBody)
                .build();

        client.newCall(request).enqueue(cb);
    }
}