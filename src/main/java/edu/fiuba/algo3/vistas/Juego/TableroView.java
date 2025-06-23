package edu.fiuba.algo3.vistas.Juego;

import edu.fiuba.algo3.modelo.secciones.tablero.Seccion;
import edu.fiuba.algo3.modelo.secciones.tablero.Tablero;
import edu.fiuba.algo3.vistas.Juego.Cartas.CartaUnidadVisual;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class TableroView {

    @FXML private HBox seccionAsedio1;
    @FXML private HBox seccionRango1;
    @FXML private HBox seccionCuerpoACuerpo1;
    @FXML private HBox seccionCuerpoACuerpo0;
    @FXML private HBox seccionRango0;
    @FXML private HBox seccionAsedio0;

    private final Tablero tablero;

    public TableroView(Tablero tablero) {
        this.tablero = tablero;
    }

    @FXML
    public Node construir() {
        cargarCartasEn(seccionAsedio1, "Asedio1");
        cargarCartasEn(seccionRango1, "Rango1");
        cargarCartasEn(seccionCuerpoACuerpo1, "CuerpoACuerpo1");
        cargarCartasEn(seccionCuerpoACuerpo0, "CuerpoACuerpo0");
        cargarCartasEn(seccionRango0, "Rango0");
        cargarCartasEn(seccionAsedio0, "Asedio0");
        return null;
    }

    private void cargarCartasEn(HBox hbox, String clave) {
        Seccion seccion = tablero.obtenerSeccionPorClave(clave);
        for (var carta : seccion.getCartas()) {
            hbox.getChildren().add(new CartaUnidadVisual(carta));
        }
    }
}
