package edu.fiuba.algo3.modelo.cartas.unidades;

import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.modificadores.Modificador;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.principal.Contexto;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.tablero.NoSePuedeEliminarClimaSiNoHayClima;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class CartaUnidad implements Carta, Puntuable {

    private String nombre;
    private List<String> secciones;
    private final int valorBase;
    private int valorActual;
    private Modificador modificador;
    //Constructores
    public CartaUnidad(String nombre,List<String> secciones, int valor, Modificador modificador) {
        this.nombre = nombre;
        this.secciones = secciones;
        this.valorActual = valor;
        this.valorBase = valor;
        this.modificador = modificador;
    }

    @Override
    public void aplicarModificador(Contexto contexto) {
        try {
            this.modificador.modificar(contexto);
        } catch (TipoDeSeccionInvalidaError | NoSePuedeEliminarClimaSiNoHayClima ignored) {
        }
    }

    @Override
    public void retrotraerModificacion(Contexto contexto){
        modificador.retrotraerContexto(contexto);
    }

    public String getNombre() {
        return this.nombre;
    }

    public String mostrarCarta(){

        return (nombre + modificador.mostrarModificadores());

    }

    public void modificarValor(int nuevoValor) {

        this.valorActual = nuevoValor;
    }

    public void multiplicarValor(double n) {
        if(n == 1){
            this.valorActual = this.valorBase;
        }
        this.valorActual = (int) (n * this.valorActual);

    }

    public void sumaValor( int aSumar) {

        this.valorActual = aSumar + this.valorActual;

    }

    //METODOS PARA AGIL. Se podria hacer test de lo interno.
    public void agregarSeccion(String seccion) {
        this.secciones.add(seccion);
    }

    public void quitarUltimaSeccion() {
        if (!this.secciones.isEmpty()) {
            this.secciones.remove(this.secciones.size() - 1);
        }
    }

    @Override
    public boolean esEspecial(){
        return false;
    }

    public int ValorActual(){
        return this.valorActual;
    }



    public List<String> getSecciones(){
        return this.secciones;
    }

    public void prepararContexto(Contexto contexto) {
        modificador.prepararContexto(contexto);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        CartaUnidad otra = (CartaUnidad) obj;

        return valorActual == otra.valorActual &&
                valorBase == otra.valorBase &&
                modificador == otra.modificador &&
                nombre.equals(otra.nombre) &&
                secciones.equals(otra.secciones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, secciones, valorActual, valorBase, modificador);
    }

    public String getModificadores() {
        return modificador.mostrarModificadores();
    }
}
