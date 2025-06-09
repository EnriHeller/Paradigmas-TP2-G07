package edu.fiuba.algo3.modelo.cartas.especiales;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Modificador;
import edu.fiuba.algo3.modelo.principal.Contexto;

import java.util.List;

public class TierraArrasada implements Carta, Modificador {

    private String nombre;
    private Modificador modificador;

    public TierraArrasada(Modificador modificador, String nombre) {
        this.modificador = modificador;
        this.nombre = nombre;
    }

    public boolean esEspecial(){ return true;}
    @Override
    public String mostrarCarta(){
        return (nombre + modificador.mostrarModificadores());
    }

    @Override
    public String mostrarModificadores() {
        return modificador.mostrarModificadores() + " TierraArrasada" ;
    }

    @Override
    public void modificar(Contexto contextoModificador) {
    }

}
