package edu.fiuba.algo3.vistas;

import java.util.Objects;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.controller.Audio;
import edu.fiuba.algo3.controller.Menu;
import edu.fiuba.algo3.controller.Utils;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.NoSePuedeCumplirSolicitudDeCartas;
import edu.fiuba.algo3.modelo.principal.UnoDeLosMazosNoCumpleRequitos;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.secciones.tablero.TipoDeSeccionInvalidaError;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MenuView {

    private final Menu menu = new Menu();

    // Componentes desde el FXML
    @FXML private final TextField inputJ1 = new TextField();
    @FXML private final TextField inputJ2 = new TextField();
    @FXML private final Button botonIniciar = new Button("Iniciar Juego");


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
        VBox seccionJ1 = construirSeccionJugador("Jugador 1", inputJ1, true);
        VBox seccionJ2 = construirSeccionJugador("Jugador 2", inputJ2, false);

        // Contenedores para centrar cada sección
        HBox contenedorJ1 = new HBox(seccionJ1);
        contenedorJ1.setAlignment(Pos.CENTER);
        contenedorJ1.setPrefWidth(App.WIDTH / 2.0);
        HBox contenedorJ2 = new HBox(seccionJ2);
        contenedorJ2.setAlignment(Pos.CENTER);
        contenedorJ2.setPrefWidth(App.WIDTH / 2.0);

        // Línea divisoria
        javafx.scene.shape.Line linea = new javafx.scene.shape.Line(0, 0, 0, 400);
        linea.setStroke(javafx.scene.paint.Color.GOLD);
        linea.setStrokeWidth(3);
        linea.setOpacity(0.7);
        linea.setTranslateY(20);
        VBox lineaBox = new VBox(linea);
        lineaBox.setAlignment(Pos.CENTER);
        lineaBox.setPrefHeight(400);

        HBox seccionesJugadores = new HBox(0, contenedorJ1, lineaBox, contenedorJ2);
        seccionesJugadores.setAlignment(Pos.CENTER);
        seccionesJugadores.setPrefWidth(App.WIDTH);
        seccionesJugadores.setPadding(new Insets(40));

        layout.setCenter(seccionesJugadores);

        // Botón iniciar juego
        botonIniciar.setStyle("-fx-font-size: 20px; -fx-background-radius: 50px; -fx-border-radius: 50px; -fx-padding: 10 40; -fx-background-color: #222; -fx-border-color: #FF3333; -fx-border-width: 2px; -fx-text-fill: #FF3333; -fx-font-weight: bold;-fx-cursor: hand;");
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

                // Espera 3 segundos antes de reproducir la música de fondo
                new Thread(() -> {
                    try {
                        Thread.sleep(1500);
                        try {
                            audio.play("/audio/escapism.wav");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } catch (InterruptedException ignored) {}
                }).start();


                Audio efecto = Audio.getInstanceEffect();
                efecto.play("/audio/effects/start.wav");

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

        return layout;
    }

    private VBox construirSeccionJugador(String titulo, TextField campoNombre, boolean esJugador1) {
        VBox seccion = new VBox(10);
        seccion.setAlignment(Pos.CENTER); // Centrado vertical y horizontal
        seccion.setPrefHeight(800); // Asegura altura para centrar verticalmente

        Label label = new Label(titulo);
        label.setStyle("-fx-text-fill: gold; -fx-font-size: 28px; -fx-font-weight: bold; -fx-effect: dropshadow(gaussian, #222, 2, 0.5, 0, 0);");
        label.setAlignment(Pos.CENTER);

        campoNombre.setPromptText("Nombre...");
        campoNombre.setMaxWidth(200);

        Label elegir = new Label("Elegí tu mazo:");
        elegir.setStyle("-fx-text-fill: #FF3333; -fx-font-size: 22px; -fx-font-weight: bold;");
        elegir.setAlignment(Pos.CENTER);

        // Lógica de selección
        boolean mazoASeleccionado = (esJugador1 ? menu.getMazoJugador1() == menu.getMazoA() : menu.getMazoJugador2() == menu.getMazoA());
        boolean mazoBSeleccionado = (esJugador1 ? menu.getMazoJugador1() == menu.getMazoB() : menu.getMazoJugador2() == menu.getMazoB());
        boolean habilitado = true;

        ImageView imagenMazoA = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/imagenes/mazo_a.png")).toExternalForm()));
        imagenMazoA.setFitWidth(120);
        imagenMazoA.setFitHeight(160);
        imagenMazoA.setPreserveRatio(true);
        imagenMazoA.setStyle("-fx-cursor: hand;" + (mazoASeleccionado ? "-fx-effect: dropshadow(gaussian, #FF3333, 12, 0.7, 0, 0);" : ""));
        if (!habilitado) {
            javafx.scene.shape.Rectangle overlay = new javafx.scene.shape.Rectangle(120, 160);
            overlay.setFill(javafx.scene.paint.Color.rgb(80,80,80,0.55));
            StackPane stack = new StackPane(imagenMazoA, overlay);
            imagenMazoA = new ImageView(stack.snapshot(null, null));
        }
        imagenMazoA.setOnMouseClicked(e -> {
            if (habilitado) seleccionarMazo(esJugador1, menu.getMazoA());
        });

        ImageView imagenMazoB = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/imagenes/mazo_b.png")).toExternalForm()));
        imagenMazoB.setFitWidth(120);
        imagenMazoB.setFitHeight(160);
        imagenMazoB.setPreserveRatio(true);
        imagenMazoB.setStyle("-fx-cursor: hand;" + (mazoBSeleccionado ? "-fx-effect: dropshadow(gaussian, #FF3333, 12, 0.7, 0, 0);" : ""));
        if (!habilitado) {
            javafx.scene.shape.Rectangle overlay = new javafx.scene.shape.Rectangle(120, 160);
            overlay.setFill(javafx.scene.paint.Color.rgb(80,80,80,0.55));
            StackPane stack = new StackPane(imagenMazoB, overlay);
            imagenMazoB = new ImageView(stack.snapshot(null, null));
        }
        imagenMazoB.setOnMouseClicked(e -> {
            if (habilitado) seleccionarMazo(esJugador1, menu.getMazoB());
        });

        HBox mazos = new HBox(20, imagenMazoA, imagenMazoB);
        mazos.setAlignment(Pos.CENTER);

        VBox mazosYTitulo = new VBox(20, elegir, mazos);
        mazosYTitulo.setAlignment(Pos.CENTER);

        // Espaciador flexible para centrar verticalmente el bloque de mazos
        javafx.scene.layout.Region spacerTop = new javafx.scene.layout.Region();
        javafx.scene.layout.Region spacerBottom = new javafx.scene.layout.Region();
        VBox.setVgrow(spacerTop, javafx.scene.layout.Priority.ALWAYS);
        VBox.setVgrow(spacerBottom, javafx.scene.layout.Priority.ALWAYS);

        seccion.getChildren().setAll(label, campoNombre, spacerTop, mazosYTitulo, spacerBottom);
        return seccion;
    }

    private void seleccionarMazo(boolean esJugador1, Mazo mazoSeleccionado) {
        menu.seleccionarMazo(esJugador1, mazoSeleccionado);
        actualizarEstilos();
        deshabilitarBotones();
        validarInicio();
    }

    private void actualizarEstilos() {
        // Reconstruye todo el bloque central para evitar duplicados o desbordes
        BorderPane layout = (BorderPane) botonIniciar.getScene().getRoot();
        VBox seccionJ1 = construirSeccionJugador("Jugador 1", inputJ1, true);
        VBox seccionJ2 = construirSeccionJugador("Jugador 2", inputJ2, false);

        HBox contenedorJ1 = new HBox(seccionJ1);
        contenedorJ1.setAlignment(Pos.CENTER);
        contenedorJ1.setPrefWidth(App.WIDTH / 2.0);
        HBox contenedorJ2 = new HBox(seccionJ2);
        contenedorJ2.setAlignment(Pos.CENTER);
        contenedorJ2.setPrefWidth(App.WIDTH / 2.0);

        javafx.scene.shape.Line linea = new javafx.scene.shape.Line(0, 0, 0, 400);
        linea.setStroke(javafx.scene.paint.Color.GOLD);
        linea.setStrokeWidth(3);
        linea.setOpacity(0.7);
        linea.setTranslateY(20);
        VBox lineaBox = new VBox(linea);
        lineaBox.setAlignment(Pos.CENTER);
        lineaBox.setPrefHeight(400);

        HBox seccionesJugadores = new HBox(0, contenedorJ1, lineaBox, contenedorJ2);
        seccionesJugadores.setAlignment(Pos.CENTER);
        seccionesJugadores.setPrefWidth(App.WIDTH);
        seccionesJugadores.setPadding(new Insets(40));

        layout.setCenter(seccionesJugadores);
    }

    private void deshabilitarBotones() {
        // No hace falta deshabilitar botones, ahora se usan imágenes
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
