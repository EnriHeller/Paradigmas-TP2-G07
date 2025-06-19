package edu.fiuba.algo3.modelo.principal;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.secciones.tablero.TableroSingleton;

import java.util.List;

public class ModificadorContexto {
    private TableroSingleton tablero;
    private String seccion;
    private CartaUnidad carta;
    private List<CartaUnidad> cartasDeLaSeccion;
    private Jugador jugador;

    public ModificadorContexto(TableroSingleton tablero, String seccion, CartaUnidad carta, List<CartaUnidad> cartasDeLaSeccion, Jugador jugador) {
        this.tablero = tablero;
        this.seccion = seccion;
        this.carta = carta;
        this.cartasDeLaSeccion = cartasDeLaSeccion;
        this.jugador = jugador;
    }

    public TableroSingleton getTablero(){
        return tablero;
    }

    public String getSeccion(){
        return seccion;
    }

    public CartaUnidad getCarta(){
        return carta;
    }
    public List<CartaUnidad> getCartasDeLaSeccion(){
        return cartasDeLaSeccion;
    }

    public Jugador getJugador(){
        return jugador;
    }

}
