package edu.fiuba.algo3.entrega_4;

import edu.fiuba.algo3.modelo.cartas.especiales.MoraleBoost;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.modificadores.Medico;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.principal.NoSePuedeCumplirSolicitudDeCartas;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Test22MoraleBoostPruebaMetodos {
    @Test
    public void testMoraleBoostCubreMetodosConFlujoReal() throws TipoDeSeccionInvalidaError, NoSePuedeCumplirSolicitudDeCartas, edu.fiuba.algo3.modelo.principal.UnoDeLosMazosNoCumpleRequitos {
        MoraleBoost moraleBoost = new MoraleBoost();
        Base base = new Base();
        List<String> secciones = new ArrayList<>();
        secciones.add("CuerpoACuerpo");
        List<CartaUnidad> cartas = new ArrayList<>();
        // Una carta base para poder ver el efecto de MoraleBoost
        cartas.add(new CartaUnidad("Base'e", secciones, 8, base));
        // 19 cartas MoraleBoost
        for (int i = 0; i < 19; i++) {
            cartas.add(new CartaUnidad("Morale'e", secciones, 8, moraleBoost));
        }
        // Otra carta base para asegurar 21
        cartas.add(new CartaUnidad("Base'e2", secciones, 8, base));
        Mazo mazoMock1 = new Mazo(new ArrayList<>(cartas));
        Mazo mazoMock2 = new Mazo(new ArrayList<>(cartas));
        Jugador jugador1 = new Jugador("JugadorTest1");
        jugador1.agregarMazo(mazoMock1);
        Jugador jugador2 = new Jugador("JugadorTest2");
        jugador2.agregarMazo(mazoMock2);
        Juego juego = new Juego(jugador1, jugador2);
        jugador1.agregarCartasAMano(10);
        // Jugar primero una carta base
        CartaUnidad cartaBase = (CartaUnidad) jugador1.cartasEnMano().get(0);
        assertDoesNotThrow(() -> juego.jugarCarta(cartaBase, new Seccion("CuerpoACuerpo", 0)));
        // Ahora jugar una carta MoraleBoost
        CartaUnidad cartaMorale = null;
        for (var carta : jugador1.cartasEnMano()) {
            if (carta instanceof CartaUnidad && ((CartaUnidad) carta).getModificadores().contains("MoraleBoost")) {
                cartaMorale = (CartaUnidad) carta;
                break;
            }
        }
        CartaUnidad finalCartaMorale = cartaMorale;
        assertDoesNotThrow(() -> juego.jugarCarta(finalCartaMorale, new Seccion("CuerpoACuerpo", 0)));
    }

    @Test
    public void testMetodosBasicosMoraleBoost() {
        MoraleBoost moraleBoost = new MoraleBoost();
        assertTrue(moraleBoost.esEspecial());
        assertEquals("MoraleBoost", moraleBoost.mostrarCarta());
        assertEquals("MoraleBoost", moraleBoost.mostrarModificadores());
    }

    @Test
    public void testAplicarModificadorNoLanzaError() {
        MoraleBoost moraleBoost = new MoraleBoost();
        // Contexto y Seccion mock hechos a mano
        class SeccionMock extends Seccion {
            public SeccionMock() throws TipoDeSeccionInvalidaError { super("CuerpoACuerpo", 0); }
            @Override
            public java.util.List<CartaUnidad> getCartasActuales() { return new java.util.ArrayList<>(); }
        }
        class ContextoMock extends edu.fiuba.algo3.modelo.principal.Contexto {
            public ContextoMock() throws TipoDeSeccionInvalidaError { super(null, new SeccionMock(), null, null, java.util.List.of()); }
            @Override
            public Seccion getSeccion() {
                try {
                    return new SeccionMock();
                } catch (TipoDeSeccionInvalidaError e) {
                    throw new RuntimeException(e);
                }
            }
        }
        try {
            var contexto = new ContextoMock();
            assertDoesNotThrow(() -> moraleBoost.aplicarModificador(contexto));
        } catch (TipoDeSeccionInvalidaError e) {
            fail("No debería lanzar TipoDeSeccionInvalidaError");
        }
    }
}
