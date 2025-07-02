package edu.fiuba.algo3.modelo.principal;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;

public class Contexto {
    private Tablero tablero;
    private Seccion seccion = null;
    private CartaUnidad carta = null;
    private Jugador jugador;

    public Contexto(Tablero tablero, Seccion seccion, CartaUnidad carta, Jugador jugador) {
        this.tablero = tablero;
        this.seccion = seccion;
        this.carta = carta;
        this.jugador = jugador;
    }

    public Contexto(Tablero tablero, Jugador jugador) {
        this.tablero = tablero;
        this.jugador = jugador;
    }


    public Tablero getTablero(){
        return tablero;
    }

    public Seccion getSeccion(){
        return seccion;
    }

    public CartaUnidad getCarta(){
        return carta;
    }

    public Jugador getJugador(){
        return jugador;
    }

}
