package edu.fiuba.algo3.modelo.cartas.especiales;

import edu.fiuba.algo3.modelo.cartas.Carta;

public class CartaNevada implements CartaClimatica, Carta {

    public CartaNevada(){

    }

    @Override
    public boolean esEspecial() {
        return true;
    }

    @Override
    public String mostrarCarta(){
        return "Nevada";
    }

    @Override
    public Clima CrearClima() {
        return (new ClimaNevado());
    }
}
