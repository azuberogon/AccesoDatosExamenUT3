package com.masanz.ut2.ejercicio5.model;

public class Articulo {

    private String id;
    private String nombre;
    private int valor;
    private String propietario;

    public Articulo(String id, String nombre, int valor, String propietario) {
        this.id = id;
        this.nombre = nombre;
        this.valor = valor;
        this.propietario = propietario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }
}
