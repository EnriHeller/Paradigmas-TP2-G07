package edu.fiuba.algo3.modelo.secciones;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.CartaUnidad;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Seccion {

    // Lista de cartas en esta sección
    protected List<CartaUnidad> cartas;

    public Seccion() {
        this.cartas = new ArrayList<>();
    }

    public void agregarCarta(Carta carta) {
        this.cartas.add((CartaUnidad) carta);
    }

    public List<Carta> obtenerCartas() {
        return new ArrayList<>(this.cartas);
    }

    public abstract int calcularPuntos();

    public abstract String obtenerNombre();

    public static boolean esValida(String nombreSeccion) {
        // Lista de secciones válidas en el juego
        List<String> seccionesValidas = Arrays.asList("Asedio", "Rango", "Cuerpo a Cuerpo");
        return seccionesValidas.contains(nombreSeccion);
    }
    public void limpiar() {
        this.cartas.clear();
    }

    public boolean contieneCarta(CartaUnidad cartaSeleccionada) {
        return this.cartas.contains(cartaSeleccionada);
    }
}