package edu.fiuba.algo3.modelo.principal;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.jugador.SeccionesJugador;

import java.util.List;
import java.util.Scanner;

public class Jugador {
    private String nombre;
    private Mazo mazo;
    private SeccionesJugador seccionesDelJugador;

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

    public Jugador(String nombre, Mazo mazo, SeccionesJugador instanciaDeSecciones) {
        this.nombre = nombre;
        this.mazo = mazo;
        this.seccionesDelJugador = instanciaDeSecciones;
    }

    public Carta jugarCarta(Carta carta) {
        return seccionesDelJugador.removerCarta("Mano", carta);
    }
    //Fase inicial y preparacion
    public void agregarCartasAMano(int n) throws TipoDeSeccionInvalidaError, NoSePuedeCumplirSolcitudDeCartas {
        List<Carta> cartas = mazo.repartirCarta(n);
        seccionesDelJugador.agregarCartas("Mano", cartas);
    }

    public void descartarCartas(List<Carta> unasCartas) {
        mazo.recibirCartas(seccionesDelJugador.removerCartas("Mano", unasCartas));
    }

    public int cartasRestantesEnSeccion(String clave) {
        return seccionesDelJugador.cartasRestantes(clave);
    }

    public Carta removerCartaDeSeccion(String clave, Carta carta) {
        return seccionesDelJugador.removerCarta(clave, carta);
    }

    public String nombre() {
        return nombre;
    }

    public void agregarCartasAlDescarte(List<Carta> cartas) {
        seccionesDelJugador.agregarCartas("Descarte", cartas);
    }

        // Método para tests: jugar carta por índice sin interacción (deben DESAPARECER en lo posible)

    public int cartasRestantes() {
        return mazo.cantidadDeCartas();
    }
    public CartaUnidad removerUltimaCartaDeDescarte(){
        return (CartaUnidad) seccionesDelJugador.removerCarta("Descarte", seccionesDelJugador.cartasRestantes("Descarte") - 1);
    }
}
