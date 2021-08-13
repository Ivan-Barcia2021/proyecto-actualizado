package com.ort.apportresuelve;

public class usuario {
    private String DNI;
    private String Departamento;
    private String Nombre;
    private String Sede;
    private String contrasena;
    public usuario(){

    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getDepartamento(){ return Departamento; }
    public void setDepartamento(String Departamento){this.Departamento=Departamento;}
    public  String getNombre() {return Nombre;}
    public void setNombre(String Nombre){this.Nombre=Nombre;}
    public String getSede (){return Sede;}
    public void setSede(String Sede){this.Sede=Sede;}
    public String getContrasena(){return contrasena;}
    public void setContrasena(String contrasena){this.contrasena=contrasena;}

}
