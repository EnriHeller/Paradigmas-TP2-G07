package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Test02JugadorSeLeReparten10CartasDeSuMazo {

    @Test
    public void jugadorRecibe10CartasInicialesEnSuMano() throws TipoDeSeccionInvalidaError {
        
        List<Carta> cartas = new ArrayList<Carta>();

        for (int i = 0; i < 21; i++) {
            //Esto hay que mockearlo en vez de mandar cosas por default
            cartas.add(new CartaUnidad());
        }

        Mazo mazo = new Mazo(cartas);
        Jugador jugador = new Jugador("JugadorTest", mazo);

        // Act
        jugador.agregarCartasAMano(10);

        // Assert
        assertEquals(10, jugador.cartasEnMano(), "El jugador debe tener 10 cartas en la mano al comenzar");
    }
}