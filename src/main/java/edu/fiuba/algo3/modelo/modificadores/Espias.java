package edu.fiuba.algo3.modelo.modificadores;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.principal.Contexto;
import edu.fiuba.algo3.modelo.principal.NoSePuedeCumplirSolcitudDeCartas;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;

public class Espias implements Modificador {

    private final Modificador modificador;


    public Espias(Modificador modificador){
        this.modificador = modificador;
    }

    @Override
    public String mostrarModificadores() {
        return modificador.mostrarModificadores() + " Espias" ;
    }

    @Override
    public void modificar(Contexto contextoModificador) {
        String seccion = contextoModificador.getSeccion();

        Seccion seccionContraria = contextoModificador.getTablero().getSeccion(seccion + String.valueOf(contextoModificador.getJugador() == 0 ? 1 : 0));

        CartaUnidad cartaAgregar = contextoModificador.getTablero().removerCarta(seccion + String.valueOf(contextoModificador.getJugador()), contextoModificador.getCarta());
        seccionContraria.agregarCarta(cartaAgregar);

        try{
            contextoModificador.getJugadorClase().agregarCartasAMano(2);
        }catch(TipoDeSeccionInvalidaError | NoSePuedeCumplirSolcitudDeCartas e){

        }


    }


}