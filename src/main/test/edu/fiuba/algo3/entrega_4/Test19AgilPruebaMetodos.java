package edu.fiuba.algo3.entrega_4;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Agil;
import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.principal.NoSePuedeCumplirSolicitudDeCartas;
import edu.fiuba.algo3.modelo.principal.UnoDeLosMazosNoCumpleRequitos;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test19AgilPruebaMetodos {
    @Test
    public void testAgilCubreMetodosConFlujoReal() {
        Agil agil = new Agil(new Base());
        List<String> secciones = new ArrayList<>();
        secciones.add("CuerpoACuerpo");
        List<CartaUnidad> cartasAgil = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            cartasAgil.add(new CartaUnidad("Agil'e", secciones, 8, agil));
        }
        Mazo mazoMock1 = new Mazo(new ArrayList<>(cartasAgil));
        Mazo mazoMock2 = new Mazo(new ArrayList<>(cartasAgil));
        Jugador jugador1 = new Jugador("JugadorTest1");
        jugador1.agregarMazo(mazoMock1);
        Jugador jugador2 = new Jugador("JugadorTest2");
        jugador2.agregarMazo(mazoMock2);
        Juego juego;
        try {
            juego = new Juego(jugador1, jugador2);
            jugador1.agregarCartasAMano(10);
        } catch (TipoDeSeccionInvalidaError | NoSePuedeCumplirSolicitudDeCartas | UnoDeLosMazosNoCumpleRequitos e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        CartaUnidad cartaEnMano = (CartaUnidad) jugador1.cartasEnMano().get(0);
        assertDoesNotThrow(() -> {
            try {
                juego.jugarCarta(cartaEnMano, new Seccion("CuerpoACuerpo", 0));
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        });
    }

    @Test
    public void testAgilMetodosBasicos() {
        Agil agil = new Agil(new Base());
        // mostrarModificadores
        assertTrue(agil.mostrarModificadores().contains("Agil"));
        // modificar (con contexto mock)
        class ContextoMock extends edu.fiuba.algo3.modelo.principal.Contexto {
            public ContextoMock() { super(null, null, null, null); }
        }
        assertDoesNotThrow(() -> agil.modificar(new ContextoMock()));
    }
}
