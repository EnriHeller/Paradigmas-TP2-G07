package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.principal.UnoDeLosMazosNoCumpleRequitos;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList; // o import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class Test01JugadorConCartasSuficientes {

    @Test
    public void jugadorTieneCartasSuficientesAlEmpezar() throws UnoDeLosMazosNoCumpleRequitos {
        int cartasMinimasEsperadas = 21;

        // Crear 21 Cartas
        List<Carta> cartas = new ArrayList<Carta>();
        
        for (int i = 0; i < 21; i++) {
            Carta carta = new CartaUnidad();
            cartas.add(carta);
        }

        // Crear el mazo con esas cartas
        Mazo mazo = new Mazo(cartas); // Asegurate de tener este constructor

        assertDoesNotThrow(() -> new Juego(mazo, mazo));
    }
}