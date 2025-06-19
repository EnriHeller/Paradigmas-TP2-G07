package edu.fiuba.algo3.modelo.secciones;

import edu.fiuba.algo3.modelo.cartas.CartaUnidad;

public class CuerpoACuerpo extends Seccion{

    public CuerpoACuerpo() {
        super();
    }
    @Override
    public String obtenerNombre() {
        return "Cuerpo a Cuerpo";
    }
    @Override
    public int calcularPuntos() {
        // Modificar la logica cuando haya cartas especiales
        int puntaje = cartas.stream().mapToInt(CartaUnidad::getPuntos).sum();
        return puntaje;

    }
}
