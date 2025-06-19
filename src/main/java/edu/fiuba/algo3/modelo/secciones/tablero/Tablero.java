package edu.fiuba.algo3.modelo.secciones.tablero;

import edu.fiuba.algo3.modelo.Errores.*;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.cartas.especiales.Clima;
import java.util.*;

public class Tablero {

    private final List<Seccion> seccionesJugador1;
    private final List<Seccion> seccionesJugador2;
    private final Map<Jugador, List<Seccion>> seccionesPorJugador; 

    public Tablero(Jugador j1, Jugador j2) throws TipoDeSeccionInvalidaError{
        this.seccionesJugador1 = new ArrayList<>();
        this.seccionesJugador2 = new ArrayList<>();
        this.seccionesPorJugador = new HashMap<>();
        this.seccionesPorJugador.put(j1, seccionesJugador1);
        this.seccionesPorJugador.put(j2, seccionesJugador2);
        inicializarSecciones(j1, j2);
    }

    private void inicializarSecciones(Jugador j1, Jugador j2) throws TipoDeSeccionInvalidaError{
        seccionesJugador1.add(new Asedio());
        seccionesJugador1.add(new Rango());
        seccionesJugador1.add(new CuerpoACuerpo());
        seccionesJugador2.add(new Asedio());
        seccionesJugador2.add(new Rango());
        seccionesJugador2.add(new CuerpoACuerpo());
    }

    public List<Seccion> mostrarTableroParaJugador(Jugador jugador){
        return seccionesPorJugador.get(jugador);
    }

    public void jugarCarta(Carta carta, Seccion seccionDestino) throws TipoDeSeccionInvalidaError, CartaNoJugable {
        if (carta == null || seccionDestino == null) {
            throw new IllegalArgumentException("Carta o sección destino no pueden ser nulos");
        }

        if (carta.esEspecial()) {
            throw new CartaNoJugable(); 
        }

        CartaUnidad cartaUnidad = (CartaUnidad) carta;
        if (!cartaUnidad.cartaAdmiteSeccion(seccionDestino.getNombre())) {
            throw new TipoDeSeccionInvalidaError();
        }

        seccionDestino.agregarCarta(cartaUnidad);
        seccionDestino.sumarPuntaje(cartaUnidad.getPuntaje());
    }

    public void afectarClima(Seccion seccion, Clima nuevoClima) throws TipoDeSeccionInvalidaError, NoSePuedeEliminarClimaSiNoHayClima {
        seccion.afectarClima(nuevoClima);
    }

    public void afectarClimas(){
        for (List<Seccion> listaSecciones : seccionesPorJugador.values()) {
            for (Seccion seccion : listaSecciones) {
                seccion.afectarClima();
            }
        }
    }

    public Map<Jugador, List<Seccion>> getSeccionesPorJugador(){
        return seccionesPorJugador;
    }
    
    public List<Seccion> todasLasSecciones() {
        List<Seccion> todas = new ArrayList<>();
        for (List<Seccion> lista : seccionesPorJugador.values()) {
            todas.addAll(lista);
        }
        return todas;
    }

    public void removerCartasDeValorMaximo() {
        for (Seccion seccion : todasLasSecciones()) {
            List<CartaUnidad> cartas = seccion.getCartas();
            if (!cartas.isEmpty()) {
                int max = cartas.stream()
                    .filter(c -> !c.mostrarCarta().contains("Legendaria"))
                    .mapToInt(CartaUnidad::ValorActual)
                    .max().orElse(0);
                cartas.removeIf(c -> c.ValorActual() == max && !c.mostrarCarta().contains("Legendaria"));
            }
        }
    }

    
}