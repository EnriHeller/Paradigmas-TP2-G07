package edu.fiuba.algo3.modelo.cartas.especiales;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.modificadores.Modificador;
import edu.fiuba.algo3.modelo.principal.Contexto;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;
import edu.fiuba.algo3.modelo.secciones.tablero.TipoDeSeccionInvalidaError;

public class TierraArrasada extends CartaEspecial implements Carta, Modificador {

    public TierraArrasada() {
        this.nombre = "TierraArrasada";
        this.descripcion = "Destruye las cartas de mayor valor en la sección afectada.";
        this.tipo = "Especial";
        this.afectado = java.util.List.of("Rango", "Asedio", "CuerpoACuerpo");
    }

    @Override
    public boolean esEspecial() { return true; }

    @Override
    public String mostrarCarta() {
        return "TierraArrasada";
    }

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
        return "TierraArrasada";
    }

    @Override
    public void modificar(Contexto contextoModificador) throws TipoDeSeccionInvalidaError {
        Tablero tablero = contextoModificador.getTablero();
        tablero.removerCartasDeValorMaximo();
    }
}
