package edu.fiuba.algo3.modelo.cartas.especiales;

import java.util.Arrays;
import java.util.List;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.modificadores.Modificador;
import edu.fiuba.algo3.modelo.principal.Contexto;
import edu.fiuba.algo3.modelo.secciones.tablero.NoSePuedeEliminarClimaSiNoHayClima;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;
import edu.fiuba.algo3.modelo.secciones.tablero.TipoDeSeccionInvalidaError;

public class TiempoDespejado extends CartaEspecial implements Carta, Modificador {

    public TiempoDespejado() {
        this.nombre = "Tiempo Despejado";
        this.descripcion = "Elimina el efecto de clima en todas las secciones afectadas.";
        this.tipo = "Especial";
        this.afectado = Arrays.asList("Rango", "Asedio", "CuerpoACuerpo");
    }

    @Override
    public boolean esEspecial() {
        return true;
    }

    @Override
    public String mostrarCarta() {
        // Debe devolver el nombre normalizado para la imagen correspondiente
        return "tiempoDespejado";
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
    public String mostrarModificadores() {
        return "tiempoDespejado";
    }

    @Override
    public void modificar(Contexto modificadorContexto) throws TipoDeSeccionInvalidaError, NoSePuedeEliminarClimaSiNoHayClima {
        Tablero tablero = modificadorContexto.getTablero();
        List<String> claves = Arrays.asList("Rango", "Asedio", "CuerpoACuerpo");
        int seccionesSinClima = 0;
        for (int i = 0; i < 2; i++) {
            for (String clave : claves) {
                Seccion seccion = new Seccion(clave, i);
                if (!tablero.afectadaClima(seccion)) {
                    seccionesSinClima++;
                } else {
                    tablero.afectarClima(seccion, new SinClima());
                }
            }
        }
        if (seccionesSinClima == 6) {
            throw new NoSePuedeEliminarClimaSiNoHayClima();
        }
    }
}
