package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.App;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.util.Objects;

public class MenuView {

    public VBox construir() {
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(40));
        layout.setAlignment(Pos.CENTER);

        // Fondo con imagen
        Image fondo = new Image(Objects.requireNonNull(getClass().getResource("/imagenes/menu.png")).toExternalForm());

        BackgroundImage bgImage = new BackgroundImage(
                fondo,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );

        layout.setBackground(new Background(bgImage));

        // Título
        Text titulo = new Text("GWENT - Ingreso de jugadores");
        titulo.setStyle("-fx-fill: white; -fx-font-size: 24; -fx-font-weight: bold;");

        // Campos de nombre con estilo
        TextField inputJ1 = crearCampoTexto("Nombre del Jugador 1");
        TextField inputJ2 = crearCampoTexto("Nombre del Jugador 2");

        // Botón
        Button boton = new Button("Comenzar Partida");
        boton.setStyle("-fx-background-color: #ffffffaa; -fx-background-radius: 8;");
        boton.setOnAction(e -> {
            String j1 = inputJ1.getText().trim();
            String j2 = inputJ2.getText().trim();

            if (j1.isEmpty() || j2.isEmpty() || j1.equals(j2)) {
                mostrarAlerta("Nombres inválidos", "Los nombres no pueden estar vacíos ni repetidos.");
                return;
            }

            // Simular inicio de juego
            JuegoView juego = new JuegoView(j1, j2);
            Scene escenaJuego = new Scene(juego.construir(), 800, 600);
            App.cambiarEscena(escenaJuego);
        });

        layout.getChildren().addAll(titulo, inputJ1, inputJ2, boton);
        return layout;
    }

    private TextField crearCampoTexto(String placeholder) {
        TextField campo = new TextField();
        campo.setPromptText(placeholder);
        campo.setMaxWidth(250);
        campo.setStyle(
                "-fx-background-color: rgba(255,255,255,0.5); " +
                        "-fx-text-fill: black; " +
                        "-fx-background-radius: 10; " +
                        "-fx-border-radius: 10; " +
                        "-fx-border-color: transparent;"
        );
        return campo;
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
