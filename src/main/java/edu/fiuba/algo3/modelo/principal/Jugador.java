package edu.fiuba.algo3.modelo.principal;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.jugador.Mano;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.jugador.SeccionesJugador;

import java.util.List;

public class Jugador {
    private String nombre;
    private Mazo mazo;
    private SeccionesJugador seccionesDelJugador;
    private Mano mano;
    private static int maxDescartable = 2;
    private int cantidadDescartadas = 0;

    public Jugador() {
        this.nombre = "";
        this.mazo = null;
        this.seccionesDelJugador = null;
    }

    public Jugador(String nombre, Mazo mazo) {
        this.nombre = nombre;
        this.mazo = mazo;
        this.mano = null;
        this.seccionesDelJugador = null;

        //this.mano = new Mano(mazo.repartirMano());
    }

    public Jugador(String nombre, Mazo mazo, SeccionesJugador instanciaDeSecciones) {
        this.nombre = nombre;
        this.mazo = mazo;
        this.mano = null;

        this.seccionesDelJugador = instanciaDeSecciones;
    }

    public Carta jugarCarta(Carta carta) {
        return seccionesDelJugador.removerCarta("Mano", carta);
    }
    //Fase inicial y preparacion

    public void agregarCartasAMano(int n) throws TipoDeSeccionInvalidaError, NoSePuedeCumplirSolicitudDeCartas {
        List<Carta> cartas = mazo.repartirCarta(n, this.mano);


        seccionesDelJugador.agregarCartas("Mano", cartas);
    }

    public void descartarCarta(Carta carta) {
        if (cantidadDescartadas == maxDescartable ) {
            throw new CantidadMaximaDeDescarteAlcanzadaError("Alcanzaste el maximo de cartas que puedes cambiar ");
        }
        mazo.recibirCarta(carta);
        cantidadDescartadas += 1;
    }

    public int cartasRestantesEnSeccion(String clave) {
        return seccionesDelJugador.cartasRestantes(clave);
    }

    public Carta removerCartaDeSeccion(String clave, int index) {
        return seccionesDelJugador.removerCarta(clave, index);
    }
    public void agregarCartasAlDescarte(List<Carta> cartas) {
        seccionesDelJugador.agregarCartas("Descarte", cartas);
    }

        // Método para tests: jugar carta por índice sin interacción (deben DESAPARECER en lo posible)

    public int cartasRestantes() {
        return mazo.cantidadDeCartas();
    }
}