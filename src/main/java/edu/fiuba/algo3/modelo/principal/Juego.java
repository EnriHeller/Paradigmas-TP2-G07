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

//    public void repartirCartasAlJugador(int jugador) throws TipoDeSeccionInvalidaError {
//        int minCartasAMano = 10;
//        Jugador jugadorElegido = jugadores.get(jugador);
//        try {
//            jugadorElegido.agregarCartasAMano(minCartasAMano);
//        } catch (NoSePuedeCumplirSolicitudDeCartas e) {
//            throw new RuntimeException(e);
//        }
//    }

//    public void descartarCartasDeMano(int jugadorID, List<Carta> cartasQueSeQuierenDescartar) {
//        jugadores.get(jugadorID).descartarAlMazo(cartasQueSeQuierenDescartar);
//    }

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

    public int cartasRestantesJugador(String seccionJugador,int jugador_i) throws TipoDeSeccionInvalidaError {
        Jugador jugador = jugadores.get(jugador_i);
        return jugador.cartasRestantesEnSeccion(seccionJugador);
    }

    public String mostrarGanador(){
        return administradorTurno.mostrarGanador();
    }

    public boolean juegoTerminado(){
        return administradorTurno.juegoTerminado();
    }

    public boolean seLogroRepartirCartasDelMazoALosJugadores(){
        Jugador jugador1 = jugadores.get(0);
        Jugador jugador2 = jugadores.get(1);

        return (jugador1.cartasRestantesEnSeccion("Mano") == 10 && jugador2.cartasRestantesEnSeccion("Mano") == 10);
    }

    public void finalizarRonda() {
        administradorTurno.finalizarRonda(tablero);
    }

    public Jugador getJugador2() {
        return jugadores.get(1);
    }

    public Jugador getJugador1() {
        return jugadores.get(0);
    }

    public Tablero getTablero() {
        return tablero;
    }

    public Carta getUltimaCartaDeLaPilaDeDescarte(){
        Jugador jugadorActualizado = administradorTurno.jugadorActual();
        return jugadorActualizado.getCartaEnDescarte();
    }

    public int cartasEnMazoActual() {
        return administradorTurno.jugadorActual().cartasRestantesEnSeccion("Mazo");
    }

    public List<Map<String, Integer>> getPuntosPorRonda() {
        return administradorTurno.getPuntosPorRonda();
    }


}