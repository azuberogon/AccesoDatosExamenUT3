package com.masanz.ut2.ejercicio5.dto;

import java.util.Date;
import java.util.Objects;

public class Usuario {

    private int id;
    private String fullName;
    private String user;
    private String email;
    private String password;
    private int saldo;
    private Date creationDate;
    private Date modificationDate;

    public Usuario(){
        this.id = -1;
        this.fullName = "";
        this.user = "";
        this.email = "";
        this.password = "";
        this.saldo = 0;
        this.creationDate = new Date();
        this.modificationDate = new Date();
    }

    public Usuario(String fullName, String user, String email, String password, int saldo) {
        this.id = -1;
        this.fullName = fullName;
        this.user = user;
        this.email = email;
        this.password = password;
        this.saldo = saldo;
        this.creationDate = new Date();
        this.modificationDate = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", user='" + user + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", saldo='" + saldo + '\'' +
                ", creationDate=" + creationDate +
                ", modificationDate=" + modificationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id == usuario.id || Objects.equals(user, usuario.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user);
    }
}
