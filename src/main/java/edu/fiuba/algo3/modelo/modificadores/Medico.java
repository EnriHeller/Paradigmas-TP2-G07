package edu.fiuba.algo3.modelo.modificadores;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.principal.Contexto;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.secciones.tablero.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.tablero.NoSePuedeEliminarClimaSiNoHayClima;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;

import java.util.List;

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

        Jugador jugador = contextoModificador.getJugador();
        if (jugador.cartasRestantesEnSeccion("Descarte") == 0) throw new PilaDescarteNula();

        CartaUnidad cartaDescartada = (CartaUnidad) jugador.removerUltimaCartaDeDescarte();

        List<String> tipo = cartaDescartada.getSecciones(); // Por ejemplo: "Rango", "Asedio", etc.
        int jugadorID = jugador.ordenDeJuego();         // El ID del jugador (0 o 1)

        try {
            Seccion destino = new Seccion(tipo.get(0), jugadorID);
            contextoModificador.getTablero().agregarCarta(destino, cartaDescartada);
        } catch (TipoDeSeccionInvalidaError e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void retrotraerContexto(Contexto contexto){
        modificador.retrotraerContexto(contexto);
    }


}
