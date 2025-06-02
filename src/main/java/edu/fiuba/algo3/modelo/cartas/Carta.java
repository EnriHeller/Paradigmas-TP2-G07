package edu.fiuba.algo3.modelo.cartas;

import edu.fiuba.algo3.modelo.Modificador;

import java.util.ArrayList;

public abstract class Carta {

    ArrayList<String> secciones;
    int valor;
    String nombre;
    Modificador modificador;

    public Carta(ArrayList<String> secciones, int valor){
        this.secciones = secciones;
        this.valor = valor;
    }

    public String mostrarCarta(){

        return (nombre + modificador.mostrarModificadores());

    }

    public int getValor(){
        return this.valor;
    }

    //public List<String> puedeColocarse();
    // public String visualizarCartaComoString()
}
