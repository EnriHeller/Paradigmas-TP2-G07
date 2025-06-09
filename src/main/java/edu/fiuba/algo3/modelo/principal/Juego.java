package edu.fiuba.algo3.modelo.principal;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.secciones.Seccion;

import java.util.List;

public class Juego {

    private final List<Jugador> jugadores;
    private final AdministradorDeTurno administradorTurnos;
    private final Ronda rondaActual;
    private final Tablero tablero;

    public Juego(Jugador jugador1, Jugador jugador2, Tablero tablero) {
        // Los jugadores ya vienen inicializados desde el Main con sus mazos
        this.jugadores = List.of(jugador1, jugador2);
        this.tablero = tablero;
        // Inicializar turnos y ronda
        this.administradorTurnos = new AdministradorDeTurno(jugador1, jugador2);
        this.rondaActual = new Ronda(jugador1, jugador2, new Tablero());
    }

    public void jugarCarta(Carta carta, Seccion seccionDestino) {
        Jugador jugadorActual = administradorTurnos.jugadorActual();

        // El jugador juega la carta desde su mano
        Carta cartaJugada = jugadorActual.jugarCarta(carta);

        // El Juego delega al tablero la colocación de la carta
        tablero.jugarCarta(jugadorActual, cartaJugada, seccionDestino);

    }

    public int calularPuntajeJugador(Jugador jugador) {
        return tablero.calcularPuntajeJugador(jugador);
    }


    public void iniciarJuego() {
        // La dejo por si llega a servir para algo
        System.out.println("El juego ha comenzado.");
    }

    public void siguienteTurno() {
        if (rondaActual.estaFinalizada()) {
            reiniciarRonda();
        } else {
            administradorTurnos.siguiente();
        }
    }

    private void reiniciarRonda() {
        rondaActual.reiniciar();
        for (Jugador jugador : jugadores) {
            jugador.reiniciarPaso();
        }
    }

    public Jugador jugadorActual() {
        return administradorTurnos.jugadorActual();
    }

    public boolean verificarCondicionesDeVictoria() {
        // Completa lógica para condiciones de victoria.
        return false;
    }
}