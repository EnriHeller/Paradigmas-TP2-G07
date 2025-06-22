package edu.fiuba.algo3.entrega_4;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.modificadores.Unidas;
import edu.fiuba.algo3.modelo.secciones.jugador.Mano;
import edu.fiuba.algo3.modelo.principal.Contexto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class Test31CartaFuncionaCorrectamente {
    static class CartaBase implements Carta {
        private boolean especial;
        private String nombre;
        private boolean modificadorAplicado = false;
        private boolean retrotraido = false;
        public CartaBase(boolean especial, String nombre) {
            this.especial = especial;
            this.nombre = nombre;
        }
        @Override
        public boolean esEspecial() { return especial; }
        @Override
        public String mostrarCarta() { return nombre; }
        @Override
        public void aplicarModificador(Contexto contexto) { modificadorAplicado = true; }
        @Override
        public void retrotraerModificacion(Contexto contexto) { retrotraido = true; }
    }

    @org.junit.jupiter.api.Test
    public void testMetodosCarta() {
        CartaBase carta = new CartaBase(true, "EspecialX");
        assertTrue(carta.esEspecial());
        assertEquals("EspecialX", carta.mostrarCarta());
        Contexto ctx = null;
        carta.aplicarModificador(ctx);
        carta.retrotraerModificacion(ctx);
        // No exception = OK, and methods called
        assertTrue(carta.modificadorAplicado);
        assertTrue(carta.retrotraido);
    }

    @org.junit.jupiter.api.Test
    public void testDefaultRetrotraerNoHaceNada() {
        class CartaSinOverride implements Carta {
            @Override public boolean esEspecial() { return false; }
            @Override public String mostrarCarta() { return "N"; }
            @Override public void aplicarModificador(Contexto contexto) { }
        }
        Carta carta = new CartaSinOverride();
        // No exception should be thrown
        carta.retrotraerModificacion(null);
    }
}
