package edu.fiuba.algo3.modelo;


public class Jugador {
    private String nombre;
    private Mazo mazo;
    private Mano mano;
    private PilaDescarte pilaDescarte;


    public Jugador(String nombre, Mazo mazo) {
        this.nombre = nombre;
        this.mazo = mazo;
        this.mano = new Mano();
        this.pilaDescarte = new PilaDescarte();
    }

    public Carta jugarCarta() {

        int eleccion =
        // jugador le pide a Su mano mostrar todas las cartas disponibles sus cartas y elige 1
        // Dentro: cada Carta se muestra abstractamente como string
        // el jugador elije uno de esos abtractos y se le da la carta
        // si la puede jugar se descartaCarta (mano piede su referencia) y se le da a tablero
        // sino puede vuelve a intentarlo (manejo de errores)

    }

    public void descartarCarta(Carta unaCarta) {}

    public void agregarCartasAMano(int n){
        mano.agregarCartas(mazo.repartirCarta(n));
    }

    public int cartasEnMano(){
        return mano.cartasRestantes();
    }

    public int cartasRestantes() {

        return mazo.cantidadDeCartas();

    }
}