package com.vrodriguez.cinesaragon.almacen;

public class DatosdeCine {

    private String nombreCine;
    private String direccionCine;
    private Integer idFotoCine;
    private String telefonoCine;
    private String mailCine;
    private String webCine;
    private Integer idPelicula1, idPelicula2, idPelicula3, idPelicula4;

    public DatosdeCine (String nombreCine, String direccionCine, Integer idFotoCine, String telefonoCine, String mailCine, String webCine, Integer idPelicula1, Integer idPelicula2, Integer idPelicula3, Integer idPelicula4) {
        this.nombreCine = nombreCine;
        this.direccionCine = direccionCine;
        this.idFotoCine = idFotoCine;
        this.telefonoCine = telefonoCine;
        this.mailCine = mailCine;
        this.webCine = webCine;
        this.idPelicula1 = idPelicula1;
        this.idPelicula2 = idPelicula2;
        this.idPelicula3 = idPelicula3;
        this.idPelicula4 = idPelicula4;
    }

    public String getNombreCine() {
        return nombreCine;
    }

    public void setNombreCine(String nombreCine) {
        this.nombreCine = nombreCine;
    }

    public String getDireccionCine() {
        return direccionCine;
    }

    public void setDireccionCine(String direccionCine) {
        this.direccionCine = direccionCine;
    }

    public Integer getIdFotoCine() {
        return idFotoCine;
    }

    public void setIdFotoCine(Integer idFotoCine) {
        this.idFotoCine = idFotoCine;
    }

    public String getTelefonoCine() {
        return telefonoCine;
    }

    public void setTelefonoCine(String telefonoCine) {
        this.telefonoCine = telefonoCine;
    }

    public String getMailCine() {
        return mailCine;
    }

    public void setMailCine(String mailCine) {
        this.mailCine = mailCine;
    }

    public String getWebCine() {
        return webCine;
    }

    public void setWebCine(String webCine) {
        this.webCine = webCine;
    }

    public Integer getIdPelicula1() {
        return idPelicula1;
    }

    public void setIdPelicula1(Integer idPelicula1) {
        this.idPelicula1 = idPelicula1;
    }

    public Integer getIdPelicula2() {
        return idPelicula2;
    }

    public void setIdPelicula2(Integer idPelicula2) {
        this.idPelicula2 = idPelicula2;
    }

    public Integer getIdPelicula3() {
        return idPelicula3;
    }

    public void setIdPelicula3(Integer idPelicula3) {
        this.idPelicula3 = idPelicula3;
    }

    public Integer getIdPelicula4() {
        return idPelicula4;
    }

    public void setIdPelicula4(Integer idPelicula4) {
        this.idPelicula4 = idPelicula4;
    }
}
