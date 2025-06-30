package edu.fiuba.algo3.mocks;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Unidas;
import edu.fiuba.algo3.modelo.modificadores.Base;
import java.util.List;

public class CartaUnidadUnidasMock extends CartaUnidad {
    public CartaUnidadUnidasMock(String nombre, List<String> secciones, int valor) {
        super(nombre, secciones, valor, new Unidas(new Base()));
    }
}
