package edu.fiuba.algo3.vistas.juego.cartas;

import java.util.Objects;

import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CartaUnidadVisual extends CartaVisual {

    public CartaUnidadVisual(CartaUnidad carta) {
        super(carta);
        // construirVista() se debe llamar externamente después de la construcción
    }

    private String normalizarNombreParaImagen(String nombre) {
        String normalizado = java.text.Normalizer.normalize(nombre, java.text.Normalizer.Form.NFD)
            .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")
            .replaceAll("[^A-Za-z0-9]", "");
        return normalizado;
    }

    @Override
    public void construirVista() {
        CartaUnidad unidad = (CartaUnidad) carta;
        String nombreBase;
        try {
            nombreBase = unidad.getNombre();
        } catch (Exception e) {
            String mostrar = unidad.mostrarCarta();
            int idx = mostrar.indexOf(' ');
            if (idx > 0) nombreBase = mostrar.substring(0, idx);
            else nombreBase = mostrar;
        }
        String nombreImagen = normalizarNombreParaImagen(nombreBase);
        String ruta = "/imagenes/" + nombreImagen + ".png";
        System.err.println("[CartaUnidadVisual] Buscando imagen: " + ruta);
        Image imagen;
        try {
            imagen = new Image(Objects.requireNonNull(getClass().getResourceAsStream(ruta)));
        } catch (Exception e) {
            System.err.println("[CartaUnidadVisual] Imagen no encontrada para: " + ruta + ". Usando imagen por defecto. Excepcion: " + e);
            imagen = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/BirnaBrant.png")));
        }
        ImageView vistaImagen = crearImagenEstandar(imagen);

        // Solo imagen y hoverBorder, igual que CartaEspecialVisual
        this.getChildren().clear();
        setTamanioEstandar();
        mainStack.getChildren().clear();
        mainStack.getChildren().addAll(vistaImagen, hoverBorder); // hoverBorder siempre arriba
        if (!this.getChildren().contains(mainStack)) {
            this.getChildren().add(mainStack);
        }
    }
}
