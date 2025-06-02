package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Seccion {
    private Map<String,Seccion> secciones;
    protected List<Carta> cartasActuales;

    public Seccion(String claveSeccion) throws TipoDeSeccionInvalidaError {
        if (!puedeEstar(claveSeccion)) {
            throw new TipoDeSeccionInvalidaError();
        }
        this.cartasActuales = new ArrayList<>();

    }

    private Seccion seleccionarSeccion(String claveSeccion) throws TipoDeSeccionInvalidaError {
        Seccion seccionElegida = null;
        if (claveSeccion.equals("Rango")) {
            seccionElegida = new Rango(claveSeccion);
        } else if (claveSeccion.equals("Asedio")) {
            seccionElegida = new Asedio(claveSeccion);
        } else if (claveSeccion.equals("CuerpoACuerpo")) {
            seccionElegida =  new CuerpoACuerpo(claveSeccion);
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

    public boolean contiene(Carta carta){
        return this.cartasActuales.contains((carta));
    }

    public List<Carta> getCartasActuales() {
        return this.cartasActuales;
    }

    // Método para tests: remover carta por índice sin interacción
    public Carta removerCartaPorIndice(int indice) {
        return cartasActuales.remove(indice);
    }

}
