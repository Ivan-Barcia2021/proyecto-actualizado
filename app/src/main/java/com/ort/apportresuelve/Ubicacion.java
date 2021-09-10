package com.ort.apportresuelve;

import android.text.format.DateFormat;

import java.io.Serializable;
import java.util.Date;

public class Ubicacion implements Serializable {
    private  String descripcion;
    private String edificio;
    private String piso;
    private String aula;


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

    private void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    public String getPiso() {
        return piso;
    }

    private void setPiso(String piso) {
        this.piso = piso;
    }

    public String getAula() {
        return aula;
    }

    private void setAula(String aula) {
        this.aula = aula;
    }
    public    String getDescripcion(){return descripcion;}
    private void setDescripcion(String descripcion){this.descripcion=descripcion;}
    @Override
    public String toString() {
        return descripcion;

    }

}
