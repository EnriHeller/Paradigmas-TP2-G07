package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.cartas.CartasFactory;
import edu.fiuba.algo3.modelo.modificadores.ModificadoresFactory;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import org.json.simple.parser.JSONParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import edu.fiuba.algo3.modelo.principal.NoSePuedeCumplirSolicitudDeCartas;

public class MenuView {

    private final Mazo mazoA;
    private final Mazo mazoB;

    private Mazo mazoJugador1;
    private Mazo mazoJugador2;

    private final TextField inputJ1 = new TextField();
    private final TextField inputJ2 = new TextField();
    private final Button botonIniciar = new Button("➜");

    // Estilo botones
    private static final String ESTILO_MAZO = "-fx-background-color: rgba(255,255,255,0.8);";
    private static final String ESTILO_MAZO_SELECCIONADO = "-fx-background-color: #444; -fx-text-fill: white;";

    // Botones seleccionables
    private Button botonMazo1J1;
    private Button botonMazo2J1;
    private Button botonMazo1J2;
    private Button botonMazo2J2;

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

    public BorderPane construir() {
        BorderPane layout = new BorderPane();
        layout.setPrefSize(800, 600);

        // Fondo
        Image fondo = new Image(Objects.requireNonNull(getClass().getResource("/imagenes/menu2.png")).toExternalForm());
        BackgroundImage bgImage = new BackgroundImage(
                fondo,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false)
        );
        layout.setBackground(new Background(bgImage));

        // Centro: secciones jugadores
        HBox seccionesJugadores = new HBox(40);
        seccionesJugadores.setPadding(new Insets(40));
        seccionesJugadores.setAlignment(Pos.CENTER);

        VBox seccionJ1 = construirSeccionJugador("Jugador 1", inputJ1, true);
        VBox seccionJ2 = construirSeccionJugador("Jugador 2", inputJ2, false);

        seccionesJugadores.getChildren().addAll(seccionJ1, seccionJ2);
        layout.setCenter(seccionesJugadores);

        // Botón comenzar
        botonIniciar.setStyle("-fx-font-size: 20px; -fx-background-radius: 50%; -fx-padding: 10;");
        botonIniciar.setDisable(true);
        botonIniciar.setOnAction(e -> iniciarPartida());

        HBox contenedorBoton = new HBox(botonIniciar);
        contenedorBoton.setAlignment(Pos.TOP_CENTER);
        contenedorBoton.setPadding(new Insets(20));
        layout.setBottom(contenedorBoton);

        return layout;
    }

    private VBox construirSeccionJugador(String titulo, TextField campoNombre, boolean esJugador1) {
        VBox seccion = new VBox(10);
        seccion.setAlignment(Pos.TOP_CENTER);

        Label label = new Label(titulo);
        label.setStyle("-fx-text-fill: white; -fx-font-size: 16;");

        campoNombre.setPromptText("Nombre...");
        campoNombre.setMaxWidth(200);
        campoNombre.textProperty().addListener((obs, o, n) -> validarInicio());

        Label elegir = new Label("Elegí tu mazo:");
        elegir.setStyle("-fx-text-fill: white;");

        Button botonMazo1 = new Button("Mazo A");
        Button botonMazo2 = new Button("Mazo B");

        botonMazo1.setStyle(ESTILO_MAZO);
        botonMazo2.setStyle(ESTILO_MAZO);

        botonMazo1.setOnAction(e -> seleccionarMazo(esJugador1, mazoA));
        botonMazo2.setOnAction(e -> seleccionarMazo(esJugador1, mazoB));

        if (esJugador1) {
            botonMazo1J1 = botonMazo1;
            botonMazo2J1 = botonMazo2;
        } else {
            botonMazo1J2 = botonMazo1;
            botonMazo2J2 = botonMazo2;
        }

        HBox mazos = new HBox(10, botonMazo1, botonMazo2);
        mazos.setAlignment(Pos.CENTER);

        seccion.getChildren().addAll(label, campoNombre, elegir, mazos);
        return seccion;
    }

    private void seleccionarMazo(boolean esJugador1, Mazo mazoSeleccionado) {
        if (esJugador1) {
            mazoJugador1 = mazoSeleccionado;
            mazoJugador2 = (mazoSeleccionado == mazoA) ? mazoB : mazoA;

            // Estilo visual jugador 1
            botonMazo1J1.setStyle(mazoJugador1 == mazoA ? ESTILO_MAZO_SELECCIONADO : ESTILO_MAZO);
            botonMazo2J1.setStyle(mazoJugador1 == mazoB ? ESTILO_MAZO_SELECCIONADO : ESTILO_MAZO);

            // Estilo visual jugador 2 automático
            botonMazo1J2.setStyle(mazoJugador2 == mazoA ? ESTILO_MAZO_SELECCIONADO : ESTILO_MAZO);
            botonMazo2J2.setStyle(mazoJugador2 == mazoB ? ESTILO_MAZO_SELECCIONADO : ESTILO_MAZO);

            // Deshabilitar ambos paneles
            botonMazo1J1.setDisable(true);
            botonMazo2J1.setDisable(true);
            botonMazo1J2.setDisable(true);
            botonMazo2J2.setDisable(true);

        } else {
            mazoJugador2 = mazoSeleccionado;
            mazoJugador1 = (mazoSeleccionado == mazoA) ? mazoB : mazoA;

            // Estilo visual jugador 2
            botonMazo1J2.setStyle(mazoJugador2 == mazoA ? ESTILO_MAZO_SELECCIONADO : ESTILO_MAZO);
            botonMazo2J2.setStyle(mazoJugador2 == mazoB ? ESTILO_MAZO_SELECCIONADO : ESTILO_MAZO);

            // Estilo visual jugador 1 automático
            botonMazo1J1.setStyle(mazoJugador1 == mazoA ? ESTILO_MAZO_SELECCIONADO : ESTILO_MAZO);
            botonMazo2J1.setStyle(mazoJugador1 == mazoB ? ESTILO_MAZO_SELECCIONADO : ESTILO_MAZO);

            botonMazo1J1.setDisable(true);
            botonMazo2J1.setDisable(true);
            botonMazo1J2.setDisable(true);
            botonMazo2J2.setDisable(true);
        }

        validarInicio();
    }


    private void validarInicio() {
        boolean nombresValidos = !inputJ1.getText().trim().isEmpty()
                && !inputJ2.getText().trim().isEmpty()
                && !inputJ1.getText().trim().equals(inputJ2.getText().trim());

        boolean mazosElegidos = mazoJugador1 != null && mazoJugador2 != null;

        botonIniciar.setDisable(!(nombresValidos && mazosElegidos));
    }

    private void iniciarPartida() {
        Jugador j1 = new Jugador(inputJ1.getText().trim());
        Jugador j2 = new Jugador(inputJ2.getText().trim());

        j1.agregarMazo(mazoJugador1);
        j2.agregarMazo(mazoJugador2);

        App.cambiarEscena(new Scene(new JuegoView(j1, j2).construir(), 1000, 700));
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
