package com.quadcode.simo.model;

public class Referencia {
    private int Id;
    private int DireccionId;
    private String Nombre;
    private String Telefono;
    private String Relacion;

    public Referencia(int Id, int DireccionId, String Nombre, String Telefono, String Relacion) {
        this.Id = Id;
        this.DireccionId = DireccionId;
        this.Nombre = Nombre;
        this.Telefono = Telefono;
        this.Relacion = Relacion;
    }
    public int getId() {
        return Id;
    }
    public void setId(int Id) {
        this.Id = Id;
    }
    public int getDireccionId() {
        return DireccionId;
    }
    public void setDireccionId(int DireccionId) {
        this.DireccionId = DireccionId;
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    public String getTelefono() {
        return Telefono;
    }
    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }
    public String getRelacion() {
        return Relacion;
    }
    public void setRelacion(String Relacion) {
        this.Relacion = Relacion;
    }
}
