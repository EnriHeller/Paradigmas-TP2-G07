package edu.fiuba.algo3.mocks;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.principal.Contexto;

public class CartaEspecialMock implements Carta {
    private final String nombre;
    private final boolean especial;

    public CartaEspecialMock(String nombre) {
        this.nombre = nombre;
        this.especial = true;
    }

    @Override
    public boolean esEspecial() {
        return especial;
    }

    @Override
    public String mostrarCarta() {
        return nombre;
    }

    @Override
    public void aplicarModificador(Contexto contexto) {
        // No-op para mock
    }

    @Override
    public void retrotraerModificacion(Contexto contexto) {
        // No-op para mock
    }
}
