package com.vrodriguez.cinesaragon;

import android.app.Application;

import com.vrodriguez.cinesaragon.modelos.Persona;

import okhttp3.OkHttpClient;

public class CinesAragonApplication extends Application {
    OkHttpClient client;
    Persona usuarioLogueado;

    @Override
    public void onCreate() {
        super.onCreate();

        client = new OkHttpClient();
    }

    public OkHttpClient getHttpClient() {
        return client;
    }

    public Persona getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Persona usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }
}
