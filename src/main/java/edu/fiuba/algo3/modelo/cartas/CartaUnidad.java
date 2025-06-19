package edu.fiuba.algo3.modelo.cartas;

import edu.fiuba.algo3.modelo.modificadores.Modificador;

import java.util.ArrayList;
import java.util.List;

public class CartaUnidad implements Carta {
    private final String nombre;
    private final List<String> secciones;
    private final int puntos;
    private final List<Modificador> modificadores;

    public CartaUnidad(String nombre, List<String> secciones, int puntos) {
        this.nombre = nombre;
        this.puntos = puntos;
        this.secciones = secciones;
        this.modificadores = new ArrayList<>();
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

    @Override
    public boolean esEspecial() { return false; }

    @Override
    public String getNombre() { return nombre; }

    public boolean esValida(String nombreSeccion) {
        return secciones.contains(nombreSeccion);
    }
}