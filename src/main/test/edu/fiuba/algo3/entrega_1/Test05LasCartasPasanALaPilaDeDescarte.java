package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.cartas.CartaUnidad;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.jugador.Mano;
import edu.fiuba.algo3.modelo.principal.ConstructorMazo;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Tablero;
import edu.fiuba.algo3.modelo.secciones.Seccion;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test05LasCartasPasanALaPilaDeDescarte {

    @Test
    public void pilaDescarteRecibeCartasJugadas() {
        // Verificar que las cartas pasen a la pila de descarte

        Tablero tablero = new Tablero();
        ConstructorMazo constructorMazo = new ConstructorMazo();

        Jugador jugador1 = new Jugador("Jugador1", constructorMazo.construirMazo());
        Jugador jugador2 = new Jugador("pepita", constructorMazo.construirMazo());
        Juego juego = new Juego(jugador1, jugador2, tablero);

        Jugador jugadorActual = juego.jugadorActual();

        Mano manoJugadorActual = jugadorActual.obtenerMano();
        CartaUnidad cartaSeleccionada = (CartaUnidad) manoJugadorActual.obtenerCartas().get(0);

        Seccion asedioJugadorActual = tablero.getSeccionesJugador1().stream()
                .filter(seccion -> seccion.obtenerNombre().equals("Asedio"))
                .findFirst()
                .orElse(null);

        juego.jugarCarta(cartaSeleccionada, asedioJugadorActual);
        jugadorActual.agregarCartaAlDescarte(cartaSeleccionada);

        // Assert
        assertFalse(jugadorActual.obtenerMano().obtenerCartas().contains(cartaSeleccionada),
                "La carta debe retirarse de la mano del jugador actual después de ser jugada.");

        assertTrue(jugadorActual.obtenerPilaDeDescarte().obtenerCartas().contains(cartaSeleccionada),
                "La carta jugada debería estar presente en la pila de descarte del jugador actual tras su uso.");
    }


}