package com.ort.apportresuelve;

public class usuario {
    private int DNI;
    private String DEPARTAMENTO;
    private String NOMBRE;
    private String SEDE;
    private String contraseña;
    public usuario(){

    }
    public int getDNI(){return DNI;}
    public void setDNI(int DNI){this.DNI=DNI;}
    public String getDEPARTAMENTO(){ return DEPARTAMENTO; }
    public void setDEPARTAMENTO(String DEPARTAMENTO){this.DEPARTAMENTO=DEPARTAMENTO;}
    public  String getNOMBRE() {return NOMBRE;}
    public void setNOMBRE(String NOMBRE){this.NOMBRE=NOMBRE;}
    public String getSEDE (){return SEDE;}
    public void setSEDE(String SEDE){this.SEDE=SEDE;}
    public String getContraseña(){return contraseña;}
    public void setContraseña(String contraseña){this.contraseña=contraseña;}

}
