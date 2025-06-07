package edu.fiuba.algo3.modelo.cartas;

import java.util.List;

public abstract class Carta {

    private final String nombre;

    public Carta(String nombre) {
        this.nombre = nombre;
    }

    public String obtenerNombre() {
        return nombre;
    }

    public abstract boolean esEspecial();

}
