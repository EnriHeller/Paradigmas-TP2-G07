package edu.fiuba.algo3.modelo.principal;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.CartaNoJugable;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.jugador.SeccionesSinPuntaje;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Juego {
    private int ciclos;
    private Ronda[] rondas;
    private Tablero tablero;
    private List<Jugador> jugadores;
    private int moneda;
    private int jugadorQueInicia;

    public Juego() throws TipoDeSeccionInvalidaError {

        this.ciclos = 0;
        this.rondas = new Ronda[3];
        this.tablero = Tablero.getInstancia();
        this.jugadores = new ArrayList<Jugador>();
        this.moneda = 0;
        this.jugadorQueInicia = -1;

    }

    //Fase inicial
    public Juego(String nombreJugador1, String nombreJugador2, Mazo mazoDelJugador1, Mazo mazoDelJugador2) throws UnoDeLosMazosNoCumpleRequitos, TipoDeSeccionInvalidaError {
        this.ciclos = 0;
        this.rondas = new Ronda[3];

        if (mazoDelJugador1.cantidadDeCartas() < 21 || mazoDelJugador2.cantidadDeCartas() < 21) {
            throw new UnoDeLosMazosNoCumpleRequitos();
        }
        this.tablero = Tablero.getInstancia();
        this.jugadores = new ArrayList<>();
        jugadores.add(new Jugador(nombreJugador1, mazoDelJugador1));
        jugadores.add(new Jugador(nombreJugador1, mazoDelJugador2));

        this.moneda = 0;
        this.jugadorQueInicia = -1;
    }

    //fasePreparacion

    public void darMano(int jugadorID, int cantidadDeCartas) throws TipoDeSeccionInvalidaError, NoSePuedeCumplirSolcitudDeCartas {
        jugadores.get(jugadorID).agregarCartasAMano(cantidadDeCartas);
    }

    public void descartarCartasDeMano(int jugadorID, List<Carta> cartasQueSeQuierenDescartar) {
        jugadores.get(jugadorID).descartarCartas(cartasQueSeQuierenDescartar);
    }

    private void tirarMoneda(){
        moneda = Math.random() < 0.5 ? -1 : 1;

        if (moneda == -1){
            jugadorQueInicia = 1;
        } else{
            jugadorQueInicia = 0;
        }

    }

    //fase de juego
    public void jugarCarta(int jugadorID, Carta carta, String dondeJugarla) {
        try {
            if (carta.esEspecial()) {
                // Cartas especiales aún no implementadas :b
            } else {
                CartaUnidad cartaUnidad = (CartaUnidad) carta;
                tablero.agregarCarta(dondeJugarla + jugadorID, cartaUnidad);
                cartaUnidad.aplicarModificador();
                rondas[ciclos].agregarPuntajeJugador(jugadorID, cartaUnidad.ValorActual());

            }
        } catch (CartaNoJugable | TipoDeSeccionInvalidaError e) {
            // No hacemos nada si hay una excepción
        }
    }

    //Fase de win (o continuar)

    public int puntaje(){
        return 0;
    }

    public Jugador iniciarJugador(int jugador){
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿Cual es el nombre que quieres tener al jugar?\n");
        String nombreInput = scanner.nextLine().trim();
        String nombre;
        if (nombreInput.equals("\n")) {
            nombre = "Jugador" + String.valueOf(jugador);
        } else{
            nombre = nombreInput;
        }

        System.out.println("¿Tienes un mazo creado para jugar?\nSi es asi, por favor dar la ruta donde se encuentra");
        String mazoInput = scanner.nextLine().trim();
        Mazo mazo;
        if (mazoInput.equals("\n")) {
            mazo = new Mazo(new ArrayList<Carta>()); // crear el creador de mazo porfis
        } else{
            mazo = new Mazo(new ArrayList<Carta>()); // Simil, crear el creador de mazos
        }

        return new Jugador(nombre, mazo);

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

        Tablero secciones = Tablero.getInstancia();
        SeccionesSinPuntaje secccionesDelJugador1 = SeccionesSinPuntaje.seccionesDelJugador();
        Scanner scanner = new Scanner(System.in);
        boolean eleccionErronea = true;
        int opcion = -1;

        while (eleccionErronea) {
            System.out.print("Ingrese el número de la carta que quiere elegir: ");
            try {
                opcion = scanner.nextInt();

                if (opcion >= 0 && opcion < secccionesDelJugador1.cartasRestantes("Jugador" + String.valueOf(jugador))) {
                    eleccionErronea = false;
                } else {
                    System.out.println("Opción inválida. Por favor ingrese un número entre 0 y 10.");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor ingrese un número válido.");
                scanner.next();
            }
        }

        return new CartaUnidad(); //seccionesSinPuntaje[jugador].removerCarta("Mano", opcion);

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
                    //try {
                        //secciones.agregarCarta(dondeJuegaPrimero, (CartaUnidad) cartaJugadaPorPrimero);
                        //cartaJugable1 = true; // Solo si no lanza excepción
                    //} catch (CartaNoJugable | TipoDeSeccionInvalidaError e) {
                        //System.out.println("La carta no se puede jugar en esa sección. Elegí otra.");
                    //}
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
                    //try {
                        //secciones.agregarCarta(dondeJuegaPrimero, (CartaUnidad) cartaJugadaPorSegundo);
                        //cartaJugable2 = true; // Solo si no lanza excepción
                    //} catch (CartaNoJugable | TipoDeSeccionInvalidaError e) {
                        //System.out.println("La carta no se puede jugar en esa sección. Elegí otra.");
                    //}
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



    public String mostrarGanador(){
        return "";
    }

    public boolean juegoTerminado(){
        return false;
    }

}