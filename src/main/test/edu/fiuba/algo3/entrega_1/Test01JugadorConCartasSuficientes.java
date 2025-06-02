package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList; // o import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class Test01JugadorConCartasSuficientes {

    @Test
    public void jugadorTieneCartasSuficientesAlEmpezar() {
        int cartasMinimasEsperadas = 21;

        // Crear 21 mocks de Carta
        List<Carta> cartas = new ArrayList<Carta>();
        
        for (int i = 0; i < 21; i++) {
            Carta cartaMock = mock(Carta.class);
            cartas.add(cartaMock);
        }

        // Crear el mazo con esas cartas
        Mazo mazo = new Mazo(cartas); // Asegurate de tener este constructor

        // Crear el jugador (el constructor debe aceptar el mazo)
        Jugador jugador = new Jugador("jugadorTest", mazo); // No más "mockito de mazo"

        // Verificar que el jugador empieza con 21 cartas
        assertEquals(cartasMinimasEsperadas, jugador.cartasRestantes(),
                "El jugador debe tener 21 cartas al comenzar el juego");
    }
}