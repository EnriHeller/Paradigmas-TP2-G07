package edu.fiuba.algo3.modelo.principal;

import java.util.HashMap;
import java.util.Map;

public class Ronda {
    private String ganadorRonda;
    private final Map<String, Integer> puntajeJugadores;

    public Ronda() {
        this.ganadorRonda = "";
        this.puntajeJugadores = new HashMap<>();
    }

    public void agregarPuntajeJugador(String nombreJugador, int puntajeCarta) {
        puntajeJugadores.put(nombreJugador,
                puntajeJugadores.getOrDefault(nombreJugador, 0) + puntajeCarta);
    }

    private void calcularGanadorRonda() {
        if (puntajeJugadores.size() < 2) {
            ganadorRonda = "Empate";
            return;
        }

        String[] jugadores = puntajeJugadores.keySet().toArray(new String[0]);
        int puntaje1 = puntajeJugadores.get(jugadores[0]);
        int puntaje2 = puntajeJugadores.get(jugadores[1]);

        if (puntaje1 == puntaje2) {
            ganadorRonda = "Empate";
        } else {
            ganadorRonda = (puntaje1 > puntaje2) ? jugadores[0] : jugadores[1];
        }
    }

    public String getGanadorRonda() {
        calcularGanadorRonda();
        return ganadorRonda;
    }

}
