package edu.fiuba.algo3.modelo.cartas;

import edu.fiuba.algo3.modelo.modificadores.Modificador;
import edu.fiuba.algo3.modelo.secciones.Seccion;

import java.util.List;

public class CartaUnidad extends Carta {

    private final List<String> secciones;
    private final int puntos;
    private List<Modificador> modificadores;
    // private final boolean tieneModificadores;

    public CartaUnidad(String nombre, List<String> secciones, int puntos) {
        super(nombre);
        this.puntos = puntos;
        this.secciones = secciones;
        // this.tieneModificadores = tieneModificadores;
    }

    public List<String> getSecciones() {
        return secciones;
    }

    public int getPuntos() {
        return this.puntos;
    }
    public void agregarModificador(Modificador modificador) {
        this.modificadores.add(modificador);
    }

//    public boolean tieneModificadores() {
//        return tieneModificadores;
//    }

    public boolean esValida(String nombreSeccion) {
        return secciones.contains(nombreSeccion);
    }
}