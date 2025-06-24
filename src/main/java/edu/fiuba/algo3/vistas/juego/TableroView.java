package edu.fiuba.algo3.vistas.juego;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;
import edu.fiuba.algo3.vistas.juego.cartas.CartaUnidadVisual;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


public class TableroView {

    private final Tablero tablero;
    private int seccionWidth = 630;
    private int seccionHeight = 75;
    private Carta cartaElegida;
    private List<HBox> seccionesVisuales = new ArrayList<>(); // Para mantener referencias

    public TableroView(Tablero tablero) {
        this.tablero = tablero;
    }

    public StackPane construir() {
        // Usar Pane para layout absoluto
        Pane root = new Pane();
        root.setPrefSize(App.WIDTH, App.HEIGHT);

//        Image fondo = new Image(Objects.requireNonNull(getClass().getResource("/imagenes/emptyBoard.png")).toExternalForm());
//        ImageView fondoView = new ImageView(fondo);
//        fondoView.setPreserveRatio(false);
//        fondoView.setFitWidth(App.WIDTH);
//        fondoView.setFitHeight(App.HEIGHT);
//        fondoView.setSmooth(true);
//        fondoView.setCache(true);
//        fondoView.setLayoutX(0);
//        fondoView.setLayoutY(0);

        Pane overlay = new Pane();
        overlay.setPrefSize(App.WIDTH, App.HEIGHT);
        int x_seccion = 500;
        int ultimo_y = 10;
        int espacio = 25;
        List<String> claves = List.of("Asedio1", "Rango1", "CuerpoACuerpo1", "Asedio0", "Rango0", "CuerpoACuerpo0");
        for (String clave:claves){
            agregarSeccion(overlay, clave, x_seccion, ultimo_y);
            ultimo_y = ultimo_y + espacio + seccionHeight;
        }

        root.getChildren().addAll(overlay);
        return new StackPane(root);
    }

    private void agregarSeccion(Pane contenedor, String clave, double x, double y) {
        Seccion seccion = tablero.obtenerSeccionPorClave(clave);

        HBox visual = new HBox(5);

        visual.setStyle("-fx-background-color: rgba(255,255,255,0.15); -fx-border-color: black;");
        visual.setPrefSize(seccionWidth, seccionHeight); // ancho ajustado a la imagen
        visual.setLayoutX(x);
        visual.setLayoutY(y);

        visual.setOnMouseClicked(event -> {
            if(cartaElegida != null){
                seccion.agregarCarta((CartaUnidad) cartaElegida);
                //Re-renderizar la seccion actualizada
                actualizarSeccion(visual, (CartaUnidad) cartaElegida);
                cartaElegida = null;
            }
        });

        for (CartaUnidad carta : seccion.getCartas()) {
            visual.getChildren().add(new CartaUnidadVisual(carta));
        }
        contenedor.getChildren().add(visual);
        seccionesVisuales.add(visual); // Guardamos la referencia para poder limpiar después
    }

    private void actualizarSeccion(HBox visual, CartaUnidad cartaElegida){

        // Se agrega la carta al HBox
        visual.getChildren().add(new CartaUnidadVisual(cartaElegida));
    }


   // Método para limpiar todo el tablero al cambio de ronda
   public void limpiarTablero() {
       for (HBox seccionVisual : seccionesVisuales) {
           seccionVisual.getChildren().clear();
       }
   }

}
