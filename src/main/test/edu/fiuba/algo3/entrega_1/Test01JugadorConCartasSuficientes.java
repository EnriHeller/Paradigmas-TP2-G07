package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.mocks.ConstructorMazoMock;
import edu.fiuba.algo3.controller.ConstructorMazo;
import edu.fiuba.algo3.modelo.Errores.NoSePuedeCumplirSolicitudDeCartas;
import edu.fiuba.algo3.modelo.Errores.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.Errores.UnoDeLosMazosNoCumpleRequitos;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Test01JugadorConCartasSuficientes {

    @Test
    public void jugadorTieneCartasSuficientesAlEmpezar() throws Exception, NoSePuedeCumplirSolicitudDeCartas, UnoDeLosMazosNoCumpleRequitos, TipoDeSeccionInvalidaError {

        ConstructorMazo constructorMazo = ConstructorMazoMock.crearDosMazosDeUnidades();
        List<Mazo> mazos = constructorMazo.construirMazos("mock");

        Mazo m1 = mazos.get(0);
        Jugador j1 = new Jugador("Pepito");
        
        j1.agregarMazo(m1);
        assertTrue(j1.HayCartasSuficientesEnMazo(m1));

    }
}