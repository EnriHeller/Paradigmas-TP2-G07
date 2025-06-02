package edu.fiuba.algo3.modelo;
import java.util.Random;


import java.util.List;

public class Juego {
    private int ciclos;
    private List<Ronda> rondas;
    private Tablero tablero;
    private List<Jugador> jugadores;
    private int moneda;


    public Juego() {}

    public int puntaje(){
        return 0;
    }


    public void tirarMoneda(){
        moneda = Math.random() < 0.5 ? -1 : 1;
    }

    public void jugarRonda(){

        

        ciclos++;
    }

    public String mostrarGanador(){
        return "";
    }

    public boolean juegoTerminado(){
        return false;
    }

}