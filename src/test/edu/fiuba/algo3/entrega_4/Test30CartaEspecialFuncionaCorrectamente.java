package edu.fiuba.algo3.entrega_4;

import edu.fiuba.algo3.modelo.cartas.especiales.CartaEspecial;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.modificadores.Unidas;
import edu.fiuba.algo3.modelo.secciones.jugador.Mano;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test30CartaEspecialFuncionaCorrectamente {

    static class CartaEspecialBase extends CartaEspecial {
        public CartaEspecialBase(String nombre, String descripcion, String tipo, List<String> afectado) {
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.tipo = tipo;
            this.afectado = afectado;
        }
    }

    @org.junit.jupiter.api.Test
    public void testGettersDevuelvenValoresCorrectos() {
        List<String> afectado = Arrays.asList("CuerpoACuerpo", "Rango");
        CartaEspecial carta = new CartaEspecialBase("EspecialTest", "Desc test", "TipoTest", afectado);
        assertEquals("EspecialTest", carta.getNombre());
        assertEquals("Desc test", carta.getDescripcion());
        assertEquals("TipoTest", carta.getTipo());
        assertEquals(afectado, carta.getAfectado());
    }
}
