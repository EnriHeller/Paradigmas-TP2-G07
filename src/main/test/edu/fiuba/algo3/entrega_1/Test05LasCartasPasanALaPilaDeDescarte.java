package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.principal.UnoDeLosMazosNoCumpleRequitos;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test05LasCartasPasanALaPilaDeDescarte {

    @Test
    public void pilaDescarteRecibeCartasJugadas() throws TipoDeSeccionInvalidaError, UnoDeLosMazosNoCumpleRequitos {
        int cartasJugadasEsperadas = 8;
        List<Carta> cartas = new ArrayList<Carta>();
        for (int i = 0; i < 21; i++) {
            Carta carta = new CartaUnidad();
            cartas.add(carta);
        }
        Mazo mazo = new Mazo(cartas);
        List<CartaUnidad> cartasJugadas = new ArrayList<CartaUnidad>();

        ArrayList<String> secciones = new ArrayList<String>();

        Seccion seccionSimulada = new Seccion("Rango", 0);

        secciones.add("Rango");

        Jugador jugador1 = new Jugador("JugadorTest1", mazo);

        Juego juego = new Juego(jugador1, new Jugador("JugadorTest2", mazo));

        juego.jugarCarta(new CartaUnidad("Cualesquiera1",secciones, 8 , new Base()), seccionSimulada);
        juego.jugarCarta(new CartaUnidad("Cualesquiera2",secciones, 8 , new Base()), seccionSimulada);
        juego.jugarCarta(new CartaUnidad("Cualesquiera3",secciones, 8 , new Base()), seccionSimulada);
        juego.jugarCarta(new CartaUnidad("Cualesquiera4",secciones, 8 , new Base()), seccionSimulada);
        juego.jugarCarta(new CartaUnidad("Cualesquiera5",secciones, 8 , new Base()), seccionSimulada);
        juego.jugarCarta(new CartaUnidad("Cualesquiera6",secciones, 8 , new Base()), seccionSimulada);
        juego.jugarCarta(new CartaUnidad("Cualesquiera7",secciones, 8 , new Base()), seccionSimulada);
        juego.jugarCarta(new CartaUnidad("Cualesquiera8",secciones, 8 , new Base()), seccionSimulada);

        juego.finalizarRonda();

        assertEquals(cartasJugadasEsperadas, jugador1.cartasRestantesEnSeccion("Descarte"));


    }

}
