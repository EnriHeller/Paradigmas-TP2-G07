package edu.fiuba.algo3.modelo.cartas.especiales;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.modificadores.Modificador;
import edu.fiuba.algo3.modelo.principal.Contexto;
import edu.fiuba.algo3.modelo.secciones.tablero.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.tablero.NoSePuedeEliminarClimaSiNoHayClima;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;

import java.util.Arrays;

public class TormentaDeSkellige extends CartaEspecial implements CartaClimatica, Carta, Modificador {

    public TormentaDeSkellige(){
        this.nombre = "Tormenta de Skellige";
        this.descripcion = "Las cartas de esa secciones afectadas tendran 1 de puntaje.";
        this.tipo = "Especial";
        this.afectado = Arrays.asList("Rango", "CuerpoACuerpo");
    }

    @Override
    public boolean esEspecial() {
        return true;
    }

    @Override
    public String mostrarCarta(){
        return "TormentaDeSkellige";
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
        return (new ClimaUnidades1Puntaje());
    }

    @Override
    public String mostrarModificadores(){
        return "TormentaDeSkellige";
    }

    @Override
    public void modificar(Contexto modificadorContexto) throws TipoDeSeccionInvalidaError, NoSePuedeEliminarClimaSiNoHayClima {
        Tablero tablero = modificadorContexto.getTablero();
        Clima clima = crearClima();

        tablero.afectarClima(new Seccion("CuerpoACuerpo", 0), clima);
        tablero.afectarClima(new Seccion("CuerpoACuerpo", 1), clima);
        tablero.afectarClima(new Seccion("Rango", 0), clima);
        tablero.afectarClima(new Seccion("Rango", 1), clima);

    }

}
