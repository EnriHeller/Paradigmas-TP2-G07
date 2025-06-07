package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.cartas.Carta;

public class Jugador {

    private String nombre;
    private Mazo mazo;
    private Mano mano;
    private boolean pasoDeRonda;
    // Revisar si hace falta / vale la pena tener las secciones que
    // corresponden a cada jugador

    public Jugador(String nombre,Mazo mazo){
        this.nombre = nombre;
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

    public int cantidadEnMano() {
        return mano.cantidad();
    }

    public int cantidadEnMazo() {
        return mazo.cantidad();
    }

    public boolean compararNombre(String nombre) {
        return this.nombre.equals(nombre);
    }

    public Mano obtenerMano() { return mano;}


}
