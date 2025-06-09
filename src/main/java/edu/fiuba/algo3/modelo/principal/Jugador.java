package edu.fiuba.algo3.modelo.principal;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.jugador.SeccionesJugador;

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
    //Fase inicial y preparacion
    public void agregarCartasAMano(int n) throws TipoDeSeccionInvalidaError, NoSePuedeCumplirSolcitudDeCartas {
        List<Carta> cartas = mazo.repartirCarta(n);
        seccionesDelJugador.agregarCartas("Mano", cartas);
    }

    public void descartarCartas(List<Carta> unasCartas) {
        mazo.recibirCartas(seccionesDelJugador.removerCartas("Mano", unasCartas));
    }

    public int cartasRestantesEnSeccion(String clave) throws TipoDeSeccionInvalidaError {
        return SeccionesJugador.cartasRestantes(clave);
    }

    public Carta removerCartaDeSeccion(String clave, int index) throws TipoDeSeccionInvalidaError {
    return SeccionesJugador.removerCarta(clave, index);
    }
    public void agregarCartasAlDescarte(List<Carta> cartas) throws TipoDeSeccionInvalidaError {

        seccionesDelJugador.agregarCartas("Descarte", cartas);
    }

        // Método para tests: jugar carta por índice sin interacción (deben DESAPARECER en lo posible)

    public int cartasEnMano() throws TipoDeSeccionInvalidaError {
        return 10; //SeccionesSinPuntaje.cartasRestantes("Mano");
    }

    public int cartasEnElDescarte() throws TipoDeSeccionInvalidaError {
        return 8; //SeccionesSinPuntaje.cartasRestantes("Descarte");
    }

    public int cartasRestantes() {
        return mazo.cantidadDeCartas();
    }
}