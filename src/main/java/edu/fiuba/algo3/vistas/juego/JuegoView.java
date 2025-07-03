package edu.fiuba.algo3.vistas.juego;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import edu.fiuba.algo3.controller.Audio;
import edu.fiuba.algo3.controller.Bienvenida;
import edu.fiuba.algo3.controller.EspecialController;
import edu.fiuba.algo3.controller.FinalizadorDeJuego;
import edu.fiuba.algo3.controller.HandlerSeleccionarCarta;
import edu.fiuba.algo3.controller.TableroController;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.vistas.BotonMusica;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class JuegoView {
    private final Juego juego;
    private final FinalizadorDeJuego finalizadorDeJuego;
    private final EspecialController especialController;
    private final int xMazo = 1140;
    private final int yMazo = 425;

    public JuegoView(Juego juego) {
        this.juego = juego;
        this.finalizadorDeJuego = new FinalizadorDeJuego(juego);
        this.especialController = new EspecialController(juego);
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

        // Historial de puntos del juego
        Label puntosJugador1 = new Label();
        Label puntosJugador2 = new Label();

        puntosJugador1.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 14px; -fx-text-fill: white;");
        puntosJugador2.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 14px; -fx-text-fill: white;");

        puntosJugador1.setLayoutX(55);
        puntosJugador1.setLayoutY(485);

        puntosJugador2.setLayoutX(55);
        puntosJugador2.setLayoutY(165);

        bloqueJuego.getChildren().addAll(puntosJugador1, puntosJugador2);

        // --- TIRAMOS MONEDA ANTES DE CREAR VISTAS ---
        juego.tirarMoneda();

        // Tablero (centrado dentro del bloque)
        TableroController tableroController = new TableroController(juego);
        TableroView tablero = new TableroView(tableroController);
        Region tableroRegion = tablero.construir();
        tableroRegion.setLayoutX(0); // El tablero ya está centrado en su propio StackPane
        tableroRegion.setLayoutY(0);
        bloqueJuego.getChildren().add(tableroRegion);

        //Le pasamos al controller de especiales referencia al tableroView
        especialController.setTableroView(tablero);

        // Mazo (abajo derecha, relativo al bloque)
        int cartasRestantes = juego.cartasRestantesJugador("Mazo", juego.jugadorActual().ordenDeJuego());
        edu.fiuba.algo3.vistas.juego.cartas.MazoView mazoView = new edu.fiuba.algo3.vistas.juego.cartas.MazoView(cartasRestantes);
        mazoView.setLayoutX(xMazo); // Ajusta según el diseño del bloque
        mazoView.setLayoutY(yMazo);
        bloqueJuego.getChildren().add(mazoView);


        // Mano (abajo, centrada respecto al bloque)
        HandlerSeleccionarCarta handlerSelect = new HandlerSeleccionarCarta(tablero);
        ManoView mano = new ManoView(juego.mostrarManoActual(), handlerSelect, especialController);
        Region manoRegion = mano.construir();
        manoRegion.setLayoutX(310); // Ajusta según el diseño
        manoRegion.setLayoutY(560); // Ajusta según el diseño
        bloqueJuego.getChildren().add(manoRegion);
        tablero.setManoView(mano);

        //Le pasamos al controller de especiales referencia al manoView
        especialController.setManoView(mano);

        // Centro de turnos (izquierda)
        CentroDeAdministracionTurnos turnos = new CentroDeAdministracionTurnos(juego);
        turnos.setLabelsExternos(puntosJugador1, puntosJugador2);
        turnos.setOnTurnoFinalizado(() -> {
            try {
                finalizadorDeJuego.verificarFinDeJuego();
            } catch (Exception ex) {
                // Manejo simple de error
            }
        });
        VBox panelTurno = turnos.construir(tablero, mano);
        panelTurno.setLayoutX(-10);
        panelTurno.setLayoutY(210);
        bloqueJuego.getChildren().add(panelTurno);

        // Pila de descarte (arriba derecha)
        PilaDescarteView pilaDescarteJugador = new PilaDescarteView();
        Region pilaRegion = pilaDescarteJugador.construir();
        pilaRegion.setLayoutX(xMazo);
        pilaRegion.setLayoutY(yMazo - 110);
        bloqueJuego.getChildren().add(pilaRegion);

        turnos.setPilaDescarteView(pilaDescarteJugador);

        //Boton de musica
        Button botonMusicaElem = BotonMusica.crear();
        botonMusicaElem.setPrefSize(48, 48);
        botonMusicaElem.setMinSize(48, 48);
        botonMusicaElem.setMaxSize(48, 48);
        botonMusicaElem.setLayoutX(85);
        botonMusicaElem.setLayoutY(580); 
        bloqueJuego.getChildren().add(botonMusicaElem); 

        // Botón Salir con imagen, mismo tamaño que el de música
        ImageView salirImg = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/imagenes/salir.png")).toExternalForm()));
        salirImg.setFitWidth(48);
        salirImg.setFitHeight(48);
        Button botonSalir = new Button();
        botonSalir.setGraphic(salirImg);
        botonSalir.setPrefSize(48, 48);
        botonSalir.setMinSize(48, 48);
        botonSalir.setMaxSize(48, 48);
        botonSalir.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        botonSalir.setTooltip(new Tooltip("Salir"));
        botonSalir.setOnAction(e -> {
            try {
                Audio.getInstance().stop();
                Bienvenida.mostrarBienvenida();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        botonSalir.setLayoutX(30);
        botonSalir.setLayoutY(580);
        bloqueJuego.getChildren().add(botonSalir);

        // Nombres de los jugadores en dorado, borde negro, centrados y alineados con los botones
        // Obtener nombres directamente de la lista interna de jugadores
        String nombreJ1 = "Jugador 1";
        String nombreJ2 = "Jugador 2";
        try {
            java.lang.reflect.Field field = juego.getClass().getDeclaredField("jugadores");
            field.setAccessible(true);
            java.util.List<?> jugadores = (java.util.List<?>) field.get(juego);
            if (jugadores.size() >= 2) {    
                Object jugador1 = jugadores.get(0);
                Object jugador2 = jugadores.get(1);
                java.lang.reflect.Method getNombre = jugador1.getClass().getMethod("getNombre");
                nombreJ1 = (String) getNombre.invoke(jugador1);
                nombreJ2 = (String) getNombre.invoke(jugador2);
            }
        } catch (Exception ignored) {}
        Label labelJ1 = new Label(nombreJ1);
        labelJ1.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: gold; -fx-effect: dropshadow(gaussian, #000, 2, 0.8, 1, 1);");
        labelJ1.setLayoutX(65); // Alineado con los botones
        labelJ1.setLayoutY(460); // Centrado vertical
        Label labelJ2 = new Label(nombreJ2);
        labelJ2.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: gold; -fx-effect: dropshadow(gaussian, #000, 2, 0.8, 1, 1);");
        labelJ2.setLayoutX(65); // Alineado con los botones
        labelJ2.setLayoutY(130); // Justo debajo del otro
        bloqueJuego.getChildren().addAll(labelJ1, labelJ2);

        // Centrar el bloque de juego en el StackPane
        stack.getChildren().add(bloqueJuego);
        StackPane.setAlignment(bloqueJuego, Pos.CENTER);

        Platform.runLater(() -> animarReparto(stack, manoRegion));
        stack.getStylesheets().add(getClass().getResource("/carta-visual.css").toExternalForm());

        root.setCenter(stack);
        return root;
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