module edu.fiuba.algo3 {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires java.desktop;
    requires json.simple;
    requires transitive javafx.graphics;

    exports edu.fiuba.algo3;
    exports edu.fiuba.algo3.controller;
    exports edu.fiuba.algo3.vistas.juego;
    exports edu.fiuba.algo3.vistas.juego.cartas;
    exports edu.fiuba.algo3.modelo.cartas;
    exports edu.fiuba.algo3.modelo.cartas.unidades;
    opens edu.fiuba.algo3.controller to javafx.fxml;
}