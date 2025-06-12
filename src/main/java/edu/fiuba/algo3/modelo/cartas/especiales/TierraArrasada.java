package edu.fiuba.algo3.modelo.cartas.especiales;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Modificador;
import edu.fiuba.algo3.modelo.principal.Contexto;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;

import java.util.List;

public class TierraArrasada implements Carta, Modificador {

    public TierraArrasada() {

    }

    public boolean esEspecial(){ return true;}
    @Override
    public String mostrarCarta(){
        return "TierraArrasada";
    }

    @Override
    public String mostrarModificadores() {
        return "TierraArrasada" ;
    }

    @Override
    public void modificar(Contexto contextoModificador) throws TipoDeSeccionInvalidaError{
        var tablero = contextoModificador.getTablero();
        tablero.removerCartasDeValorMaximo();
    }

}
