package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.cartas.CartaUnidad;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.jugador.Mano;
import edu.fiuba.algo3.modelo.principal.ConstructorMazo;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Tablero;
import edu.fiuba.algo3.modelo.secciones.Seccion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Test03JugadorPuedeColocarCartaEnSeccionDelTablero {

    @Test
    public void jugadorColocaUnaCartaDeSuManoEnUnaSeccion() {
        // Verificar que un jugador pueda colocar una carta en una sección del tablero

        Tablero tablero = new Tablero();
        ConstructorMazo constructorMazo = new ConstructorMazo();

        Juego juego = new Juego(
                new Jugador("pepito", constructorMazo.construirMazo()),
                new Jugador("pepita", constructorMazo.construirMazo()),
                tablero
        );

        Mano manoJugadorActual = juego.jugadorActual().obtenerMano();

        CartaUnidad cartaSeleccionada = (CartaUnidad) manoJugadorActual.obtenerCartas().get(0);

        boolean esJugador1 = juego.jugadorActual().compararNombre("pepito");
        Seccion asedioJugadorActual = (esJugador1 ? tablero.getSeccionesJugador1() : tablero.getSeccionesJugador2()).stream()
                .filter(seccion -> seccion.obtenerNombre().equals("Asedio"))
                .findFirst()
                .orElse(null);

        juego.jugarCarta(cartaSeleccionada, asedioJugadorActual);

        assertNotNull(asedioJugadorActual);
        assertTrue(asedioJugadorActual.contieneCarta(cartaSeleccionada),
                "La carta no fue colocada correctamente en la sección especificada.");

        // Verificamos que la carta ya no esté en la mano del jugador actual
        assertEquals(9, juego.jugadorActual().cantidadEnMano(),
                "La carta aún está en la mano del jugador después de ser colocada en el tablero.");
    }

}