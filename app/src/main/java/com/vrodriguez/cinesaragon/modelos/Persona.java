package com.vrodriguez.cinesaragon.modelos;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Persona {
    /*
    {
      "id": "1",
      "usuario": "alberto",
      "nombre": "",
      "apellidos": "",
      "telefono": "954334432",
      "correo": "aksj@gmail.com",
      "fecha": "0000-00-00",
      "tarjeta": "12485796",
      "ok": true
    }
    */
     Long id;
     String usuario;
     String pass;
     String nombre;
     String apellidos;
     String telefono;
     String tarjeta;
     String correo;
     String fecha;
     boolean ok;

    public Persona() {}

    public Persona(Long id, String usuario, String pass, String nombre, String apellidos, String telefono, String tarjeta, String correo, String fecha) {
        this.id = id;
        this.usuario = usuario;
        this.pass = pass;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.tarjeta = tarjeta;
        this.correo = correo;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {return pass;}

    public void setPass(String pass) {this.pass = pass;}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public static Persona fromJSON(JSONObject json) throws IllegalArgumentException, JSONException {
        if (!json.getBoolean("ok")) {
            String reason = json.getString("reason");
            throw new IllegalArgumentException(reason);
        }

        return new Persona(
                json.getLong("id"),
                json.getString("usuario"),
                "",
                json.getString("nombre"),
                json.getString("apellidos"),
                json.getString("telefono"),
                json.getString("tarjeta"),
                json.getString("correo"),
                json.getString("fecha"));
    }

}
