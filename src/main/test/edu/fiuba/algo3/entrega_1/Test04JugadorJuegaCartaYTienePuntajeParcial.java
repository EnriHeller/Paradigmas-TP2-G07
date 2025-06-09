package edu.fiuba.algo3.entrega_1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.jugador.Mano;
import edu.fiuba.algo3.modelo.principal.ConstructorMazo;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Tablero;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.cartas.CartaUnidad; // O la implementación específica de Carta

import edu.fiuba.algo3.modelo.secciones.Seccion;
import edu.fiuba.algo3.modelo.errores.TipoDeSeccionInvalidaError;

public class Test04JugadorJuegaCartaYTienePuntajeParcial {

    @Test
    public void jugadorJuegaCartaYTienePuntajeParcial() throws TipoDeSeccionInvalidaError {
        // Verificar que un jugador juegue una carta de su mazo y tenga un puntaje parcial.
        //Arrange
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

        // Act
        juego.jugarCarta(cartaSeleccionada, asedioJugadorActual);

        // Assert
        assertEquals(1, juego.calularPuntajeJugador(jugadorActual),
                "El jugador actual juega una carta de valor 1 y debería tener un puntaje parcial de 1.");

    }

}