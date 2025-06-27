package edu.fiuba.algo3.entrega_4;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.modificadores.ModificadoresFactory;
import edu.fiuba.algo3.modelo.modificadores.Modificador;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.principal.NoSePuedeCumplirSolicitudDeCartas;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class Test24ModificadoresFactoryFuncionaCorrectamente {
    
    @Test
    public void testModificadoresFactoryCreaCorrectamente() {
        ModificadoresFactory factory = new ModificadoresFactory();
        // Base para encadenar
        Modificador base = new Base();
        // Crea todos los modificadores conocidos
        assertDoesNotThrow(() -> factory.crearModificador("legendaria", base));
        assertDoesNotThrow(() -> factory.crearModificador("medico", base));
        assertDoesNotThrow(() -> factory.crearModificador("agil", base));
        assertDoesNotThrow(() -> factory.crearModificador("carta unida", base));
        assertDoesNotThrow(() -> factory.crearModificador("espia", base));
        assertDoesNotThrow(() -> factory.crearModificador("suma valor base", base));
        // Crea modificador con mayúsculas y tildes
        assertDoesNotThrow(() -> factory.crearModificador("Ágil", base));
        // Los especiales deben devolver null
        assertDoesNotThrow(() -> {
            Modificador m = factory.crearModificador("Tierra arrasada", base);
            assert m == null;
        });
        // Un modificador desconocido debe lanzar excepción
        try {
            factory.crearModificador("noexiste", base);
            assert false : "Debe lanzar excepción para modificador desconocido";
        } catch (IllegalArgumentException e) {
            // OK
        }
    }
}
