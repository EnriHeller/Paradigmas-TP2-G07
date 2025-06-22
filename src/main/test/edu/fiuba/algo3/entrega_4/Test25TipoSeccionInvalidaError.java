package edu.fiuba.algo3.entrega_4;

import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.modificadores.Modificador;
import edu.fiuba.algo3.modelo.modificadores.ModificadoresFactory;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class Test25TipoSeccionInvalidaError {
    @Test
    public void testSeccionInvalidaError() {
        // Probar que crear una Seccion con clave inválida lanza TipoDeSeccionInvalidaError
        assertDoesNotThrow(() -> {
            try {
                new Seccion("NoExiste", 0);
                assert false : "Debe lanzar TipoDeSeccionInvalidaError para clave inválida";
            } catch (TipoDeSeccionInvalidaError e) {
                // OK
            }
        });
    }
}
