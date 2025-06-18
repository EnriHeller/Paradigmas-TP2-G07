package edu.fiuba.algo3.modelo.cartas.unidades;

import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.modificadores.Modificador;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.principal.Contexto;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.tablero.NoSePuedeEliminarClimaSiNoHayClima;

import java.util.List;
import java.util.ArrayList;

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

    public CartaUnidad(String nombre, List<String> secciones, int valor) {
        this.nombre = nombre;
        this.secciones = secciones;
        this.valorActual = valor;
        this.valorBase = valor;
        this.modificador = new Base();
    }

    // Constructor sin argumentos para tests
    public CartaUnidad() {
        this.nombre = "";
        this.secciones = new ArrayList<>();
        this.valorActual = 0;
        this.valorBase = 0;
        this.modificador = new Base();
    }

    public void aplicarModificador(Contexto contexto) throws TipoDeSeccionInvalidaError {
        try {
            this.modificador.modificar(contexto);
        } catch (NoSePuedeEliminarClimaSiNoHayClima ignored) {
        }
    }

    public int getPuntajeBase() {
        return this.valorBase;
    }

    public List<String> puedeColocarse() {
        return this.secciones;
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

    public void multiplicarValor(String posibleCartaMismoModificador, int n) throws NoEsLaMismaUnidad {
        if (!posibleCartaMismoModificador.equals(this.nombre)){
            throw new NoEsLaMismaUnidad("No es la misma Unidad");
        }
        this.valorActual = n * this.valorActual;

    }
    public void multiplicarValor(int n) {

        this.valorActual = n * this.valorActual;

    }

    public void sumaValor( int aSumar) {

        this.valorActual = aSumar + this.valorActual;

    }

    public void agregarSeccion(String seccion) {
        this.secciones.add(seccion);
    }

    @Override
    public boolean esEspecial(){
        return false;
    }

    public int ValorActual(){
        return this.valorActual;
    }

    public boolean coincideSeccion(String seccion){
        return this.secciones.contains(seccion);
    }

    public List<String> getSecciones(){
        return this.secciones;
    }

    public void prepararContexto(Contexto contexto) {
        modificador.prepararContexto(contexto);
    }

    @Override
    public String toString() {
        return "CartaUnidad{" +
                "nombre='" + nombre + '\'' + ", modificador=" + modificador.getClass().getSimpleName() +
                '}';
    }
}
