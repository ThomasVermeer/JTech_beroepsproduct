package com.example.jtech_beroepsproduct;

import com.example.jtech_beroepsproduct.screens.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        // 1. De balk met knoppen
        HBox navBar = new HBox(15);
        navBar.getStyleClass().add("nav-bar");

        Button btnSoldaten = new Button("Soldaten");
        Button btnMateriaal = new Button("Materiaal");

        // 2. Koppel de CSS klassen uit je styleguide
        btnSoldaten.getStyleClass().add("nav-button");
        btnMateriaal.getStyleClass().add("nav-button");

        // 3. ACTIE: Open soldaten in een nieuw venster
        btnSoldaten.setOnAction(e -> {
            Stage venster = new Stage();
            venster.setTitle("Soldaten Beheer");

            // Maak een nieuwe scene voor dit specifieke venster
            Scene soldatenScene = new Scene(new SoldatenScreen(), 800, 600);

            // Gebruik het juiste relatieve pad naar je CSS
            soldatenScene.getStylesheets().add(getClass().getResource("stylesheets/style.css").toExternalForm());

            venster.setScene(soldatenScene);
            venster.show();
        });

        navBar.getChildren().addAll(btnSoldaten, btnMateriaal);


        Scene homeScene = new Scene(navBar, 400, 100);


        homeScene.getStylesheets().add(getClass().getResource("/com/example/jtech_beroepsproduct/stylesheets/style.css").toExternalForm());

        stage.setTitle("J-Tac Menu");
        stage.setScene(homeScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}