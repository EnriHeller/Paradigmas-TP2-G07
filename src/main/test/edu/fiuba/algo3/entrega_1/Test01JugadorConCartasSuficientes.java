package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.mocks.ConstructorDeMazoMock;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Test01JugadorConCartasSuficientes {

    @Test
    public void jugadorTieneCartasSuficientesAlEmpezar() {
        try {
            List<Mazo> mazos = ConstructorDeMazoMock.crearDosMazosDeUnidades().construirMazos(null);
            Jugador jugador1 = new Jugador("Jugador1");
            Jugador jugador2 = new Jugador("Jugador2");
            jugador1.agregarMazo(mazos.get(0));
            jugador2.agregarMazo(mazos.get(1));
            assertDoesNotThrow(() -> new Juego(jugador1, jugador2));
        } catch (Exception e) {
            fail("Falló inicializar Jugadores con cartas suficientes " + e.getMessage());
        }
    }
}