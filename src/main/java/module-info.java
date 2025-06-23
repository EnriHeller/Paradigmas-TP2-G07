module edu.fiuba.algo3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires json.simple;
    requires jdk.dynalink;

    exports edu.fiuba.algo3;
    exports edu.fiuba.algo3.controller; // Exportar este paquete
    opens edu.fiuba.algo3.controller to javafx.fxml;
    opens edu.fiuba.algo3.vistas to javafx.fxml;
    exports edu.fiuba.algo3.vistas;
    exports edu.fiuba.algo3.vistas.Juego;
    opens edu.fiuba.algo3.vistas.Juego to javafx.fxml;
}