package edu.fiuba.algo3.modelo;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class Juego {
    private int ciclos;
    private Ronda[] rondas;
    private Tablero tablero;
    private List<Jugador> jugadores;
    private int moneda;
    private int jugadorQueInicia;

    public Juego() {

        this.ciclos = 0;
        rondas = new Ronda[3];
        tablero = new Tablero();
        jugadores = new ArrayList<Jugador>();
        moneda = 0;
        jugadorQueInicia = -1;

    }

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

    public void jugarRonda() {
        while (jugadores.get(jugadorQueInicia).pasarTurno()) {
            Carta cartaJugadaPorPrimero = jugadores.get(jugadorQueInicia).jugarCarta();

            boolean yaJugo = false;

            while (!yaJugo) {
                String dondeJuegaPrimero = jugadores.get(jugadorQueInicia).SeccionElegida();
                yaJugo = tablero.jugarCarta(jugadorQueInicia, cartaJugadaPorPrimero, dondeJuegaPrimero);

            }
            rondas[ciclos].agregarPuntajeJugador(jugadorQueInicia,cartaJugadaPorPrimero.getValor());
        }

        while(jugadores.get(jugadorQueInicia+moneda).pasarTurno()){
            Carta cartaJugadaPorSegundo = jugadores.get(jugadorQueInicia + moneda).jugarCarta();

            String dondeJuegaSegundo = jugadores.get(jugadorQueInicia + moneda).SeccionElegida();

            boolean yaJugoSegundo = false;

            while (!yaJugoSegundo) {

                yaJugoSegundo = tablero.jugarCarta(jugadorQueInicia + moneda, cartaJugadaPorSegundo, dondeJuegaSegundo);

            }
            rondas[ciclos].agregarPuntajeJugador(jugadorQueInicia+moneda,cartaJugadaPorSegundo.getValor());
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