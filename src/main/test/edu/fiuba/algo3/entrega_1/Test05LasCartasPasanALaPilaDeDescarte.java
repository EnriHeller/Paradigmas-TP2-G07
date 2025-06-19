package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.jugador.SeccionesJugador;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test05LasCartasPasanALaPilaDeDescarte {

    @Test
    public void pilaDescarteRecibeCartasJugadas() {
        int cartasJugadasEsperadas = 8;
        List<Carta> cartas = new ArrayList<Carta>();
        for (int i = 0; i < 21; i++) {
            Carta carta = new CartaUnidad();
            cartas.add(carta);
        }
        Mazo mazo = new Mazo(cartas);
        List<Carta> cartasJugadas = new ArrayList<Carta>();
        for (int i = 0; i < 8; i++) {
            Carta carta = new CartaUnidad();
            cartasJugadas.add(carta);
        }
        Jugador jugador = null;
        SeccionesJugador secciones = null;
        try {
            secciones = SeccionesJugador.seccionesDelJugador("Jugador0");
        } catch (edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError e) {
            org.junit.jupiter.api.Assertions.fail("No se esperaba TipoDeSeccionInvalidaError al obtener SeccionesJugador: " + e.getMessage());
        }
        if (secciones != null) {
            try {
                jugador = new Jugador("JugadorTest", mazo, secciones);
            } catch (Exception e) {
                org.junit.jupiter.api.Assertions.fail("No se esperaba excepción al crear Jugador: " + e.getMessage());
            }
        }
        if (jugador != null) {
            try {
                jugador.agregarCartasAlDescarte(cartasJugadas);
            } catch (Exception e) {
                org.junit.jupiter.api.Assertions.fail("No se esperaba excepción al agregar cartas al descarte: " + e.getMessage());
            }
            try {
                int cartasEnDescarte = 0;
                cartasEnDescarte = jugador.cartasRestantesEnSeccion("Descarte");
                assertEquals(cartasJugadasEsperadas, cartasEnDescarte,
                        "El jugador debe tener 8 cartas al comenzar el juego");
            } catch (Exception e) {
                org.junit.jupiter.api.Assertions.fail("No se esperaba excepción al consultar cartas en el descarte: " + e.getMessage());
            }
        }
    }

}
