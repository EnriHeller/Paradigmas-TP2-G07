package edu.fiuba.algo3.modelo.cartas.unidades;

import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.modificadores.Modificador;
import edu.fiuba.algo3.modelo.cartas.Carta;

import java.util.List;
import java.util.ArrayList;

public class CartaUnidad implements Carta {

    String nombre;
    List<String> secciones;
    private final int valorBase;
    private int valorActual;
    Modificador modificador;

    public CartaUnidad(String nombre,List<String> secciones, int valor, Modificador modificador) {
        this.nombre = nombre;
        this.secciones = secciones;
        this.valorActual = valor;
        this.valorBase = valor;
        this.modificador = modificador;
    }

    public CartaUnidad(String nombre, List<String> secciones, int valor) {
        this.nombre = nombre;
        this.secciones = secciones;
        this.valorActual = valor;
        this.valorBase = valor;
        this.modificador = new Base();
    }

    // Constructor sin argumentos para tests
    public CartaUnidad() {
        this.nombre = "";
        this.secciones = new ArrayList<>();
        this.valorActual = 0;
        this.valorBase = 0;
        this.modificador = new Base();
    }

    public void aplicarModificador(List<CartaUnidad> cartas) {
        this.modificador.modificar();
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
        this.valorActual = nuevoValor;
    }

    public void modificarValor(String posibleCartaMismoModificador, int n) throws NoEsLaMismaUnidad {
        if (!posibleCartaMismoModificador.equals(this.nombre)){
            throw new NoEsLaMismaUnidad("No es la misma Unidad");
        }
        if (n == 1){
            this.valorActual = 1;
        } else{ this.valorActual = n * this.valorBase; }

    }


    @Override
    public boolean esEspecial(){
        return false;
    }

    public int ValorActual(){
        return this.valorActual;
    }

    public boolean coincideSeccion(String seccion){
        return this.secciones.contains(seccion);
    }
}
