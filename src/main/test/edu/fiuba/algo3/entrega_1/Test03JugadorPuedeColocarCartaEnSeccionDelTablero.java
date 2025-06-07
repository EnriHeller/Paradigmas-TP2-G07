package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.CartaUnidad; // O la implementación real de Carta
import edu.fiuba.algo3.modelo.errores.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.jugador.Mano;
import edu.fiuba.algo3.modelo.principal.ConstructorMazo;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Tablero;
import edu.fiuba.algo3.modelo.secciones.Seccion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import edu.fiuba.algo3.modelo.*;

import java.util.List;
import java.util.ArrayList;

public class Test03JugadorPuedeColocarCartaEnSeccionDelTablero {

    @Test
    public void jugadorColocaUnaCartaDeSuManoEnUnaSeccion() {

        Tablero tablero = new Tablero();
        ConstructorMazo constructorMazo = new ConstructorMazo();

        Jugador jugador1 = new Jugador("pepito", constructorMazo.construirMazo());
        Jugador jugador2 = new Jugador("pepita", constructorMazo.construirMazo());
        Juego juego = new Juego(jugador1, jugador2, tablero);

        Mano manoJ1 = jugador1.obtenerMano();
        CartaUnidad cartaSeleccionada = (CartaUnidad) manoJ1.obtenerCartas().get(0);


        Seccion asedioJugador1 = tablero.getSeccionesJugador1().stream()
                .filter(seccion -> seccion.obtenerNombre().equals("Asedio"))
                .findFirst()
                .orElse(null);

        assertNotNull(asedioJugador1, "La sección de Asedio del jugador no fue encontrada en el tablero.");

        tablero.jugarCarta(jugador1, cartaSeleccionada, asedioJugador1);

        assertTrue(asedioJugador1.contieneCarta(cartaSeleccionada),
                "La carta no fue colocada correctamente en la sección especificada.");

//        assertEquals(9, jugador1.cantidadEnMano(),
//                "La carta aún está en la mano del jugador después de ser colocada en el tablero.");
    }

}