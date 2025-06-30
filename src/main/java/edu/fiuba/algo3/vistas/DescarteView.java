package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.cartas.Carta;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.awt.*;
import java.text.Normalizer;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javafx.scene.control.ScrollPane;

public class DescarteView extends Stage {
    private final List<Carta> cartasMano;
    private final Set<Carta> cartasSeleccionadas = new HashSet<>();

    private final VBox layout = new VBox(20);
    private final HBox contenedorCartas = new HBox(10);
    private final Button confirmarBtn = new Button("Confirmar descarte");
    private final ScrollPane scrollPane = new ScrollPane();

    public DescarteView(List<Carta> cartasMano) {
        this.cartasMano = cartasMano;
    }

    public Parent construir() {
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-image: url('/imagenes/fondo_descarte.png'); "
                + "-fx-background-size: cover;");

        contenedorCartas.setAlignment(Pos.CENTER);
        mostrarCartasDeLaMano();

        confirmarBtn.setDisable(true);
        confirmarBtn.setOnAction(e -> {
            // Aca podes manejar el paso al siguiente estado (mostrar el mazo)
            System.out.println("Cartas seleccionadas para descartar: " + cartasSeleccionadas);
        });

        layout.getChildren().addAll(contenedorCartas, confirmarBtn);
        this.setScene(new Scene(layout, 1100, 700));
        this.setTitle("Descartar Cartas");
        this.show();

        mostrarCartasDeLaMano();

        return layout;
    }

    private void mostrarCartasDeLaMano() {
        contenedorCartas.getChildren().clear();

        for (Carta carta : cartasMano) {
            VBox cartaBox = crearCartaVisual(carta);
            contenedorCartas.getChildren().add(cartaBox);
        }
    }

    private VBox crearCartaVisual(Carta carta) {
        Image imagen = null;
        String nombreBase;

        try {
            if (!carta.esEspecial()) {
                nombreBase = ((CartaUnidad) carta).getNombre();
            } else {
                nombreBase = (String) carta.getClass().getMethod("getNombre").invoke(carta);
            }
        } catch (Exception e) {
            String mostrar = carta.mostrarCarta();
            int idx = mostrar.indexOf(' ');
            nombreBase = (idx > 0) ? mostrar.substring(0, idx) : mostrar;
        }

        String nombreImagen = normalizarNombre(nombreBase);
        String ruta = "/imagenes/" + nombreImagen + ".png";
        try {
            imagen = new Image(Objects.requireNonNull(getClass().getResourceAsStream(ruta)));
        } catch (Exception e) {
            imagen = new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                    carta.esEspecial() ? "/imagenes/Lluviatorrencial.png" : "/imagenes/BirnaBrant.png"
            )));
        }

        ImageView imagenView = new ImageView(imagen);
        imagenView.setFitHeight(150);
        imagenView.setPreserveRatio(true);

        StackPane mainStack = new StackPane();
        mainStack.getChildren().add(imagenView);

        if (carta.esEspecial()) {
            javafx.scene.shape.Circle circulo = new javafx.scene.shape.Circle(13);
            circulo.setFill(javafx.scene.paint.Color.rgb(30, 30, 30, 0.85));
            circulo.setStroke(javafx.scene.paint.Color.GOLD);
            circulo.setStrokeWidth(2);
            javafx.scene.text.Text textoE = new javafx.scene.text.Text("E");
            textoE.setFill(javafx.scene.paint.Color.WHITE);
            textoE.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");

            StackPane overlay = new StackPane(circulo, textoE);
            overlay.setPickOnBounds(false);
            overlay.setMouseTransparent(true);
            overlay.setTranslateX(52);
            overlay.setTranslateY(0);

            Pane overlayPane = new Pane(overlay);
            overlayPane.setPrefSize(0, 0);
            overlayPane.setMouseTransparent(true);

            mainStack.getChildren().add(overlayPane);
        } else {
            // Para unidad: mostrar valor actual dentro de círculo
            int valorActual = ((CartaUnidad) carta).ValorActual();

            javafx.scene.shape.Circle circulo = new javafx.scene.shape.Circle(13);
            circulo.setFill(javafx.scene.paint.Color.rgb(30, 30, 30, 0.85));
            circulo.setStroke(javafx.scene.paint.Color.GOLD);
            circulo.setStrokeWidth(2);

            javafx.scene.text.Text textoValor = new javafx.scene.text.Text(String.valueOf(valorActual));
            textoValor.setFill(javafx.scene.paint.Color.WHITE);
            textoValor.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");

            StackPane overlay = new StackPane(circulo, textoValor);
            overlay.setPickOnBounds(false);
            overlay.setMouseTransparent(true);
            overlay.setTranslateX(52);
            overlay.setTranslateY(0);

            Pane overlayPane = new Pane(overlay);
            overlayPane.setPrefSize(0, 0);
            overlayPane.setMouseTransparent(true);

            mainStack.getChildren().add(overlayPane);
        }

        VBox cartaBox = new VBox(mainStack);
        cartaBox.setAlignment(Pos.CENTER);
        cartaBox.setStyle("-fx-border-color: transparent; -fx-border-width: 4px;");

        return cartaBox;
    }


    private String normalizarNombre(String nombre) {
        String normalizado = Normalizer.normalize(nombre, Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")
                .replaceAll("[^A-Za-z0-9]", "");
        return normalizado;
    }




    /*
    private void mostrarMazoParaReemplazo(BorderPane layout) {
        layout.setTop(null); // Eliminamos la vista de la mano

        Label labelMazo = new Label("Selecciona 2 cartas del mazo:");
        labelMazo.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");
        VBox contenedorMazo = new VBox(10, labelMazo);
        contenedorMazo.setAlignment(Pos.CENTER);
        contenedorMazo.setPadding(new Insets(30));

        HBox cartasMazoBox = new HBox(15);
        cartasMazoBox.setAlignment(Pos.CENTER);

        List<String> idsSeleccionadasDelMazo = new ArrayList<>();

        juego.verCartasDisponiblesEnMazo().forEach(carta -> {
            CartaVisual cartaVisual = new CartaVisual(carta);
            cartaVisual.setOnMouseClicked(e -> {
                if (idsSeleccionadasDelMazo.contains(carta.getId())) {
                    idsSeleccionadasDelMazo.remove(carta.getId());
                    cartaVisual.deseleccionar();
                } else if (idsSeleccionadasDelMazo.size() < 2) {
                    idsSeleccionadasDelMazo.add(carta.getId());
                    cartaVisual.seleccionar();
                }
            });
            cartasMazoBox.getChildren().add(cartaVisual);
        });

        contenedorMazo.getChildren().add(cartasMazoBox);
        layout.setTop(contenedorMazo);

        Button botonFinalizar = new Button("Confirmar selección");
        botonFinalizar.setStyle("-fx-font-size: 18px;");
        botonFinalizar.setOnAction(e -> {
            if (idsSeleccionadasDelMazo.size() != 2) {
                mostrarAlerta("Debes seleccionar exactamente 2 cartas del mazo.");
                return;
            }

            juego.agregarCartasAMano(idsSeleccionadasDelMazo); // método a implementar
            ((Stage) layout.getScene().getWindow()).close();
        });

        VBox cajaInferior = new VBox(botonFinalizar);
        cajaInferior.setAlignment(Pos.CENTER);
        cajaInferior.setPadding(new Insets(20));
        layout.setBottom(cajaInferior);
    }

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Atención");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

     */

}
