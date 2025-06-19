package edu.fiuba.algo3.modelo.modificadores;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.principal.Contexto;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.tablero.NoSePuedeEliminarClimaSiNoHayClima;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;

import java.util.List;

public class SumaValorBase implements Modificador{
    private final Modificador modificador;
    private String dondeSeJugo;


    public SumaValorBase(Modificador modificador){
        this.modificador = modificador;
        this.dondeSeJugo = "";
    }

    @Override
    public String mostrarModificadores() {
        return modificador.mostrarModificadores() + " Base" ;
    }

    @Override
    public void modificar(Contexto contextoModificador) throws TipoDeSeccionInvalidaError {

        try {
            modificador.modificar(contextoModificador);
        } catch (NoSePuedeEliminarClimaSiNoHayClima | TipoDeSeccionInvalidaError ignored) {
        }

        String seccionClave = contextoModificador.getSeccion() + String.valueOf(contextoModificador.getJugador());
        this.dondeSeJugo = seccionClave;
        Seccion seccion = contextoModificador.getTablero().seccion(seccionClave);

        List<CartaUnidad> cartas = seccion.getCartasActuales();

        for(CartaUnidad carta : cartas){
            carta.sumaValor(1);
        }
    }

    @Override
    public void retrotraerContexto(Contexto contexto) {

        try {

            List<CartaUnidad> cartas = contexto.getTablero().getCartasSeccion(this.dondeSeJugo);

            for (CartaUnidad carta : cartas) {
                carta.sumaValor(-1);
            }
        } catch (TipoDeSeccionInvalidaError ignored){

        }

        modificador.retrotraerContexto(contexto);

    }

}
