package edu.fiuba.algo3.modelo.principal;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;

public class Contexto {
    private Tablero tablero;
    private Seccion seccion;
    private CartaUnidad carta;
    //private int jugadorID;
    private Jugador jugador;

    public Contexto(Tablero tablero, Seccion seccion, CartaUnidad carta, Jugador jugador) {
        this.tablero = tablero;
        this.seccion = seccion;
        this.carta = carta;
        //this.jugadorID = jugadorID;
        this.jugador = jugador;
    }

    public Contexto() {

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

    //public int getJugador(){
    //    return jugadorID;
    //}

    public Jugador getJugadorClase(){
        return jugador;
    }

}
