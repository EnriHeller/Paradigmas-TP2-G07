package edu.fiuba.algo3.vistas;

import java.util.Objects;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.controller.Audio;
import edu.fiuba.algo3.controller.Menu;
import edu.fiuba.algo3.controller.Utils;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.NoSePuedeCumplirSolicitudDeCartas;
import edu.fiuba.algo3.modelo.principal.UnoDeLosMazosNoCumpleRequitos;
import edu.fiuba.algo3.modelo.secciones.tablero.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.vistas.juego.JuegoView;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MenuView {

    private final Menu menu = new Menu();

    // Componentes desde el FXML
    @FXML private final TextField inputJ1 = new TextField();
    @FXML private final TextField inputJ2 = new TextField();
    @FXML private final Button botonIniciar = new Button("Iniciar Juego");

    @FXML private Button botonMazo1J1 = new Button("Mazo A");
    @FXML private Button botonMazo2J1 = new Button("Mazo B");
    @FXML private Button botonMazo1J2 = new Button("Mazo A");
    @FXML private Button botonMazo2J2 = new Button("Mazo B");


    @FXML
    public Parent construir() {
        BorderPane layout = new BorderPane();
        layout.setPrefSize(App.WIDTH, App.HEIGHT);


        // Fondo
        Image fondo = new Image(Objects.requireNonNull(getClass().getResource("/imagenes/menu2.png")).toExternalForm());
        BackgroundImage bgImage = new BackgroundImage(
                fondo,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, true) // cover width/height, no aspect ratio
        );
        layout.setBackground(new Background(bgImage));

        // Centro: secciones jugadores
        HBox seccionesJugadores = new HBox(40);
        seccionesJugadores.setPadding(new javafx.geometry.Insets(40));
        seccionesJugadores.setAlignment(javafx.geometry.Pos.CENTER);

        VBox seccionJ1 = construirSeccionJugador("Jugador 1", inputJ1, true);
        VBox seccionJ2 = construirSeccionJugador("Jugador 2", inputJ2, false);

        BotonMusica botonMusica = new BotonMusica();
        Button botonMusicaReal = botonMusica.crear();
        botonMusicaReal.setLayoutX(1000);
        botonMusicaReal.setLayoutX(0);

        HBox contenedorMusica = new HBox(botonMusicaReal);
        contenedorMusica.setAlignment(Pos.TOP_RIGHT);
        contenedorMusica.setPadding(new Insets(10));
        layout.setTop(contenedorMusica);

        seccionesJugadores.getChildren().addAll(seccionJ1, seccionJ2);
        layout.setCenter(seccionesJugadores);

        // Botón iniciar juego
        botonIniciar.setStyle("-fx-font-size: 20px; -fx-background-radius: 50%; -fx-padding: 10;");
        botonIniciar.setDisable(true);
        botonIniciar.setOnAction(e -> {
            try {
                // Crear los jugadores con sus mazos y luego la partida
                var jugadores = menu.getJugadoresInicializados(inputJ1.getText(), inputJ2.getText());
                Juego juego = new Juego(jugadores.get(0), jugadores.get(1));
                juego.darMano(0, 10);
                juego.darMano(1, 10);

                JuegoView juegoView = new JuegoView(juego);

                Audio audio = Audio.getInstance();
                audio.stop();
                audio.play("/audio/escapism.wav");

                Utils.cambiarEscena(new Scene(juegoView.construir(), App.WIDTH, App.HEIGHT));

            } catch (TipoDeSeccionInvalidaError ex) {
                mostrarAlerta("Error: TipoDeSeccionInvalidaError", ex.getMessage(), ex);
            } catch (NoSePuedeCumplirSolicitudDeCartas ex) {
                mostrarAlerta("Error: NoSePuedeCumplirSolicitudDeCartas", ex.getMessage(), ex);
            } catch (UnoDeLosMazosNoCumpleRequitos ex) {
                mostrarAlerta("Error: UnoDeLosMazosNoCumpleRequitos", ex.getMessage(), ex);
            } catch (Exception ex) {
                mostrarAlerta("Error inesperado", ex.getMessage(), ex);
            }
        });
        HBox contenedorBoton = new HBox(botonIniciar);
        contenedorBoton.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        contenedorBoton.setPadding(new javafx.geometry.Insets(20));
        layout.setBottom(contenedorBoton);

        // Listeners para inputs
        inputJ1.textProperty().addListener((obs, o, n) -> validarInicio());
        inputJ2.textProperty().addListener((obs, o, n) -> validarInicio());

        // Listeners para botones de mazo
        botonMazo1J1.setOnAction(e -> seleccionarMazo(true, menu.getMazoA()));
        botonMazo2J1.setOnAction(e -> seleccionarMazo(true, menu.getMazoB()));
        botonMazo1J2.setOnAction(e -> seleccionarMazo(false, menu.getMazoA()));
        botonMazo2J2.setOnAction(e -> seleccionarMazo(false, menu.getMazoB()));

        return layout;
    }

    private VBox construirSeccionJugador(String titulo, TextField campoNombre, boolean esJugador1) {
        VBox seccion = new VBox(10);
        seccion.setAlignment(javafx.geometry.Pos.TOP_CENTER);

        Label label = new Label(titulo);
        label.setStyle("-fx-text-fill: white; -fx-font-size: 16;");

        campoNombre.setPromptText("Nombre...");
        campoNombre.setMaxWidth(200);

        Label elegir = new Label("Elegí tu mazo:");
        elegir.setStyle("-fx-text-fill: white;");

        Button botonMazo1 = new Button("Mazo A");
        Button botonMazo2 = new Button("Mazo B");

        botonMazo1.setStyle("-fx-background-color: rgba(255,255,255,0.8);");
        botonMazo2.setStyle("-fx-background-color: rgba(255,255,255,0.8);");

        botonMazo1.setOnAction(e -> seleccionarMazo(esJugador1, menu.getMazoA()));
        botonMazo2.setOnAction(e -> seleccionarMazo(esJugador1, menu.getMazoB()));

        if (esJugador1) {
            botonMazo1J1 = botonMazo1;
            botonMazo2J1 = botonMazo2;
        } else {
            botonMazo1J2 = botonMazo1;
            botonMazo2J2 = botonMazo2;
        }

        HBox mazos = new HBox(10, botonMazo1, botonMazo2);
        mazos.setAlignment(javafx.geometry.Pos.CENTER);

        seccion.getChildren().addAll(label, campoNombre, elegir, mazos);
        return seccion;
    }

    private void seleccionarMazo(boolean esJugador1, Mazo mazoSeleccionado) {
        menu.seleccionarMazo(esJugador1, mazoSeleccionado);
        actualizarEstilos();
        deshabilitarBotones();
        validarInicio();
    }

    private void actualizarEstilos() {
        String estiloNormal = "-fx-background-color: rgba(255,255,255,0.8);";
        String estiloSeleccionado = "-fx-background-color: #444; -fx-text-fill: white;";

        botonMazo1J1.setStyle(menu.getMazoJugador1() == menu.getMazoA() ? estiloSeleccionado : estiloNormal);
        botonMazo2J1.setStyle(menu.getMazoJugador1() == menu.getMazoB() ? estiloSeleccionado : estiloNormal);
        botonMazo1J2.setStyle(menu.getMazoJugador2() == menu.getMazoA() ? estiloSeleccionado : estiloNormal);
        botonMazo2J2.setStyle(menu.getMazoJugador2() == menu.getMazoB() ? estiloSeleccionado : estiloNormal);
    }

    private void deshabilitarBotones() {
        botonMazo1J1.setDisable(true);
        botonMazo2J1.setDisable(true);
        botonMazo1J2.setDisable(true);
        botonMazo2J2.setDisable(true);
    }

    private void validarInicio() {
        boolean nombresValidos = menu.nombresValidos(inputJ1.getText(), inputJ2.getText());
        boolean mazosElegidos = menu.mazosElegidos();
        botonIniciar.setDisable(!(nombresValidos && mazosElegidos));
    }


    // Sobrecarga para mostrar el stacktrace real de una excepción
    private void mostrarAlerta(String titulo, String mensaje, Throwable ex) {
        System.err.println("[" + titulo + "] " + (mensaje != null ? mensaje : "Sin mensaje"));
        if (ex != null) ex.printStackTrace();
        else new Exception().printStackTrace();
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
