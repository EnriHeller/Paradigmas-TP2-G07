package edu.fiuba.algo3.modelo.principal;

import edu.fiuba.algo3.modelo.jugador.Jugador;

public class Ronda {
    private AdministradorDeTurno turnos;
    private Tablero tablero;

    public Ronda(Jugador j1, Jugador j2, Tablero tablero) {
        this.turnos = new AdministradorDeTurno(j1, j2);
        this.tablero = tablero;
    }

    public boolean estaFinalizada() {
        return turnos.ambosPasaron();
    }

    public void registrarPaso(Jugador jugador) {
        jugador.pasar();
    }

    public Jugador jugadorActual() {
        return turnos.jugadorActual();
    }

    public void siguienteTurno() {
        turnos.siguiente();
    }
// Por ahora no lo hacemos pq tablero no conoce jugador
//    public Jugador determinarGanador() {
//        Jugador j1 = turnos.getJugador(0);
//        Jugador j2 = turnos.getJugador(1);
//        int puntos1 = tablero.obtenerPuntaje(j1);
//        int puntos2 = tablero.obtenerPuntaje(j2);
//
//        if (puntos1 >= puntos2) return j1;
//        return j2;
//    }

    public void reiniciar() {
        tablero.limpiar();
        turnos.reiniciar();
    }
}
