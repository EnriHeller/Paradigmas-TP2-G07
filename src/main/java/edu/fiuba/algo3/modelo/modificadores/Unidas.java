package edu.fiuba.algo3.modelo.modificadores;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;

import java.util.List;

public class Unidas implements Modificador {

    private final Modificador modificador;

    public Unidas(Modificador modificador) {
        this.modificador = modificador;
    }

    @Override
    public String mostrarModificadores() {
        return modificador.mostrarModificadores() + " Unidad";
    }

    @Override
    public void modificar() throws TipoDeSeccionInvalidaError {
        Tablero secciones = Tablero.getInstancia();
        //List<CartaUnidad> cartas = secciones.getCartas(seccion);
        modificador.modificar();
        //modificarComportamientoDeCartas(cartas);

    }

    public void modificarComportamiento(CartaUnidad carta) {
        carta.modificarValor(2 * carta.ValorActual());
    }

    public void modificarComportamientoDeCartas(List<CartaUnidad> cartas) {
        int cantidadUnidad = (int) cartas.stream()
                .filter(carta -> carta.mostrarCarta().contains("Unidad"))
                .count();

        // Solo modificar si hay 2 o más
        if (cantidadUnidad >= 2) {
            for (CartaUnidad carta : cartas) {
                if (carta.mostrarCarta().contains("Unidad")) {
                    modificarComportamiento(carta);
                }
            }
        }
    }

}
