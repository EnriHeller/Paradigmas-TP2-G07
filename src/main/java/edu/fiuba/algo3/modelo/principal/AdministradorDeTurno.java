package edu.fiuba.algo3.modelo.principal;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AdministradorDeTurno {
    private final List<Jugador> jugadores;
    private final List<Ronda> rondas = new ArrayList<>();
    private int indiceActual = 0;
    private int ciclos = 0;

    public AdministradorDeTurno(List<Jugador> jugadores) {
        this.jugadores = jugadores;
        for (int i = 0; i < jugadores.size(); i++) {
            jugadores.get(i).setOrdenDeJuego(i);
        }
    }

    public void indiceActual(int indice) {
        this.indiceActual = indice;
    }

    public void tirarMoneda() {
        this.indiceActual = new Random().nextInt(2);
    }

    public Jugador jugadorActual() {
        return jugadores.get(indiceActual);
    }

    public void siguiente() {
        indiceActual = 1 - indiceActual;
    }

    public void finalizarRonda(Tablero tablero) {
        List<CartaUnidad> cartasDel1 = tablero.removerCartasDeJugador(0);
        List<CartaUnidad> cartasDel2 = tablero.removerCartasDeJugador(1);

        for (CartaUnidad carta : cartasDel1) {
            Contexto contexto = new Contexto(tablero, new Seccion(), carta, jugadores.get(0));
            carta.retrotraerModificacion(contexto);
        }

        for (CartaUnidad carta : cartasDel2) {
            Contexto contexto = new Contexto(tablero, new Seccion(), carta, jugadores.get(1));
            carta.retrotraerModificacion(contexto);
        }

        jugadores.get(0).agregarCartasAlDescarte(new ArrayList<>(cartasDel1));
        jugadores.get(1).agregarCartasAlDescarte(new ArrayList<>(cartasDel2));
        ciclos++;
    }

    private void agregarRonda() {
        rondas.add(new Ronda());
    }

    public void actualizarRonda(int puntaje) {
        if (rondas.size() <= ciclos) {
            agregarRonda();
        }
        rondas.get(ciclos).agregarPuntajeJugador(jugadores.get(indiceActual).nombre(), puntaje);
    }

    public String mostrarGanador() {
        String ganador = "empate";
        int contadorJ1 = 0;
        int contadorJ2 = 0;

        String nombreJugador1 = jugadores.get(0).nombre();
        String nombreJugador2 = jugadores.get(1).nombre();

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

    public boolean juegoTerminado() {
        if (ciclos < 2) {
            return false;
        } else return !mostrarGanador().equals("empate");
    }
}
