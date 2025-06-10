package edu.fiuba.algo3.modelo.cartas.especiales;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Modificador;
import edu.fiuba.algo3.modelo.principal.Contexto;

import java.util.List;

public class TierraArrasada implements Carta, Modificador {

    private String nombre;
    private Modificador modificador;

    public TierraArrasada(Modificador modificador, String nombre) {
        this.modificador = modificador;
        this.nombre = nombre;
    }

    public boolean esEspecial(){ return true;}
    @Override
    public String mostrarCarta(){
        return (nombre + modificador.mostrarModificadores());
    }

    @Override
    public String mostrarModificadores() {
        return modificador.mostrarModificadores() + " TierraArrasada" ;
    }

    @Override
    public void modificar(Contexto contextoModificador) {
        var tablero = contextoModificador.getTablero();
        //String claveSeccion = contextoModificador.getSeccion();
        try {
            List<CartaUnidad> cartas = tablero.getCartas();
            if (cartas.isEmpty()) return;

            // Buscar el valor máximo entre cartas NO legendarias
            int max = 0;

            for (CartaUnidad carta : cartas) {
                // Usar mostrarCarta() para detectar si tiene el modificador Legendaria
                if (!carta.mostrarCarta().contains("Legendaria")) {
                    int valor = carta.ValorActual();
                    if (valor > max) max = valor;
                }
            }
            if (max == 0) return; // No hay cartas no legendarias

            // Identificar las cartas a eliminar (no legendarias y con valor == max)

            List<CartaUnidad> aEliminar = new java.util.ArrayList<>();

            for (CartaUnidad carta : cartas) {
                if (!carta.mostrarCarta().contains("Legendaria") && carta.ValorActual() == max) {
                    aEliminar.add(carta);
                }
            }

            for (String claveSeccion : tablero.getSecciones().keySet()) {
                tablero.removerCartas(claveSeccion, aEliminar);
            }

        } catch (Exception e) {
            // Manejo simple: no hacer nada si hay error
        }
    }

}
