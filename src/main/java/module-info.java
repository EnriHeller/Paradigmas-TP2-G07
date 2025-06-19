module edu.fiuba.algo3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires json.simple;

    exports edu.fiuba.algo3;
    exports edu.fiuba.algo3.controller; // Exportar este paquete
    opens edu.fiuba.algo3.controller to javafx.fxml;
    exports edu.fiuba.algo3.vistas;
    opens edu.fiuba.algo3.vistas to javafx.fxml; // Abre el paquete para JavaFX
}