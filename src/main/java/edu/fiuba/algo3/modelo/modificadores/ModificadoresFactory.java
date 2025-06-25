package edu.fiuba.algo3.modelo.modificadores;

import edu.fiuba.algo3.modelo.cartas.especiales.MoraleBoost;

public class ModificadoresFactory {
    public Modificador crearModificador(String tipoModificador, Modificador siguienteModificador) {
        String normalizado = tipoModificador.trim().toLowerCase().replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u");
        // Si es una carta especial, devolver null
        if (
            normalizado.equals("quemar") ||
            normalizado.equals("tierra arrasada") ||
            normalizado.equals("cuerno del comandante") ||
            normalizado.equals("clima")
        ) {
            return null;
        }
        switch (normalizado) {
            case "legendaria":
                return new Legendaria();
            case "medico":
                return new Medico(siguienteModificador);
            case "agil":
                return new Agil(siguienteModificador);
            case "carta unida":
                return new Unidas(siguienteModificador);
            case "espia":
                return new Espias(siguienteModificador);
            case "suma valor base":
                return new SumaValorBase(siguienteModificador);
            default:
                throw new IllegalArgumentException("Tipo de modificador no reconocido: " + tipoModificador);
        }
    }
}
