package edu.fiuba.algo3.modelo.modificadores;

public class ModificadoresFactory {
    public Modificador crearModificador(String tipoModificador, Modificador siguienteModificador) {
        switch (tipoModificador) {
            case "Legendaria":
                return new Legendaria();
            case "Medico":
                return new Medico(siguienteModificador);
            case "Agil":
                return new Agil(siguienteModificador);
            case "Carta Unida":
                return new Unidas(siguienteModificador);
            case "Espia":
                return new Espias(siguienteModificador);
            case "suma valor base":
                return new SumaValorBase(siguienteModificador);
            case "Morale Boost":
                return new Legendaria();
            case "Quemar":
                return new Legendaria();
            default:
                throw new IllegalArgumentException("Tipo de modificador no reconocido: " + tipoModificador);
        }
    }
}
