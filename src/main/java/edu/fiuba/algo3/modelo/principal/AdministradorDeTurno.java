package edu.fiuba.algo3.modelo.principal;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class AdministradorDeTurno {
    private final List<Jugador> jugadores;
    private Ronda[] rondas = new Ronda[3];
    private int indiceActual;
    private int ciclos = 0;

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

    public void finalizarRonda(Tablero tablero){
        for (Jugador jugador : jugadores) {
            List<Seccion> secciones = tablero.mostrarTableroParaJugador(jugador);
            for (Seccion seccion : secciones) {
                List<CartaUnidad> cartasRemovidas = seccion.removerCartas();
                for (CartaUnidad carta : cartasRemovidas) {
                    Contexto contexto = new Contexto(tablero, seccion, carta, jugador);
                    carta.retrotraerModificacion(contexto);
                    jugador.DescartarCarta(carta);
                }
            }
        }
        ciclos++;
    }

    private void agregarRonda() {
        rondas[ciclos] = new Ronda();
    }

    public void actualizarRonda(int puntaje){
        if (rondas[ciclos] == null){
            agregarRonda();
        }
        rondas[ciclos].agregarPuntajeJugador(indiceActual,puntaje);
    }

    public String mostrarGanador(){
        String ganador = "empate";
        int contadorJ1 = 0;
        int contadorJ2 = 0;

        String nombreJugador1 = jugadores.get(0).getNombre();
        String nombreJugador2 = jugadores.get(1).getNombre();

        for (Ronda ronda : rondas) {
            if (ronda == null) continue;
            String ganadorRonda = ronda.getGanadorRonda();

            if (ganadorRonda.equals(nombreJugador1)) {
                contadorJ1++;
            } else if (ganadorRonda.equals(nombreJugador2)) {
                contadorJ2++;
            }
        }

        if (contadorJ1 > contadorJ2) {
            ganador = nombreJugador1;
        } else if (contadorJ2 > contadorJ1) {
            ganador = nombreJugador2;
        }

        return ganador;
    }

    public boolean juegoTerminado(){
        if (ciclos < 2){
            return false;
        } else return !mostrarGanador().equals("empate");
    }
}


