package com.quadcode.simo.model;

import javax.xml.namespace.QName;

public class PacienteSearch {
    private String name;

    public PacienteSearch(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
