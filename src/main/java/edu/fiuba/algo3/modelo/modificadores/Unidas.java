package edu.fiuba.algo3.modelo.modificadores;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.Errores.NoEsLaMismaUnidad;
import edu.fiuba.algo3.modelo.Errores.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.Errores.NoSePuedeEliminarClimaSiNoHayClima;
import edu.fiuba.algo3.modelo.principal.Contexto;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;

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
    public void modificar(Contexto contextoModificador) throws TipoDeSeccionInvalidaError, NoSePuedeEliminarClimaSiNoHayClima, edu.fiuba.algo3.modelo.Errores.NoSePuedeCumplirSolicitudDeCartas {
        modificador.modificar(contextoModificador);
        Tablero tablero = contextoModificador.getTablero();
        String seccionNombre = contextoModificador.getSeccion();
        // Buscar la sección correspondiente en el tablero
        Seccion seccion = null;
        for (Seccion s : tablero.todasLasSecciones()) {
            if (s.getNombre().equals(seccionNombre)) {
                seccion = s;
                break;
            }
        }
        if (seccion == null) {
            throw new TipoDeSeccionInvalidaError();
        }
        List<CartaUnidad> cartasDeSeccion = seccion.getCartas();
        String nombreCarta = contextoModificador.getCarta().getNombre();
        modificarComportamientoDeCartas(nombreCarta, cartasDeSeccion);
    }

    @Override
    public void retrotraerContexto(Contexto contexto) {
        CartaUnidad carta = contexto.getCarta();
        carta.multiplicarValor(1); // Resetear valor
        modificador.retrotraerContexto(contexto);
    }

    public void modificarComportamientoDeCartas(String nombreCarta, List<CartaUnidad> cartas) {
        int cantidadUnidas = 0;
        for (CartaUnidad carta : cartas) {
            if (carta.getNombre().equals(nombreCarta)) {
                cantidadUnidas++;
            }
        }
        // Solo modificar si hay 2 o más
        if (cantidadUnidas >= 2) {
            for (CartaUnidad carta : cartas) {
                if (carta.getNombre().equals(nombreCarta)) {
                    try {
                        carta.multiplicarValor(nombreCarta, cantidadUnidas);
                    } catch (NoEsLaMismaUnidad ignored) {}
                }
            }
        }
    }
}
