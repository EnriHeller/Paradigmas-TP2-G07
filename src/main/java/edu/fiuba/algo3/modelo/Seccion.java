package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class Seccion {
    protected List<CartaUnidad> cartasActuales;
    private String clave;
    private Clima clima;

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

    public void agregarCarta(CartaUnidad carta) throws TipoDeSeccionInvalidaError {
        //Validar si entra o no la carta. 
        cartasActuales.add(carta);
        carta.aplicarModificador(this.cartasActuales);
    }

    public String getClave() {
        return this.clave;
    }

    public void afectarClima(Clima nuevoClima) {
        this.clima = nuevoClima;
        nuevoClima.afectarCartas(cartasActuales);
    }


    public boolean contiene(Carta carta){
        return this.cartasActuales.contains((carta));
    }

    public List<CartaUnidad> getCartasActuales() {
        return this.cartasActuales;
    }

    // Método para tests: remover carta por índice sin interacción
    public Carta removerCartaPorIndice(int indice) {
        return cartasActuales.remove(indice);
    }

    public int getPuntajeTotal() {
        int puntaje = 0;
        for (CartaUnidad carta : cartasActuales) {
            puntaje += carta.ValorActual();
        }
        return puntaje;
    }
}

