package com.salemobile.essis.magnus.Entidades;

import java.io.Serializable;

public class Clientes implements Serializable {
    private String Nombre,Credito;
    public Clientes(String nombre, String credito) {
        Nombre = nombre;
        Credito = credito;
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String nombre) {
        Nombre = nombre;
    }
    public String getCredito() {
        return Credito;
    }
    public void setCredito(String credito) {
        Credito = credito;
    }
}
