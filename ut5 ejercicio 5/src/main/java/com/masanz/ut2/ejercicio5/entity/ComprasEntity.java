package com.masanz.ut2.ejercicio5.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "compras", schema = "ejercicio5", catalog = "")
public class ComprasEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "id_objeto")
    private int idObjeto;
    @Basic
    @Column(name = "id_comprador")
    private int idComprador;
    @Basic
    @Column(name = "id_vendedor")
    private int idVendedor;
    @Basic
    @Column(name = "fecha_compra")
    private Date fechaCompra;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(int idObjeto) {
        this.idObjeto = idObjeto;
    }

    public int getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(int idComprador) {
        this.idComprador = idComprador;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    @Override
    public String toString() {
        return "ComprasEntity{" +
                "id=" + id +
                ", idObjeto=" + idObjeto +
                ", idComprador=" + idComprador +
                ", idVendedor=" + idVendedor +
                ", fechaCompra=" + fechaCompra +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComprasEntity that = (ComprasEntity) o;
        return id == that.id && idObjeto == that.idObjeto && idComprador == that.idComprador && idVendedor == that.idVendedor && Objects.equals(fechaCompra, that.fechaCompra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idObjeto, idComprador, idVendedor, fechaCompra);
    }
}
