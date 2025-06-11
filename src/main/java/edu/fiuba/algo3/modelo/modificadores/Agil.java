package edu.fiuba.algo3.modelo.modificadores;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.principal.Contexto;

public class Agil implements Modificador {

    private final Modificador modificador;


    public Agil(Modificador modificador){
        this.modificador = modificador;
    }

    @Override
    public String mostrarModificadores() {
        return modificador.mostrarModificadores() + " Agil" ;
    }

    //Supuesto: se puede jugar donde quiera el jugador, se quita la restriccion de la carta base.
    @Override
    public void modificar(Contexto contextoModificador) {
        CartaUnidad carta = contextoModificador.getCarta();
        carta.agregarSeccion(contextoModificador.getSeccion());
    }


}

