package com.quadcode.simo.model;

import java.sql.Date;

public class Abonos {
    private int Id;
    private int VentaId;
    private Date FechaAbono;
    private Float Monto;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getVentaId() {
        return VentaId;
    }

    public void setVentaId(int ventaId) {
        VentaId = ventaId;
    }

    public Date getFechaAbono() {
        return FechaAbono;
    }

    public void setFechaAbono(Date fechaAbono) {
        FechaAbono = fechaAbono;
    }

    public Float getMonto() {
        return Monto;
    }

    public void setMonto(Float monto) {
        Monto = monto;
    }
}
