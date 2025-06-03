package edu.fiuba.algo3.modelo;

import java.util.List;

public class Unidad implements Modificador {

    private final Modificador modificador;

    public Unidad() {
        this.modificador = new Base();
    }

    public Unidad(Modificador modificador) {
        this.modificador = modificador;
    }

    @Override
    public String mostrarModificadores() {
        return modificador.mostrarModificadores() + " Unidad";
    }

    @Override
    public void modificarComportamiento(CartaUnidad carta) {
        carta.modificarValor(2 * carta.ValorActual());
    }

    @Override
    public void modificarComportamientoSeccion(List<CartaUnidad> cartas) {
        int cantidadUnidad = (int) cartas.stream()
                .filter(carta -> carta.mostrarCarta().contains("Unidad"))
                .count();

        // Solo modificar si hay 2 o más
        if (cantidadUnidad >= 2) {
            for (CartaUnidad carta : cartas) {
                if (carta.mostrarCarta().contains("Unidad")) {
                    carta.modificarValor(cantidadUnidad * carta.getPuntajeBase());
                }
            }
        }
    }

}
