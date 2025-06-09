package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.principal.ConstructorMazo;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Tablero;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class Test01JugadorConCartasSuficientes {

    @Test
    public void jugadorTieneCartasSuficientesAlEmpezar() {

        // Verificar que un jugador posea cartas suficientes para empezar el juego en su mazo.

        int cantidadEsperadaMazo = 11;  // se espera que mazo tenga 11 porque automaticamente se sacan 10 para la mano
        int cantidadEsperadaMano = 10;
        Tablero tablero = new Tablero();
        ConstructorMazo constructorMazo = new ConstructorMazo();
        Jugador jugador1 = new Jugador("pepito", constructorMazo.construirMazo());
        Jugador jugador2 = new Jugador("pepita", constructorMazo.construirMazo());
        Juego juego = new Juego(jugador1, jugador2, tablero);

        assertEquals(cantidadEsperadaMazo, juego.jugadorActual().cantidadEnMazo());
        assertEquals(cantidadEsperadaMano, juego.jugadorActual().cantidadEnMano());
        }
}