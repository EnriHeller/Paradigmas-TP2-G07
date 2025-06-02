package edu.fiuba.algo3.entrega_1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.CartaUnidad; // O la implementación específica de Carta
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Mazo;
import edu.fiuba.algo3.modelo.secciones.Seccion;
import edu.fiuba.algo3.modelo.errores.TipoDeSeccionInvalidaError;

public class Test04JugadorJuegaCartaYTienePuntajeParcial {

    @Test
    public void jugadorJuegaCartaYTienePuntajeParcial() throws TipoDeSeccionInvalidaError {
        // Arrange: Crear una carta real con un puntaje de 7
        Carta cartaReal = new CartaUnidad(1); // Suponiendo que el constructor acepta un valor de puntaje

        List<Carta> cartas = new ArrayList<>();
        cartas.add(cartaReal);

        // Crear el mazo con la carta real
        Mazo mazo = new Mazo(cartas);

        // Crear el jugador con el mazo
        Jugador jugador = new Jugador("JugadorTest", mazo);
        jugador.agregarCartasAMano(1); // Agregar la carta a la mano del jugador

        // Crear una sección real
        Seccion seccion = new Seccion("Rango");

        // Act
        Carta cartaJugada = jugador.jugarCartaPorIndice(0);
        seccion.agregarCarta(cartaJugada);

        // Obtener el puntaje total de la sección
        int puntaje = seccion.getPuntajeTotal();

        // Assert
        assertEquals(7, puntaje, "El puntaje parcial de la sección debe ser igual al valor de la carta jugada");
    }
}