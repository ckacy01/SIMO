package com.quadcode.simo.model;


public class Paciente {
    private int diagnosticoId;
    private int pacienteId;
    private String nombrePaciente;
    private String nombreCliente;
    private String material;
    private String tratamiento;
    private String lente;
    private Float esferaDerecho;
    private Float cilindroDerecho;
    private Float adicionDerecho;
    private Float DIDerecho;
    private Float ejeDerecho;
    private Float esferaIzquierdo;
    private Float cilindroIzquierdo;
    private Float adicionIzquierdo;
    private Float DIIzquierdo;
    private Float ejeIzquierdo;

    public String getNombrePaciente() {
        return nombrePaciente;
    }
    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public int getDiagnosticoId() {
        return diagnosticoId;
    }

    public void setDiagnosticoId(int diagnosticoId) {
        this.diagnosticoId = diagnosticoId;
    }

    public int getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(int pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getLente() {
        return lente;
    }

    public void setLente(String lente) {
        this.lente = lente;
    }

    public Float getEsferaDerecho() {
        return esferaDerecho;
    }

    public void setEsferaDerecho(Float esferaDerecho) {
        this.esferaDerecho = esferaDerecho;
    }

    public Float getCilindroDerecho() {
        return cilindroDerecho;
    }

    public void setCilindroDerecho(Float cilindroDerecho) {
        this.cilindroDerecho = cilindroDerecho;
    }

    public Float getDIDerecho() {
        return DIDerecho;
    }

    public void setDIDerecho(Float DIDerecho) {
        this.DIDerecho = DIDerecho;
    }

    public Float getAdicionDerecho() {
        return adicionDerecho;
    }

    public void setAdicionDerecho(Float adicionDerecho) {
        this.adicionDerecho = adicionDerecho;
    }

    public Float getEjeDerecho() {
        return ejeDerecho;
    }

    public void setEjeDerecho(Float ejeDerecho) {
        this.ejeDerecho = ejeDerecho;
    }

    public Float getEsferaIzquierdo() {
        return esferaIzquierdo;
    }

    public void setEsferaIzquierdo(Float esferaIzquierdo) {
        this.esferaIzquierdo = esferaIzquierdo;
    }

    public Float getCilindroIzquierdo() {
        return cilindroIzquierdo;
    }

    public void setCilindroIzquierdo(Float cilindroIzquierdo) {
        this.cilindroIzquierdo = cilindroIzquierdo;
    }

    public Float getDIIzquierdo() {
        return DIIzquierdo;
    }

    public void setDIIzquierdo(Float DIIzquierdo) {
        this.DIIzquierdo = DIIzquierdo;
    }

    public Float getEjeIzquierdo() {
        return ejeIzquierdo;
    }

    public void setEjeIzquierdo(Float ejeIzquierdo) {
        this.ejeIzquierdo = ejeIzquierdo;
    }

    public Float getAdicionIzquierdo() {
        return adicionIzquierdo;
    }

    public void setAdicionIzquierdo(Float adicionIzquierdo) {
        this.adicionIzquierdo = adicionIzquierdo;
    }
}
