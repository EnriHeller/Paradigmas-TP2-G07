package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.controller.ConstructorMazo;
import edu.fiuba.algo3.modelo.cartas.CartasFactory;
import edu.fiuba.algo3.modelo.modificadores.ModificadoresFactory;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.principal.UnoDeLosMazosNoCumpleRequitos;
import edu.fiuba.algo3.modelo.secciones.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.vistas.Juego.JuegoView;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import org.json.simple.parser.JSONParser;

import java.io.InputStream;
import java.util.List;
import java.util.Objects;

public class MenuView {

    // Mazo base
    private final Mazo mazoA;
    private final Mazo mazoB;

    // Mazos seleccionados
    private Mazo mazoJugador1;
    private Mazo mazoJugador2;

    // Componentes desde el FXML
    @FXML private TextField inputJ1;
    @FXML private TextField inputJ2;
    @FXML private Button botonIniciar;

    @FXML private Button botonMazo1J1;
    @FXML private Button botonMazo2J1;
    @FXML private Button botonMazo1J2;
    @FXML private Button botonMazo2J2;

    public MenuView() {
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

    @FXML
    public void construir() {
        botonIniciar.setDisable(true);

        inputJ1.textProperty().addListener((obs, o, n) -> validarInicio());
        inputJ2.textProperty().addListener((obs, o, n) -> validarInicio());

        botonMazo1J1.setOnAction(e -> seleccionarMazo(true, mazoA));
        botonMazo2J1.setOnAction(e -> seleccionarMazo(true, mazoB));
        botonMazo1J2.setOnAction(e -> seleccionarMazo(false, mazoA));
        botonMazo2J2.setOnAction(e -> seleccionarMazo(false, mazoB));
    }

    private void seleccionarMazo(boolean esJugador1, Mazo mazoSeleccionado) {
        asignarMazos(esJugador1, mazoSeleccionado);
        actualizarEstilos();
        deshabilitarBotones();
        validarInicio();
    }

    private void asignarMazos(boolean esJugador1, Mazo mazoSeleccionado) {
        if (esJugador1) {
            mazoJugador1 = mazoSeleccionado;
            mazoJugador2 = (mazoSeleccionado == mazoA) ? mazoB : mazoA;
        } else {
            mazoJugador2 = mazoSeleccionado;
            mazoJugador1 = (mazoSeleccionado == mazoA) ? mazoB : mazoA;
        }
    }

    private void actualizarEstilos() {
        String estiloNormal = "-fx-background-color: rgba(255,255,255,0.8);";
        String estiloSeleccionado = "-fx-background-color: #444; -fx-text-fill: white;";

        botonMazo1J1.setStyle(mazoJugador1 == mazoA ? estiloSeleccionado : estiloNormal);
        botonMazo2J1.setStyle(mazoJugador1 == mazoB ? estiloSeleccionado : estiloNormal);
        botonMazo1J2.setStyle(mazoJugador2 == mazoA ? estiloSeleccionado : estiloNormal);
        botonMazo2J2.setStyle(mazoJugador2 == mazoB ? estiloSeleccionado : estiloNormal);
    }

    private void deshabilitarBotones() {
        botonMazo1J1.setDisable(true);
        botonMazo2J1.setDisable(true);
        botonMazo1J2.setDisable(true);
        botonMazo2J2.setDisable(true);
    }

    private void validarInicio() {
        boolean nombresValidos = !inputJ1.getText().trim().isEmpty()
                && !inputJ2.getText().trim().isEmpty()
                && !inputJ1.getText().trim().equals(inputJ2.getText().trim());

        boolean mazosElegidos = mazoJugador1 != null && mazoJugador2 != null;

        botonIniciar.setDisable(!(nombresValidos && mazosElegidos));

        botonIniciar.setOnAction(e -> {
            try {
                iniciarPartida();
            } catch (Exception | TipoDeSeccionInvalidaError ex) {
                mostrarAlerta("Error al iniciar el juego", ex.getMessage());
            }
        });
    }

    private void iniciarPartida() throws TipoDeSeccionInvalidaError, UnoDeLosMazosNoCumpleRequitos {
        Jugador j1 = new Jugador(inputJ1.getText().trim());
        Jugador j2 = new Jugador(inputJ2.getText().trim());

        j1.agregarMazo(mazoJugador1);
        j2.agregarMazo(mazoJugador2);

        Juego juego = new Juego(j1, j2);
        JuegoView vista = new JuegoView(juego);
        App.cambiarEscena(new Scene(vista.construir(), 1000, 700));
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
