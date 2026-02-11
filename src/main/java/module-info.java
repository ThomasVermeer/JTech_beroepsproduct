module com.example.jtech_beroepsproduct {
    requires javafx.controls;
    requires javafx.fxml;
    //zelf toegevoegd
    opens com.example.jtech_beroepsproduct.model to javafx.base;
//zelf toegevoegd
    opens com.example.jtech_beroepsproduct to javafx.fxml;
    exports com.example.jtech_beroepsproduct;
    //zelf toegevoegd
    exports com.example.jtech_beroepsproduct.screens;
}