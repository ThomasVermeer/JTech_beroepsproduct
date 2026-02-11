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
        //De navigatiebalk met de vier specifieke knoppen
        HBox navBar = new HBox(15);
        navBar.getStyleClass().add("nav-bar");

        Button btnSoldaten = new Button("Soldaten");
        Button btnMaterialen = new Button("Materialen");
        Button btnVoertuigen = new Button("Voertuigen");
        Button btnKoppelingen = new Button("Koppelingen");

        //styling ophalen uit de style (nav-button uit de stylesheet)
        btnSoldaten.getStyleClass().add("nav-button");
        btnMaterialen.getStyleClass().add("nav-button");
        btnVoertuigen.getStyleClass().add("nav-button");
        btnKoppelingen.getStyleClass().add("nav-button");

        // acties koppelen aan de juiste schermen
        btnSoldaten.setOnAction(e -> openNieuwVenster("Soldaten Beheer", new SoldatenScreen()));
        btnMaterialen.setOnAction(e -> openNieuwVenster("Materiaal Beheer", new MateriaalScreen()));
        btnVoertuigen.setOnAction(e -> openNieuwVenster("Voertuigen Beheer", new VoertuigenScreen()));
        btnKoppelingen.setOnAction(e -> openNieuwVenster("Koppelingen Beheer", new KoppelingenScreen()));

        navBar.getChildren().addAll(btnSoldaten, btnMaterialen, btnVoertuigen, btnKoppelingen);

        // home scene setup
        Scene homeScene = new Scene(navBar, 600, 150);

        // css pad ophalen
        String cssPath = getClass().getResource("/com/example/jtech_beroepsproduct/stylesheets/style.css").toExternalForm();
        homeScene.getStylesheets().add(cssPath);

        stage.setTitle("J-Tac Beheersysteem");
        stage.setScene(homeScene);
        stage.show();
    }

    // hulpmethode om vensters te openen met de juiste styling
    private void openNieuwVenster(String titel, javafx.scene.layout.Region scherm) {
        Stage venster = new Stage();
        venster.setTitle(titel);
        Scene scene = new Scene(scherm, 900, 650); // Iets groter voor de tabellen

        String css = getClass().getResource("/com/example/jtech_beroepsproduct/stylesheets/style.css").toExternalForm();
        scene.getStylesheets().add(css);

        venster.setScene(scene);
        venster.show();
    }

    public static void main(String[] args) {
        launch();
    }
}