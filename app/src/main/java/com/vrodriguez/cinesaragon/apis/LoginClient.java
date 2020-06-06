package com.vrodriguez.cinesaragon.apis;

import com.vrodriguez.cinesaragon.Constantes.Configuracion;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class LoginClient {

    private OkHttpClient client;

    public LoginClient(OkHttpClient client) {
        this.client = client;
    }

    public void login(String usuario, String password, Callback cb) {
        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("usuario", usuario)
                .add("pass", password)
                .build();

        Request request = new Request.Builder()
                .url(Configuracion.SERVIDOR + Configuracion.FUNCION_LOGIN)
                .post(formBody)
                .build();

        client.newCall(request).enqueue(cb);
    }

}
