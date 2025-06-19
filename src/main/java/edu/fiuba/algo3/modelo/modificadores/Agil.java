package edu.fiuba.algo3.modelo.modificadores;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.principal.Contexto;
import edu.fiuba.algo3.modelo.Errores.*;

public class Agil implements Modificador {

    private final Modificador modificador;

    //Supuesto: se puede jugar donde quiera el jugador, se quita la restriccion de la carta base.
    public void prepararContexto(Contexto contextoModificador) {
        modificador.prepararContexto(contextoModificador);
        // Solo agrega la sección si aún no está presente
        if (!contextoModificador.getCarta().getSecciones().contains(contextoModificador.getSeccion())) {
            contextoModificador.getCarta().agregarSeccion(contextoModificador.getSeccion());
        }
    }

    public Agil(Modificador modificador){
        this.modificador = modificador;
    }

    @Override
    public String mostrarModificadores() {
        return modificador.mostrarModificadores() + " Agil" ;
    }

    @Override
    public void modificar(Contexto contextoModificador) throws TipoDeSeccionInvalidaError, NoSePuedeEliminarClimaSiNoHayClima, NoSePuedeCumplirSolicitudDeCartas {
        modificador.modificar(contextoModificador);
    }

    @Override
    public void retrotraerContexto(Contexto contexto){
        var secciones = contexto.getCarta().getSecciones();
        if (secciones.size() > 1) {
            secciones.remove(secciones.size() - 1);
        }
        modificador.retrotraerContexto(contexto);
    }

}

