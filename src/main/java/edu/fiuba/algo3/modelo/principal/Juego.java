package edu.fiuba.algo3.modelo.principal;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Juego {
    private List<Jugador> jugadores;
    private AdministradorDeTurno administradorTurno;
    private Tablero tablero;


    //FASE INICIAL
    public Juego(String nombreJugador1, String nombreJugador2, Mazo mazoDelJugador1, Mazo mazoDelJugador2) throws UnoDeLosMazosNoCumpleRequitos, TipoDeSeccionInvalidaError {

        if (mazoDelJugador1.cartasRestantes() < 21 || mazoDelJugador2.cartasRestantes() < 21) {
            throw new UnoDeLosMazosNoCumpleRequitos();
        }

        this.tablero = new Tablero();

        this.jugadores = new ArrayList<>();
        jugadores.add(new Jugador(nombreJugador1, mazoDelJugador1));
        jugadores.add(new Jugador(nombreJugador2, mazoDelJugador2));

        this.administradorTurno = new AdministradorDeTurno(jugadores);

    }

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


    public void repartirCartasAlJugador(int jugador) throws TipoDeSeccionInvalidaError {
        int minCartasAMano = 10;
        Jugador jugadorElegido = jugadores.get(jugador);
        try {
            jugadorElegido.agregarCartasAMano(minCartasAMano);
        } catch (NoSePuedeCumplirSolcitudDeCartas e) {
            throw new RuntimeException(e);
        }
    }

    public void darMano(int jugadorID, int cantidadDeCartas) throws TipoDeSeccionInvalidaError, NoSePuedeCumplirSolcitudDeCartas {
        jugadores.get(jugadorID).agregarCartasAMano(cantidadDeCartas);
    }

    public void descartarCartasDeMano(int jugadorID, List<Carta> cartasQueSeQuierenDescartar) {
        jugadores.get(jugadorID).descartarAlMazo(cartasQueSeQuierenDescartar);
    }


    //FASE DE JUEGO
    public void jugarCarta(Carta carta, Seccion seccion) throws TipoDeSeccionInvalidaError {
        if (carta.esEspecial()){
            Contexto contexto = new Contexto(this.tablero, tablero.obtenerSeccion(seccion), new CartaUnidad(), administradorTurno.jugadorActual());
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

    public boolean pasarTurno() {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("¿quieres seguir jugando? S o N: ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "S":
                    return true;
                case "N":
                    return false;
                default:
                    System.out.println("Opción inválida. Intentá de nuevo.\n");
            }
        }
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
        Jugador jugador1 = jugadores.get(1);
        Jugador jugador2 = jugadores.get(2);

        return (jugador1.cartasRestantesEnSeccion("Mano") == 10 && jugador2.cartasRestantesEnSeccion("Mano") == 10);
    }

    public void finalizarRonda() {
        administradorTurno.finalizarRonda(tablero);
    }
}