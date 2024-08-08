package com.quadcode.simo.model;

public class Cliente {
    private int Id;
    private String Nombre;
    private String Telefono;
    private String ReferidoId;
    private String DireccionId;

    public Cliente(int id, String nombre, String email, String telefono, String referidoId, String direccionId){
        this.Id = id;
        this.Nombre = nombre;
        this.Telefono = telefono;
        this.ReferidoId = referidoId;
        this.DireccionId = direccionId;
    }

    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getTelefono() {
        return Telefono;
    }
    public void setTelefono(String telefono) {
        Telefono = telefono;
    }
    public String getReferidoId() {
        return ReferidoId;
    }
    public void setReferidoId(String referidoId) {
        ReferidoId = referidoId;
    }
    public String getDireccionId() {
        return DireccionId;
    }
    public void setDireccionId(String direccionId) {
        DireccionId = direccionId;
    }
}
