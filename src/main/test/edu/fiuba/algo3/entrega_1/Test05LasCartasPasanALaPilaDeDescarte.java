package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test05LasCartasPasanALaPilaDeDescarte {

    @Test
    public void pilaDescarteRecibeCartasJugadas() throws TipoDeSeccionInvalidaError {
        int cartasJugadasEsperadas = 8;

        // Crear 8 Carta
        List<Carta> cartasJugadas = new ArrayList<Carta>();

        for (int i = 0; i < 8; i++) {
            Carta carta = new CartaUnidad();
            cartasJugadas.add(carta);
        }

        // Crear el jugador
        Jugador jugador = new Jugador();

        jugador.agregarCartasAlDescarte(cartasJugadas);

        assertEquals(cartasJugadasEsperadas, jugador.cartasEnElDescarte(),
                "El jugador debe tener 8 cartas al comenzar el juego");
    }

}
