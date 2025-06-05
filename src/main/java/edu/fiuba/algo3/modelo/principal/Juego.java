package edu.fiuba.algo3.modelo.principal;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.CartaNoJugable;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.jugador.SeccionesJugador;
import edu.fiuba.algo3.modelo.secciones.tablero.Secciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Juego {
    private int ciclos;
    private Ronda[] rondas;
    //private SeccionesJugador[] SeccionesJugador;
    //private Secciones secciones;
    private List<Jugador> jugadores;
    private List<Mazo> mazos;

    private int moneda;
    private int jugadorQueInicia;

    public Juego() {

        this.ciclos = 0;
        this.rondas = new Ronda[3];
        //this.SeccionesJugador =  SeccionesJugador;
        //this.secciones = new Secciones;
        this.moneda = 0;
        this.jugadorQueInicia = -1;

    }
    private void inicializarMazos() {
        // esto de reemplaza por contructorMazo
        List<Carta> cartasJ1 = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            cartasJ1.add(new CartaUnidad());
        }
        mazos.add(new Mazo(cartasJ1));

        List<Carta> cartasJ2 = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            cartasJ2.add(new CartaUnidad());
        }

        mazos.add(new Mazo(cartasJ2));
    }
    
    private void inicializarJugadores(List<Object> opciones_j1,List<Object> opciones_j2) {

        String nombrej1 = (String) opciones_j1.get(0);
        Mazo mazoj1 = mazos.get((int) opciones_j1.get(1));

        String nombrej2 = (String) opciones_j2.get(1);
        Mazo mazoj2 = mazos.get((int) opciones_j2.get(1));

        Jugador jugador1 = new Jugador(nombrej1, mazoj1);
        Jugador jugador2 = new Jugador(nombrej2, mazoj2);

        jugadores.add(jugador1);
        jugadores.add(jugador2);
    }

    public int puntaje(){
        return 0;
    }


    private void tirarMoneda(){
        moneda = Math.random() < 0.5 ? -1 : 1;

        if (moneda == -1){
            jugadorQueInicia = 1;
        } else{
            jugadorQueInicia = 0;
        }
    }

    public String SeccionElegida() {
        Scanner scanner = new Scanner(System.in);

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

    private Carta eleccionDeCarta(int jugador, String clave) throws TipoDeSeccionInvalidaError {

        Scanner scanner = new Scanner(System.in);
        boolean eleccionErronea = true;
        int opcion = -1;

        Jugador jugadorActual = jugadores.get(jugador);
        int cantidadCartas = jugadorActual.cartasRestantesEnSeccion(clave);


        while (eleccionErronea) {
            System.out.print("Ingrese el número de la carta que quiere elegir: ");
            try {
                opcion = scanner.nextInt();

                if (opcion >= 0 && opcion <jugadorActual.cartasRestantesEnSeccion(clave)) {
                    eleccionErronea = false;
                } else {
                    System.out.println("Opción inválida. Por favor ingrese un número entre 0 y 10.");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor ingrese un número válido.");
                scanner.next();
            }
        }
        Carta cartaADevolver = jugadorActual.removerCartaDeSeccion("Mano", opcion);
        return cartaADevolver;

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

    public void jugarRonda() throws TipoDeSeccionInvalidaError {
        while (pasarTurno()) {
            Carta cartaJugadaPorPrimero = eleccionDeCarta(jugadorQueInicia, "mano");

            boolean cartaJugable1 = false;
            while (!cartaJugable1) {
                String dondeJuegaPrimero = SeccionElegida();

                if (!cartaJugadaPorPrimero.esEspecial()) {
                    try {
                        Secciones.agregarCarta(dondeJuegaPrimero, (CartaUnidad) cartaJugadaPorPrimero);
                        cartaJugable1 = true; // Solo si no lanza excepción
                    } catch (CartaNoJugable | TipoDeSeccionInvalidaError e) {
                        System.out.println("La carta no se puede jugar en esa sección. Elegí otra.");
                    }
                } else {
                    cartaJugable1 = true; // Por si las cartas especiales no necesitan validación
                }
            }
            if(!cartaJugadaPorPrimero.esEspecial()) {
                rondas[ciclos].agregarPuntajeJugador(jugadorQueInicia,((CartaUnidad) cartaJugadaPorPrimero).ValorActual());
            }
        }

        while (pasarTurno()) {
            Carta cartaJugadaPorSegundo = eleccionDeCarta(jugadorQueInicia+moneda, "mano");

            boolean cartaJugable2 = false;
            while (!cartaJugable2) {
                String dondeJuegaPrimero = SeccionElegida();

                if (!cartaJugadaPorSegundo.esEspecial()) {
                    try {
                        Secciones.agregarCarta(dondeJuegaPrimero, (CartaUnidad) cartaJugadaPorSegundo);
                        cartaJugable2 = true; // Solo si no lanza excepción
                    } catch (CartaNoJugable | TipoDeSeccionInvalidaError e) {
                        System.out.println("La carta no se puede jugar en esa sección. Elegí otra.");
                    }
                } else {
                    cartaJugable2 = true; // Por si las cartas especiales no necesitan validación
                }
            }
            if(!cartaJugadaPorSegundo.esEspecial()) {
                rondas[ciclos].agregarPuntajeJugador(jugadorQueInicia,((CartaUnidad) cartaJugadaPorSegundo).ValorActual());
            }
        }

        ciclos++;
    }

    public int cartasRestantesJugador(int jugador_i){
        Jugador jugador = jugadores.get(jugador_i);

        return jugador.cartasRestantes();
    }

    public String mostrarGanador(){
        return "";
    }

    public boolean juegoTerminado(){
        return false;
    }


    public void iniciarJuego(List<Object> opciones_j1, List<Object> opciones_j2){

        inicializarMazos();
        inicializarJugadores(opciones_j1, opciones_j2);
        tirarMoneda();
        //iniciarFaseInicial
        //iniciarFasePreparacion
        //iniciarFaseDeJuego
        //faseFinal() = determinarGanador

    }

   
    public boolean iniciarFasePreparacion() throws TipoDeSeccionInvalidaError{
        repartirCartasAlJugador(1);
        repartirCartasAlJugador(2);
        boolean seRepartio = seLogroRepartirCartasDelMazoALosJugadores();
        return seRepartio;
    }
    public void repartirCartasAlJugador(int jugador) throws TipoDeSeccionInvalidaError {
        int minCartasAMano = 10;
        Jugador jugadorElegido = jugadores.get(jugador);
        jugadorElegido.agregarCartasAMano(minCartasAMano);
    }

    public boolean seLogroRepartirCartasDelMazoALosJugadores(){
        Jugador jugador1 = jugadores.get(1);
        Jugador jugador2 = jugadores.get(2);

        return (jugador1.cartasRestantes() == 10 && jugador2.cartasRestantes() == 10);
    }
	
    
}