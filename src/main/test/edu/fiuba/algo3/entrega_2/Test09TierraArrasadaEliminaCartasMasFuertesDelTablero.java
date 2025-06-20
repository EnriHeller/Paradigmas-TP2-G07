package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.controller.ConstructorMazo;
import edu.fiuba.algo3.mocks.ConstructorMazoMock;
import edu.fiuba.algo3.modelo.Errores.CartaNoJugable;
import edu.fiuba.algo3.modelo.Errores.NoSePuedeCumplirSolicitudDeCartas;
import edu.fiuba.algo3.modelo.Errores.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.Errores.UnoDeLosMazosNoCumpleRequitos;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.especiales.TierraArrasada;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.modificadores.Legendaria;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.*;

public class Test09TierraArrasadaEliminaCartasMasFuertesDelTablero {

    @Test
    public void testTierraArrasadaEliminaCartasMasFuertes() throws Exception {
        // Secciones válidas para las cartas
        ArrayList<String> secciones = new ArrayList<>();
        secciones.add("Rango");

        // Cartas de unidad
        CartaUnidad carta2 = new CartaUnidad("Soldado", secciones, 2, new Base());
        CartaUnidad carta3 = new CartaUnidad("Mago", secciones, 3, new Base());
        CartaUnidad carta4a = new CartaUnidad("Arquero", secciones, 4, new Base());
        CartaUnidad carta4b = new CartaUnidad("Lancero", secciones, 4, new Base());
        CartaUnidad carta4c = new CartaUnidad("Caballero", secciones, 4, new Base());
        CartaUnidad legendaria10 = new CartaUnidad("Dragon", secciones, 10, new Legendaria());

        // Carta especial Tierra Arrasada
        TierraArrasada tierraArrasada = new TierraArrasada(
            "Tierra Arrasada",
            "Elimina las cartas más fuertes de la sección",
            secciones
        );

        // Mock de la mano del jugador 1
        ArrayList<Carta> cartasMano = new ArrayList<>();
        cartasMano.add(carta2);
        cartasMano.add(carta3);
        cartasMano.add(carta4a);
        cartasMano.add(carta4b);
        cartasMano.add(carta4c);
        cartasMano.add(legendaria10);
        cartasMano.add(tierraArrasada);

        Jugador j1 = spy(new Jugador("Pepito"));
        doReturn(cartasMano).when(j1).cartasEnMano();
        Jugador j2 = new Jugador("Fulano");

        // Mazos (no se usan realmente porque la mano está mockeada)
        ConstructorMazo constructorMazo = ConstructorMazoMock.crearDosMazosEstandar();
        List<Mazo> mazos = constructorMazo.construirMazos("mock");
        Mazo m1 = mazos.get(0);
        Mazo m2 = mazos.get(1);
        try {
            j1.agregarMazo(m1);
            j2.agregarMazo(m2);
        } catch (NoSePuedeCumplirSolicitudDeCartas e) {
            fail("No se pudo agregar el mazo: " + e.getMessage());
        }

        Juego juego;
        try {
            juego = new Juego(j1, j2);
        } catch (TipoDeSeccionInvalidaError e) {
            fail("No se pudo crear el juego: " + e.getMessage());
            return;
        }
        juego.tirarMoneda();

        // Jugar cartas en la sección "Rango"
        List<Seccion> tableroActual = juego.mostrarTableroActual();
        final Seccion seccionRango;
        {
            Seccion encontrada = null;
            for (Seccion s : tableroActual) {
                if (s.getNombre().equals("Rango")) {
                    encontrada = s;
                    break;
                }
            }
            seccionRango = encontrada;
        }
        assertNotNull(seccionRango, "No se encontró la sección Rango en el tablero");

        // Juega todas las cartas de unidad
        for (Carta carta : Arrays.asList(carta2, carta3, carta4a, carta4b, carta4c, legendaria10)) {
            assertDoesNotThrow(() -> juego.jugarCarta((CartaUnidad) carta, seccionRango),
                "No se pudo jugar la carta de unidad: " + ((CartaUnidad)carta).getNombre());
            juego.siguienteJugador();
            juego.siguienteJugador(); // Para que siempre juegue j1
        }

        // Guardar cartas antes de Tierra Arrasada
        List<CartaUnidad> cartasAntes = new ArrayList<>(seccionRango.getCartas());
        assertTrue(cartasAntes.contains(carta4a));
        assertTrue(cartasAntes.contains(carta4b));
        assertTrue(cartasAntes.contains(carta4c));
        assertTrue(cartasAntes.contains(legendaria10));

        // Jugar Tierra Arrasada
        try {
            juego.jugarCarta(tierraArrasada, seccionRango);
        } catch (TipoDeSeccionInvalidaError | CartaNoJugable e) {
            fail("No se pudo jugar Tierra Arrasada: " + e.getMessage());
        }

        // Cartas después de Tierra Arrasada
        List<CartaUnidad> cartasRestantes = new ArrayList<>(seccionRango.getCartas());

        // Las cartas con valor 4 (no legendarias) deberían haber sido eliminadas
        assertFalse(cartasRestantes.contains(carta4a), "carta4a no fue eliminada. Cartas restantes: " + cartasRestantes);
        assertFalse(cartasRestantes.contains(carta4b), "carta4b no fue eliminada. Cartas restantes: " + cartasRestantes);
        assertFalse(cartasRestantes.contains(carta4c), "carta4c no fue eliminada. Cartas restantes: " + cartasRestantes);

        // Las otras cartas deberían seguir presentes
        assertTrue(cartasRestantes.contains(carta2), "carta2 fue eliminada incorrectamente");
        assertTrue(cartasRestantes.contains(carta3), "carta3 fue eliminada incorrectamente");
        assertTrue(cartasRestantes.contains(legendaria10), "la carta legendaria fue eliminada incorrectamente");
    }
}
