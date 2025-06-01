package modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JugadorConCartasSuficientesTest {

    @test
    public void jugadorTieneCartasSuficientesAlEmpezar() {
        // Crear el juego
        public int cartasMinimasEsperadas = 21;
        Jugador jugador = new Jugador("jugadorTest", /*mockito de mazo*/);

        assertEquals(cartasMinimasEsperadas, jugador.cartasRestantes(),
                    "El jugador debe tener 21 cartas al comenzar el juego");
    }

}