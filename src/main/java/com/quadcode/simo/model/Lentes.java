package com.quadcode.simo.model;

public class Lentes {
    private int Id;
    private String Nombre;
    private int Stock;
    private float PrecioContado;
    private float PrecioCredito;
    private String Descripcion;

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

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public float getPrecioContado() {
        return PrecioContado;
    }

    public void setPrecioContado(float precioContado) {
        PrecioContado = precioContado;
    }

    public float getPrecioCredito() {
        return PrecioCredito;
    }

    public void setPrecioCredito(float precioCredito) {
        PrecioCredito = precioCredito;
    }
}
