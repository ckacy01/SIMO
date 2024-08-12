package com.quadcode.simo.model;

public class ProductosyMicas {
    private int id;
    private String Nombre;
    private Float PrecioContado;
    private Float PrecioCredito;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public Float getPrecioContado() {
        return PrecioContado;
    }

    public void setPrecioContado(Float precioContado) {
        PrecioContado = precioContado;
    }

    public Float getPrecioCredito() {
        return PrecioCredito;
    }

    public void setPrecioCredito(Float precioCredito) {
        PrecioCredito = precioCredito;
    }
}
