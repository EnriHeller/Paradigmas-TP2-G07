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

    private String normalizarNombreParaImagen(String nombre) {
        // Quita tildes, espacios, mayúsculas, y caracteres especiales
        String normalizado = java.text.Normalizer.normalize(nombre, java.text.Normalizer.Form.NFD)
            .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")
            .replaceAll("[^A-Za-z0-9]", "");
        return normalizado;
    }

    @Override
    public void construirVista() {
        String nombreBase;
        // Si la carta tiene getNombre(), úsalo. Si no, parsea el nombre base.
        try {
            // Intenta usar getNombre() si existe
            nombreBase = (String) carta.getClass().getMethod("getNombre").invoke(carta);
        } catch (Exception e) {
            // Fallback: usa mostrarCarta() y toma solo la primera palabra (antes de un espacio o modificador)
            String mostrar = carta.mostrarCarta();
            int idx = mostrar.indexOf(' ');
            if (idx > 0) nombreBase = mostrar.substring(0, idx);
            else nombreBase = mostrar;
        }
        String nombreImagen = normalizarNombreParaImagen(nombreBase);
        String ruta = "/imagenes/" + nombreImagen + ".png";
        System.err.println("[CartaEspecialVisual] Buscando imagen: " + ruta);
        Image imagen;
        try {
            imagen = new Image(Objects.requireNonNull(getClass().getResourceAsStream(ruta)));
        } catch (Exception e) {
            System.err.println("[CartaEspecialVisual] Imagen no encontrada para: " + ruta + ". Usando imagen por defecto. Excepcion: " + e);
            imagen = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/Lluviatorrencial.png")));
        }
        ImageView vistaImagen = crearImagenEstandar(imagen);
        setTamanioEstandar();
        mainStack.getChildren().clear();
        mainStack.getChildren().addAll(vistaImagen, hoverBorder); // hoverBorder always on top
    }
}