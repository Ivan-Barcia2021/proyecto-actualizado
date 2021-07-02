package com.ort.apportresuelve;

public class Ubicacion {
    private String edificio;
    private String piso;
    private String aula;
    private  String descripcion;
    private String tipoDeReclamo;

    public String getTipoDeReclamo() {
        return tipoDeReclamo;
    }

    public void setTipoDeReclamo(String tipoDeReclamo) {
        this.tipoDeReclamo = tipoDeReclamo;
    }

    public Ubicacion(){

    }

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }
    public  String getDescripcion(){return descripcion;}
    public void setDescripcion(String descripcion){this.descripcion=descripcion;}
    @Override
    public String toString() {
        return edificio;
    }
}
