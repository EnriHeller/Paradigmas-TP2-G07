package edu.fiuba.algo3.modelo.principal;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.Errores.*;
import edu.fiuba.algo3.modelo.secciones.jugador.Mano;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.jugador.PilaDescarte;
import edu.fiuba.algo3.modelo.secciones.jugador.SeccionJugador;

import java.util.List;

public class Jugador {
    private int minCartasEnMazo = 21;
    private int minCartasEnMano = 10;

    private String nombre;
    private Mazo mazo;
    private Mano mano;
    private PilaDescarte pilaDescarte;
    private Boolean pasoDeRonda;
    //private SeccionesJugador seccionesDelJugador;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new Mano();
        this.pilaDescarte = new PilaDescarte();
        this.pasoDeRonda = false;
    }

    //Fase Inicial
    public void agregarMazo(Mazo mazo) throws NoSePuedeCumplirSolicitudDeCartas {
        if (!HayCartasSuficientesEnMazo(mazo)){
            throw new NoSePuedeCumplirSolicitudDeCartas();
        }
        this.mazo = mazo;
    }

    public void agregarCartasAMano(int n) throws TipoDeSeccionInvalidaError, NoSePuedeCumplirSolicitudDeCartas {
        mazo.repartirCartas(n, mano);

        if(!TengoCartasSuficientesEnMano()){
            throw new NoSePuedeCumplirSolicitudDeCartas();
        }
    }

    //Interacción con secciones generales
    private int cartasRestantes(SeccionJugador seccion) {
        return seccion.cartasRestantes();
    }

    private Carta removerCarta(SeccionJugador seccion, Carta carta) {
        return seccion.removerCarta(carta);
    }

    private void agregarCartas(SeccionJugador seccion, List<Carta> cartas) {
        seccion.agregarCartas(cartas);
    }

    //INTERACCION USUARIO - SECCIONES DE USUARIO

    public boolean HayCartasSuficientesEnMazo(Mazo m){
        return cartasRestantes(m) >= minCartasEnMazo;
    }

    //GETTERS
    public String getNombre() {
        return nombre;
    }
    public boolean haPasado() {
        return pasoDeRonda;
    }

    //SETTERS
    public void pasar() {
        this.pasoDeRonda = true;
    }

    public void reiniciarPaso() {
        this.pasoDeRonda = false;
    }

    //Fase de juego

    public Carta jugarCarta(Carta carta) {
        return mano.removerCarta( carta);
    }

    // Métodos de alto nivel para cumplir Demeter
    public void RemoverCartaDeMano(Carta carta) {
        mano.removerCarta(carta);
    }

    public List<Carta> cartasEnMano() {
        return mano.obtenerCartas();
    }

    public void DescartarCarta(Carta carta) {
        pilaDescarte.agregarCarta(carta);
    }

    //Validaciones

    public boolean TengoCartasSuficientesEnMano(){
        return mano.cartasRestantes() >= minCartasEnMano;
    }


}