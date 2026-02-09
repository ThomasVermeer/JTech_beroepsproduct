module com.example.jtech_beroepsproduct {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.jtech_beroepsproduct to javafx.fxml;
    exports com.example.jtech_beroepsproduct;
}