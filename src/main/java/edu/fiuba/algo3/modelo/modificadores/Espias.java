package edu.fiuba.algo3.modelo.modificadores;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.principal.Contexto;
import edu.fiuba.algo3.modelo.principal.NoSePuedeCumplirSolicitudDeCartas;
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
    public void modificar(Contexto contextoModificador) throws TipoDeSeccionInvalidaError, NoSePuedeEliminarClimaSiNoHayClima {
        modificador.modificar(contextoModificador);

        Tablero tablero = contextoModificador.getTablero();
        Seccion seccion = contextoModificador.getSeccion();

        // Mueve la carta a la seccion contraria
        CartaUnidad cartaEspia = (CartaUnidad) contextoModificador.getCarta();
        tablero.removerCarta(seccion, cartaEspia);
        tablero.agregarCarta(tablero.seccionContraria(seccion), cartaEspia);

        // El jugador que recibe la carta espía roba 2 cartas
        int rivalId = 1 - seccion.getJugadorId();
        try {
            contextoModificador.getJugadores().get(rivalId).agregarCartasAMano(2);
        } catch (NoSePuedeCumplirSolicitudDeCartas e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void retrotraerContexto(Contexto contexto){
        modificador.retrotraerContexto(contexto);
    }

}