package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;

import java.util.List;

public class TableroController {
    private final Juego juego;
    private final Tablero modeloTablero;

    public TableroController(Juego juego) {
        this.juego = juego;
        this.modeloTablero = juego.getTablero();
    }

    public boolean puedeAgregar(String claveSeccion, CartaUnidad carta) {
        Seccion seccion = modeloTablero.obtenerSeccionPorClave(claveSeccion);
        return seccion.puedeAgregar(carta);
    }

    public void jugarCarta(Carta carta, String claveSeccion) throws TipoDeSeccionInvalidaError {
        Seccion seccion = modeloTablero.obtenerSeccionPorClave(claveSeccion);
        System.out.println("Inicio de jugar carta");
        for (CartaUnidad cartaUnidad : seccion.getCartas()) {
            System.out.println("Carta en seccion antes de la jugada: " + cartaUnidad.mostrarCarta() + "\n" + "Puntaje de esa carta:" + cartaUnidad.ValorActual() + "\n");
        }
        juego.jugarCarta(carta, seccion);
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
        return List.of("Asedio1", "Rango1", "CuerpoACuerpo1", "Asedio0", "Rango0", "CuerpoACuerpo0");
    }
}
