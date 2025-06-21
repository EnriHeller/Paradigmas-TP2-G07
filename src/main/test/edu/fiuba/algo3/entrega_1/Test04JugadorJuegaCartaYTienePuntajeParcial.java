package edu.fiuba.algo3.entrega_1;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.ArrayList;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.jugador.SeccionesJugador;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import org.junit.jupiter.api.Test;

public class Test04JugadorJuegaCartaYTienePuntajeParcial {
    @Test
    public void jugadorJuegaCartaYTienePuntajeParcial() {
        try {
            List<String> seccionesCartaUnidad = new ArrayList<>();
            seccionesCartaUnidad.add("Rango");
            CartaUnidad cartaUnidad = new CartaUnidad("CartaTest",seccionesCartaUnidad, 7);
            List<Carta> cartas = new ArrayList<>();
            cartas.add(cartaUnidad);
            Mazo mazo = new Mazo(cartas);
            SeccionesJugador secciones = null;
            try {
                secciones = SeccionesJugador.seccionesDelJugador("Jugador0");
            } catch (edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError e) {
                org.junit.jupiter.api.Assertions.fail("No se esperaba TipoDeSeccionInvalidaError al obtener SeccionesJugador: " + e.getMessage());
            }
            Jugador jugador = null;
            if (secciones != null) {
                try {
                    jugador = new Jugador("JugadorTest", mazo, secciones);
                } catch (Exception e) {
                    org.junit.jupiter.api.Assertions.fail("No se esperaba excepción al crear Jugador: " + e.getMessage());
                }
            }
            if (jugador != null) {
                try {
                    jugador.agregarCartasAMano(1);
                } catch (edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError | edu.fiuba.algo3.modelo.principal.NoSePuedeCumplirSolcitudDeCartas e) {
                    org.junit.jupiter.api.Assertions.fail("No se esperaba excepción checked al agregar cartas a la mano: " + e.getMessage());
                } catch (Exception e) {
                    org.junit.jupiter.api.Assertions.fail("No se esperaba excepción al agregar cartas a la mano: " + e.getMessage());
                }
                Seccion seccion = null;
                try {
                    seccion = new Seccion("Rango", 0);
                } catch (edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError e) {
                    org.junit.jupiter.api.Assertions.fail("No se esperaba TipoDeSeccionInvalidaError al crear Seccion: " + e.getMessage());
                }
                if (seccion != null) {
                    try {
                        CartaUnidad cartaJugada = (CartaUnidad) jugador.jugarCarta(cartaUnidad);
                        seccion.agregarCarta(cartaJugada);
                        int puntaje = seccion.getPuntajeTotal();
                        assertEquals(7, puntaje);
                    } catch (Exception e) {
                        org.junit.jupiter.api.Assertions.fail("No se esperaba excepción al jugar carta o agregar a sección: " + e.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            org.junit.jupiter.api.Assertions.fail("No se esperaba excepción general: " + e.getMessage());
        }
    }
}
