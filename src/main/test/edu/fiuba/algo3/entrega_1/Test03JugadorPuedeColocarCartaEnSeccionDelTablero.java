package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.secciones.jugador.Mano;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import edu.fiuba.algo3.modelo.Errores.*;
import edu.fiuba.algo3.modelo.secciones.tablero.*;

import java.util.List;
import java.util.ArrayList;

public class Test03JugadorPuedeColocarCartaEnSeccionDelTablero  {
    // Verificar que un jugador pueda colocar una carta en una sección del tablero

    @Test
    public void jugadorPuedeColocarCartaEnSeccion() throws NoSePuedeCumplirSolicitudDeCartas, UnoDeLosMazosNoCumpleRequitos, TipoDeSeccionInvalidaError, CartaNoJugable {
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


        //Preparacion
        juego.repartirManoInicial(j1);
        juego.repartirManoInicial(j2);
        juego.tirarMoneda();

        //Mostramos estado actual del juego (Para renderizar, solo se renderiza lo de jugador actual)
        List<Seccion> tablero = juego.mostrarTableroActual();
        List<Carta> mano = juego.mostrarManoActual();

        //Elegimos carta y sección
        Carta cartaElegida = mano.get(0);
        Seccion seccionElegida = tablero.get(0);
        
        juego.jugarCarta(cartaElegida, seccionElegida);
        
        assertTrue(seccionElegida.contiene(cartaElegida));
        
    }
}
