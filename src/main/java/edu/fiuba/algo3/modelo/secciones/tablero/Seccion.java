package edu.fiuba.algo3.modelo.secciones.tablero;

import edu.fiuba.algo3.modelo.cartas.especiales.SinClima;
import edu.fiuba.algo3.modelo.secciones.tablero.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.cartas.especiales.Clima;

import java.util.ArrayList;
import java.util.List;

public class Seccion {
    protected List<CartaUnidad> cartasActuales;
    private String clave;
    private final int jugadorId;
    private Clima clima;

    public Seccion(){
        this.cartasActuales = new ArrayList<>();
        this.jugadorId = -1;
        this.clave = "";
        this.clima = new SinClima();
    }

    public Seccion(String claveSeccion, int jugadorId) throws TipoDeSeccionInvalidaError {
        if (!puedeEstar(claveSeccion)) {
            throw new TipoDeSeccionInvalidaError();
        }
        this.clave = claveSeccion;
        this.jugadorId = jugadorId;
        this.cartasActuales = new ArrayList<>();
        this.clima = new SinClima();
    }

    private boolean puedeEstar(String claveSeccion){
        return (claveSeccion.equals("Rango") || claveSeccion.equals("Asedio") || claveSeccion.equals("CuerpoACuerpo"));
    }

    public CartaUnidad removerCarta(CartaUnidad carta) {
        for (int i = 0; i < cartasActuales.size(); i++) {
            if (cartasActuales.get(i).equals(carta)) {
                return cartasActuales.remove(i);
            }
        }
        throw new IllegalArgumentException("La carta no está en la seccion " + clave);
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

    public String getClave() {
        return this.clave;
    }

    public boolean hayClima(){
        return clima.hayCLima();
    }

    public void afectarClima(Clima nuevoClima) throws NoSePuedeEliminarClimaSiNoHayClima {
        // retrotraer el cambio del clima anterior
        this.clima = nuevoClima;
        nuevoClima.afectarCartas(cartasActuales);
    }

    public void afectarClima() {
        this.clima.afectarCartas(cartasActuales);
    }


    public List<CartaUnidad> getCartas() {
        return cartasActuales;
    }

    public boolean puedeAgregar(CartaUnidad carta, int idJugadorActual){
        List<String> secciones = carta.getSecciones();

        for (String seccion : secciones) {
            if (seccion.equals(this.clave) && (jugadorId == idJugadorActual)) {
                return true;
            }
        }
        return false;
    }

    public boolean contiene(Carta carta){
        return this.cartasActuales.contains((carta));
    }

    public int getPuntajeTotal() {
        int puntaje = 0;
        for (CartaUnidad carta : cartasActuales) {
            puntaje += carta.ValorActual();
        }
        return puntaje;
    }

    public List<CartaUnidad> removerCartas() {
        List<CartaUnidad> copia = new ArrayList<CartaUnidad>(cartasActuales);
        cartasActuales.clear();
        return copia;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Seccion seccion = (Seccion) obj;
        return jugadorId == seccion.jugadorId && clave.equals(seccion.clave);
    }

    @Override
    public int hashCode() {
        int result = clave.hashCode();
        result = 31 * result + jugadorId;
        return result;
    }

    public int getJugadorId() {
        return jugadorId;
    }
}

