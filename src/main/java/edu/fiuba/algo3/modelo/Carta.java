package edu.fiuba.algo3.modelo;

import java.util.List;

public abstract class Carta {

    List<String> secciones;
    int valor;
    String nombre;
    Modificador modificador;

    public Carta(List<String> secciones, int valor){
        this.secciones = secciones;
        this.valor = valor;
    }

    public abstract boolean esEspecial();

    public abstract String mostrarCarta();

    //public List<String> puedeColocarse();
    // public String visualizarCartaComoString()
}
