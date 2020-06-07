package com.vrodriguez.cinesaragon.modelos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Pelicula {

    long id;
    String titulo;
    String sinopsis;
    String genero;
    String clasi;
    long estrellas;
    String imagen;
    String sesion1;
    String sesion2;
    String sesion3;
    String sesion4;
    boolean ok;

    public Pelicula(long id, String titulo, String sinopsis, String imagen, String genero, String clasi, Long estrellas, String sesion1, String sesion2, String sesion3, String sesion4) {
        this.id = id;
        this.titulo = titulo;
        this.sinopsis = sinopsis;
        this.genero = genero;
        this.clasi = clasi;
        this.estrellas = estrellas;
        this.imagen = imagen;
        this.sesion1 = sesion1;
        this.sesion2 = sesion2;
        this.sesion3 = sesion3;
        this.sesion4 = sesion4;
    }


    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public String getGenero() {
        return genero;
    }

    public String getClasi() {
        return clasi;
    }

    public Long getEstrellas() {
        return estrellas;
    }

    public String getImagen() {
        return imagen;
    }

    public String getSesion1() {
        return sesion1;
    }

    public String getSesion2() {
        return sesion2;
    }

    public String getSesion3() {
        return sesion3;
    }

    public String getSesion4() {
        return sesion4;
    }

    public Pelicula fromJSON(JSONObject json) throws IllegalArgumentException, JSONException {
        if (!json.getBoolean("ok")) {
            String reason = json.getString("reason");
            throw new IllegalArgumentException(reason);
        }

        return new Pelicula(
                json.getLong("id"),
                json.getString("titulo"),
                json.getString("sinopsis"),
                json.getString("imagen"),
                json.getString("genero"),
                json.getString("clasi"),
                json.getLong("estrellas"),
                json.getString("sesion1"),
                json.getString("sesion2"),
                json.getString("sesion3"),
                json.getString("sesion4"));
    }

    public Pelicula(JSONObject jsonObject) throws JSONException {
        this.id = jsonObject.getLong("id");
        this.titulo = jsonObject.getString("titulo");
        this.sinopsis = jsonObject.getString("sinopsis");
        this.imagen = jsonObject.getString("imagen");
        this.genero = jsonObject.getString("genero");
        this.clasi = jsonObject.getString("clasi");
        this.estrellas = jsonObject.getLong("estrellas");
        this.sesion1 = jsonObject.getString("sesion1");
        this.sesion2 = jsonObject.getString("sesion2");
        this.sesion3 = jsonObject.getString("sesion3");
        this.sesion4 = jsonObject.getString("sesion4");
    }

    public static ArrayList<Pelicula> fromJSONArray(JSONArray array) {
        ArrayList<Pelicula> resultado = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            try{
                resultado.add(new Pelicula(array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return resultado;
    }
}
