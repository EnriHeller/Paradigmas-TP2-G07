package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.CartaUnidad;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Test01JugadorConCartasSuficientes {

    @Test
    public void jugadorTieneCartasSuficientesAlEmpezar() {
        int cartasMinimasEsperadas = 21;

        // Crear 21 instancias reales de CartaUnidad (o la implementación específica de Carta)
        List<Carta> cartas = new ArrayList<>();
        for (int i = 0; i < cartasMinimasEsperadas; i++) {
            cartas.add(new CartaUnidad(4)); // Ajusta esto si usas otra implementación de Carta
        }

        // Crear el mazo con las cartas reales
        Mazo mazo = new Mazo(cartas);

        // Crear el jugador con el mazo
        Jugador jugador = new Jugador("jugadorTest", mazo);

        // Verificar que el jugador empieza con 21 cartas
        assertEquals(cartasMinimasEsperadas, jugador.cartasRestantes(),
                "El jugador debe tener 21 cartas al comenzar el juego");
    }
}