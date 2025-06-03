package edu.fiuba.algo3.entrega_1;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.CartaUnidad;
import edu.fiuba.algo3.modelo.Carta;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Mazo;
import edu.fiuba.algo3.modelo.Seccion;
import edu.fiuba.algo3.modelo.TipoDeSeccionInvalidaError;

public class Test04JugadorJuegaCartaYTienePuntajeParcial {
    @Test
    public void jugadorJuegaCartaYTienePuntajeParcial() throws TipoDeSeccionInvalidaError {
        // Arrange
        List<String> seccionesCartaUnidad = new ArrayList<>();
        seccionesCartaUnidad.add("Rango");
        CartaUnidad cartaUnidad = new CartaUnidad(seccionesCartaUnidad, 7);

        List<Carta> cartas = new ArrayList<>();
        cartas.add(cartaUnidad);
        Mazo mazo = new Mazo(cartas);
        Jugador jugador = new Jugador("JugadorTest", mazo);
        jugador.agregarCartasAMano(1);

        Seccion seccion = new Seccion("Rango");

        // Act
        CartaUnidad cartaJugada = (CartaUnidad) jugador.jugarCartaPorIndice(0);
        seccion.agregarCarta(cartaJugada);
        
        int puntaje = seccion.getPuntajeTotal();
        
        // Assert
        assertEquals(7, puntaje, "El puntaje parcial de la sección debe ser igual al valor de la carta jugada");
    }
}
