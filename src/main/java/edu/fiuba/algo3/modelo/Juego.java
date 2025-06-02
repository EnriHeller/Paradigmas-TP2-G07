package edu.fiuba.algo3.modelo;
import java.util.Random;
import java.util.List;

public class Juego {
    private int ciclos;
    private List<Ronda> rondas;
    private Tablero tablero;
    private List<Jugador> jugadores;
    private int moneda;
    private int jugadorQueInicia;

    public Juego() {}

    public int puntaje(){
        return 0;
    }


    private void tirarMoneda(){
        moneda = Math.random() < 0.5 ? -1 : 1;

        if (moneda == -1){
            jugadorQueInicia = 1;
        } else{
            jugadorQueInicia = 0;
        }

    }

    public void jugarRonda(){
        for (Jugador jugador : jugadores){
            // Falta implementar pasarTurno y la lógica de selección de sección
            // Aquí solo se deja el esqueleto para evitar errores de compilación
            // Puedes completar la lógica según tu modelo
        }
        ciclos++;
    }

    public String mostrarGanador(){
        return "";
    }

    public boolean juegoTerminado(){
        return false;
    }

}