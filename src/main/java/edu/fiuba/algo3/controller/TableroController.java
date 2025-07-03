package edu.fiuba.algo3.controller;

import java.util.List;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.CartaNoJugable;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;
import edu.fiuba.algo3.modelo.secciones.tablero.TipoDeSeccionInvalidaError;

public class TableroController {
    private final Juego juego;
    private final Tablero modeloTablero;

    public TableroController(Juego juego) {
        this.juego = juego;
        this.modeloTablero = juego.getTablero();
    }

    public boolean hayClimaEnSeccion(String claveSeccion) {
        return (modeloTablero.obtenerSeccionPorClave(claveSeccion).hayClima());
    }


    public boolean puedeAgregar(String claveSeccion, CartaUnidad carta) {
        if(carta.mostrarCarta().contains("Agil")){
            return true;
        }
        Seccion seccion = modeloTablero.obtenerSeccionPorClave(claveSeccion);
        //validacion de que el jugador esta jugando en su seccion
        int idActual = juego.actual();
        return seccion.puedeAgregar(carta, idActual);
    }

    public void removerCartaEnMano(Carta cartaElegida){
        
        juego.removerCartaEnMano(cartaElegida);
    }

    public void jugarCarta(Carta carta, String claveSeccion) throws TipoDeSeccionInvalidaError, CartaNoJugable {
        Seccion seccion = modeloTablero.obtenerSeccionPorClave(claveSeccion);
        System.out.println("Inicio de jugar carta");
        for (CartaUnidad cartaUnidad : seccion.getCartas()) {
            System.out.println("Carta en seccion antes de la jugada: " + cartaUnidad.mostrarCarta() + "\n" + "Puntaje de esa carta:" + cartaUnidad.ValorActual() + "\n");
        }
        juego.jugarCarta(carta, seccion);
        // Refrescar la mano con la lista actualizada del jugador
        
        for (CartaUnidad cartaUnidad : seccion.getCartas()) {
            System.out.println("Carta en seccion despues de la jugada: " + cartaUnidad.mostrarCarta() + "\n" + "Puntaje de esa carta:" + cartaUnidad.ValorActual() + "\n");
        }
        System.out.println("Fin de jugar carta");
    }

    public int getPuntajeSeccion(String claveSeccion) {
        Seccion seccion = modeloTablero.obtenerSeccionPorClave(claveSeccion);
        return seccion.getPuntajeTotal();
    }

    public List<CartaUnidad> getCartasEnSeccion(String claveSeccion) {
        Seccion seccion = modeloTablero.obtenerSeccionPorClave(claveSeccion);
        return seccion.getCartas();
    }

    public List<String> getClavesSecciones() {
        return List.of("Asedio1", "Rango1", "CuerpoACuerpo1", "CuerpoACuerpo0", "Rango0", "Asedio0");
    }

    public Juego getJuego() {
        return this.juego;
    }
}
