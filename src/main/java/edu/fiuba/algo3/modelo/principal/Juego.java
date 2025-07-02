package edu.fiuba.algo3.modelo.principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;
import edu.fiuba.algo3.modelo.secciones.tablero.TipoDeSeccionInvalidaError;


public class Juego {
    private final List<Jugador> jugadores;
    private final AdministradorDeTurno administradorTurno;
    private final Tablero tablero;


    //FASE INICIAL
    public Juego(Jugador jugador1, Jugador jugador2) throws UnoDeLosMazosNoCumpleRequitos, TipoDeSeccionInvalidaError {

        if (jugador1.cartasRestantesEnSeccion("Mazo") < 21 || jugador2.cartasRestantesEnSeccion("Mazo") < 21) {
            throw new UnoDeLosMazosNoCumpleRequitos();
        }

        //Se instancia por primera vez al tablero. (A chequear si es necesario)
        this.tablero = new Tablero();

        this.jugadores = new ArrayList<>();
        jugadores.add(jugador1);
        jugadores.add(jugador2);

        this.administradorTurno = new AdministradorDeTurno(jugadores);

    }

    public void definirQuienEmpieza(int indice){
        administradorTurno.indiceActual(indice);
    }

    public void siguienteJugador(){
        administradorTurno.siguiente();
    }

    //FASE DE PREPARACIÓN

    public void tirarMoneda(){
        administradorTurno.tirarMoneda();
    }

    public int actual(){
        return administradorTurno.actual();
    }
    
    public Jugador jugadorActual() {
        return administradorTurno.jugadorActual();
    }

    public void removerCartaEnMano(Carta carta){
        Jugador jugadorActual = administradorTurno.jugadorActual();
        jugadorActual.removerCartaEnMano(carta);
    }

    public void darMano(int jugadorID, int cantidadDeCartas) throws TipoDeSeccionInvalidaError, NoSePuedeCumplirSolicitudDeCartas {
        jugadores.get(jugadorID).agregarCartasAMano(cantidadDeCartas);
    }

    //FASE DE JUEGO
    public void jugarCarta(Carta carta, Seccion seccion) throws TipoDeSeccionInvalidaError {
        if (carta.esEspecial()){
            Contexto contexto = new Contexto(this.tablero, administradorTurno.jugadorActual());
            carta.aplicarModificador(contexto);
        } else{
            Contexto contexto = new Contexto(this.tablero, tablero.obtenerSeccion(seccion), (CartaUnidad) carta, administradorTurno.jugadorActual());
            CartaUnidad cartaUnidad = (CartaUnidad) carta;
            cartaUnidad.prepararContexto(contexto);
            tablero.agregarCarta(tablero.obtenerSeccion(seccion), cartaUnidad);
            cartaUnidad.aplicarModificador(contexto);
            tablero.afectarClimas();

            administradorTurno.actualizarRonda(((CartaUnidad) carta).ValorActual());
        }
    }

    public List<Carta> mostrarManoActual(){
        Jugador jugadorActual = administradorTurno.jugadorActual();
        return jugadorActual.cartasEnMano();
    }

    //VERIFICACIONES

    public int puntajeEnSeccion(Seccion seccion) throws TipoDeSeccionInvalidaError {
        return tablero.PuntajeSeccion(seccion);
    }

    public int cartasRestantesJugador(String seccionJugador,int jugador_i) {
        Jugador jugador = jugadores.get(jugador_i);
        return jugador.cartasRestantesEnSeccion(seccionJugador);
    }

    public String mostrarGanador(){
        return administradorTurno.mostrarGanador();
    }

    public boolean juegoTerminado(){
        return administradorTurno.juegoTerminado();
    }

    public void finalizarRonda() {
        administradorTurno.finalizarRonda(tablero);
    }

    public Tablero getTablero() {
        return tablero;
    }

    public List<Map<String, Integer>> getPuntosPorRonda() {
        return administradorTurno.getPuntosPorRonda();
    }

    public int getNumeroRondaActual() {
        // ciclos es el número de rondas finalizadas, la ronda actual es ciclos+1
        return administradorTurno.getNumeroRondaActual();
    }


}