package edu.fiuba.algo3.vistas;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import edu.fiuba.algo3.controller.Audio;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.principal.NoSePuedeCumplirSolicitudDeCartas;
import edu.fiuba.algo3.modelo.secciones.tablero.TipoDeSeccionInvalidaError;
import edu.fiuba.algo3.vistas.juego.ManoView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class DescarteView extends Stage {
    private final List<Carta> cartasMano;
    private final Set<Carta> cartasSeleccionadas = new HashSet<>();
    private Jugador jugadorActual;

    private HBox contenedorCartas = new HBox(10);
    private HBox contenedorBoton;
    private int seSeleccionaronCartas;
    private ManoView mano;
    private Button confirmarBtn;
    private Label cartasSeleccionadasDescartadas;
    private int cantidadCartasDescartadas;
    private boolean seHizoElDescarte;


    public DescarteView(List<Carta> cartasMano, ManoView manoCartas, Jugador jugadorActual) {
        this.mano = manoCartas;
        this.cartasMano = cartasMano;
        this.seSeleccionaronCartas = 0;
        this.seHizoElDescarte = false;
        this.jugadorActual = jugadorActual;
    }

    public Parent construir() {
        BorderPane layout = new BorderPane();
        Image fondo = new Image(Objects.requireNonNull(getClass().getResource("/imagenes/fondo_descarte.png")).toExternalForm());
        BackgroundImage bgImage = new BackgroundImage(
                fondo,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, true)
        );
        layout.setBackground(new Background(bgImage));

        Label titulo = new Label("Podes descartar hasta 2 cartas antes de comenzar");
        titulo.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: gold; -fx-effect: dropshadow(gaussian, #000000, 2, 0.5, 1, 1);");
        titulo.setAlignment(Pos.CENTER);
        titulo.setTranslateY(-100);

        this.confirmarBtn = new Button("Confirmar descarte");
        this.confirmarBtn. setTranslateY(100);
        confirmarBtn.setStyle("-fx-background-color: #181818; -fx-background-radius: 18px; -fx-border-radius: 18px; -fx-border-color: gold; -fx-border-width: 2px; -fx-text-fill: gold; -fx-font-weight: bold; -fx-font-size: 16px; -fx-padding: 8 24; -fx-cursor: hand;");
        confirmarBtn.setOnAction(e -> {
            Audio click = Audio.getInstanceEffect();
            try {
                click.play("/audio/effects/sword.wav");
            } catch (Exception ex) {}
            try {
                confirmarDescarte();
            } catch (TipoDeSeccionInvalidaError | NoSePuedeCumplirSolicitudDeCartas ex) {
                throw new RuntimeException(ex);
            }
        });

        this.contenedorCartas = mostrarCartasDeLaMano();
        contenedorCartas.setAlignment(Pos.CENTER);

        this.contenedorBoton = new HBox(confirmarBtn);
        contenedorBoton.setAlignment(Pos.CENTER);

        this.cartasSeleccionadasDescartadas = new Label(cantidadCartasDescartadas + "/2");

        VBox contenedorCentral = new VBox(10, titulo, contenedorCartas, contenedorBoton, cartasSeleccionadasDescartadas);
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

    private String normalizarNombreParaImagen(String nombre) {
        // Quita tildes y caracteres especiales, deja solo letras y espacios
        String sinTildes = java.text.Normalizer.normalize(nombre, java.text.Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")
                .replaceAll("[^A-Za-z0-9 ]", "");
        // Convierte a camelCase
        String[] partes = sinTildes.split(" ");
        StringBuilder camelCase = new StringBuilder();
        for (int i = 0; i < partes.length; i++) {
            String parte = partes[i];
            if (parte.isEmpty()) continue;
            if (i == 0) {
                camelCase.append(parte.substring(0, 1).toLowerCase()).append(parte.substring(1));
            } else {
                camelCase.append(parte.substring(0, 1).toUpperCase()).append(parte.substring(1));
            }
        }
        return camelCase.toString();
    }

    private VBox crearCartaVisual(Carta carta) {
        Image imagen = null;
        String nombreBase;
        try {
            if (!carta.esEspecial()) {
                nombreBase = ((CartaUnidad) carta).getNombre();
            } else {
                // Usa getNombre si existe, si no mostrarCarta
                try {
                    nombreBase = (String) carta.getClass().getMethod("getNombre").invoke(carta);
                } catch (Exception e) {
                    nombreBase = carta.mostrarCarta();
                }
            }
        } catch (Exception e) {
            nombreBase = carta.mostrarCarta();
        }
        String nombreImagen = normalizarNombreParaImagen(nombreBase);
        String ruta = "/imagenes/" + nombreImagen + ".png";
        try {
            imagen = new Image(Objects.requireNonNull(getClass().getResourceAsStream(ruta)));
        } catch (Exception e) {
            imagen = new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                    carta.esEspecial() ? "/imagenes/falta-especial.png" : "/imagenes/falta-unidad.png"
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
        cartaBox.setStyle("-fx-border-color: transparent; -fx-border-width: 4px; -fx-cursor: hand;");

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
            if (seHizoElDescarte) return;

            if (cartasSeleccionadas.contains(carta)) {
                cartasSeleccionadas.remove(carta);
                cartaBox.setStyle("-fx-border-color: transparent; -fx-border-width: 4px;");
                seSeleccionaronCartas--;
                cantidadCartasDescartadas--;
            } else {
                if (seSeleccionaronCartas >= 2) return;
                cartasSeleccionadas.add(carta);
                cartaBox.setStyle("-fx-border-color: gold; -fx-border-width: 4px;");
                seSeleccionaronCartas++;
                cantidadCartasDescartadas++;
            }

            Audio click = Audio.getInstanceEffect();
            try {
                click.play("/audio/effects/click.wav");
            } catch (Exception ex) {}

            cartasSeleccionadasDescartadas.setText(cartasSeleccionadas.size() + "/2");
        });

        return cartaBox;
    }

    private void confirmarDescarte() throws TipoDeSeccionInvalidaError, NoSePuedeCumplirSolicitudDeCartas {
        if(seHizoElDescarte){
            return;
        }

        System.out.println("Se descartan " + cartasSeleccionadas.size() + " cartas.");

        cartasMano.removeAll(cartasSeleccionadas);
        cartasMano.addAll(jugadorActual.dameCartasNuevas(cantidadCartasDescartadas));

        mano.actualizarCartas(cartasMano);

        System.out.println("Cartas descartadas: " + cartasSeleccionadas);

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
