package edu.fiuba.algo3.modelo.principal;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.jugador.SeccionesSinPuntaje;

import java.util.List;
import java.util.Scanner;

public class Jugador {
    private String nombre;
    private Mazo mazo;
    private SeccionesSinPuntaje seccionesDelJugador;

    public Jugador() {
        this.nombre = "";
        this.mazo = null;
        this.seccionesDelJugador = null;
    }

    public Jugador(String nombre, Mazo mazo) {
        this.nombre = nombre;
        this.mazo = mazo;
        this.seccionesDelJugador = null;
    }

    public Jugador(String nombre, Mazo mazo, SeccionesSinPuntaje instanciaDeSecciones) {
        this.nombre = nombre;
        this.mazo = mazo;
        this.seccionesDelJugador = instanciaDeSecciones;
    }

    public Carta jugarCarta(Carta carta) throws TipoDeSeccionInvalidaError {

        return seccionesDelJugador.removerCarta("Mano",carta);
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

    public void agregarCartasAMano(int n) throws TipoDeSeccionInvalidaError {
        List<Carta> cartas = mazo.repartirCarta(n);
        seccionesDelJugador.agregarCartas("Mano", cartas);
    }

    public void agregarCartasAlDescarte(List<Carta> cartas) throws TipoDeSeccionInvalidaError {

        seccionesDelJugador.agregarCartas("Descarte", cartas);
    }

        // Método para tests: jugar carta por índice sin interacción (deben DESAPARECER en lo posible)

    public void descartarCarta(Carta unaCarta) {}

    public int cartasEnMano() throws TipoDeSeccionInvalidaError {
        return seccionesDelJugador.cartasRestantes("Mano");
    }

    public int cartasEnElDescarte() throws TipoDeSeccionInvalidaError {
        return seccionesDelJugador.cartasRestantes("Descarte");
    }

    public int cartasRestantes() {

        return mazo.cantidadDeCartas();

    }
}