package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Test02JugadorSeLeReparten10CartasDeSuMazo {

    @Test
    public void jugadorRecibe10CartasInicialesEnSuMano() {
        // Arrange: Crear 21 cartas reales (pueden ser stubs o mocks si necesitas que hagan algo)
        LinkedList<Carta> cartas = new LinkedList<>();
        for (int i = 0; i < 21; i++) {
            cartas.add(new CartaUnidad()); // Usamos un stub simple que implementa Carta
        }

        Mazo mazo = new Mazo(cartas);
        Jugador jugador = new Jugador("JugadorTest", mazo);

        // Act
        jugador.agregarCartasAMano(10);

        // Assert
        assertEquals(10, jugador.cartasEnMano(), "El jugador debe tener 10 cartas en la mano al comenzar");
        assertEquals(11, mazo.cantidadDeCartas(), "El mazo debe quedarse con 11 cartas después de repartir 10");
    }
}