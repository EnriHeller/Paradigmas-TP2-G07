package edu.fiuba.algo3.modelo.secciones;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.CartaUnidad;

public class Asedio extends Seccion {

    public Asedio() {
        super();
    }

    @Override
    public String obtenerNombre() {
        return "Asedio";
    }

    @Override
    public int calcularPuntos() {
        // Modificar la logica cuando haya cartas especiales
        int puntaje = cartas.stream().mapToInt(CartaUnidad::getPuntos).sum();
        return puntaje;

    }

    public void agregarCarta(CartaUnidad carta) {
        if (carta.esValida(obtenerNombre())) {
            cartas.add(carta);
        }
    }
}
