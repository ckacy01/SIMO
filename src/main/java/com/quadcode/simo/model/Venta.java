package com.quadcode.simo.model;

import java.sql.Date;
import java.time.LocalDate;

public class Venta {
    private int Id;
    private String NombreCliente;
    private String NombrePaciente;
    private String NombreProducto;
    private String NombreMica;
    private String Tinte;
    private String MetodoPago;
    private String PeriodoAbonos;
    private Float SaldoActual;
    private Float CostoTotal;
    private Float Enganche;
    private Date FechaVenta;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombreCliente() {
        return NombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        NombreCliente = nombreCliente;
    }

    public String getNombrePaciente() {
        return NombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        NombrePaciente = nombrePaciente;
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

    public String getTinte() {
        return Tinte;
    }

    public void setTinte(String tinte) {
        Tinte = tinte;
    }

    public String getMetodoPago() {
        return MetodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        MetodoPago = metodoPago;
    }

    public Float getSaldoActual() {
        return SaldoActual;
    }

    public void setSaldoActual(Float saldoActual) {
        SaldoActual = saldoActual;
    }

    public String getPeriodoAbonos() {
        return PeriodoAbonos;
    }

    public void setPeriodoAbonos(String periodoAbonos) {
        PeriodoAbonos = periodoAbonos;
    }

    public Float getCostoTotal() {
        return CostoTotal;
    }

    public void setCostoTotal(Float costoTotal) {
        CostoTotal = costoTotal;
    }

    public Float getEnganche() {
        return Enganche;
    }

    public void setEnganche(Float enganche) {
        Enganche = enganche;
    }

    public Date getFechaVenta() {
        return FechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        FechaVenta = fechaVenta;
    }
}
