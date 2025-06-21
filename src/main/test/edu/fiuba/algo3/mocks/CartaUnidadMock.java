package edu.fiuba.algo3.mocks;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import java.util.ArrayList;
import java.util.List;
import edu.fiuba.algo3.modelo.modificadores.Base;

public class CartaUnidadMock extends CartaUnidad {
    public CartaUnidadMock() {
        // Llama al constructor de CartaUnidad con valores dummy válidos
        super("MockUnidad", seccionesMock(), 1, new Base());
    }

    public CartaUnidadMock(String nombre, List<String> secciones, int valor) {
        super(nombre, secciones, valor, new Base());
    }

    private static List<String> seccionesMock() {
        List<String> secciones = new ArrayList<>();
        secciones.add("Rango");
        secciones.add("Asedio");
        secciones.add("CuerpoACuerpo");
        return secciones;
    }
}
