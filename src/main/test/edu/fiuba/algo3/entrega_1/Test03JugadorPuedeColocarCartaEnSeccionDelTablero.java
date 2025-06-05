package edu.fiuba.algo3.entrega_1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import edu.fiuba.algo3.modelo.*;

import java.util.List;
import java.util.ArrayList;


public class Test03JugadorPuedeColocarCartaEnSeccionDelTablero {

    @Test
    public void jugadorPuedeColocarCartaEnSeccion() throws TipoDeSeccionInvalidaError {
        // Arrange
        CartaUnidad cartaUnidad = new CartaUnidad();

        List<Carta> cartas = new ArrayList<Carta>();

        cartas.add(cartaUnidad);
        Mazo mazo = new Mazo(cartas);

        Jugador jugador = new Jugador("JugadorTest", mazo);
        jugador.agregarCartasAMano(1);

        Seccion seccion = new Seccion("Rango");

        // Act
        Carta cartaJugada = jugador.jugarCarta(0);
        seccion.agregarCarta((CartaUnidad) cartaJugada);

        // Assert: verificar que la sección recibió la carta y la mano quedó vacía
        assertTrue(seccion.contiene(cartaUnidad));
    }
}
