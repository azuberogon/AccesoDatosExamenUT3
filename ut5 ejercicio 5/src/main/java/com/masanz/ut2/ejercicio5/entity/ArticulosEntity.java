package com.masanz.ut2.ejercicio5.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "articulos", schema = "ejercicio5", catalog = "")
public class ArticulosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "valor")
    private int valor;
    @Basic
    @Column(name = "id_propietario")
    private int idPropietario;

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
        return "ArticulosEntity{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", valor=" + valor +
                ", idPropietario=" + idPropietario +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticulosEntity that = (ArticulosEntity) o;
        return id == that.id && valor == that.valor && idPropietario == that.idPropietario && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, valor, idPropietario);
    }
}
