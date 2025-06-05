package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.principal.Juego;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList; // o import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class Test01JugadorConCartasSuficientes {

    @Test
    public void jugadorTieneCartasSuficientesAlEmpezar() {
        /*int cartasMinimasEsperadas = 21;

        // Crear 21 Cartas
        List<Carta> cartas = new ArrayList<Carta>();
        
        for (int i = 0; i < 21; i++) {
            Carta carta = new CartaUnidad();
            cartas.add(carta);
        }

        // Crear el mazo con esas cartas
        Mazo mazo = new Mazo(cartas); // Asegurate de tener este constructor

        // Crear el jugador (el constructor debe aceptar el mazo)
        Jugador jugador = new Jugador("jugadorTest", mazo); // No más "mockito de mazo"

        // Verificar que el jugador empieza con 21 cartas
        assertEquals(cartasMinimasEsperadas, jugador.cartasRestantes(),
                "El jugador debe tener 21 cartas al comenzar el juego");
        */

        List<Object> opciones_jugador_1 = new ArrayList<>();
        opciones_jugador_1.add("Pepito");
        opciones_jugador_1.add(0);

        List<Object> opciones_jugador_2 = new ArrayList<>();
        opciones_jugador_2.add("Mengano");
        opciones_jugador_2.add(1);

        Juego nuevoJuego = new Juego();
        nuevoJuego.iniciarJuego(opciones_jugador_1, opciones_jugador_2);

        assertEquals(21, nuevoJuego.cartasRestantesJugador(0));
    }
}