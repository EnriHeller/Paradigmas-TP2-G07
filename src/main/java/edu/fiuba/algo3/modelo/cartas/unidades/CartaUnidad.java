package edu.fiuba.algo3.modelo.cartas.unidades;

import edu.fiuba.algo3.modelo.modificadores.Base;
import edu.fiuba.algo3.modelo.modificadores.Modificador;
import edu.fiuba.algo3.modelo.principal.Contexto;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.modelo.Errores.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class CartaUnidad implements Carta {

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

    public boolean cartaAdmiteSeccion(String seccionDestino) {
        return secciones.contains(seccionDestino);
    }

    @Override
    public void aplicarModificador(Contexto contexto) {
        try {
            this.modificador.modificar(contexto);
        } catch (TipoDeSeccionInvalidaError | NoSePuedeEliminarClimaSiNoHayClima | NoSePuedeCumplirSolicitudDeCartas ignored) {
        }
    }

    public int getPuntaje() {
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

    // public void prepararContexto(Contexto contexto) {
    //     modificador.prepararContexto(contexto);
    // }


    //Funcion para debug (no borrar hasta entrega final)
    @Override
    public String toString() {
        return "CartaUnidad{" +
                "nombre='" + nombre + '\'' + ", modificador=" + modificador.getClass().getSimpleName() +
                '}';
    }

    public String getModificadores() {
        return modificador.mostrarModificadores() + " Base" ;
    } // para construir la carta visual

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

}
