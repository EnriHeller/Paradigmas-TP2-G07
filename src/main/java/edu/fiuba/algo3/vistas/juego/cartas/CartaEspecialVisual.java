package edu.fiuba.algo3.vistas.juego.cartas;

import java.util.Objects;

import edu.fiuba.algo3.modelo.cartas.Carta;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CartaEspecialVisual extends CartaVisual {

    public CartaEspecialVisual(Carta carta) {
        super(carta);
        // Do not call construirVista() here; it will be called externally as with CartaUnidadVisual
    }

    @Override
    public void construirVista() {
        //this.setSpacing(5);

        String nombre = carta.mostrarCarta();
        String ruta = "/imagenes/" + nombre.replaceAll(" ", "") + ".png";
        Image imagen;
        try {
            imagen = new Image(Objects.requireNonNull(getClass().getResourceAsStream(ruta)));
        } catch (Exception e) {
            System.err.println("[CartaEspecialVisual] Imagen no encontrada para: " + ruta + ". Usando imagen por defecto. Excepcion: " + e);
            imagen = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/Lluviatorrencial.png")));
        }
        ImageView vistaImagen = crearImagenEstandar(imagen);
        setTamanioEstandar();
        this.getChildren().clear();
        this.getChildren().add(vistaImagen);
    }
}