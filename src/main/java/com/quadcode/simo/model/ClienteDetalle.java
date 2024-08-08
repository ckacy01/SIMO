package com.quadcode.simo.model;

public class ClienteDetalle {
    private int Id;
    private String Nombre;
    private String Email;
    private String Telefono;
    private String Calle1;
    private String CodigoPostal1;
    private String EntreCalles1;
    private String Colonia1;
    private String Referencia;
    private String NombreFam;
    private String TelefonoFam;
    private String RelacionConCliente;
    private String Calle2;
    private String CodigoPostal2;
    private String EntreCalles2;
    private String Colonia2;
    private String Referencia2;

    public ClienteDetalle(int id, String nombre, String email, String telefono, String calle1, String codigoPostal1, String entreCalles1, String colonia1, String referencia, String nombreFam, String telefonoFam, String relacionConCliente, String calle2, String codigoPostal2, String colonia2, String entreCalles2, String Referencia2) {
        Id = id;
        Nombre = nombre;
        Email = email;
        Telefono = telefono;
        Calle1 = calle1;
        CodigoPostal1 = codigoPostal1;
        EntreCalles1 = entreCalles1;
        Colonia1 = colonia1;
        Referencia = referencia;
        NombreFam = nombreFam;
        TelefonoFam = telefonoFam;
        RelacionConCliente = relacionConCliente;
        Calle2 = calle2;
        CodigoPostal2 = codigoPostal2;
        Colonia2 = colonia2;
        EntreCalles2 = entreCalles2;
        this.Referencia2 = Referencia2;
    }

    public ClienteDetalle() {
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getCalle1() {
        return Calle1;
    }

    public void setCalle1(String calle1) {
        Calle1 = calle1;
    }

    public String getCodigoPostal1() {
        return CodigoPostal1;
    }

    public void setCodigoPostal1(String codigoPostal1) {
        CodigoPostal1 = codigoPostal1;
    }

    public String getEntreCalles1() {
        return EntreCalles1;
    }

    public void setEntreCalles1(String entreCalles1) {
        EntreCalles1 = entreCalles1;
    }

    public String getColonia1() {
        return Colonia1;
    }

    public void setColonia1(String colonia1) {
        Colonia1 = colonia1;
    }

    public String getReferencia() {
        return Referencia;
    }

    public void setReferencia(String referencia) {
        Referencia = referencia;
    }

    public String getNombreFam() {
        return NombreFam;
    }

    public void setNombreFam(String nombreFam) {
        NombreFam = nombreFam;
    }

    public String getTelefonoFam() {
        return TelefonoFam;
    }

    public void setTelefonoFam(String telefonoFam) {
        TelefonoFam = telefonoFam;
    }

    public String getRelacionConCliente() {
        return RelacionConCliente;
    }

    public void setRelacionConCliente(String relacionConCliente) {
        RelacionConCliente = relacionConCliente;
    }

    public String getCalle2() {
        return Calle2;
    }

    public void setCalle2(String calle2) {
        Calle2 = calle2;
    }

    public String getCodigoPostal2() {
        return CodigoPostal2;
    }

    public void setCodigoPostal2(String codigoPostal2) {
        CodigoPostal2 = codigoPostal2;
    }

    public String getEntreCalles2() {
        return EntreCalles2;
    }

    public void setEntreCalles2(String entreCalles2) {
        EntreCalles2 = entreCalles2;
    }

    public String getColonia2() {
        return Colonia2;
    }

    public void setColonia2(String colonia2) {
        Colonia2 = colonia2;
    }
    public String getReferencia2() {
        return Referencia2;
    }
    public void setReferencia2(String referencia2) {
        Referencia2 = referencia2;
    }
}
