package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import edu.fiuba.algo3.modelo.Errores.*;


import java.util.List;
import java.util.ArrayList;

public class Test03JugadorPuedeColocarCartaEnSeccionDelTablero  {
    // Verificar que un jugador pueda colocar una carta en una sección del tablero

    @Test
    public void jugadorPuedeColocarCartaEnSeccion() throws NoSePuedeCumplirSolicitudDeCartas, UnoDeLosMazosNoCumpleRequitos, TipoDeSeccionInvalidaError {
        // Crear 21 Cartas
        List<Carta> cartas = new ArrayList<>();
        for (int i = 0; i < 21; i++) {

            cartas.add(new CartaUnidad());

        }

        Mazo m1 = new Mazo(cartas);
        Jugador j1 = new Jugador("Pepito");

        Mazo m2 = new Mazo(cartas);
        Jugador j2 = new Jugador("Juanito");

        j1.agregarMazo(m1);
        j2.agregarMazo(m2);

        Juego juego = new Juego(j1, j2);

        juego.repartirManoInicial(j1);
        juego.repartirManoInicial(j2);
        juego.tirarMoneda();

        //juego.jugarCarta()
        
        
        
        // // Arrange
        // List<String> seccionesCartaUnidad = new ArrayList<>();
        // seccionesCartaUnidad.add("Rango"); // La carta puede ir en Rango
        // CartaUnidad cartaUnidad = new CartaUnidad("CartaTest", seccionesCartaUnidad, 8, new Base());
        // List<Carta> cartas = new ArrayList<>();
        // cartas.add(cartaUnidad);

        // for (int i = 0; i < 20; i++) cartas.add(new CartaUnidad());
        // Mazo mazo = new Mazo(cartas);

        // try {
        //     Juego juego = new Juego("Jugador1", "Jugador2", mazo, mazo);
        //     juego.darMano(0, 10);

        //     // Pasar "Rango" para que la clave generada sea "Rango0"
        //     assertDoesNotThrow(() -> juego.jugarCarta(0, cartaUnidad, "Rango"));
        // } catch (TipoDeSeccionInvalidaError | NoSePuedeCumplirSolcitudDeCartas e) {
        //     fail("No se esperaba excepción: " + e.getMessage());
        // }
    }
}
