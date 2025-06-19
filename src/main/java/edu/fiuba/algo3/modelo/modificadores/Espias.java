package edu.fiuba.algo3.modelo.modificadores;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.principal.Contexto;
import edu.fiuba.algo3.modelo.Errores.*;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.modelo.principal.Jugador;
import java.util.ArrayList;

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
    public void modificar(Contexto contextoModificador) throws TipoDeSeccionInvalidaError, NoSePuedeEliminarClimaSiNoHayClima, NoSePuedeCumplirSolicitudDeCartas {
        modificador.modificar(contextoModificador);
        var tablero = contextoModificador.getTablero();
        var jugadorActual = contextoModificador.getJugadorClase();

        // Buscar el oponente
        Jugador jugadorOponente = null;
        for (Jugador j : tablero.getSeccionesPorJugador().keySet()) {
            if (!j.equals(jugadorActual)) {
                jugadorOponente = j;
                break;
            }
        }

        var seccionNombre = contextoModificador.getSeccion();
        // Buscar la sección propia y la del oponente por nombre
        Seccion seccionPropia = null;
        for (Seccion s : tablero.mostrarTableroParaJugador(jugadorActual)) {
            if (s.getNombre().equals(seccionNombre)) {
                seccionPropia = s;
                break;
            }
        }
        Seccion seccionContraria = null;
        for (Seccion s : tablero.mostrarTableroParaJugador(jugadorOponente)) {
            if (s.getNombre().equals(seccionNombre)) {
                seccionContraria = s;
                break;
            }
        }
        if (seccionPropia == null || seccionContraria == null) throw new TipoDeSeccionInvalidaError();
        CartaUnidad cartaAgregar = contextoModificador.getCarta();
        seccionPropia.removerCarta(cartaAgregar);
        seccionContraria.agregarCarta(cartaAgregar);
        jugadorActual.agregarCartasAMano(2);
    }

    @Override
    public void retrotraerContexto(Contexto contexto){
        modificador.retrotraerContexto(contexto);
    }

}