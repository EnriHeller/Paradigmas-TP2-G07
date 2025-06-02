package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Seccion {
    private Map<String,Seccion> secciones;
    private List<Carta> cartasActuales;

    public Seccion(String claveSeccion) throws TipoDeSeccionInvalidaError {
        if (!puedeEstar(claveSeccion)) {
            throw new TipoDeSeccionInvalidaError();
        }//si solo que tengo duda con los constructores de las hijas estaba viendo
        this.cartasActuales = new ArrayList<>();
        this.secciones = new HashMap<>();
        Seccion seccionElegida = seleccionarSeccion(claveSeccion);

        secciones.put(claveSeccion,seccionElegida);
    }

    private Seccion seleccionarSeccion(String claveSeccion) throws TipoDeSeccionInvalidaError {
        Seccion seccionElegida = null;
        if (claveSeccion.equals("Rango")) {
            seccionElegida = new Rango();
        } else if (claveSeccion.equals("Asedio")) {
            seccionElegida = new Asedio();
        } else if (claveSeccion.equals("CuerpoACuerpo")) {
            seccionElegida =  new CuerpoACuerpo();
        }
        return seccionElegida;
    }

    private boolean puedeEstar(String claveSeccion){
        return claveSeccion.equals("Rango") || claveSeccion.equals("Asedio") || claveSeccion.equals("CuerpoACuerpo");
    }

    public int puntosEnSeccion(String claveSeccion) {
        Seccion seccion = secciones.get(claveSeccion);
        if (seccion == null) {
            throw new IllegalArgumentException("No existe la sección con clave: " + claveSeccion);
        }
        int puntosTotales = 0;
        for (Carta carta : seccion.getCartasActuales()) {
            puntosTotales += carta.getValor();
        }
        return puntosTotales;
    }

    public int puntosTotalesDeLasSecciones(){
        int puntosTotalesEnTodasLasSecciones = 0;
        for (String seccion : secciones.keySet()) {
            puntosTotalesEnTodasLasSecciones = puntosEnSeccion(seccion);
        }
        return puntosTotalesEnTodasLasSecciones;
    }

    public void agregarCarta(Carta carta) throws TipoDeSeccionInvalidaError {
        cartasActuales.add(carta);
    }

    public List<Carta> getCartasActuales() {
        return this.cartasActuales;
    }

}
