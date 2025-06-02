package edu.fiuba.algo3.entrega_1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import edu.fiuba.algo3.modelo.*;

import java.util.LinkedList;

public class Test03JugadorPuedeColocarCartaEnSeccionDelTablero {

    @Test
    public void jugadorPuedeColocarCartaEnSeccion() throws TipoDeSeccionInvalidaError {
        // Arrange
        Carta cartaMock = mock(Carta.class);
        Seccion seccionMock = mock(Seccion.class);

        LinkedList<Carta> cartas = new LinkedList<>();
        cartas.add(cartaMock);
        Mazo mazo = new Mazo(cartas);
        Jugador jugador = new Jugador("JugadorTest", mazo);
        jugador.agregarCartasAMano(1);

        // Act
        Carta cartaJugada = jugador.jugarCarta();
        seccionMock.agregarCarta(cartaJugada);

        // Assert: verificar que la sección recibió la carta
        verify(seccionMock).agregarCarta(cartaMock);
        assertEquals(0, jugador.cartasEnMano());
    }
}
