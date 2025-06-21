package edu.fiuba.algo3.modelo.cartas.especiales;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.modificadores.Modificador;
import edu.fiuba.algo3.modelo.principal.Contexto;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.tablero.NoSePuedeEliminarClimaSiNoHayClima;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
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
    public void aplicarModificador(Contexto contexto) {
        try {
            modificar(contexto);
        } catch (TipoDeSeccionInvalidaError | NoSePuedeEliminarClimaSiNoHayClima e) {
            throw new RuntimeException(e);
        }
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

        tablero.afectarClima(new Seccion("CuerpoACuerpo", 0), clima);
        tablero.afectarClima(new Seccion("CuerpoACuerpo", 1), clima);

    }

}
