package edu.fiuba.algo3.modelo.principal;

import edu.fiuba.algo3.modelo.jugador.Jugador;

import java.util.Random;

public class AdministradorDeTurno {
    private final Jugador[] jugadores;
    private int indiceActual;

    public AdministradorDeTurno(Jugador jugador1, Jugador jugador2) {
        this.jugadores = new Jugador[]{jugador1, jugador2};
        this.indiceActual = new Random().nextInt(2);
    }

    public Jugador jugadorActual() {
        return jugadores[indiceActual];
    }

    public void siguiente() {
        indiceActual = 1 - indiceActual;
    }

    public boolean ambosPasaron() {
        return jugadores[0].haPasado() && jugadores[1].haPasado();
    }

    public void reiniciar() {
        jugadores[0].reiniciarPaso();
        jugadores[1].reiniciarPaso();
        indiceActual = new Random().nextInt(2);
    }
}

