package edu.fiuba.algo3.modelo.secciones.jugador;

import edu.fiuba.algo3.modelo.cartas.Carta;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Mano extends SeccionJugador {

    private List<Carta> cartasEnMano;

    public Mano() {
        this.cartasEnMano = new ArrayList<>();
    }

    private int eleccionDeCarta(int cantCartas){

        Scanner scanner = new Scanner(System.in);
        boolean eleccionErronea = true;
        int opcion = -1;

        while (eleccionErronea) {
            System.out.print("Ingrese el número de la carta que quiere elegir: ");
            try {
                opcion = scanner.nextInt();

                if (opcion >= 0 && opcion < cantCartas) {
                    eleccionErronea = false;
                } else {
                    System.out.println("Opción inválida. Por favor ingrese un número entre 0 y 10.");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor ingrese un número válido.");
                scanner.next();
            }
        }

        return opcion;

    }

    public int mostrarCartas(){
        int i = 0;
        for (Carta carta: cartasEnMano){
            System.out.println(i + ": " + carta.mostrarCarta());
            i++;
        }

        return eleccionDeCarta(cartasEnMano.size());
    }

    public Carta removerCarta(int indice) {

        return cartasEnMano.remove(indice);
    }

    public Carta removerCarta(Carta carta) {
        for (int i = 0; i < cartasEnMano.size(); i++) {
            if (cartasEnMano.get(i).equals(carta)) {
                return cartasEnMano.remove(i);
            }
        }
        throw new IllegalArgumentException("La carta no está en la mano");
    }

    public List<Carta> obtenerCartas() {
        return new ArrayList<>(cartasEnMano);
    }

    public int cartasRestantes() {

        return cartasEnMano.size();

    }
}

