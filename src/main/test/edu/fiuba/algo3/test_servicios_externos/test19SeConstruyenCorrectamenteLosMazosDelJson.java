package edu.fiuba.algo3.test_servicios_externos;

import edu.fiuba.algo3.controller.ConstructorMazo;
import edu.fiuba.algo3.modelo.cartas.Carta;
import edu.fiuba.algo3.modelo.secciones.jugador.Mazo;
import edu.fiuba.algo3.modelo.modificadores.ModificadoresFactory;
import edu.fiuba.algo3.modelo.principal.Jugador;
import edu.fiuba.algo3.modelo.cartas.CartasFactory;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class test19SeConstruyenCorrectamenteLosMazosDelJson {

    String rutaGwent = "src/main/resources/json/gwent.json";
    //String rutaUnidades = "src/main/resources/json/unidades.json";
    //String rutaEspeciales = "src/main/resources/json/especiales.json";

    @Test
    public void testObtenerMazosDesdeJSON() throws Exception {

        ConstructorMazo constructor = new ConstructorMazo(
                new ModificadoresFactory(),
                new CartasFactory(),
                new JSONParser()
        );

        List<Mazo> mazos = constructor.construirMazos(rutaGwent);
        Mazo m1 = mazos.get(0);
        Mazo m2 = mazos.get(1);

        Jugador j1 = new Jugador("Pepito");
        Jugador j2 = new Jugador("Juanito");

        assertTrue(j1.HayCartasSuficientesEnMazo(m1));
        assertTrue(j2.HayCartasSuficientesEnMazo(m2));

        // String rutaEspeciales = "src/main/resources/json/especiales.json";
        // ConstructorMazo constructor = new ConstructorMazo(
        //         new ModificadoresFactory(),
        //         new CartasFactory(),
        //         new JSONParser()
        // );
        // // Usamos el método para mazo personalizado con ambos archivos
        // Mazo mazo = constructor.personalizarMazo(rutaUnidades, rutaEspeciales);
        // assertNotNull(mazo, "El mazo no debe ser null");
        // assertTrue(mazo.cartasRestantes() >= 10, "El mazo debe tener al menos 10 cartas");
        // List<Carta> cartas = mazo.obtenerCartas();
        // assertFalse(cartas.isEmpty(), "El mazo debe tener cartas");
        // assertNotNull(cartas.get(0), "Las cartas deben ser válidas");
    }
}
