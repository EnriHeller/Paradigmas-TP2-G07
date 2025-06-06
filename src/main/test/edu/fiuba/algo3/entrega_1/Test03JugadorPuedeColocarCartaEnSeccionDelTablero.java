package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.CartaNoJugable;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.principal.NoSePuedeCumplirSolcitudDeCartas;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.jugador.SeccionesSinPuntaje;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;


public class Test03JugadorPuedeColocarCartaEnSeccionDelTablero {

    @Test
    public void jugadorPuedeColocarCartaEnSeccion() throws TipoDeSeccionInvalidaError, CartaNoJugable, NoSePuedeCumplirSolcitudDeCartas {
        // Arrange

        List<String> seccionesCartaUnidad = new ArrayList<>();
        seccionesCartaUnidad.add("Rango");
        CartaUnidad cartaUnidad = new CartaUnidad("CartaTest", seccionesCartaUnidad, 8, new Base());

        List<Carta> cartas = new ArrayList<Carta>();

        cartas.add(cartaUnidad);
        Mazo mazo = new Mazo(cartas);

        Jugador jugador = new Jugador("JugadorTest", mazo, SeccionesSinPuntaje.seccionesDelJugador("Jugador0"));
        jugador.agregarCartasAMano(1);

        Tablero secciones  = Tablero.getInstancia();

        // Act
        Carta cartaJugada = jugador.jugarCarta(cartaUnidad);
        secciones.agregarCarta("Rango0", (CartaUnidad) cartaJugada);

        // Assert: verificar que la sección recibió la carta y la mano quedó vacía
        assertTrue(secciones.contiene("Rango0", cartaJugada));
    }
}
