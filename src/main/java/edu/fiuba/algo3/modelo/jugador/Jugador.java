package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.cartas.Carta;

import java.util.List;

public class Jugador {

    private final String nombre;
    private final Mazo mazo;
    private final Mano mano;
    private boolean pasoDeRonda;
    private final PilaDeDescarte pilaDescarte;
    // Revisar si hace falta / vale la pena tener las secciones que
    // corresponden a cada jugador

    public Jugador(String nombre,Mazo mazo){
        this.nombre = nombre;
        this.mazo = mazo;
        this.mano = new Mano(mazo.repartirMano());
        pasoDeRonda = false;
        pilaDescarte = new PilaDeDescarte();
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

    public PilaDeDescarte obtenerPilaDeDescarte() { return pilaDescarte;}

    public void agregarCartasAlDescarte(List<Carta> cartas) {
        pilaDescarte.agregarCartas(cartas);
    }
    public void agregarCartaAlDescarte(Carta carta) {
        pilaDescarte.agregarCarta(carta);
    }
    public void recuperarCartaDelDescarte(Carta carta) {
        mano.agregarCarta(pilaDescarte.recuperarCarta(carta));
    }
    public int cartasEnElDescarte() {
        return pilaDescarte.obtenerCartas().size();
    }


}
