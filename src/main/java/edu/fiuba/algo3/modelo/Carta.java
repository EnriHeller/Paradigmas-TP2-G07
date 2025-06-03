package edu.fiuba.algo3.modelo;

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

    public abstract String mostrarCarta();

    //public List<String> puedeColocarse();
    // public String visualizarCartaComoString()
}
