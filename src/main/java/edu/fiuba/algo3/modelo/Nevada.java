package edu.fiuba.algo3.modelo;

public class Nevada implements CartaEspecialClimatica, Carta{

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
