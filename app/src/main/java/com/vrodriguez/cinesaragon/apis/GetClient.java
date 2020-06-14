package com.vrodriguez.cinesaragon.apis;

import com.vrodriguez.cinesaragon.Constantes.Configuracion;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class GetClient {
    private OkHttpClient client;

    public GetClient(OkHttpClient client) {
        this.client = client;
    }


    public void getClient(String id, Callback cb) {
        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("id", id)
                .build();

        Request request = new Request.Builder()
                .url(Configuracion.SERVIDOR + Configuracion.FUNCION_OBTENER_USER)
                .post(formBody)
                .build();

        client.newCall(request).enqueue(cb);
    }

}
