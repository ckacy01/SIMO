package com.quadcode.simo.model;

import java.sql.Date;

public class NuevaVenta {
    private String NombrePaciente;
    private Date FechaVenta;
    private float CostoTotal;
    private float SaldoActual;
    private String PeriodoAbonos;
    private float Enganche;
    private String Tinte;
    private String NombreProducto;
    private String NombreMica;
    private String MetodoPago;

    public String getNombrePaciente() {
        return NombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        NombrePaciente = nombrePaciente;
    }

    public Date getFechaVenta() {
        return FechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        FechaVenta = fechaVenta;
    }

    public float getSaldoActual() {
        return SaldoActual;
    }

    public void setSaldoActual(float saldoActual) {
        SaldoActual = saldoActual;
    }

    public float getCostoTotal() {
        return CostoTotal;
    }

    public void setCostoTotal(float costoTotal) {
        CostoTotal = costoTotal;
    }

    public String getPeriodoAbonos() {
        return PeriodoAbonos;
    }

    public void setPeriodoAbonos(String periodoAbonos) {
        PeriodoAbonos = periodoAbonos;
    }

    public float getEnganche() {
        return Enganche;
    }

    public void setEnganche(float enganche) {
        Enganche = enganche;
    }

    public String getTinte() {
        return Tinte;
    }

    public void setTinte(String tinte) {
        Tinte = tinte;
    }

    public String getNombreProducto() {
        return NombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        NombreProducto = nombreProducto;
    }

    public String getNombreMica() {
        return NombreMica;
    }

    public void setNombreMica(String nombreMica) {
        NombreMica = nombreMica;
    }

    public String getMetodoPago() {
        return MetodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        MetodoPago = metodoPago;
    }
}
