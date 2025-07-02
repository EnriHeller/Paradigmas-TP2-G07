package edu.fiuba.algo3.modelo.cartas.especiales;

import java.util.List;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Modificador;
import edu.fiuba.algo3.modelo.principal.Contexto;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.secciones.tablero.NoSePuedeEliminarClimaSiNoHayClima;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;
import edu.fiuba.algo3.modelo.secciones.tablero.TipoDeSeccionInvalidaError;

public class MoraleBoost extends CartaEspecial implements Carta, Modificador {
    private Modificador modificador;

    public MoraleBoost() {
        this.nombre = "MoraleBoost";
        this.descripcion = "Duplica el valor de las cartas aliadas en la seccion.";
        this.tipo = "Especial";
        this.afectado = List.of("CuerpoACuerpo", "Rango", "Asedio");
    }

    public MoraleBoost(Modificador siguienteModificador) {
        this.modificador = siguienteModificador;
    }

    @Override
    public boolean esEspecial() { return true; }

    @Override
    public String mostrarCarta() { return nombre; }

    @Override
    public void aplicarModificador(Contexto contexto) {
        try {
            modificar(contexto);
        } catch (TipoDeSeccionInvalidaError e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String mostrarModificadores() {
        return nombre;
    }

    @Override
    public void retrotraerContexto(Contexto contexto) {
        Tablero tablero = contexto.getTablero();
        Seccion seccion = tablero.obtenerSeccion(contexto.getSeccion());
        List<CartaUnidad> cartasActuales = seccion.getCartas();
        for (CartaUnidad carta : cartasActuales) {
            carta.volverValorBase();
        }

        if (modificador != null) modificador.retrotraerContexto(contexto);

    }

    @Override
    public void modificar(Contexto contextoModificador) throws TipoDeSeccionInvalidaError {

        try {
            if (modificador != null) modificador.modificar(contextoModificador);
        } catch (NoSePuedeEliminarClimaSiNoHayClima ignored) {
        }

        Seccion seccionContexto = contextoModificador.getSeccion();

        if(seccionContexto == null){
            Jugador jugador = contextoModificador.getJugador();
            int idJugadorActual = jugador.ordenDeJuego();
            List<Seccion> secciones = contextoModificador.getTablero().obtenerSeccionesDelJugador(idJugadorActual);
    
            for (Seccion seccion : secciones) {
                if (afectado.contains(seccion.getClave())) {
                    List<CartaUnidad> cartasActuales = seccion.getCartas();
                    for (CartaUnidad carta : cartasActuales) {
                        if (!carta.esLegendaria()) {
                            carta.multiplicarValorBase(2);
                        }
                    }
                }
            }
        }else{
            try {
                if (modificador != null) modificador.modificar(contextoModificador);
            } catch (NoSePuedeEliminarClimaSiNoHayClima ignored) {}

            List<CartaUnidad> cartasActuales = seccionContexto.getCartas();
            for (CartaUnidad carta : cartasActuales) {
                if (!carta.esLegendaria()) carta.multiplicarValor(2);
            }
        }
    }
}
