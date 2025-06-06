package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.cartas.Carta;

public class Jugador {

    private Mazo mazo;
    private Mano mano;
    private boolean pasoDeRonda;
    // Revisar si hace falta / vale la pena tener las secciones que
    // corresponden a cada jugador

    public Jugador(Mazo mazo){
        this.mazo = mazo;
        this.mano = new Mano(mazo.repartirMano());
        pasoDeRonda = false;
    }
    public void devolverCarta(Carta carta) {
        this.mano.agregarCarta(carta);
    }
    public Carta jugarCarta(Carta carta) {
        return mano.quitarCarta(carta);
    }

    // La lógica para cambiar ese estado esta en el Jugador mismo
    // porque es su decisión pasar o no.
    public void pasar() {
        this.pasoDeRonda = true;
    }

    public boolean haPasado() {
        return pasoDeRonda;
    }

    public void reiniciarPaso() {
        this.pasoDeRonda = false;
    }


}
