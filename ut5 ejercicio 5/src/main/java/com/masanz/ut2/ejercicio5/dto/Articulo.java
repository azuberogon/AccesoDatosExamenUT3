package com.masanz.ut2.ejercicio5.dto;

public class Articulo {

    private int id;
    private String nombre;
    private int valor;
    private int idPropietario;

    public Articulo(){
        this.id = -1;
        this.nombre = "";
        this.valor = -1;
        this.idPropietario = -1;
    }

    public Articulo(int id, String nombre, int valor, int idPropietario) {
        this.id = id;
        this.nombre = nombre;
        this.valor = valor;
        this.idPropietario = idPropietario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(int idPropietario) {
        this.idPropietario = idPropietario;
    }

    @Override
    public String toString() {
        return "Articulo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", valor=" + valor +
                ", idPropietario=" + idPropietario +
                '}';
    }
}
