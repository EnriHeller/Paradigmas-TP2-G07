package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.especiales.TierraArrasada;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.tablero.NoSePuedeEliminarClimaSiNoHayClima;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.NoSePuedeCumplirSolcitudDeCartas;
import edu.fiuba.algo3.modelo.principal.UnoDeLosMazosNoCumpleRequitos;
import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.modificadores.Legendaria;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


//Tierra arrasada: Quema a las cartas más fuertes del tablero

//Nos piden verificar que si se usa un TierraArrasada se eliminen las cartas correctamente.

// las más fuertes son las que más puntos tienen en esa ronda, por ejemplo:
// Si tenés cartas con puntaje 2, 3, 4, 4, 4 - se queman las tres cartas de puntaje 4
// Si tenés cartas con puntaje 4, 5, 6, 9 - se quema solo la de 9
// Si tenés 7, 8 y una legendaria de 10 (es legendaria , no le afecta) - se quema la de 8
// Es la del maximo

//-> habría que tener un Juego inicializado en una instancia particular donde haya varias cartas jugadas ya para verificar que el jugador, al jugar la carta especial tierra arrasada, haga lo correspondiente.


public class Test09TierraArrasadaEliminaCartasMasFuertesDelTablero {

    @Test
    public void testTierraArrasadaEliminaCartasMasFuertes() throws TipoDeSeccionInvalidaError, NoSePuedeEliminarClimaSiNoHayClima, NoSePuedeCumplirSolcitudDeCartas, UnoDeLosMazosNoCumpleRequitos {
        // 1. Crear cartas de unidad y una con legendaria como modificador

        ArrayList<String> secciones = new ArrayList<>();
        secciones.add("Rango");

        Seccion seccionSimulada = new Seccion("Rango", 0);

        CartaUnidad carta2 = new CartaUnidad("Soldado", secciones, 2, new Base());
        CartaUnidad carta3 = new CartaUnidad("Mago", secciones, 3, new Base());
        
        CartaUnidad carta4a = new CartaUnidad("Arquero", secciones, 4, new Base());
        CartaUnidad carta4b = new CartaUnidad("Lancero", secciones, 4, new Base());
        CartaUnidad carta4c = new CartaUnidad("Caballero", secciones, 4, new Base());


        // Carta con modificador legendaria
        CartaUnidad legendaria10 = new CartaUnidad("Dragon", secciones, 10, new Legendaria());

        //Carta Tierra Arrazada
        TierraArrasada tierraArrasada = new TierraArrasada();

        // 2. Añadimos cartas que va a usar
        List<Carta> cartasJugador = new ArrayList<>();
        
        // Empezamos añadiendo mazo con cartas random
        for (int i = 0; i < 15; i++) cartasJugador.add(new CartaUnidad());

        //Cartas que tendran los jugadores
        cartasJugador.add(carta2);
        cartasJugador.add(carta3);
        cartasJugador.add(carta4a);
        cartasJugador.add(carta4b);
        //cartasJugador.add(carta4c);
        cartasJugador.add(legendaria10);
        cartasJugador.add(tierraArrasada);
        
        Mazo mazo_j1 = new Mazo(new ArrayList<>(cartasJugador));
        Mazo mazo_j2 = new Mazo(new ArrayList<>(cartasJugador));

        Juego juego;

        juego = new Juego("Jugador1", "Jugador2", mazo_j1, mazo_j2);
        juego.darMano(0, 10);

        // 3. Jugar las cartas en la sección "Rango"
        juego.jugarCarta(0, carta2, seccionSimulada);
            juego.jugarCarta(1, carta3, seccionSimulada);
            juego.jugarCarta(0, carta4a, seccionSimulada);
            juego.jugarCarta(1, carta4b, seccionSimulada);
            juego.jugarCarta(0, carta4c, seccionSimulada);
            juego.jugarCarta(0, legendaria10, seccionSimulada);
            juego.jugarCarta(1,tierraArrasada, seccionSimulada);

            // Verificamos todas las cartas que quedaron en el tablero
            List<CartaUnidad> cartasRestantes = Tablero.getInstancia().getCartas();

            // Las cartas con valor 4 (no legendarias) deberían haber sido eliminadas
            assertFalse(cartasRestantes.contains(carta4a), "carta4a no fue eliminada. Cartas restantes: " + cartasRestantes);
            assertFalse(cartasRestantes.contains(carta4b), "carta4b no fue eliminada. Cartas restantes: " + cartasRestantes );
            assertFalse(cartasRestantes.contains(carta4c), "carta4c no fue eliminada. Cartas restantes: " + cartasRestantes);

            // Las otras cartas deberían seguir presentes
            assertTrue(cartasRestantes.contains(carta2), "carta2 fue eliminada incorrectamente");
            assertTrue(cartasRestantes.contains(carta3), "carta3 fue eliminada incorrectamente");
            assertTrue(cartasRestantes.contains(legendaria10), "la carta legendaria fue eliminada incorrectamente");
    }
}
