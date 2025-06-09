package edu.fiuba.algo3.modelo.principal;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.jugador.SeccionesJugador;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;

public class Contexto {
    private Tablero tablero;
    private String seccion;
    private CartaUnidad carta;
    private SeccionesJugador sinPuntaje;
    private int jugadorID;
    private Jugador jugador;

    public Contexto(Tablero tablero, String seccion, CartaUnidad carta,  int jugadorID, SeccionesJugador sinPuntaje, Jugador jugador) {
        this.tablero = tablero;
        this.seccion = seccion;
        this.carta = carta;
        this.jugadorID = jugadorID;
        this.sinPuntaje = sinPuntaje;
        this.jugador = jugador;
    }

    public Contexto() {

    }

    public Tablero getTablero(){
        return tablero;
    }

    public String getSeccion(){
        return seccion;
    }

    public CartaUnidad getCarta(){
        return carta;
    }

    public int getJugador(){
        return jugadorID;
    }

    public SeccionesJugador getSinPuntaje(){
        return sinPuntaje;
    }

    public Jugador getJugadorClase(){
        return jugador;
    }

}
