package edu.fiuba.algo3.modelo;

import java.util.List;

public class Juego {
    private int ciclos;
    private List<Ronda> rondas;
    private Tablero tablero;
    private List<Jugador> jugadores;

    public Juego() {}

    public int puntaje(){
        return 0;
    }

    public void jugarRonda(){

    }

    public String mostrarGanador(){
        return "";
    }

    public boolean juegoTerminado(){
        return false;
    }

}