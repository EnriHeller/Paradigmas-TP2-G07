package edu.fiuba.algo3.modelo;

public class Nevada implements CartaEspecialClimatica{

    public Nevada(){

    }

    @Override
    public Clima CrearClima() {
        return (new ClimaNevado());
    }
}
