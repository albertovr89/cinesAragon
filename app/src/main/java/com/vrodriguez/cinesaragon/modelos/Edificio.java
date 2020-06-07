package com.vrodriguez.cinesaragon.modelos;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;


@Parcel
public class Edificio {
/*
{
    "id": "1",
    "nombre": "Cine Venecia",
    "direccion": "C/Milagro 26",
    "telefono": "963852741",
    "correo": "",
    "peli1": "1",
    "peli2": "2",
    "peli3": "3",
    "peli4": "4",
    "peli5": "5",
    "peli6": "0",
    "peli7": "0",
    "peli8": "0",
    "ok": true
}
 */
    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String correo;
    private String peli1;
    private String peli2;
    private String peli3;
    private String peli4;
    private String peli5;
    private String peli6;
    private String peli7;
    private String peli8;
    private boolean ok;


    public Edificio() {
    }

    public Edificio(long id, String nombre, String direccion, String telefono, String correo, String peli1, String peli2, String peli3, String peli4, String peli5, String peli6, String peli7, String peli8) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.peli1 = peli1;
        this.peli2 = peli2;
        this.peli3 = peli3;
        this.peli4 = peli4;
        this.peli5 = peli5;
        this.peli6 = peli6;
        this.peli7 = peli7;
        this.peli8 = peli8;
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

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPeli1() {
        return peli1;
    }

    public void setPeli1(String peli1) {
        this.peli1 = peli1;
    }

    public String getPeli2() {
        return peli2;
    }

    public void setPeli2(String peli2) {
        this.peli2 = peli2;
    }

    public String getPeli3() {
        return peli3;
    }

    public void setPeli3(String peli3) {
        this.peli3 = peli3;
    }

    public String getPeli4() {
        return peli4;
    }

    public void setPeli4(String peli4) {
        this.peli4 = peli4;
    }

    public String getPeli5() {
        return peli5;
    }

    public void setPeli5(String peli5) {
        this.peli5 = peli5;
    }

    public String getPeli6() {
        return peli6;
    }

    public void setPeli6(String peli6) {
        this.peli6 = peli6;
    }

    public String getPeli7() {
        return peli7;
    }

    public void setPeli7(String peli7) {
        this.peli7 = peli7;
    }

    public String getPeli8() {
        return peli8;
    }

    public void setPeli8(String peli8) {
        this.peli8 = peli8;
    }

    public static Edificio fromJSON(JSONObject json) throws IllegalArgumentException, JSONException {
        if (!json.getBoolean("ok")) {
            String reason = json.getString("reason");
            throw new IllegalArgumentException(reason);
        }

        return new Edificio(
                json.getLong("id"),
                json.getString("nombre"),
                json.getString("direccion"),
                json.getString("telefono"),
                json.getString("correo"),
                json.getString("peli1"),
                json.getString("peli2"),
                json.getString("peli3"),
                json.getString("peli4"),
                json.getString("peli5"),
                json.getString("peli6"),
                json.getString("peli7"),
                json.getString("peli8"));
    }
}
