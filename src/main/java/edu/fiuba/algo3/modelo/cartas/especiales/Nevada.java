package edu.fiuba.algo3.modelo.cartas.especiales;

import edu.fiuba.algo3.modelo.cartas.Carta;

public class Nevada implements CartaEspecialClimatica, Carta {

    public Nevada(){

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
