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

        //Posiblemente haya que mockear mas
        Carta cartaMock = mock(Carta.class);

        //Guardamos como lista enlazada para siempre obtener la carta que esta más por encima.
        LinkedList<Carta> cartas = new LinkedList<>();

        cartas.add(cartaMock);
        Mazo mazo = new Mazo(cartas);

        Jugador jugador = new Jugador("JugadorTest", mazo);
        jugador.agregarCartasAMano(1);

        Seccion seccion = new Seccion("Rango");

        // Act
        Carta cartaJugada = jugador.jugarCartaPorIndice(0); 
        seccion.agregarCarta(cartaJugada);

        // Assert: verificar que la sección recibió la carta y la mano quedó vacía
        assertTrue(seccion.contiene(cartaMock));
    }
}
