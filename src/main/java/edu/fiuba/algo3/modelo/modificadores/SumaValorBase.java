package edu.fiuba.algo3.modelo.modificadores;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.principal.Contexto;
import edu.fiuba.algo3.modelo.Errores.*;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;

import java.util.List;

public class SumaValorBase implements Modificador{
    private final Modificador modificador;
    private String dondeSeJugo;


    public SumaValorBase(Modificador modificador){
        this.modificador = modificador;
        this.dondeSeJugo = "";
    }

    @Override
    public String mostrarModificadores() {
        return modificador.mostrarModificadores() + " Base" ;
    }

    @Override
    public void modificar(Contexto contextoModificador) throws edu.fiuba.algo3.modelo.Errores.TipoDeSeccionInvalidaError, edu.fiuba.algo3.modelo.Errores.NoSePuedeEliminarClimaSiNoHayClima, edu.fiuba.algo3.modelo.Errores.NoSePuedeCumplirSolicitudDeCartas {
        modificador.modificar(contextoModificador);
        String seccionClave = contextoModificador.getSeccion().getNombre();
        Jugador jugador = contextoModificador.getJugadorClase();
        Tablero tablero = contextoModificador.getTablero();
        Seccion seccion = null;
        for (Seccion s : tablero.mostrarTableroParaJugador(jugador)) {
            if (s.getNombre().equals(seccionClave)) {
                seccion = s;
                break;
            }
        }
        if (seccion == null) throw new edu.fiuba.algo3.modelo.Errores.TipoDeSeccionInvalidaError();
        this.dondeSeJugo = seccionClave;
        List<CartaUnidad> cartas = seccion.getCartas();
        for(CartaUnidad carta : cartas){
            carta.sumaValor(1);
        }
    }

    @Override
    public void retrotraerContexto(Contexto contexto) {
        String seccionClave = this.dondeSeJugo;
        Jugador jugador = contexto.getJugadorClase();
        Tablero tablero = contexto.getTablero();
        Seccion seccion = null;
        for (Seccion s : tablero.mostrarTableroParaJugador(jugador)) {
            if (s.getNombre().equals(seccionClave)) {
                seccion = s;
                break;
            }
        }
        if (seccion == null) return; // No se puede retrotraer si no existe la sección
        List<CartaUnidad> cartas = seccion.getCartas();
        for (CartaUnidad carta : cartas) {
            carta.sumaValor(-1);
        }
        modificador.retrotraerContexto(contexto);
    }

}
