package edu.fiuba.algo3.modelo.cartas.especiales;

import edu.fiuba.algo3.modelo.Errores.*;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Modificador;
import edu.fiuba.algo3.modelo.principal.Contexto;
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


    public boolean esEspecial(){ return true;}
    @Override
    public String getTipo() {
        return tipo;
    }

    @Override
    public String mostrarModificadores() {
        return "TierraArrasada" ;
    }

    @Override
    public void modificar(Contexto contexto) throws TipoDeSeccionInvalidaError{
        Tablero tablero = contexto.getTablero();
        tablero.removerCartasDeValorMaximo();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public java.util.List<String> getAfectado() {
        return afectado;
    }

}
