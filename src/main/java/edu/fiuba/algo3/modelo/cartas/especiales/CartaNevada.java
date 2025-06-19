package edu.fiuba.algo3.modelo.cartas.especiales;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.modificadores.Modificador;
import edu.fiuba.algo3.modelo.Errores.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.Errores.NoSePuedeEliminarClimaSiNoHayClima;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;
import edu.fiuba.algo3.modelo.principal.Contexto;

import java.util.List;

public class CartaNevada implements CartaClimatica, Carta, Modificador, CartaEspecial {

    private final String nombre;
    private final String descripcion;
    private final String tipo;
    private final List<String> afectado;

    public CartaNevada(String nombre, String descripcion, List<String> afectado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = "Nevada";
        this.afectado = afectado;
    }

    @Override
    public boolean esEspecial() {
        return true;
    }


    @Override
    public Clima crearClima() {
        return (new ClimaNevado());
    }

    @Override
    public String mostrarModificadores(){
        return "Nevada";
    }

    @Override
    public void modificar(Contexto modificadorContexto) throws TipoDeSeccionInvalidaError, NoSePuedeEliminarClimaSiNoHayClima {
        TableroSingleton tablero = modificadorContexto.getTablero();
        Clima clima = crearClima();

        tablero.afectarClima("CuerpoACuerpo0", clima);
        tablero.afectarClima("CuerpoACuerpo1", clima);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public List<String> getAfectado() {
        return afectado;
    }
}
