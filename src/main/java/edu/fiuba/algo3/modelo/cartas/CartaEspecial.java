package edu.fiuba.algo3.modelo.cartas;

public class CartaEspecial implements Carta {
    private final String nombre;

    public CartaEspecial(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean esEspecial() {
        return true;
    }

    @Override
    public String getNombre() {
        return nombre;
    }
}
