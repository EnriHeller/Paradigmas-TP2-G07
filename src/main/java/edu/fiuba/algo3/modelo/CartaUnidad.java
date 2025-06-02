package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class CartaUnidad extends Carta {

    ArrayList<String> secciones;
    int valor;
    ArrayList<Modificador> modificadores;

    public CartaUnidad(ArrayList<String> secciones, int valor) {
        super(secciones, valor);
        this.secciones = secciones;
        this.valor = valor;
        this.modificadores = new ArrayList<>();
    }

    // Constructor sin argumentos para tests
    public CartaUnidad() {
        super(new ArrayList<>(), 0);
        this.secciones = new ArrayList<>();
        this.valor = 0;
        this.modificadores = new ArrayList<>();
    }

    public void aplicarModificador(Modificador modificador) {
        this.modificadores.add(modificador);
    }

    public int getPuntaje() {
        return this.valor;
    }

    public ArrayList<String> puedeColocarse() {
        return this.secciones;
    }
}
