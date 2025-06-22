package edu.fiuba.algo3.modelo.cartas.especiales;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Modificador;
import edu.fiuba.algo3.modelo.principal.Contexto;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;

import java.util.List;

public class TierraArrasada implements Carta, Modificador, CartaEspecial {

    private final String nombre;
    private final String descripcion;
    private final String tipo;
    private final java.util.List<String> afectado;

    public TierraArrasada(String nombre, String descripcion, java.util.List<String> afectado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = "Tierra arrasada";
        this.afectado = afectado;
    }

    public TierraArrasada() {
        this.nombre = "TierraArrasada";
        this.descripcion = "Destruye las cartas de mayor valor en la sección afectada.";
        this.tipo = "Especial";
        this.afectado = List.of("Rango", "Asedio", "CuerpoACuerpo");
    }

    public boolean esEspecial(){ return true;}
    @Override
    public String mostrarCarta(){
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
        return "TierraArrasada" ;
    }

    @Override
    public void modificar(Contexto contextoModificador) throws TipoDeSeccionInvalidaError{
        Tablero tablero = contextoModificador.getTablero();
        tablero.removerCartasDeValorMaximo();
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String getTipo() {
        return tipo;
    }

    @Override
    public List<String> getAfectado() {
        return afectado;
    }
}
