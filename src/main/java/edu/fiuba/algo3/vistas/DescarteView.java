package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.principal.Juego;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.vistas.juego.ManoView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.awt.*;
import java.text.Normalizer;
import java.util.*;
import java.util.List;

import javafx.scene.control.ScrollPane;

public class DescarteView extends Stage {
    private final List<Carta> cartasMano;
    private final Set<Carta> cartasSeleccionadas = new HashSet<>();

    private HBox contenedorCartas = new HBox(10);
    private HBox contenedorBoton;
    private int seSeleccionaronCartas;
    private ManoView mano;
    private Button confirmarBtn;
    private Label cartasSeleccionadasDescartadas;
    private int cantidadCartasDescartadas;

    public DescarteView(List<Carta> cartasMano, ManoView manoCartas) {
        this.mano = manoCartas;
        this.cartasMano = cartasMano;
        this.seSeleccionaronCartas = 0;
    }

    public Parent construir() {
        BorderPane layout = new BorderPane();
        Image fondo = new Image(Objects.requireNonNull(getClass().getResource("/imagenes/fondo_descarte.png")).toExternalForm());
        BackgroundImage bgImage = new BackgroundImage(
                fondo,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, true) // cover width/height, no aspect ratio
        );
        layout.setBackground(new Background(bgImage));

        this.confirmarBtn = new Button("Confirmar descarte");
        confirmarBtn.setDisable(true);
        confirmarBtn.setOnAction(e -> {
            confirmarDescarte();
        });

        this.contenedorCartas = mostrarCartasDeLaMano();
        contenedorCartas.setAlignment(Pos.CENTER);

        this.contenedorBoton = new HBox(confirmarBtn);
        contenedorBoton.setAlignment(Pos.CENTER);

        this.cartasSeleccionadasDescartadas = new Label(cantidadCartasDescartadas + "/2");

        VBox contenedorCentral = new VBox(30, contenedorCartas, contenedorBoton, cartasSeleccionadasDescartadas);

        contenedorCentral.setAlignment(Pos.CENTER);
        contenedorCentral.setPadding(new Insets(100, 0, 0, 0));

        layout.setCenter(contenedorCentral);

        return layout;
    }

    private HBox mostrarCartasDeLaMano() {
        HBox contenedorCartasBox = new HBox();
        for (Carta carta : cartasMano) {
            VBox cartaBox = crearCartaVisual(carta);
            contenedorCartasBox.getChildren().add(cartaBox);
        }
        return contenedorCartasBox;
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

        ImageView imagenView = crearImagenEstandar(imagen);
        StackPane mainStack = new StackPane(imagenView);

        // Círculo overlay (valor o "E")
        javafx.scene.shape.Circle circulo = new javafx.scene.shape.Circle(13);
        circulo.setFill(javafx.scene.paint.Color.rgb(30, 30, 30, 0.85));
        circulo.setStroke(javafx.scene.paint.Color.GOLD);
        circulo.setStrokeWidth(2);

        javafx.scene.text.Text textoOverlay = new javafx.scene.text.Text();
        textoOverlay.setFill(javafx.scene.paint.Color.WHITE);
        textoOverlay.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");

        if (carta.esEspecial()) {
            textoOverlay.setText("E");
        } else {
            textoOverlay.setText(String.valueOf(((CartaUnidad) carta).ValorActual()));
        }

        StackPane overlay = new StackPane(circulo, textoOverlay);
        overlay.setTranslateX(52);

        Pane overlayPane = new Pane(overlay);
        overlayPane.setMouseTransparent(true);
        mainStack.getChildren().add(overlayPane);

        VBox cartaBox = new VBox(mainStack);
        cartaBox.setAlignment(Pos.CENTER);
        cartaBox.setStyle("-fx-border-color: transparent; -fx-border-width: 4px;");

        // -------------------- VENTANA EMERGENTE ------------------------
        Popup infoPopup = new Popup();
        VBox infoOverlay = construirInfoCarta(carta);
        infoOverlay.setStyle("-fx-background-color: rgba(20, 20, 20, 0.95); -fx-padding: 10; -fx-background-radius: 8;");
        infoOverlay.setMaxWidth(200);
        infoOverlay.setPrefWidth(180);
        infoOverlay.setEffect(new javafx.scene.effect.DropShadow());
        infoPopup.getContent().add(infoOverlay);

        cartaBox.setOnMouseEntered(e -> {
            if (!infoPopup.isShowing()) {
                infoPopup.show(cartaBox, e.getScreenX() + 10, e.getScreenY() - 40);
            }
        });

        cartaBox.setOnMouseMoved(e -> {
            if (infoPopup.isShowing()) {
                infoPopup.setX(e.getScreenX() + 10);
                infoPopup.setY(e.getScreenY() - 40);
            }
        });

        cartaBox.setOnMouseExited(e -> infoPopup.hide());

        cartaBox.setOnMouseClicked(e -> {
            if (cartasSeleccionadas.contains(carta)) {
                cartasSeleccionadas.remove(carta);
                cartaBox.setStyle("-fx-border-color: transparent; -fx-border-width: 4px;");
                seSeleccionaronCartas--;
            } else {
                if (seSeleccionaronCartas >= 2) return; // Máximo 2
                cartasSeleccionadas.add(carta);
                cartaBox.setStyle("-fx-border-color: gold; -fx-border-width: 4px;");
                seSeleccionaronCartas++;
            }
            // Habilita o deshabilita el botón de confirmar
            this.cantidadCartasDescartadas += 1;
            this.cartasSeleccionadasDescartadas.setText(cartasSeleccionadas.size() + "/2");
            actualizarEstadoBoton();
        });

        return cartaBox;
    }

    private void actualizarEstadoBoton() {
        if(cantidadCartasDescartadas >= 2 ){
            confirmarBtn.setDisable(false);
        }
        confirmarBtn.setDisable(cartasSeleccionadas.size() != 2);

    }

    private void confirmarDescarte() {
        // Elimina las cartas seleccionadas de la mano
        cartasMano.removeAll(cartasSeleccionadas);
        mano.actualizarCartas(cartasMano); // si tu ManoView tiene un método así

        // Podés imprimir para testear
        System.out.println("Cartas descartadas: " + cartasSeleccionadas);

        // TODO: Mostrar las cartas del mazo después (siguiente paso)
        // Por ahora podés cerrar o limpiar la vista
        this.close();
    }



    private ImageView crearImagenEstandar(Image imagen) {
        int ANCHO = 80;
        int ALTO = 100;
        ImageView vistaImagen = new ImageView(imagen);
        vistaImagen.setFitWidth(ANCHO);
        vistaImagen.setFitHeight(ALTO);
        vistaImagen.setPreserveRatio(false);
        vistaImagen.setPickOnBounds(false);
        return vistaImagen;
    }


    private String normalizarNombre(String nombre) {
        String normalizado = Normalizer.normalize(nombre, Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")
                .replaceAll("[^A-Za-z0-9]", "");
        return normalizado;
    }

    private VBox construirInfoCarta(Carta carta) {
        VBox infoOverlay = new VBox(5);

        if (!carta.esEspecial()) {
            try {
                CartaUnidad unidad = (CartaUnidad) carta;
                Label nombre = new Label("Nombre: " + unidad.getNombre());
                nombre.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");
                infoOverlay.getChildren().add(nombre);

                String secciones = String.join(", ", unidad.getSecciones());
                Label seccion = new Label("Sección: " + secciones);
                seccion.setStyle("-fx-text-fill: #e0e0e0;");
                infoOverlay.getChildren().add(seccion);

                Label valor = new Label("Valor: " + unidad.ValorActual());
                valor.setStyle("-fx-text-fill: #ffd700;");
                infoOverlay.getChildren().add(valor);

                String mods = unidad.getModificadores();
                if (mods != null && !mods.isEmpty() && !mods.equals("Base")) {
                    Label modificadores = new Label("Modificadores: " + mods);
                    modificadores.setStyle("-fx-text-fill: #b0e57c;");
                    infoOverlay.getChildren().add(modificadores);
                }
            } catch (Exception e) {
                infoOverlay.getChildren().add(new Label("Error mostrando info de carta unidad."));
            }
        } else {
            try {
                String nombre = null, tipo = null, descripcion = null;
                List<String> afectados = null;

                try {
                    nombre = (String) carta.getClass().getMethod("getNombre").invoke(carta);
                } catch (Exception ignored) {}

                try {
                    tipo = (String) carta.getClass().getMethod("getTipo").invoke(carta);
                } catch (Exception ignored) {}

                try {
                    descripcion = (String) carta.getClass().getMethod("getDescripcion").invoke(carta);
                } catch (Exception ignored) {}

                try {
                    Object afectadosObj = carta.getClass().getMethod("getAfectado").invoke(carta);
                    if (afectadosObj instanceof List<?>) {
                        afectados = new ArrayList<>();
                        for (Object o : (List<?>) afectadosObj) {
                            if (o != null) afectados.add(o.toString());
                        }
                    }
                } catch (Exception ignored) {}

                if (nombre != null) {
                    Label l = new Label("Nombre: " + nombre);
                    l.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");
                    infoOverlay.getChildren().add(l);
                }
                if (tipo != null) {
                    Label l = new Label("Tipo: " + tipo);
                    l.setStyle("-fx-text-fill: #e0e0e0;");
                    infoOverlay.getChildren().add(l);
                }
                if (afectados != null && !afectados.isEmpty()) {
                    Label l = new Label("Afecta: " + String.join(", ", afectados));
                    l.setStyle("-fx-text-fill: #b0e57c;");
                    infoOverlay.getChildren().add(l);
                }
                if (descripcion != null && !descripcion.isEmpty()) {
                    Label l = new Label(descripcion);
                    l.setWrapText(true);
                    l.setStyle("-fx-text-fill: #cccccc; -fx-font-size: 11;");
                    infoOverlay.getChildren().add(l);
                }
            } catch (Exception e) {
                infoOverlay.getChildren().add(new Label("Error mostrando info de carta especial."));
            }
        }

        return infoOverlay;
    }
}
