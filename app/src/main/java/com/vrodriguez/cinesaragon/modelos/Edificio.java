package com.vrodriguez.cinesaragon.modelos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;


@Parcel
public class Edificio {

     public Long id;
     public String nombre;
     public String direccion;
     public String telefono;
     public String correo;
     public String web;
     public String imagen;
     public boolean ok;


    public Edificio() {
    }

    public Edificio(long id, String nombre, String direccion, String telefono, String correo, String web, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.web = web;
        this.imagen = imagen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getWeb() { return web; }

    public void setWeb(String web) { this.web = web; }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public static Edificio fromJSON(JSONObject json) throws IllegalArgumentException, JSONException {
  /*      if (!json.getBoolean("ok")) {
            String reason = json.getString("reason");
            throw new IllegalArgumentException(reason);
        }*/

        return new Edificio(
                json.getLong("id"),
                json.getString("nombre"),
                json.getString("direccion"),
                json.getString("telefono"),
                json.getString("correo"),
                json.getString("web"),
                json.getString("imagen"));
    }

    public static ArrayList<Edificio> fromJSONArray(JSONArray array) {
        ArrayList<Edificio> resultado = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            try{
                resultado.add(Edificio.fromJSON(array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return resultado;
    }
}
