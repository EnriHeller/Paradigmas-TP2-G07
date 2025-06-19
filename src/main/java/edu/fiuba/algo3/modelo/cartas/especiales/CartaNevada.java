package edu.fiuba.algo3.modelo.cartas.especiales;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.modificadores.Modificador;
//import edu.fiuba.algo3.modelo.principal.Contexto;
import edu.fiuba.algo3.modelo.Errores.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.Errores.NoSePuedeEliminarClimaSiNoHayClima;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;

public class CartaNevada implements CartaClimatica, Carta, Modificador {

    public CartaNevada(){

    }

    @Override
    public boolean esEspecial() {
        return true;
    }

    @Override
    public String mostrarCarta(){
        return "Nevada";
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
        Tablero tablero = modificadorContexto.getTablero();
        Clima clima = crearClima();

        tablero.afectarClima("CuerpoACuerpo0", clima);
        tablero.afectarClima("CuerpoACuerpo1", clima);
    }

}
