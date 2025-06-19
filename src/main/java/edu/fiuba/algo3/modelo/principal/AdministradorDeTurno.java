package edu.fiuba.algo3.modelo.principal;

import edu.fiuba.algo3.modelo.principal.Jugador;

import java.util.Random;
import java.util.List;

public class AdministradorDeTurno {
    private final List<Jugador> jugadores;
    private int indiceActual;

    public AdministradorDeTurno(List<Jugador> jugadores) {
        this.jugadores = jugadores;
        
    }

    public void tirarMoneda(){
        this.indiceActual = new Random().nextInt(2);
    }

    public Jugador jugadorActual() {
        return jugadores.get(indiceActual);
    }

    public void siguiente() {
        indiceActual = 1 - indiceActual;
    }

    public boolean ambosPasaron() {
        return jugadores.get(0).haPasado() && jugadores.get(1).haPasado();
    }

    public void reiniciar() {
        jugadores.get(0).reiniciarPaso();
        jugadores.get(1).reiniciarPaso();
        indiceActual = new Random().nextInt(2);
    }
}

