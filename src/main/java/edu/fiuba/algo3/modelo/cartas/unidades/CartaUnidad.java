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

    public void multiplicarValor(int n) {
        if(n == 1){
            this.valorActual = this.valorBase;
        }
        this.valorActual = n * this.valorActual;

    }

    public void sumaValor( int aSumar) {

        this.valorActual = aSumar + this.valorActual;

    }

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

    public boolean coincideSeccion(String seccion){
        return this.secciones.contains(seccion);
    }

    public List<String> getSecciones(){
        return this.secciones;
    }

    public void prepararContexto(Contexto contexto) {
        modificador.prepararContexto(contexto);
    }
}
