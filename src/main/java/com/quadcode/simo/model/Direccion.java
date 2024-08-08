package com.quadcode.simo.model;

public class Direccion {
    private int Id;
    private String Calle;
    private String CodigoPostal;
    private String EntreCalles;
    private String Colonia;
    private String Referencia;

    public Direccion(int Id, String Calle, String CodigoPostal, String EntreCalles, String Colonia, String Referencia) {
        this.Id = Id;
        this.Calle = Calle;
        this.CodigoPostal = CodigoPostal;
        this.EntreCalles = EntreCalles;
        this.Colonia = Colonia;
        this.Referencia = Referencia;
    }

    public int getId() {
        return Id;
    }
    public void setId(int Id) {
        this.Id = Id;
    }
    public String getCalle() {
        return Calle;
    }
    public void setCalle(String Calle) {
        this.Calle = Calle;
    }
    public String getCodigoPostal() {
        return CodigoPostal;
    }
    public void setCodigoPostal(String CodigoPostal) {
        this.CodigoPostal = CodigoPostal;
    }
    public String getEntreCalles() {
        return EntreCalles;
    }
    public void setEntreCalles(String EntreCalles) {
        this.EntreCalles = EntreCalles;
    }
    public String getColonia() {
        return Colonia;
    }
    public void setColonia(String Colonia) {
        this.Colonia = Colonia;
    }
    public String getReferencia() {
        return Referencia;
    }
    public void setReferencia(String Referencia) {
        this.Referencia = Referencia;
    }
}
