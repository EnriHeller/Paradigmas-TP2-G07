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

public class DestructoraDeClima extends CartaEspecial implements Carta, Modificador {

    public DestructoraDeClima(String nombre, String descripcion, String tipo, List<String> afectado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.afectado = afectado;
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
        return "DestructorDeClima";
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
