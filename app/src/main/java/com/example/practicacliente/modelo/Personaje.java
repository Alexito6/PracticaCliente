package com.example.practicacliente.modelo;

import java.io.Serializable;
import java.util.Objects;

public class Personaje implements Serializable {
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCasa() {
        return Casa;
    }

    public void setCasa(int casa) {
        Casa = casa;
    }

    public Personaje(String nombre, int casa) {
        this.nombre = nombre;
        Casa = casa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personaje personaje = (Personaje) o;
        return Casa == personaje.Casa && Objects.equals(nombre, personaje.nombre);
    }

    private String nombre;
    private int Casa;

}
