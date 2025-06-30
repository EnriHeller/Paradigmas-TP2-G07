package edu.fiuba.algo3.vistas.juego;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import edu.fiuba.algo3.controller.*;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.vistas.BotonMusica;
import edu.fiuba.algo3.vistas.DescarteView;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class JuegoView {
    private final Juego juego;
    private final FinalizadorDeJuego finalizadorDeJuego;
    private final int xMazo = 1140;
    private final int yMazo = 425; 


    public JuegoView(Juego juego) {
        this.juego = juego;
        this.finalizadorDeJuego = new FinalizadorDeJuego(juego);

        // Tirada inicial de moneda
        juego.tirarMoneda();
    }

    public BorderPane construir() throws Exception {
        BorderPane root = new BorderPane();

        // Fondo de madera ocupa toda la pantalla (en root, no en stack)
        try {
            Image woodBg = new Image(Objects.requireNonNull(getClass().getResource("/imagenes/woodBackground.png")).toExternalForm());
            BackgroundImage bgImage = new BackgroundImage(
                woodBg,
                javafx.scene.layout.BackgroundRepeat.NO_REPEAT,
                javafx.scene.layout.BackgroundRepeat.NO_REPEAT,
                javafx.scene.layout.BackgroundPosition.CENTER,
                new javafx.scene.layout.BackgroundSize(100, 100, true, true, true, true)
            );
            root.setBackground(new javafx.scene.layout.Background(bgImage));
        } catch (Exception e) {
            System.err.println("[ERROR] No se pudo cargar woodBackground.png: " + e);
        }

        StackPane stack = new StackPane();

        // --- Bloque de juego fijo ---
        Pane bloqueJuego = new Pane();
        bloqueJuego.setPrefSize(1300, 700); // Tamaño fijo del bloque de juego
        bloqueJuego.setMinSize(1300, 700);
        bloqueJuego.setMaxSize(1300, 700);

        // Tablero (centrado dentro del bloque)
        TableroController tableroController = new TableroController(juego);
        TableroView tablero = new TableroView(tableroController);
        Region tableroRegion = tablero.construir();
        tableroRegion.setLayoutX(0); // El tablero ya está centrado en su propio StackPane
        tableroRegion.setLayoutY(0);
        bloqueJuego.getChildren().add(tableroRegion);

        
        // Pila de descarte (arriba derecha)
        PilaDescarteView pilaDescarteJugador = new PilaDescarteView(juego.getUltimaCartaDeLaPilaDeDescarte());
        Region pilaRegion = pilaDescarteJugador.construir();
        pilaRegion.setLayoutX(xMazo);
        pilaRegion.setLayoutY(yMazo - 110);
        bloqueJuego.getChildren().add(pilaRegion);
        
        // Mazo (abajo derecha, relativo al bloque)
        int cartasRestantes = juego.cartasEnMazoActual();
        edu.fiuba.algo3.vistas.juego.cartas.MazoView mazoView = new edu.fiuba.algo3.vistas.juego.cartas.MazoView(cartasRestantes);
        mazoView.setLayoutX(xMazo); // Ajusta según el diseño del bloque
        mazoView.setLayoutY(yMazo); 
        bloqueJuego.getChildren().add(mazoView);

        // Mano (abajo, centrada respecto al bloque)
        HandlerUnidadMano handlerUnidad = new HandlerUnidadMano(tablero);
        HandlerEspecialManoImpl handlerEspecial = new HandlerEspecialManoImpl(tablero);
        ManoView mano = new ManoView(juego.mostrarManoActual(), handlerUnidad, handlerEspecial);
        Region manoRegion = mano.construir();
        manoRegion.setLayoutX(310); // Ajusta según el diseño
        manoRegion.setLayoutY(560); // Ajusta según el diseño
        bloqueJuego.getChildren().add(manoRegion);
        tablero.setManoView(mano);

        // Historial de puntos del juego
        Label puntosJugador1 = new Label();
        Label puntosJugador2 = new Label();

        puntosJugador1.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 14px; -fx-text-fill: white;");
        puntosJugador2.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 14px; -fx-text-fill: white;");

        puntosJugador1.setLayoutX(55); // Ajustá estas coordenadas finamente
        puntosJugador1.setLayoutY(485);

        puntosJugador2.setLayoutX(55);
        puntosJugador2.setLayoutY(165);

        bloqueJuego.getChildren().addAll(puntosJugador1, puntosJugador2);


        // Centro de turnos (izquierda)
        CentroDeAdministracionTurnos turnos = new CentroDeAdministracionTurnos(juego);
        turnos.setLabelsExternos(puntosJugador1, puntosJugador2);
        VBox panelTurno = turnos.construir(tablero,mano);
        panelTurno.setLayoutX(-10);
        panelTurno.setLayoutY(210);
        bloqueJuego.getChildren().add(panelTurno);

        //Boton de musica
        BotonMusica botonMusica = new BotonMusica();
        Button botonMusicaElem = botonMusica.crear();
        
        // Posicionar el botón de música 
        botonMusicaElem.setLayoutX(70);
        botonMusicaElem.setLayoutY(580); 
        bloqueJuego.getChildren().add(botonMusicaElem); 

        // Boton Salir
        Button botonSalir = new Button("✖");
        botonSalir.setTooltip(new Tooltip("Salir"));
        botonSalir.setStyle("-fx-background-color: transparent; -fx-font-size: 28px; -fx-text-fill: white;");
        botonSalir.setOnMouseEntered(e -> botonSalir.setStyle(
                "-fx-background-color: red; -fx-font-size: 20px; -fx-text-fill: white;")
        );
        botonSalir.setOnMouseExited(e -> botonSalir.setStyle(
                "-fx-background-color: transparent; -fx-font-size: 20px; -fx-text-fill: white;")
        );
        botonSalir.setOnAction(e -> {
            try {
                // Detener la música al salir
                Audio.getInstance().stop();
                Bienvenida.mostrarBienvenida();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Posicionar el botón de salir
        botonSalir.setLayoutX(40);
        botonSalir.setLayoutY(580);
        bloqueJuego.getChildren().add(botonSalir);

        //Boton para descartar cartas de la mano
        Button botonDescarte = new Button("Descartar Cartas");
        botonDescarte.setStyle("-fx-background-color: rgba(255,255,255,0.8);");

        botonDescarte.setOnAction(e -> {
            crearEscenaDescarte(mano);
            botonDescarte.setDisable(true);
        });


        HBox descarteBox = new HBox(15, botonDescarte);
        descarteBox.setAlignment(javafx.geometry.Pos.CENTER);

        descarteBox.setLayoutX(1100);
        descarteBox.setLayoutY(240);
        bloqueJuego.getChildren().add(descarteBox);


        // Centrar el bloque de juego en el StackPane
        stack.getChildren().add(bloqueJuego);
        StackPane.setAlignment(bloqueJuego, Pos.CENTER);

        Platform.runLater(() -> animarReparto(stack, manoRegion));
        stack.getStylesheets().add(getClass().getResource("/carta-visual.css").toExternalForm());

        root.setCenter(stack);
        return root;
    }

    private void crearEscenaDescarte(ManoView manoView) {
        Stage ventanaDescarte = new Stage();
        ventanaDescarte.setTitle("Descartar Cartas");
        try {
            Image icono = new Image(getClass().getResourceAsStream("/imagenes/gwentLogo.png"));
            ventanaDescarte.getIcons().add(icono);
        } catch (Exception e) {
            System.err.println("[App] No se pudo cargar el icono gwentLogo.png: " + e);
        }

        ventanaDescarte.initModality(Modality.APPLICATION_MODAL);

        DescarteView vistaDescarte = new DescarteView(juego.mostrarManoActual()); // Pasás la instancia de Juego

        Scene escenaDescarte = new Scene(vistaDescarte.construir(), 1200, 600);
        ventanaDescarte.setScene(escenaDescarte);

        ventanaDescarte.showAndWait();
    }

    private void animarReparto(StackPane stack, Region manoRegion) {
        manoRegion.setVisible(false);

        Image dorso = new Image(Objects.requireNonNull(getClass().getResource("/imagenes/dorso.png")).toExternalForm());
        List<ImageView> animadas = new ArrayList<>();

        double startX = xMazo - 618;
        double startY = yMazo - 315;

        for (int i = 0; i < 10; i++) {
            ImageView carta = new ImageView(dorso);
            carta.setFitWidth(80);
            carta.setFitHeight(100);
            carta.setPreserveRatio(false);

            carta.setTranslateX(startX);
            carta.setTranslateY(startY);
            stack.getChildren().add(carta);
            animadas.add(carta);

            TranslateTransition anim = new TranslateTransition(Duration.millis(400), carta);
            anim.setToX(-300 + (i * 85)); // Mayor espaciado entre cartas (85 en lugar de 75)
            anim.setToY(250);             // ajustá también según tu layout

            anim.setDelay(Duration.millis(i * 75));
            anim.play();
        }

        PauseTransition esperar = new PauseTransition(Duration.millis(10 * 75 + 300));
        esperar.setOnFinished(e -> {
            // Animar fade out de las cartas del dorso
            int total = animadas.size();
            for (int i = 0; i < total; i++) {
                ImageView carta = animadas.get(i);
                javafx.animation.FadeTransition fade = new javafx.animation.FadeTransition(Duration.millis(250), carta);
                fade.setFromValue(1.0);
                fade.setToValue(0.0);
                fade.setDelay(Duration.millis(i * 30)); // efecto en cascada
                if (i == total - 1) {
                    fade.setOnFinished(ev -> {
                        animadas.forEach(stack.getChildren()::remove);
                        // Fade in para la mano
                        manoRegion.setOpacity(0);
                        manoRegion.setVisible(true);
                        javafx.animation.FadeTransition fadeIn = new javafx.animation.FadeTransition(Duration.millis(350), manoRegion);
                        fadeIn.setFromValue(0.0);
                        fadeIn.setToValue(1.0);
                        fadeIn.play();
                    });
                }
                fade.play();
            }
        });
        esperar.play();
    }
}