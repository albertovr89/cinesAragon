package com.vrodriguez.cinesaragon.Constantes;

public class Constantes {

    //Timeout Volley
    public static final int MY_DEFAULT_TIMEOUT = 30000;

    //Constantes campos tabla usuarios
    public static final String TABLA_USUARIO = "usuarios";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_CONTRASENA = "contrasena";
    public static final String CAMPO_TELEFONO = "telefono";
    public static final String CAMPO_MAIL = "mail";
    public static final String CAMPO_TARJETA = "tarjeta";

    //Orden SQL para crear la tabla usuarios
    public static final String CREAR_TABLA_USUARIO= "CREATE TABLE "+TABLA_USUARIO+" ("+CAMPO_NOMBRE+" TEXT, "+CAMPO_CONTRASENA+" TEXT, "+CAMPO_TELEFONO+" TEXT, "+CAMPO_MAIL+" TEXT, "+CAMPO_TARJETA+" TEXT)";

    //Constantes campos tabla cartelera
    public static final String TABLA_CINES = "cines";
    public static final String CAMPO_ID = "idCine";
    public static final String CAMPO_NOMBREC = "nombreCine";
    public static final String CAMPO_DIRECCION = "DireccionCine";
    public static final String CAMPO_FOTO = "idFotoCine";
    public static final String CAMPO_TELEFONOC = "telefonoCine";
    public static final String CAMPO_MAILC = "mailCine";
    public static final String CAMPO_WEB = "webCine";
    public static final String CAMPO_PELI1 = "idPelicula1";
    public static final String CAMPO_PELI2 = "idPelicula2";
    public static final String CAMPO_PELI3 = "idPelicula3";
    public static final String CAMPO_PELI4 = "idPelicula4";

    //Orden SQL para crear la tabla de los cines

   // public static final String CREAR_TABLA_CINES = "CREATE TABLE "+TABLA_CINES+" ("+CAMPO_ID+""


}
