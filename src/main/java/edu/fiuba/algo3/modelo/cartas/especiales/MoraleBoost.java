package edu.fiuba.algo3.modelo.cartas.especiales;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Modificador;
import edu.fiuba.algo3.modelo.principal.Contexto;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;

import java.util.List;

public class MoraleBoost implements Carta, Modificador {

    public MoraleBoost() {

    }
    public boolean esEspecial(){ return true;}
    @Override
    public String mostrarCarta(){
        return "MoraleBoost";
    }

    @Override
    public void aplicarModificador(Contexto contexto) {
        try {
            modificar(contexto);
        } catch (TipoDeSeccionInvalidaError e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String mostrarModificadores() {
        return "TierraArrasada" ;
    }

    @Override
    public void modificar(Contexto contextoModificador) throws TipoDeSeccionInvalidaError{
        Seccion seccion = contextoModificador.getSeccion();

        List<CartaUnidad> cartasActuales = seccion.getCartasActuales();

        for (CartaUnidad carta : cartasActuales) {
            carta.multiplicarValor(2);
        }
    }
}
