package edu.fiuba.algo3.modelo.modificadores;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.principal.Contexto;
import edu.fiuba.algo3.modelo.secciones.tablero.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.tablero.NoSePuedeEliminarClimaSiNoHayClima;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;

import java.util.List;

public class SumaValorBase implements Modificador{
    private final Modificador modificador;
    private Seccion dondeSeJugo;


    public SumaValorBase(Modificador modificador){
        this.modificador = modificador;
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

        Seccion seccion = contextoModificador.getSeccion();
        this.dondeSeJugo = seccion;

        List<CartaUnidad> cartas = seccion.getCartas();

        for(CartaUnidad carta : cartas){
            carta.sumaValor(1);
        }
    }

    @Override
    public void retrotraerContexto(Contexto contexto) {

        List<CartaUnidad> cartas = dondeSeJugo.getCartas();
        for (CartaUnidad carta : cartas) {
            carta.volverValorBase();
        }

        modificador.retrotraerContexto(contexto);

    }

}
