package edu.fiuba.algo3.modelo.principal;
import java.util.List;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.secciones.jugador.Descarte;
import edu.fiuba.algo3.modelo.secciones.jugador.Mano;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.tablero.TipoDeSeccionInvalidaError;

public class Jugador {
    private String nombre;
    private Mazo mazo;
    private Mano mano;
    private Descarte descarte;
    private int ordenDeJuego = 0;

    public Jugador(String nombre) {
        this.nombre = nombre;

        this.mano = new Mano();
        this.descarte = new Descarte();
    }

    public void agregarMazo(Mazo mazo) {
        this.mazo = mazo;
    }

    public int ordenDeJuego() {
        return ordenDeJuego;
    }

    public void setOrdenDeJuego(int ordenDeJuego) {
        this.ordenDeJuego = ordenDeJuego;
    }

    public Carta jugarCarta(Carta carta) {
        return mano.removerCarta(carta);
    }
    //Fase inicial y preparacion
    public void agregarCartasAMano(int n) throws TipoDeSeccionInvalidaError, NoSePuedeCumplirSolicitudDeCartas {
        List<Carta> cartas = mazo.repartirCarta(n);
        mano.agregarCartas(cartas);
    }
    public void agregarCartaAMano(Carta carta) {
        mano.agregarCarta(carta);
    }

    public void descartarAlMazo(List<Carta> unasCartas) {
        mazo.agregarCartas(mano.removerCartas(unasCartas));
    }

    public List<Carta> cartasEnMano(){
        return mano.getCartas();
    }

    public int cartasRestantesEnSeccion(String clave) {

        switch (clave) {
            case "Mano":
                return mano.cartasRestantes();
            case "Descarte":
                return descarte.cartasRestantes();
            default:
                return mazo.cartasRestantes();
        }
    }

    public List<Carta> obtenerCartasEnMano(){
        return mano.getCartas();
    }

    public String nombre() {
        return nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void agregarCartasAlDescarte(List<Carta> cartas) {
        descarte.agregarCartas(cartas);
    }

    public Carta removerUltimaCartaDeDescarte(){
        return descarte.removerUnidad();
    }

    public Carta getCartaEnDescarte() {
        return descarte.getCartaPila();
    }
}
