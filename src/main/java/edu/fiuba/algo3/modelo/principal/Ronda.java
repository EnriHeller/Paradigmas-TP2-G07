package edu.fiuba.algo3.modelo.principal;

public class Ronda {
    private String ganadorRonda;
    private final int[] puntajeJugadores;

    public Ronda(){
        this.ganadorRonda = "";
        this.puntajeJugadores = new int[2];
    }

    public void agregarPuntajeJugador(int jugadorIndex, int puntajeCarta){
        this.puntajeJugadores[jugadorIndex] += puntajeCarta;
    }

    private void ganadorRonda(){
        this.ganadorRonda = "Jugador" + String.valueOf((puntajeJugadores[0] >= puntajeJugadores[1]) ? 0 : 1);
    }

    public String getGanadorRonda() {
        ganadorRonda();
        return ganadorRonda;
    }

}
