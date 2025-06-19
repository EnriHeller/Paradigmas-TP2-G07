package edu.fiuba.algo3.modelo.secciones.tablero;

import edu.fiuba.algo3.modelo.cartas.especiales.SinClima;
import edu.fiuba.algo3.modelo.Errores.NoSePuedeEliminarClimaSiNoHayClima;
import edu.fiuba.algo3.modelo.Errores.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.Errores.CartaNoJugable;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.cartas.especiales.Clima;

import java.util.ArrayList;
import java.util.List;

public abstract class Seccion {
    protected List<CartaUnidad> cartasActuales;
    public String nombre;
    public int puntaje;
    private Clima clima;

    public Seccion() throws TipoDeSeccionInvalidaError {
        this.cartasActuales = new ArrayList<>();
        this.clima = new SinClima();
        this.puntaje = 0;
    }

    private boolean puedeEstar(String claveSeccion){
        return claveSeccion.equals("Rango") || claveSeccion.equals("Asedio") || claveSeccion.equals("CuerpoACuerpo");
    }

    public CartaUnidad removerCarta(CartaUnidad carta) {
        for (int i = 0; i < cartasActuales.size(); i++) {
            if (cartasActuales.get(i).equals(carta)) {
                return cartasActuales.remove(i);
            }
        }
        throw new IllegalArgumentException("La carta no está en la mano");
    }

    public List<CartaUnidad> removerCartas(List<CartaUnidad> cartas) {
        for (CartaUnidad carta : cartas) {
            removerCarta(carta);
        }
        return cartas;
    }

    public void agregarCarta(CartaUnidad carta){
        cartasActuales.add(carta);
    }

    public void agregarCartas(List<CartaUnidad> cartas){
        cartasActuales.addAll(cartas);
    }

    public String getNombre() {
        return this.nombre;
    }

    public void sumarPuntaje(int n){
        puntaje += n;
    }

    public int getPuntaje(){
        return puntaje;
    }

    public boolean hayClima(){
        return clima.hayCLima();
    }

    public void afectarClima(Clima nuevoClima) throws NoSePuedeEliminarClimaSiNoHayClima {
        // retrotraer el cambio del clima anterior
        this.clima = nuevoClima;
        nuevoClima.afectarCartas(cartasActuales);
    }

    public List<CartaUnidad> getCartas() {
        return cartasActuales;
    }

    public boolean contiene(Carta carta){
        return this.cartasActuales.contains((carta));
    }

    
}

