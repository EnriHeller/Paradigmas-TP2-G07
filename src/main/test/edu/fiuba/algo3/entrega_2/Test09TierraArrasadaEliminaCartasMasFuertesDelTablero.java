package edu.fiuba.algo3.entrega_2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.mocks.CartaUnidadLegendariaMock;
import edu.fiuba.algo3.mocks.CartaUnidadMock;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.CartaNoJugable;
import edu.fiuba.algo3.modelo.cartas.especiales.TierraArrasada;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.principal.NoSePuedeCumplirSolicitudDeCartas;
import edu.fiuba.algo3.modelo.principal.UnoDeLosMazosNoCumpleRequitos;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.tablero.NoSePuedeEliminarClimaSiNoHayClima;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.modelo.secciones.tablero.TipoDeSeccionInvalidaError;

public class Test09TierraArrasadaEliminaCartasMasFuertesDelTablero {

    @Test
    public void testTierraArrasadaEliminaCartasMasFuertes() throws TipoDeSeccionInvalidaError, NoSePuedeEliminarClimaSiNoHayClima, NoSePuedeCumplirSolicitudDeCartas, UnoDeLosMazosNoCumpleRequitos, IOException, ParseException, CartaNoJugable {
        // 1. Crear cartas de unidad y una con legendaria como modificador

        ArrayList<String> secciones = new ArrayList<>();
        secciones.add("Rango");

        Seccion seccionSimulada = new Seccion("Rango", 0);

        CartaUnidadMock carta2 = new CartaUnidadMock("Soldado", secciones, 2);
        CartaUnidadMock carta3 = new CartaUnidadMock("Mago", secciones, 3);

        CartaUnidadMock carta4a = new CartaUnidadMock("Arquero", secciones, 4);
        CartaUnidadMock carta4b = new CartaUnidadMock("Lancero", secciones, 4);
        CartaUnidadMock carta4c = new CartaUnidadMock("Caballero", secciones, 4);

        // Carta con modificador legendaria
        CartaUnidadLegendariaMock legendaria10 = new CartaUnidadLegendariaMock("Dragon", secciones, 10);

        //Carta Tierra Arrazada
        TierraArrasada tierraArrasada = new TierraArrasada();

        // 2. Añadimos cartas que va a usar
        List<Carta> cartasJugador = new ArrayList<>();

        // Empezamos añadiendo mazo con cartas random
        for (int i = 0; i < 15; i++) cartasJugador.add(new CartaUnidadMock());

        //Cartas que tendran los jugadores
        cartasJugador.add(carta2);
        cartasJugador.add(carta3);
        cartasJugador.add(carta4a);
        cartasJugador.add(carta4b);
        cartasJugador.add(carta4c);
        cartasJugador.add(legendaria10);
        cartasJugador.add(tierraArrasada);

        Mazo mazo_j1 = new Mazo(new ArrayList<>(cartasJugador));
        Mazo mazo_j2 = new Mazo(new ArrayList<>(cartasJugador));

        Jugador jugador1 = new Jugador("JugadorTest1");
        jugador1.agregarMazo(mazo_j1);
        Jugador jugador2 = new Jugador("JugadorTest2");
        jugador2.agregarMazo(mazo_j2);

        Juego juego;

        juego = new Juego(jugador1, jugador2);
        juego.darMano(0, 10);

        // 3. Jugar las cartas en la sección "Rango"
        juego.jugarCarta(carta2, seccionSimulada);
        juego.jugarCarta(carta3, seccionSimulada);
        juego.jugarCarta(carta4a, seccionSimulada);
        juego.jugarCarta(carta4b, seccionSimulada);
        juego.jugarCarta(carta4c, seccionSimulada);
        juego.jugarCarta(legendaria10, seccionSimulada);
        juego.jugarCartaEspecial(tierraArrasada);

        // Verificamos todas las cartas que quedaron en el tablero
        int puntajePosArrazada = juego.puntajeEnSeccion(seccionSimulada);

        assertEquals(15, puntajePosArrazada);
    }
}
