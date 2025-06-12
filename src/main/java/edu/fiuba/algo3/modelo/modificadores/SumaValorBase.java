package edu.fiuba.algo3.modelo.modificadores;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.principal.Contexto;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;

import java.util.List;

public class SumaValorBase implements Modificador{
    private final Modificador modificador;


    public SumaValorBase(Modificador modificador){
        this.modificador = modificador;
    }

    @Override
    public String mostrarModificadores() {
        return modificador.mostrarModificadores() + " Base" ;
    }

    @Override
    public void modificar(Contexto contextoModificador) throws TipoDeSeccionInvalidaError {
        Seccion seccion = contextoModificador.getTablero().seccion(contextoModificador.getSeccion());

        List<CartaUnidad> cartas = seccion.getCartasActuales();

        for(CartaUnidad carta : cartas){
            carta.sumaValor(1);
        }
    }
}
