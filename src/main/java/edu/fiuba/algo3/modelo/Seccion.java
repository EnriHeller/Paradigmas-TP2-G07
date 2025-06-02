package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class Seccion {
    protected List<Carta> cartasActuales;
    private String clave;

    public Seccion(String claveSeccion) throws TipoDeSeccionInvalidaError {
        if (!puedeEstar(claveSeccion)) {
            throw new TipoDeSeccionInvalidaError();
        }
        this.clave = claveSeccion;
        this.cartasActuales = new ArrayList<>();
    }

    private boolean puedeEstar(String claveSeccion){
        return claveSeccion.equals("Rango") || claveSeccion.equals("Asedio") || claveSeccion.equals("CuerpoACuerpo");
    }

    public void agregarCarta(Carta carta) throws TipoDeSeccionInvalidaError {
        //Validar si entra o no la carta. 
        cartasActuales.add(carta);
    }

    public String getClave() {
        return this.clave;
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

    public int getPuntajeTotal() {
        int puntaje = 0;
        for (Carta carta : cartasActuales) {
            puntaje += carta.getValor();
        }
        return puntaje;
    }
}
