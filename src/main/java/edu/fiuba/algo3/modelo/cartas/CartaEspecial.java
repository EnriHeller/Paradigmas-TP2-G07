package edu.fiuba.algo3.modelo.cartas;

import edu.fiuba.algo3.modelo.principal.Contexto;

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
    public String mostrarCarta() {
        return nombre;
    }

    @Override
    public void aplicarModificador(Contexto contexto) {
        // No-op para cartas especiales genéricas
    }
}
