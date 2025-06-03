package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class CartaUnidad extends Carta {

    ArrayList<String> secciones;
    int valor;
    Modificador modificador;

    public CartaUnidad(ArrayList<String> secciones, int valor, Modificador modificador) {
        super(secciones, valor);
        this.secciones = secciones;
        this.valor = valor;
        this.modificador = modificador;
    }

    public CartaUnidad(ArrayList<String> secciones, int valor) {
        super(secciones, valor);
        this.secciones = secciones;
        this.valor = valor;
        this.modificador = new Base();
    }

    // Constructor sin argumentos para tests
    public CartaUnidad() {
        super(new ArrayList<>(), 0);
        this.secciones = new ArrayList<>();
        this.valor = 0;
        this.modificador = new Base();
    }

    public void aplicarModificador(Modificador modificador) {

    }

    public int getPuntaje() {
        return this.valor;
    }

    public ArrayList<String> puedeColocarse() {
        return this.secciones;
    }

    public String mostrarCarta(){

        return (nombre + modificador.mostrarModificadores());

    }

    public void modificarValor(int nuevoValor){
        this.valor = nuevoValor;
    }

    public int ValorActual(){
        return this.valor;
    }
}
