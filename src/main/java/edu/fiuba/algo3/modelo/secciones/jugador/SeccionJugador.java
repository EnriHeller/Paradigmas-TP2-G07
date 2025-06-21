package edu.fiuba.algo3.modelo.secciones.jugador;

import edu.fiuba.algo3.modelo.cartas.Carta;

import java.util.ArrayList;
import java.util.List;

public interface SeccionJugador<T extends Carta> {

    public int cartasRestantes();

    public Carta removerCarta(Carta carta);

    public List<T>  removerCartas(List<T> cartas);

    public void agregarCarta(Carta carta);

    public void agregarCartas(List<T> cartas);

}
