package edu.fiuba.algo3.modelo;
import java.util.List;
import java.util.Scanner;

public class Jugador {
    private String nombre;
    private Mazo mazo;
    private Mano mano;
    private PilaDescarte pilaDescarte;

    public Jugador() {
        this.nombre = "";
        this.mazo = null;
        this.mano = new Mano();
        this.pilaDescarte = new PilaDescarte();
    }

    public Jugador(String nombre, Mazo mazo) {
        this.nombre = nombre;
        this.mazo = mazo;
        this.mano = new Mano();
        this.pilaDescarte = new PilaDescarte();
    }

    public Carta jugarCarta() {

        int eleccion = mano.mostrarCartas();

        return  mano.removerCarta(eleccion);
        // jugador le pide a Su mano mostrar todas las cartas disponibles sus cartas y elige 1
        // Dentro: cada Carta se muestra abstractamente como string
        // el jugador elije uno de esos abtractos y se le da la carta
        // si la puede jugar se descartaCarta (mano piede su referencia) y se le da a tablero
        // sino puede vuelve a intentarlo (manejo de errores)

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


        // Método para tests: jugar carta por índice sin interacción

    //Mostramos cartas por indice porque es responsabilidad de la mano mostrar las cartas para que el jugador tome una decisión.
    public Carta jugarCartaPorIndice(int indice) {
        return mano.removerCarta(indice);
    }

    public void descartarCarta(Carta unaCarta) {}

    public void agregarCartasAMano(int n){
        List<Carta> cartas = mazo.repartirCarta(n);
        mano.agregarCartas(cartas);
    }

    public void agregarCartasAlDescarte(List<Carta> cartas){
        pilaDescarte.agregarCartas(cartas);
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

    public int cartasEnMano(){
        return mano.cartasRestantes();
    }

    public int cartasEnElDescarte(){
        return pilaDescarte.cartasEnElDescarte();
    }

    public int cartasRestantes() {

        return mazo.cantidadDeCartas();

    }
}