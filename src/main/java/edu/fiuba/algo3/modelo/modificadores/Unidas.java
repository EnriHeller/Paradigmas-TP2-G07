package edu.fiuba.algo3.modelo.modificadores;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.cartas.unidades.NoEsLaMismaUnidad;
import edu.fiuba.algo3.modelo.principal.Contexto;
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
        return modificador.mostrarModificadores() + " Unidas";
    }

    @Override
    public void modificar(Contexto contextoModificador) throws TipoDeSeccionInvalidaError {
        modificador.modificar(contextoModificador);
        Tablero secciones = contextoModificador.getTablero();
        List<CartaUnidad> cartasDeSeccion = secciones.getCartas(contextoModificador.getSeccion() + String.valueOf(contextoModificador.getJugador()));
        String nombreCarta = contextoModificador.getCarta().getNombre();
        modificarComportamientoDeCartas(nombreCarta, cartasDeSeccion);

    }

    public void modificarComportamientoDeCartas(String nombreCarta,List<CartaUnidad> cartas) {
        int cantidadUnidas = (int) cartas.stream()
                .filter(carta -> carta.mostrarCarta().contains(nombreCarta))
                .count();

        // Solo modificar si hay 2 o más
        if (cantidadUnidas >= 2) {
            for (CartaUnidad carta : cartas) {
                if (carta.mostrarCarta().contains("Unidas")) {
                    try{
                        carta.multiplicarValor(nombreCarta, cantidadUnidas);
                    } catch (NoEsLaMismaUnidad ignored){

                    }
                }
            }
        }
    }

}
