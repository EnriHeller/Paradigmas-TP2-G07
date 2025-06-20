package edu.fiuba.algo3.modelo.principal;

import edu.fiuba.algo3.modelo.principal.Jugador;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;

public class AdministradorDeTurno {
    private final List<Jugador> jugadores;
    private int indiceActual;

    public AdministradorDeTurno(List<Jugador> jugadores) {
        this.jugadores = jugadores;
        
    }

    public void tirarMoneda(){
        this.indiceActual = new Random().nextInt(2);
    }

    public Jugador jugadorActual() {
        return jugadores.get(indiceActual);
    }

    public void siguiente() {
        indiceActual = 1 - indiceActual;
    }

    public boolean ambosPasaron() {
        return jugadores.get(0).haPasado() && jugadores.get(1).haPasado();
    }

    public void reiniciar() {
        jugadores.get(0).reiniciarPaso();
        jugadores.get(1).reiniciarPaso();
        indiceActual = new Random().nextInt(2);
    }

    public void finalizarRonda(Tablero tablero){
        // Remover cartas de ambos jugadores correctamente
        for (int i = 0; i < jugadores.size(); i++) {
            Jugador jugador = jugadores.get(i);
            List<Seccion> secciones = tablero.mostrarTableroParaJugador(jugador);
            for (Seccion seccion : secciones) {
                List<CartaUnidad> cartasRemovidas = seccion.removerCartas();
                for (CartaUnidad carta : cartasRemovidas) {
                    Contexto contexto = new Contexto(tablero, seccion, carta, jugador);
                    carta.retrotraerModificacion(contexto);
                    jugador.DescartarCarta(carta);
                }
            }
        }
    }
}

