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
            while(!(jugador.pasarTurno())){ 

                Carta cartaJugador = jugador.jugarCarta();
                while(/*el jugador elija mal la seccion (la carta sabe donde jugarse, el jugador elije mal por pelotudo)*/){
|                   String dondeJugarla = jugador.dondeJugarla(); // NO declararla en el while
                }
                tablero.jugarCarta(cartaJugador, dondeJugarla)

            }
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