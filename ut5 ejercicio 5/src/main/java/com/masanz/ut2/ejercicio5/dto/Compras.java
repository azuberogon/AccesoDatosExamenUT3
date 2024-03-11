package com.masanz.ut2.ejercicio5.dto;

import java.util.Date;

public class Compras {

    private int id;
    private int idArticulo;
    private int idComprador;
    private int idVendedor;
    private Date fechaComprar;

    public Compras(){
        this.id = -1;
        this.idArticulo = -1;
        this.idComprador = -1;
        this.idVendedor = -1;
        this.fechaComprar = new Date();
    }

    public Compras(int idArticulo, int idComprador, int idVendedor) {
        this.idArticulo = idArticulo;
        this.idComprador = idComprador;
        this.idVendedor = idVendedor;
        this.fechaComprar = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
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

    public Date getFechaComprar() {
        return fechaComprar;
    }

    public void setFechaComprar(Date fechaComprar) {
        this.fechaComprar = fechaComprar;
    }

    @Override
    public String toString() {
        return "Compras{" +
                "id=" + id +
                ", idArticulo=" + idArticulo +
                ", idComprador=" + idComprador +
                ", idVendedor=" + idVendedor +
                ", fechaComprar=" + fechaComprar +
                '}';
    }
}
