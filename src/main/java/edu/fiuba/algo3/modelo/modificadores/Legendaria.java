package edu.fiuba.algo3.modelo.modificadores;

import edu.fiuba.algo3.modelo.cartas.unidades.NoEsLaMismaUnidad;
import edu.fiuba.algo3.modelo.principal.Contexto;

public class Legendaria implements Modificador {

    public Legendaria(){
    }

    @Override
    public String mostrarModificadores() {
        return "Legendaria" ;
    }

    @Override
    public void modificar(Contexto contextoModificador) {
        contextoModificador.getCarta().multiplicarValor(2);
    }


}