package com.vrodriguez.cinesaragon.modelos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;


import java.util.ArrayList;

@Parcel
public class Pelicula {

    public long id;
    public String titulo;
    public String sinopsis;
    public String genero;
    public String clasi;
    public String estrellas;
    public String imagen;
    public String sesion1;
    public String sesion2;
    public String sesion3;
    public String sesion4;
    public String foto1;
    public String foto2;
    public String foto3;
    public String foto4;
    public String duracion;
    public String trailer;
    public boolean ok;

    public Pelicula() {}

    public Pelicula(long id, String titulo, String sinopsis, String imagen, String genero, String clasi, String estrellas, String sesion1, String sesion2, String sesion3, String sesion4, String foto1, String foto2, String foto3, String foto4, String duracion, String trailer) {
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
        this.foto1 = foto1;
        this.foto2 = foto2;
        this.foto3 = foto3;
        this.foto4 = foto4;
        this.duracion = duracion;
        this.trailer = trailer;
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

    public String getEstrellas() {
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

    public String getFoto1() { return foto1;}

    public String getFoto2() {
        return foto2;
    }

    public String getFoto3() {
        return foto3;
    }

    public String getFoto4() {
        return foto4;
    }

    public String getDuracion() {
        return duracion;
    }

    public String getTrailer() {
        return trailer;
    }

    public static Pelicula fromJSON(JSONObject json) throws IllegalArgumentException, JSONException {
        return new Pelicula(
                json.getLong("id"),
                json.getString("titulo"),
                json.getString("sinopsis"),
                json.getString("imagen"),
                json.getString("genero"),
                json.getString("clasi"),
                json.getString("estrellas"),
                json.getString("sesion1"),
                json.getString("sesion2"),
                json.getString("sesion3"),
                json.getString("sesion4"),
                json.getString("foto1"),
                json.getString("foto2"),
                json.getString("foto3"),
                json.getString("foto4"),
                json.getString("duracion"),
                json.getString("trailer"));
    }

    public static ArrayList<Pelicula> fromJSONArray(JSONArray array) {
        ArrayList<Pelicula> resultado = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            try{
                resultado.add(Pelicula.fromJSON(array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return resultado;
    }
}
