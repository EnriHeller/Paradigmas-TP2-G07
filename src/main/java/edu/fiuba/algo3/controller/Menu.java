package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.modelo.cartas.CartasFactory;
import edu.fiuba.algo3.modelo.modificadores.ModificadoresFactory;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.principal.UnoDeLosMazosNoCumpleRequitos;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import org.json.simple.parser.JSONParser;

import java.io.InputStream;
import java.util.List;
import java.util.Objects;

public class Menu {

    // Mazo base
    private final Mazo mazoA;
    private final Mazo mazoB;

    // Mazos seleccionados
    private Mazo mazoJugador1;
    private Mazo mazoJugador2;

    // Instancia de juego creada
    private edu.fiuba.algo3.modelo.principal.Juego juego;

    public Menu() {
        try {
            ConstructorMazo constructor = new ConstructorMazo(
                    new ModificadoresFactory(),
                    new CartasFactory(),
                    new JSONParser()
            );

            InputStream jsonStream = Objects.requireNonNull(getClass().getResourceAsStream("/json/gwent.json"));
            List<Mazo> mazos = constructor.construirMazos(jsonStream);

            mazoA = mazos.get(0);
            mazoB = mazos.get(1);

        } catch (Exception e) {
            throw new RuntimeException("Error al cargar mazos desde JSON", e);
        }
    }

    public Mazo getMazoA() { return mazoA; }
    public Mazo getMazoB() { return mazoB; }
    public Mazo getMazoJugador1() { return mazoJugador1; }
    public Mazo getMazoJugador2() { return mazoJugador2; }

    public void seleccionarMazo(boolean esJugador1, Mazo mazoSeleccionado) {
        if (esJugador1) {
            mazoJugador1 = mazoSeleccionado;
            mazoJugador2 = (mazoSeleccionado == mazoA) ? mazoB : mazoA;
        } else {
            mazoJugador2 = mazoSeleccionado;
            mazoJugador1 = (mazoSeleccionado == mazoA) ? mazoB : mazoA;
        }
    }

    public boolean nombresValidos(String nombre1, String nombre2) {
        return !nombre1.trim().isEmpty() && !nombre2.trim().isEmpty() && !nombre1.trim().equals(nombre2.trim());
    }

    public boolean mazosElegidos() {
        return mazoJugador1 != null && mazoJugador2 != null;
    }

    public void crearPartida(String nombre1, String nombre2) throws TipoDeSeccionInvalidaError, UnoDeLosMazosNoCumpleRequitos {
        Jugador j1 = new Jugador(nombre1.trim());
        Jugador j2 = new Jugador(nombre2.trim());

        j1.agregarMazo(mazoJugador1);
        j2.agregarMazo(mazoJugador2);

        this.juego = new edu.fiuba.algo3.modelo.principal.Juego(j1, j2);
        // La vista debe encargarse de mostrar la siguiente escena
    }

    public List<Jugador> getJugadoresInicializados(String nombre1, String nombre2) {
        Jugador j1 = new Jugador(nombre1.trim());
        Jugador j2 = new Jugador(nombre2.trim());
        j1.agregarMazo(mazoJugador1);
        j2.agregarMazo(mazoJugador2);
        return List.of(j1, j2);
    }

    public edu.fiuba.algo3.modelo.principal.Juego getJuego() {
        return this.juego;
    }
}
