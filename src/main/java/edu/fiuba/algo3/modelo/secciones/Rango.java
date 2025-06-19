package edu.fiuba.algo3.modelo.secciones;

public class Rango extends Seccion{

    public Rango () {
        super();
    }

    @Override
    public String obtenerNombre() {
        return "Rango";
    }
    @Override
    public int calcularPuntos() {
        // Modificar la logica cuando haya cartas especiales
        int puntaje = cartas.stream().mapToInt(carta -> carta.getPuntos()).sum();
        return puntaje;
    }
}
