package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.CartaUnidad; // O la implementación real de Carta
import edu.fiuba.algo3.modelo.errores.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.Seccion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import edu.fiuba.algo3.modelo.*;

import java.util.List;
import java.util.ArrayList;

public class Test03JugadorPuedeColocarCartaEnSeccionDelTablero {

    @Test
    public void jugadorPuedeColocarCartaEnSeccion() throws TipoDeSeccionInvalidaError {
        // Arrange: Crear una carta real
        Carta cartaReal = new CartaUnidad(4); // Ajusta esto si tienes otra implementación concreta de Carta

        List<Carta> cartas = new ArrayList<>();
        cartas.add(cartaReal);

        // Crear el mazo con la carta real
        Mazo mazo = new Mazo(cartas);

        // Crear el jugador con el mazo
        Jugador jugador = new Jugador("JugadorTest", mazo);
        jugador.agregarCartasAMano(1); // Agregar la carta a la mano del jugador

        // Crear una sección real
        Seccion seccion = new Seccion("Rango");

        // Act: El jugador juega una carta desde su mano y la coloca en la sección
        Carta cartaJugada = jugador.jugarCartaPorIndice(0); 
        seccion.agregarCarta(cartaJugada);

        // Assert: Verificar que la sección tiene la carta y que la mano del jugador quedó vacía
        assertTrue(seccion.contiene(cartaReal), "La sección debería contener la carta jugada.");
        assertEquals(0, jugador.cartasEnMano(), "El jugador no debería tener cartas en la mano después de jugar una.");
    }
}