package edu.fiuba.algo3.modelo.secciones.jugador;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.principal.NoSePuedeCumplirSolicitudDeCartas;
import java.util.Collections;
import java.util.List;

public class Mazo extends SeccionJugador<Carta> {
    public Mazo(List<Carta> cartas) {
        super();
        this.cartas.addAll(cartas);
        mezclar();
    }

    @Override
    public void agregarCarta(Carta carta) {
        cartas.add(carta);
    }

    public void mezclar() {
        Collections.shuffle(this.cartas);
    }

    public List<Carta> repartirCarta(int n) throws NoSePuedeCumplirSolicitudDeCartas {
        if (n > cartas.size()) {
            throw new NoSePuedeCumplirSolicitudDeCartas();
        }
        List<Carta> cartasRepartidas = new java.util.ArrayList<>();
        for (int i = 0; i < n; i++) {
            Carta cartaSacada = cartas.remove(cartas.size() - 1);
            cartasRepartidas.add(cartaSacada);
        }
        return cartasRepartidas;
    }
}