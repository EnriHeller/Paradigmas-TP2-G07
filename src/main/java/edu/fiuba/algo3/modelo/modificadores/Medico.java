package edu.fiuba.algo3.modelo.modificadores;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.principal.Contexto;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.jugador.SeccionesJugador;
import edu.fiuba.algo3.modelo.secciones.tablero.NoSePuedeEliminarClimaSiNoHayClima;

public class Medico implements Modificador {

    private Modificador modificador;

    public Medico(Modificador modificador) {
        this.modificador = modificador;
    }

    @Override
    public String mostrarModificadores() {
        return modificador.mostrarModificadores() + " Medico";
    }

    @Override
    public void modificar(Contexto contextoModificador) {

        try {
            modificador.modificar(contextoModificador);
        } catch (NoSePuedeEliminarClimaSiNoHayClima | TipoDeSeccionInvalidaError ignored) {
        }

        Jugador jugador = contextoModificador.getJugadorClase();
        if (jugador.cartasRestantesEnSeccion("Descarte") == 0) throw new PilaDescarteNula();

        CartaUnidad cartaDescartada = jugador.removerUltimaCartaDeDescarte();

        jugador.agregarCartaAMano(cartaDescartada);
        jugador.jugarCarta(cartaDescartada);
    }

    @Override
    public void retrotraerContexto(Contexto contexto){
        modificador.retrotraerContexto(contexto);
    }


}
