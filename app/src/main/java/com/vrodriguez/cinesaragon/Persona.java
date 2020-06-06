package com.vrodriguez.cinesaragon;

import org.jetbrains.annotations.NotNull;

public class Persona {

    private String usuario;
    private String pass;

    public Persona(String usuario, String pass){
        this.usuario = usuario;
        this.pass = pass;
    }


    public void setUsuario(String usuario) {

        this.usuario = usuario;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


    public String getUsuario() {
        return usuario;
    }

    public String getPass() {
        return pass;
    }


    @NotNull
    @Override
    public String toString() {
        return "Persona{" +
                " usuario='" + usuario + '\'' +
                ", pass='" + pass +
                '}';
    }
}
