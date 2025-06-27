package edu.fiuba.algo3.entrega_4;

import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.modificadores.Modificador;
import edu.fiuba.algo3.modelo.modificadores.ModificadoresFactory;
import edu.fiuba.algo3.modelo.secciones.tablero.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Test25TipoSeccionInvalidaError {
    @Test
    public void testSeccionInvalidaError() throws TipoDeSeccionInvalidaError {

        assertThrows(TipoDeSeccionInvalidaError.class,() -> new Seccion("CieloEstrellado", 3));

    }
}
