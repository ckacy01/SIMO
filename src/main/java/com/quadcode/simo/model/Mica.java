package com.quadcode.simo.model;

public class Mica {
    private int id;
    private String tipo;
    private float Contado;
    private float Credito;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public float getContado() {
        return Contado;
    }
    public void setContado(float Contado) {
        this.Contado = Contado;
    }
    public float getCredito() {
        return Credito;
    }
    public void setCredito(float credito) {
        Credito = credito;
    }

}