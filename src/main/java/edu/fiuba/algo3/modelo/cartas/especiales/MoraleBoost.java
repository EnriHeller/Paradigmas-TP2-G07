package edu.fiuba.algo3.modelo.cartas.especiales;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Modificador;
import edu.fiuba.algo3.modelo.principal.Contexto;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;
import edu.fiuba.algo3.modelo.secciones.tablero.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.tablero.NoSePuedeEliminarClimaSiNoHayClima;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;

import java.util.List;

public class MoraleBoost extends CartaEspecial implements Carta, Modificador {
    private Modificador modificador;

    public MoraleBoost() {
        this.nombre = "MoraleBoost";
        this.descripcion = "Duplica el valor de las cartas aliadas en la seccion.";
        this.tipo = "Especial";
        this.afectado = List.of("CuerpoACuerpo", "Rango", "Asedio");
    }

    public MoraleBoost(Modificador siguienteModificador) {
        this.nombre = "MoraleBoost";
        this.descripcion = "Duplica el valor de las cartas aliadas en la seccion.";
        this.tipo = "Especial";
        this.afectado = List.of("CuerpoACuerpo", "Rango", "Asedio");
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

        Seccion seccion = contextoModificador.getSeccion();
        List<CartaUnidad> cartasActuales = seccion.getCartas();
        for (CartaUnidad carta : cartasActuales) {
            carta.multiplicarValor(2);
        }
    }
}
