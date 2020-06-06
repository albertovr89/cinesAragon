package com.vrodriguez.cinesaragon.almacen;

public class Usuarios {

    private String usuario;
    private String contrasena;
    private String telefono;
    private String mail;
    private String tarjeta;

    public Usuarios(String usuario, String contrasena, String telefono, String mail, String tarjeta) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.telefono = telefono;
        this.mail = mail;
        this.tarjeta = tarjeta;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }
}