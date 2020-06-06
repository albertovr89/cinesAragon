package com.vrodriguez.cinesaragon.modelos;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class Persona {
    /*
    {
        "id": "1",
        "usuario": "alberto",
        "telefono": "954334432",
        "correo": "aksj@gmail.com",
        "tarjeta": "12485796",
        "ok": true
    }
    */
    private Long id;
    private String usuario;
    private String telefono;
    private String tarjeta;
    private String correo;
    private boolean ok;

    public Persona() {}

    public Persona(Long id, String usuario, String telefono, String tarjeta, String correo) {
        this.id = id;
        this.usuario = usuario;
        this.telefono = telefono;
        this.tarjeta = tarjeta;
        this.correo = correo;
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

    public static Persona fromJSON(JSONObject json) throws IllegalArgumentException, JSONException {
        if (!json.getBoolean("ok")) {
            String reason = json.getString("reason");
            throw new IllegalArgumentException(reason);
        }

        return new Persona(
                json.getLong("id"),
                json.getString("usuario"),
                json.getString("telefono"),
                json.getString("tarjeta"),
                json.getString("correo"));
    }
}
