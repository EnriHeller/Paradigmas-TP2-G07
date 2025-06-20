package edu.fiuba.algo3.controller;

import java.io.*;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.modificadores.*;
import edu.fiuba.algo3.modelo.cartas.CartasFactory;
import org.json.simple.parser.ParseException;

public class ConstructorMazo {
    ModificadoresFactory modificadoresFactory;
    CartasFactory cartasFactory;
    JSONParser analizadorJson;

    public ConstructorMazo(ModificadoresFactory modificadoresFactory, CartasFactory cartasFactory, JSONParser analizador) {
        this.modificadoresFactory = modificadoresFactory;
        this.cartasFactory = cartasFactory;
        this.analizadorJson = analizador;
    }

    public Mazo personalizarMazo(String rutaCartasUnidad, String rutaCartasEspeciales) throws IOException {
        
        List<Carta> cartas = new ArrayList<>();

        try {
            JSONArray unidades = (JSONArray) analizadorJson.parse(new FileReader(rutaCartasUnidad));
            JSONArray especiales = (JSONArray) analizadorJson.parse(new FileReader(rutaCartasEspeciales));

            construirCartas(cartas, unidades, especiales);

        } catch (ParseException e) {
            throw new IOException("Error al parsear los archivos JSON de mazo personalizado", e);
        }

        return new Mazo(cartas);
    }

    public List<Mazo> construirMazos(InputStream jsonStream) throws IOException, ParseException {

        // Cambiar de FileReader a InputStreamReader
        InputStreamReader reader = new InputStreamReader(jsonStream);

        JSONObject rutaJson = (JSONObject) analizadorJson.parse(reader);

        JSONObject mazoUno = obtenerMazo(rutaJson,"mazo_jugador_uno");
        JSONObject mazoDos = obtenerMazo(rutaJson,"mazo_jugador_dos");

        JSONArray unidades1 = obtenerCartasMazo(mazoUno, "unidades");
        JSONArray especiales1 = obtenerCartasMazo(mazoUno,"especiales");

        JSONArray unidades2 = obtenerCartasMazo(mazoDos, "unidades");
        JSONArray especiales2 = obtenerCartasMazo(mazoDos,"especiales");

        List<Carta> cartasJugador1 = new ArrayList<>();
        List<Carta> cartasJugador2 = new ArrayList<>();

        List<Mazo> mazos = new ArrayList<>();

        construirCartas(cartasJugador1, unidades1, especiales1);
        construirCartas(cartasJugador2, unidades2, especiales2);

        mazos.add(new Mazo(cartasJugador1));
        mazos.add(new Mazo(cartasJugador2));

        return mazos;
    }



    public JSONObject obtenerMazo(JSONObject ruta ,String mazoJugador){
        return (JSONObject) ruta.get(mazoJugador);
    }

    public JSONArray obtenerCartasMazo(JSONObject ruta, String tipoCartas){
        return (JSONArray) ruta.get(tipoCartas);
    }


    public void construirCartas(List<Carta> cartas, JSONArray unidades, JSONArray especiales){
        construirCartasUnidad(cartas, unidades);
        construirCartasEspecial(cartas, especiales);
    }

    private void construirCartasUnidad(List<Carta> cartas, JSONArray unidades) {
        for (Object obj : unidades) {
            JSONObject carta = (JSONObject) obj;
            String nombre = (String) carta.get("nombre");
            long puntos = (long) carta.get("puntos");
            String secciones = (String) carta.get("seccion");
            JSONArray modificadores = (JSONArray) carta.get("modificador");

            List<String> seccionesList = listarSecciones(secciones);
            Modificador mod = inicializarModificadores(modificadores);

            Carta cartaUnidad = cartasFactory.crearCarta("u", nombre, seccionesList, puntos, mod, "", "");
            cartas.add(cartaUnidad);
        }
    }

    private void construirCartasEspecial(List<Carta> cartas, JSONArray especiales) {
        for (Object obj : especiales) {
            JSONObject carta = (JSONObject) obj;
            String nombre = (String) carta.get("nombre");
            String descripcion = (String) carta.get("descripcion");
            String tipo = (String) carta.get("tipo");

            List<String> secciones = null;
            if (carta.containsKey("afectado")) {
                JSONArray afectados = (JSONArray) carta.get("afectado");
                secciones = listar(afectados);
            }

            Carta cartaEspecial = cartasFactory.crearCarta("e", nombre, secciones, 0, new Base(), descripcion, tipo);
            cartas.add(cartaEspecial);
        }
    }


    public Modificador inicializarModificadores(JSONArray modificadores){
        List<String> listaModificadores = new ArrayList<>();
        Modificador actual = new Base();
        for (Object modificador : modificadores) {
            String nombreModificador = (String) modificador;
            listaModificadores.add(nombreModificador);
        }

        if (!listaModificadores.isEmpty()) {
            for (int i = listaModificadores.size() - 1; i >= 0; i--) {
                String modificador = listaModificadores.get(i);
                actual = modificadoresFactory.crearModificador(modificador, actual);
            }
        }
        return actual;
    }

    public List<String> listarSecciones(String secciones){
        String[] listaCadenaDatos = secciones.split(", ");
        List<String> lista = new ArrayList<>(Arrays.asList(listaCadenaDatos));
        return lista;
    }

    public List<String> listar(JSONArray elementos){
        List<String> lista = new ArrayList<>();
        for (Object elemento : elementos) {
            String nombreElemento = (String) elemento;
            lista.add(nombreElemento);
        }
        return lista;
    }

}
