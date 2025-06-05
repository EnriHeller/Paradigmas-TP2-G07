package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;


public class Test03JugadorPuedeColocarCartaEnSeccionDelTablero {
    // Verificar que un jugador pueda colocar una carta en una sección del tablero

    @Test
    public void jugadorPuedeColocarCartaEnSeccion() throws TipoDeSeccionInvalidaError {
        // Arrange
        List<Object> opciones_jugador_1 = new ArrayList<>();
        opciones_jugador_1.add("Pepito");
        opciones_jugador_1.add(0);

        List<Object> opciones_jugador_2 = new ArrayList<>();
        opciones_jugador_2.add("Mengano");
        opciones_jugador_2.add(1);

        Juego juego = new Juego();
        juego.iniciarJuego(opciones_jugador_1, opciones_jugador_2);
        assertTrue(juego.iniciarFasePreparacion());

        // Act: Simular que el J1 juega la primera carta
        Carta cartaAJugar =
    }
}
