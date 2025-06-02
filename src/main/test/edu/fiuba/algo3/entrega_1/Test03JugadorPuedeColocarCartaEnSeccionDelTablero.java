package edu.fiuba.algo3.entrega_1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import edu.fiuba.algo3.modelo.*;

public class Test03JugadorPuedeColocarCartaEnSeccionDelTablero {

    @Test
    public void jugadorPuedeColocarCartaEnSeccion() throws TipoDeSeccionInvalidaError {
        // Arrange
        Carta cartaMock = mock(Carta.class);
        Mazo mazoMock = mock(Mazo.class);
        Seccion seccionMock = mock(Seccion.class);

        // Simula que la mano tiene la carta y la devuelve correctamente
        // when(mazoMock.removerCarta(cartaMock)).thenReturn(cartaMock);

        Jugador jugador = new Jugador("JugadorTest", mazoMock);

        // Act
        jugador.jugarCarta(cartaMock, seccionMock);

        // Assert: verificar que la sección recibió la carta
        verify(seccionMock).agregarCarta(cartaMock);

        // Y que la mano intentó sacar la carta
        // verify(manoMock).removerCarta(cartaMock);
    }
}
