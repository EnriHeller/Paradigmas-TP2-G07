package edu.fiuba.algo3.modelo.principal;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;

import java.util.List;

public class Contexto {
    private Tablero tablero;
    private Seccion seccion;
    private CartaUnidad carta;
    private Jugador jugador;
    private List<Jugador> jugadores; // NEW FIELD

    public Contexto(Tablero tablero, Seccion seccion, CartaUnidad carta, Jugador jugador, List<Jugador> jugadores) {
        this.tablero = tablero;
        this.seccion = seccion;
        this.carta = carta;
        this.jugador = jugador;
        this.jugadores = jugadores;
    }

    public Contexto(Tablero tablero, Seccion seccion, Jugador jugador, List<Jugador> jugadores) {
        this.tablero = tablero;
        this.seccion = seccion;
        this.jugador = jugador;
        this.jugadores = jugadores;
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

    public List<Jugador> getJugadores() {
        return jugadores;
    }

}
