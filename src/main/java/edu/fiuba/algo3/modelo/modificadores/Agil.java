package edu.fiuba.algo3.modelo.modificadores;

import edu.fiuba.algo3.modelo.principal.Contexto;
import edu.fiuba.algo3.modelo.secciones.tablero.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.tablero.NoSePuedeEliminarClimaSiNoHayClima;

public class Agil implements Modificador {

    private final Modificador modificador;

    //Supuesto: se puede jugar donde quiera el jugador, se quita la restriccion de la carta base.
    public void prepararContexto(Contexto contextoModificador) {
        modificador.prepararContexto(contextoModificador);
        contextoModificador.getCarta().agregarSeccion(contextoModificador.getSeccion().getClave());
    }

    public Agil(Modificador modificador){
        this.modificador = modificador;
    }

    @Override
    public String mostrarModificadores() {
        return modificador.mostrarModificadores() + " Agil" ;
    }

    @Override
    public void modificar(Contexto contextoModificador) {
        try {
            modificador.modificar(contextoModificador);
        } catch (NoSePuedeEliminarClimaSiNoHayClima | TipoDeSeccionInvalidaError ignored) {
        }
    }

    @Override
    public void retrotraerContexto(Contexto contexto){
        contexto.getCarta().quitarUltimaSeccion();
        modificador.retrotraerContexto(contexto);
    }

}

