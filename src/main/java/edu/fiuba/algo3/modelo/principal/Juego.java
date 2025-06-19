package edu.fiuba.algo3.modelo.principal;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Modificador;
import edu.fiuba.algo3.modelo.Errores.*;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.principal.AdministradorDeTurno;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Juego {
    private Ronda[] rondas;
    private List<Jugador> jugadores;
    private Tablero tablero;
    private AdministradorDeTurno administradorDeTurno;

    private static int minCartasEnMano = 10;
    private static int MaxDescarteInicial = 2;


    //FASE INICIAL
    public Juego(Jugador j1, Jugador j2) throws UnoDeLosMazosNoCumpleRequitos, TipoDeSeccionInvalidaError {
        this.jugadores = List.of(j1, j2);
        this.rondas = new Ronda[3];

        //Le pasamos j1 y j2 para identificar las secciones de cada jugador
        this.tablero = new Tablero(j1, j2);

        //Inicializamos tablero
        // TableroSingleton.reiniciarInstancia();
        // this.tablero = TableroSingleton.getInstancia();

        //Inicializamos administrador de turnos
        this.administradorDeTurno = new AdministradorDeTurno(jugadores);
    }

    //FASE PREPARACIÓN

    public void repartirManoInicial(Jugador jugador) throws TipoDeSeccionInvalidaError, NoSePuedeCumplirSolicitudDeCartas {
        jugador.agregarCartasAMano(minCartasEnMano);
    }


    public void descartarCartasIniciales(Jugador jugador, List<Carta> cartas) {
        if (cartas.size() > MaxDescarteInicial) {
            throw new CantidadMaximaDeDescarteAlcanzadaError("No se pueden descartar más de " + MaxDescarteInicial + " cartas en la fase inicial.");
        }
        for (Carta carta : cartas) {
            jugador.RemoverCartaDeMano(carta);
            jugador.DescartarCarta(carta);
        }
    }

    public void tirarMoneda() {
        this.administradorDeTurno.tirarMoneda();
    }

    //FASE DE JUEGO

    public void repartirNCartas(Jugador jugador, int n) throws TipoDeSeccionInvalidaError, NoSePuedeCumplirSolicitudDeCartas {
        jugador.agregarCartasAMano(n);
    }

    public List<Seccion> mostrarTableroActual() {
        Jugador jugadorActual = this.jugadorActual();
        return this.tablero.mostrarTableroParaJugador(jugadorActual);
    }

    public List<Carta> mostrarManoActual(){
        Jugador jugadorActual = this.jugadorActual();
        return jugadorActual.cartasEnMano();
    }

    private Jugador jugadorActual() {
        return this.administradorDeTurno.jugadorActual();
    }

    public void jugarCarta(Carta carta, Seccion seccion) throws TipoDeSeccionInvalidaError, CartaNoJugable {

        tablero.jugarCarta(carta, seccion);
    }

    // public void jugarRonda() throws TipoDeSeccionInvalidaError {
    //     while (pasarTurno()) {
    //         Carta cartaJugadaPorPrimero = eleccionDeCarta(jugadores.indexOf(this.jugadorQueInicia), "mano");
    //         boolean cartaJugable1 = false;
            
    //         while (!cartaJugable1) {
    //             SeccionElegida(); // Solo se ejecuta, no se guarda
    //             if (!cartaJugadaPorPrimero.esEspecial()) {
    //                 cartaJugable1 = true;
    //             } else {
    //                 cartaJugable1 = true;
    //             }
    //         }
    //         if(!cartaJugadaPorPrimero.esEspecial()) {
    //             rondas[ciclos].agregarPuntajeJugador(jugadores.indexOf(this.jugadorQueInicia),((CartaUnidad) cartaJugadaPorPrimero).ValorActual());
    //         }
    //     }

        // while (pasarTurno()) {
        //     int segundoJugadorIdx = (jugadores.indexOf(this.jugadorQueInicia) + 1) % 2;
        //     Carta cartaJugadaPorSegundo = eleccionDeCarta(segundoJugadorIdx, "mano");

        //     boolean cartaJugable2 = false;
        //     while (!cartaJugable2) {
        //         SeccionElegida(); // Solo se ejecuta, no se guarda
        //         if (!cartaJugadaPorSegundo.esEspecial()) {
        //             cartaJugable2 = true;
        //         } else {
        //             cartaJugable2 = true;
        //         }
        //     }
        //     if(!cartaJugadaPorSegundo.esEspecial()) {
        //         rondas[ciclos].agregarPuntajeJugador(segundoJugadorIdx,((CartaUnidad) cartaJugadaPorSegundo).ValorActual());
        //     }
        // }

        //ciclos++;
    //}

    // public void jugarCarta(int jugadorID, CartaUnidad carta, String dondeJugarla) {
    //     try {
    //             Jugador jugador = jugadores.get(jugadorID);
    //             // Se elimina SeccionesJugador, se usa solo Jugador y sus secciones concretas
    //             Contexto contexto = new Contexto(this.tablero, dondeJugarla, carta, jugadorID, jugador);
    //             carta.prepararContexto(contexto);
    //             tablero.agregarCarta(dondeJugarla + String.valueOf(jugadorID), carta);
    //             carta.aplicarModificador(contexto);
    //             if (this.rondas[this.ciclos] == null) {
    //                 this.rondas[this.ciclos] = new Ronda();
    //             }
    //             rondas[ciclos].agregarPuntajeJugador(jugadorID, carta.ValorActual());

    //     } catch (TipoDeSeccionInvalidaError e) {
    //         // No hacemos nada si hay una excepción
    //     }
    // }

    // public void aplicarEspecial(int jugadorID, Modificador cartaEspecial)  throws NoSePuedeEliminarClimaSiNoHayClima, TipoDeSeccionInvalidaError {
    //     CartaUnidad carta = new CartaUnidad();
    //     Jugador jugador = jugadores.get(jugadorID);
    //     Contexto contexto = new Contexto(this.tablero, "", carta, jugadorID, jugador);
    //     cartaEspecial.modificar(contexto);
    // }

    public boolean pasarTurno() {
        try (Scanner scanner = new Scanner(System.in)) {
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
    }

    //COMUNICACION

    // private Carta eleccionDeCarta(int jugador, String clave) throws TipoDeSeccionInvalidaError {
    //     // Tablero secciones = Tablero.getInstancia(); // No se usa
    //     try (Scanner scanner = new Scanner(System.in)) {
    //         boolean eleccionErronea = true;
    //         int opcion = -1;
    //         Jugador jugadorActual = jugadores.get(jugador);
    //         List<Carta> cantidadCartas = jugadorActual.cartasEnMano();
    //         while (eleccionErronea) {
    //             System.out.print("Ingrese el número de la carta que quiere elegir: ");
    //             try {
    //                 opcion = scanner.nextInt();
    //                 if (opcion >= 0 && opcion < cantidadCartas) {
    //                     eleccionErronea = false;
    //                 } else {
    //                     System.out.println("Opción inválida. Por favor ingrese un número entre 0 y " + (cantidadCartas-1) + ".");
    //                 }
    //             } catch (Exception e) {
    //                 System.out.println("Entrada inválida. Por favor ingrese un número válido.");
    //                 scanner.next();
    //             }
    //         }
    //         // Aquí se debería retornar la carta seleccionada de la mano del jugador
    //         // return jugadorActual.getMano().removerCartaPorIndice(opcion);
    //         return new CartaUnidad(); // Placeholder
    //     }
    // }

    public String SeccionElegida() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("¿En qué sección querés jugar?\n1 - Cuerpo a Cuerpo\n2 - Rango\n3 - Asedio\nElegí una opción (1, 2 o 3): ");
                String input = scanner.nextLine().trim();
                switch (input) {
                    case "1":
                        return "CuerpoACuerpo";
                    case "2":
                        return "Rango";
                    case "3":
                        return "Asedio";
                    default:
                        System.out.println("Opción inválida. Intentá de nuevo.\n");
                }
            }
        }
    }
    
    //VERIFICACIONES

    // public int puntajeEnSeccion(String nombreSeccion) throws TipoDeSeccionInvalidaError {
    //     return TableroSingleton.getInstancia().getPuntaje(nombreSeccion);
    // }

    public List<Carta> cartasEnManoJugador(int jugador_i) throws TipoDeSeccionInvalidaError {
        Jugador jugador = jugadores.get(jugador_i);
        return jugador.cartasEnMano();
    }

    public String mostrarGanador(){
        int contadorJ1 = 0;
        int contadorJ2 = 0;
        for (Ronda ronda : rondas) {
            String ganadorRonda = ronda.getGanadorRonda();
            if (ganadorRonda.equals("Jugador 1")) {
                contadorJ1++;
            } else if (ganadorRonda.equals("Jugador 2")) {
                contadorJ2++;
            }
        }
        return contadorJ1 > contadorJ2 ? "Jugador 1" : "Jugador 2";
    }

    public boolean juegoTerminado(){
        return false;
    }

    // public boolean seLogroRepartirCartasDelMazoALosJugadores(){
    //     Jugador jugador1 = jugadores.get(0);
    //     Jugador jugador2 = jugadores.get(1);
    //     return (jugador1.cartasEnMano().size() && jugador2.getMano().cartasRestantes() == 10);
    // }

    // public void gameLoop() {
    //     // Fase inicial: nombres y mazos ya seleccionados al crear Juego
    //     // Fase de preparación
    //     for (Jugador jugador : jugadores) {
    //         try {
    //             repartirManoInicial(jugador);
    //             // Aquí podrías pedir al usuario qué cartas descartar (máx MaxDescarteInicial)
    //             // List<Carta> descartes = ...
    //             // descartarCartasIniciales(jugador, descartes);
    //         } catch (TipoDeSeccionInvalidaError | NoSePuedeCumplirSolicitudDeCartas e) {
    //             System.out.println("Error al repartir mano inicial: " + e.getMessage());
    //         }
    //     }
    //     tirarMoneda();
    //     int rondasGanadasJ1 = 0;
    //     int rondasGanadasJ2 = 0;
    //     int rondaActual = 0;
    //     while (rondasGanadasJ1 < 2 && rondasGanadasJ2 < 2 && rondaActual < 3) {
    //         System.out.println("\n--- Comienza la ronda " + (rondaActual + 1) + " ---");
    //         try {
    //             jugarRonda();
    //         } catch (TipoDeSeccionInvalidaError e) {
    //             System.out.println("Error en la ronda: " + e.getMessage());
    //             break;
    //         }
    //         String ganadorRonda = rondas[rondaActual].getGanadorRonda();
    //         if (ganadorRonda.equals("Jugador 1")) {
    //             rondasGanadasJ1++;
    //             System.out.println("Ronda para Jugador 1");
    //         } else if (ganadorRonda.equals("Jugador 2")) {
    //             rondasGanadasJ2++;
    //             System.out.println("Ronda para Jugador 2");
    //         } else {
    //             System.out.println("Ronda empatada");
    //         }
    //         rondaActual++;
    //         // Aquí podrías limpiar el tablero, pasar cartas jugadas a descarte, etc.
    //     }
    //     System.out.println("\n--- Juego terminado ---");
    //     System.out.println("Ganador: " + mostrarGanador());
    // }


}