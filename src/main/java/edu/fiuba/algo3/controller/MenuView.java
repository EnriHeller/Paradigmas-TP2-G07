package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.controller.ConstructorMazo;
import edu.fiuba.algo3.modelo.cartas.CartasFactory;
import edu.fiuba.algo3.modelo.modificadores.ModificadoresFactory;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import javafx.scene.image.Image;
import org.json.simple.parser.JSONParser;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.io.InputStream;
import java.util.List;
import java.util.Objects;

public class MenuView {

    private final TextField inputJ1 = new TextField();
    private final TextField inputJ2 = new TextField();
    private final Button botonIniciar = new Button("Comenzar Partida");

    private Mazo mazoJugador1 = null;
    private Mazo mazoJugador2 = null;

    private final Mazo mazoA;
    private final Mazo mazoB;

    public MenuView() {

        // Cargar los mazos desde JSON
        Mazo tempA, tempB;
        try {
            ConstructorMazo constructor = new ConstructorMazo(
                    new ModificadoresFactory(),
                    new CartasFactory(),
                    new JSONParser()
            );

            InputStream jsonStream = Objects.requireNonNull(getClass().getResourceAsStream("/json/gwent.json"));
            List<Mazo> mazos = constructor.construirMazos(jsonStream.toString());

            tempA = mazos.get(0);
            tempB = mazos.get(1);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al cargar los mazos desde JSON");
        }

        mazoA = tempA;
        mazoB = tempB;
    }

    public VBox construir() {

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.CENTER);

        // Fondo con imagen
        Image fondo = new Image(Objects.requireNonNull(getClass().getResource("/imagenes/menu.png")).toExternalForm());
        BackgroundImage bgImage = new BackgroundImage(
                fondo,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false)
        );
        layout.setBackground(new Background(bgImage));

        Text titulo = new Text("GWENT - Ingreso de jugadores");
        titulo.setStyle("-fx-fill: white; -fx-font-size: 24; -fx-font-weight: bold;");

        configurarCampo(inputJ1, "Nombre del Jugador 1");
        configurarCampo(inputJ2, "Nombre del Jugador 2");

        HBox botonesMazo = crearSelectorDeMazo();

        botonIniciar.setDisable(true);
        botonIniciar.setOnAction(e -> {
            try {
                iniciarPartida();
            } catch (Errores.TipoDeSeccionInvalidaError | Errores.UnoDeLosMazosNoCumpleRequitos ex) {
                throw new RuntimeException(ex);
            }
        });

        layout.getChildren().addAll(titulo, inputJ1, inputJ2, botonesMazo, botonIniciar);
        return layout;
    }

    private void configurarCampo(TextField campo, String placeholder) {
        campo.setPromptText(placeholder);
        campo.setMaxWidth(250);
        campo.setStyle(
                "-fx-background-color: rgba(255,255,255,0.5); " +
                        "-fx-text-fill: black; " +
                        "-fx-background-radius: 10;"
        );
        campo.textProperty().addListener((obs, oldVal, newVal) -> validarInicio());
    }

    private HBox crearSelectorDeMazo() {
        HBox contenedor = new HBox(20);
        contenedor.setAlignment(Pos.CENTER);

        Button botonMazo1 = new Button("Elegir Mazo A");
        Button botonMazo2 = new Button("Elegir Mazo B");

        botonMazo1.setOnAction(e -> {
            mazoJugador1 = mazoA;
            mazoJugador2 = mazoB;
            botonMazo1.setDisable(true);
            botonMazo2.setDisable(true);
            validarInicio();
        });

        botonMazo2.setOnAction(e -> {
            mazoJugador1 = mazoB;
            mazoJugador2 = mazoA;
            botonMazo1.setDisable(true);
            botonMazo2.setDisable(true);
            validarInicio();
        });

        contenedor.getChildren().addAll(botonMazo1, botonMazo2);
        return contenedor;
    }

    private void validarInicio() {
        boolean nombresValidos = !inputJ1.getText().trim().isEmpty()
                && !inputJ2.getText().trim().isEmpty()
                && !inputJ1.getText().equals(inputJ2.getText());
        boolean mazoElegido = mazoJugador1 != null;
        botonIniciar.setDisable(!(nombresValidos && mazoElegido));
    }

    private void iniciarPartida() throws Errores.TipoDeSeccionInvalidaError, Errores.UnoDeLosMazosNoCumpleRequitos {
        Jugador j1 = new Jugador(inputJ1.getText().trim());
        Jugador j2 = new Jugador(inputJ2.getText().trim());

        try {
            j1.agregarMazo(mazoJugador1);
            j2.agregarMazo(mazoJugador2);
        } catch (Errores.NoSePuedeCumplirSolicitudDeCartas e) {
            mostrarAlerta("Error al asignar mazos", "Los mazos no tienen suficientes cartas.");
            return;
        }
            // Idea aprox
//        Juego juego = new Juego(j1, j2);
//        JuegoController controller = new JuegoController(juego);
//        VistaJuego vista = new VistaJuego(controller);
//        App.cambiarEscena(new Scene(vista.construir(), 1000, 700));
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}


//package edu.fiuba.algo3.controller;
//
//import edu.fiuba.algo3.App;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextField;
//import javafx.scene.image.Image;
//import javafx.scene.layout.*;
//import javafx.scene.text.Text;
//
//import java.util.Objects;
//
//public class MenuView {
//
//    public VBox construir() {
//        VBox layout = new VBox(15);
//        layout.setPadding(new Insets(40));
//        layout.setAlignment(Pos.CENTER);
//
//        // Fondo con imagen
//        Image fondo = new Image(Objects.requireNonNull(getClass().getResource("/imagenes/menu.png")).toExternalForm());
//
//        BackgroundImage bgImage = new BackgroundImage(
//                fondo,
//                BackgroundRepeat.NO_REPEAT,
//                BackgroundRepeat.NO_REPEAT,
//                BackgroundPosition.CENTER,
//                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
//        );
//
//        layout.setBackground(new Background(bgImage));
//
//        // Título
//        Text titulo = new Text("GWENT - Ingreso de jugadores");
//        titulo.setStyle("-fx-fill: white; -fx-font-size: 24; -fx-font-weight: bold;");
//
//        // Campos de nombre con estilo
//        TextField inputJ1 = crearCampoTexto("Nombre del Jugador 1");
//        TextField inputJ2 = crearCampoTexto("Nombre del Jugador 2");
//
//        // Botón
//        Button boton = new Button("Comenzar Partida");
//        boton.setStyle("-fx-background-color: #ffffffaa; -fx-background-radius: 8;");
//        boton.setOnAction(e -> {
//            String j1 = inputJ1.getText().trim();
//            String j2 = inputJ2.getText().trim();
//
//            if (j1.isEmpty() || j2.isEmpty() || j1.equals(j2)) {
//                mostrarAlerta("Nombres inválidos", "Los nombres no pueden estar vacíos ni repetidos.");
//                return;
//            }
//
//            // Simular inicio de juego
//            JuegoView juego = new JuegoView(j1, j2);
//            Scene escenaJuego = new Scene(juego.construir(), 800, 600);
//            App.cambiarEscena(escenaJuego);
//        });
//
//        layout.getChildren().addAll(titulo, inputJ1, inputJ2, boton);
//        return layout;
//    }
//
//    private TextField crearCampoTexto(String placeholder) {
//        TextField campo = new TextField();
//        campo.setPromptText(placeholder);
//        campo.setMaxWidth(250);
//        campo.setStyle(
//                "-fx-background-color: rgba(255,255,255,0.5); " +
//                        "-fx-text-fill: black; " +
//                        "-fx-background-radius: 10; " +
//                        "-fx-border-radius: 10; " +
//                        "-fx-border-color: transparent;"
//        );
//        return campo;
//    }
//
//    private void mostrarAlerta(String titulo, String mensaje) {
//        Alert alerta = new Alert(Alert.AlertType.WARNING);
//        alerta.setTitle(titulo);
//        alerta.setHeaderText(null);
//        alerta.setContentText(mensaje);
//        alerta.showAndWait();
//    }
//}
