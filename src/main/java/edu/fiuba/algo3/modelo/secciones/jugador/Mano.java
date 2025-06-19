package edu.fiuba.algo3.modelo.secciones.jugador;

import edu.fiuba.algo3.modelo.cartas.Carta;
import java.util.Scanner;

public class Mano extends SeccionJugador {
    public Mano() {
        super();
    }

    // private int eleccionDeCarta(int cantCartas) {
    //     Scanner scanner = new Scanner(System.in);
    //     boolean eleccionErronea = true;
    //     int opcion = -1;
    //     while (eleccionErronea) {
    //         System.out.print("Ingrese el número de la carta que quiere elegir: ");
    //         try {
    //             opcion = scanner.nextInt();
    //             if (opcion >= 0 && opcion < cantCartas) {
    //                 eleccionErronea = false;
    //             } else {
    //                 System.out.println("Opción inválida. Por favor ingrese un número entre 0 y " + (cantCartas-1) + ".");
    //             }
    //         } catch (Exception e) {
    //             System.out.println("Entrada inválida. Por favor ingrese un número válido.");
    //             scanner.next();
    //         }
    //     }
    //     return opcion;
    // }

    // public int mostrarCartas() {
    //     int i = 0;
    //     for (Carta carta : cartas) {
    //         System.out.println(i + ": " + carta.mostrarCarta());
    //         i++;
    //     }
    //     return eleccionDeCarta(cartas.size());
    // }
}

