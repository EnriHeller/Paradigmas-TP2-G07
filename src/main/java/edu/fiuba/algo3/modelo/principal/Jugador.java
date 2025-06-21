package edu.fiuba.algo3.modelo.principal;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.jugador.Descarte;
import edu.fiuba.algo3.modelo.secciones.jugador.Mano;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;

import java.util.List;

public class Jugador {
    private String nombre;
    private Mazo mazo;
    private Mano mano;
    private Descarte descarte;
    private int ordenDeJuego = 0;

    public Jugador() {
        this.nombre = "";
        this.mazo = null;
    }

    public Jugador(String nombre, Mazo mazo) {
        this.nombre = nombre;
        this.mazo = mazo;

        this.mano = new Mano();
        this.descarte = new Descarte();

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
    public void agregarCartasAMano(int n) throws TipoDeSeccionInvalidaError, NoSePuedeCumplirSolcitudDeCartas {
        List<Carta> cartas = mazo.repartirCarta(n);
        mano.agregarCartas(cartas);
    }
    public void agregarCartaAMano(Carta carta) {
        mano.agregarCarta(carta);
    }

    public void descartarAlMazo(List<Carta> unasCartas) {
        mazo.agregarCartas(mano.removerCartas(unasCartas));
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

    public String nombre() {
        return nombre;
    }

    public void agregarCartasAlDescarte(List<CartaUnidad> cartas) {
        descarte.agregarCartas(cartas);
    }

    public CartaUnidad removerUltimaCartaDeDescarte(){
        return descarte.removerUnidad();
    }
}
