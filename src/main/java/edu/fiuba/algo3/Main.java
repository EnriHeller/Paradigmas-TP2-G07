package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.principal.Tablero;
import edu.fiuba.algo3.vistas.App;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.principal.ConstructorMazo;
import edu.fiuba.algo3.modelo.principal.Juego;

public class Main {

    public static void main(String[] args) {

        Tablero tablero = new Tablero();
        // Construir los mazos para cada jugador
        ConstructorMazo constructorMazo = new ConstructorMazo();
        Jugador jugador1 = new Jugador("pepito", constructorMazo.construirMazo());
        Jugador jugador2 = new Jugador("pepita", constructorMazo.construirMazo());

        // Crear una instancia del juego con los jugadores
        Juego juego = new Juego(jugador1, jugador2, tablero);

        // Iniciar el juego
        juego.iniciarJuego();

        // Lógica de bucle principal
        while (!juego.verificarCondicionesDeVictoria()) {
            System.out.println("Turno del jugador: " + juego.jugadorActual());
            juego.siguienteTurno();
        }

        System.out.println("¡El juego ha terminado!");
    }
}