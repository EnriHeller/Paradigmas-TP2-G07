package edu.fiuba.algo3.modelo;
import java.util.ArrayList;
import java.util.List;

public class Tablero {
    private List<List<Seccion>> secciones;

    public Tablero() {
        this.secciones = new ArrayList<>(2);
        // Inicializa las listas de secciones para cada jugador
        this.secciones.add(new ArrayList<>()); // Jugador 1
        this.secciones.add(new ArrayList<>()); // Jugador 2
    }

    public Tablero(List<Seccion> seccionesJugador1, List<SeccionSinPuntaje> seccionesSinPuntaje) {
        this();
        this.secciones.set(0, seccionesJugador1);
    }

    // Obtiene la sección por nombre y jugador (0 o 1)
    public Seccion getSeccion(int jugador, String claveSeccion) {
        for (Seccion seccion : secciones.get(jugador)) {
            if (seccion.getClave().equals(claveSeccion)) {
                return seccion;
            }
        }
        throw new IllegalArgumentException("No existe la sección con clave: " + claveSeccion + " para el jugador " + jugador);
    }

    // persona siempre interactua con el juego-> juego le pide al jugador que juegue -> jugador juega una carta con un modificador del enunciado ->

    // persona interactua con juego -> jugador1 juegue tal carta en tal seccion -> tablero recibe la carta
    // y la seleccion a la que va -> al intentar poner la carta se aplica el modificador.


    // Puntaje de una sección para un jugador
    public int puntosEnSeccion(int jugador, String claveSeccion) {
        Seccion seccion = getSeccion(jugador, claveSeccion);
        int puntosTotales = 0;
        for (CartaUnidad carta : seccion.getCartasActuales()) {
            puntosTotales += carta.ValorActual();
        }
        return puntosTotales;
    }

    // Puntaje total de todas las secciones de un jugador
    public int puntosTotalesDeLasSecciones(int jugador){
        int puntosTotales = 0;
        for (Seccion seccion : secciones.get(jugador)) {
            for (CartaUnidad carta : seccion.getCartasActuales()) {
                puntosTotales += carta.ValorActual();
            }
        }
        return puntosTotales;
    }

    // Jugar una carta en una sección de un jugador
    public boolean jugarCarta(int jugador, CartaUnidad carta, String claveSeccion) {
        Seccion seccion = getSeccion(jugador, claveSeccion);
        try {
            seccion.agregarCarta(carta);
            return true;
        } catch (TipoDeSeccionInvalidaError e) {
            return false;
        }
    }
}