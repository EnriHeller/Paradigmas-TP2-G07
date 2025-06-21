package edu.fiuba.algo3.modelo.modificadores;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.principal.Contexto;
import edu.fiuba.algo3.modelo.principal.NoSePuedeCumplirSolcitudDeCartas;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.tablero.NoSePuedeEliminarClimaSiNoHayClima;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;

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
    public void modificar(Contexto contextoModificador) throws TipoDeSeccionInvalidaError {

        try {
            modificador.modificar(contextoModificador);
        } catch (NoSePuedeEliminarClimaSiNoHayClima | TipoDeSeccionInvalidaError ignored) {
        }

        Tablero tablero = contextoModificador.getTablero();
        Seccion seccion = contextoModificador.getSeccion();

        CartaUnidad cartaAgregar = tablero.removerCarta(seccion, contextoModificador.getCarta());
        tablero.agregarCarta(tablero.seccionContraria(seccion), cartaAgregar);

        try{
            contextoModificador.getJugadorClase().agregarCartasAMano(2);
        }catch(TipoDeSeccionInvalidaError | NoSePuedeCumplirSolcitudDeCartas e){

        }


    }

    @Override
    public void retrotraerContexto(Contexto contexto){
        modificador.retrotraerContexto(contexto);
    }

}