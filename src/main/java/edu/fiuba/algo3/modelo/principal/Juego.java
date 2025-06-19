package edu.fiuba.algo3.modelo.principal;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.CartaNoJugable;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Modificador;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.jugador.SeccionesJugador;
import edu.fiuba.algo3.modelo.secciones.tablero.NoSePuedeEliminarClimaSiNoHayClima;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Juego {
    private int ciclos;
    private Ronda[] rondas;
    private List<Jugador> jugadores;
    private int moneda;
    private int jugadorQueInicia;
    private Tablero tablero;
    private SeccionesJugador[] seccionesJugador;


    //FASE INICIAL
    public Juego(String nombreJugador1, String nombreJugador2, Mazo mazoDelJugador1, Mazo mazoDelJugador2) throws UnoDeLosMazosNoCumpleRequitos, TipoDeSeccionInvalidaError {
        this.ciclos = 0;
        this.rondas = new Ronda[3];

        if (mazoDelJugador1.cantidadDeCartas() < 21 || mazoDelJugador2.cantidadDeCartas() < 21) {
            throw new UnoDeLosMazosNoCumpleRequitos();
        }

        //Se instancia por primera vez al tablero. (A chequear si es necesario)
        this.tablero = Tablero.getInstancia();

        //Referencia a las secciones jugador (A chequear si es necesario)
        this.seccionesJugador = new SeccionesJugador[2];
        seccionesJugador[0] = SeccionesJugador.seccionesDelJugador("0");
        seccionesJugador[1] = SeccionesJugador.seccionesDelJugador("1");

        this.jugadores = new ArrayList<>();
        jugadores.add(new Jugador(nombreJugador1, mazoDelJugador1, seccionesJugador[0]));
        jugadores.add(new Jugador(nombreJugador2, mazoDelJugador2, seccionesJugador[1]));

        this.moneda = 0;
        this.jugadorQueInicia = -1;
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

    //FASE DE PREPARACIÓN

    private void tirarMoneda(){
        moneda = Math.random() < 0.5 ? -1 : 1;

        if (moneda == -1){
            jugadorQueInicia = 1;
        } else{
            jugadorQueInicia = 0;
        }
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
        jugadores.get(jugadorID).descartarCartas(cartasQueSeQuierenDescartar);
    }


    //FASE DE JUEGO
    public void jugarCarta(int jugadorID, CartaUnidad carta, String dondeJugarla) {
        Contexto contexto = new Contexto(this.tablero, dondeJugarla, (CartaUnidad) carta, jugadorID, jugadores.get(jugadorID));

        CartaUnidad cartaUnidad = (CartaUnidad) carta;
        cartaUnidad.prepararContexto(contexto);
        tablero.agregarCarta(dondeJugarla + String.valueOf(jugadorID), cartaUnidad);
        cartaUnidad.aplicarModificador(contexto);
        tablero.afectarClimas();
        if (this.rondas[this.ciclos] == null) {
            this.rondas[this.ciclos] = new Ronda();
        }
        rondas[ciclos].agregarPuntajeJugador(jugadorID, cartaUnidad.ValorActual());
    }

    public void finalizarRonda(){
        if (this.ciclos >= 2){
            rondas[this.ciclos].getGanadorRonda();
        }

        List<CartaUnidad> cartasDel1 = this.tablero.removerCartasDeJugador(0);
        List<CartaUnidad> cartasDel2 = this.tablero.removerCartasDeJugador(0);

        for (CartaUnidad carta : cartasDel1) {
            Contexto contexto = new Contexto(tablero,"", carta,  0, jugadores.get(0));
            carta.retrotraerModificacion(contexto);
        }

        for (CartaUnidad carta : cartasDel2) {
            Contexto contexto = new Contexto(tablero,"", carta,  1, jugadores.get(1));
            carta.retrotraerModificacion(contexto);
        }

        jugadores.get(0).agregarCartasAlDescarte(new ArrayList<>(cartasDel1));
        jugadores.get(1).agregarCartasAlDescarte(new ArrayList<>(cartasDel2));

    }

    public void aplicarEspecial(int jugadorID, Modificador cartaEspecial)  throws NoSePuedeEliminarClimaSiNoHayClima, TipoDeSeccionInvalidaError {
        CartaUnidad carta = new CartaUnidad();
        Contexto contexto = new Contexto(this.tablero, "", (CartaUnidad) carta, jugadorID, jugadores.get(jugadorID));
        cartaEspecial.modificar(contexto);
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

    //COMUNICACION

        private Carta eleccionDeCarta(int jugador, String clave) throws TipoDeSeccionInvalidaError {
        Tablero secciones = Tablero.getInstancia();

        Scanner scanner = new Scanner(System.in);

        boolean eleccionErronea = true;
        int opcion = -1;

        Jugador jugadorActual = jugadores.get(jugador);
        int cantidadCartas = jugadorActual.cartasRestantesEnSeccion(clave);

        while (eleccionErronea) {
            System.out.print("Ingrese el número de la carta que quiere elegir: ");
            try {
                opcion = scanner.nextInt();
                //Si la carta se encuentra en el rango dado, ya no hay eleccion erronea
                if (opcion >= 0 && opcion < seccionesJugador[jugador].cartasRestantes(clave)) {
                    eleccionErronea = false;
                } else {
                    System.out.println("Opción inválida. Por favor ingrese un número entre 0 y 10.");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor ingrese un número válido.");
                scanner.next();
            }
        }
        //Esto a chequear. Terminará de definirse con la interfaz
        return new CartaUnidad(); //SeccionesJugador[jugador].removerCarta("Mano", opcion);
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
    
    //VERIFICACIONES

    public int puntajeEnSeccion(String nombreSeccion) throws TipoDeSeccionInvalidaError {
        return Tablero.getInstancia().getPuntaje(nombreSeccion);
    }

    public int cartasRestantesJugador(String seccionJugador,int jugador_i) throws TipoDeSeccionInvalidaError {
        Jugador jugador = jugadores.get(jugador_i);
        return jugador.cartasRestantesEnSeccion(seccionJugador);
    }

    public String mostrarGanador(){
        String ganador = "";
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

    public boolean seLogroRepartirCartasDelMazoALosJugadores(){
        Jugador jugador1 = jugadores.get(1);
        Jugador jugador2 = jugadores.get(2);

        return (jugador1.cartasRestantes() == 10 && jugador2.cartasRestantes() == 10);
    }
	
}