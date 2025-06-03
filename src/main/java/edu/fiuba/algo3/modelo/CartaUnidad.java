package edu.fiuba.algo3.modelo;

import java.util.List;
import java.util.ArrayList;
import java.util.List;

public class CartaUnidad extends Carta {

    List<String> secciones;
    private int valorBase;
    private int valorActual;
    Modificador modificador;

    public CartaUnidad(List<String> secciones, int valor, Modificador modificador) {
        super(secciones, valor);
        this.secciones = secciones;
        this.valorActual = valor;
        this.valorBase = valor;
        this.modificador = modificador;
        modificador.modificarComportamiento(this);
    }

    public CartaUnidad(List<String> secciones, int valor) {
        super(secciones, valor);
        this.secciones = secciones;
        this.valorActual = valor;
        this.valorBase = valor;
        this.modificador = new Base();
    }

    // Constructor sin argumentos para tests
    public CartaUnidad() {
        super(new ArrayList<>(), 0);
        this.secciones = new ArrayList<>();
        this.valorActual = 0;
        this.valorBase = 0;
        this.modificador = new Base();
    }

    public void aplicarModificador(List<CartaUnidad> cartas) {
        this.modificador.modificarComportamientoSeccion(cartas);
    }

    public int getPuntajeBase() {
        return this.valorBase;
    }

    public List<String> puedeColocarse() {
        return this.secciones;
    }

    public String mostrarCarta(){

        return (nombre + modificador.mostrarModificadores());

    }

    public void modificarValor(int nuevoValor){
        this.valor = nuevoValor;
    }


    @Override
    public boolean esEspecial(){
        return false;
    }

    public int ValorActual(){
        return this.valor;
    }
}
