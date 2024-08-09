package com.quadcode.simo.model;

public class Cliente {
    private String Nombre;

    public Cliente(String nombre) {
        this.Nombre = nombre;
    }


    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    @Override
    public String toString() {
        return Nombre;
    }
}
