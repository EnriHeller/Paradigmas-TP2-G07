package edu.fiuba.algo3.mocks;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Legendaria;
import edu.fiuba.algo3.modelo.modificadores.Base;
import java.util.List;

public class CartaUnidadLegendariaMock extends CartaUnidad {
    public CartaUnidadLegendariaMock(String nombre, List<String> secciones, int valor) {
        super(nombre, secciones, valor, new Legendaria());
    }
}
