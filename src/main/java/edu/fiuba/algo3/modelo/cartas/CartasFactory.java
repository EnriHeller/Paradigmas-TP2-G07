package edu.fiuba.algo3.modelo.cartas;

import java.util.List;

import edu.fiuba.algo3.modelo.cartas.especiales.CartaNevada;
import edu.fiuba.algo3.modelo.cartas.especiales.TierraArrasada;
import edu.fiuba.algo3.modelo.cartas.unidades.CartaUnidad;
import edu.fiuba.algo3.modelo.modificadores.Modificador;

public class CartasFactory {
    public Carta crearCarta(String tipo ,String nombre, List<String> seccionesOAfectados ,long cantidad, Modificador modificador, String descripcion, String tipoEspecial) {
        if (tipo.equals("u")) {
            return new CartaUnidad(nombre, seccionesOAfectados, (int) cantidad, modificador);
        } else if (tipo.equals("e")) {
            if (tipoEspecial != null) {
                switch (tipoEspecial.toLowerCase()) {
                    case "tierra arrasada":
                        return new TierraArrasada();
                    case "morale boost":
                        // return new MoraleBoost(new Base(), nombre); // Si tienes la clase
                        break;
                    case "clima":
                        // Para todos los climas, usa una sola clase robusta
                        return new CartaNevada();
                    default:
                        break;
                }
            }
            // Si no se reconoce el tipo, devuelve una carta dummy robusta
            return new CartaNevada();
        }
        // Si no es ni unidad ni especial, devuelve una carta dummy robusta
        return new CartaNevada();
    }
}

