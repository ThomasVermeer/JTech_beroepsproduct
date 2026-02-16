package com.example.jtech_beroepsproduct;

import com.example.jtech_beroepsproduct.screens.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        // we gebruiken een BorderPane om de layout te beheren in één venster
        BorderPane root = new BorderPane();

        // de navigatiebalk met de vier specifieke knoppen
        HBox navBar = new HBox(15);
        navBar.getStyleClass().add("nav-bar");

        Button btnSoldaten = new Button("Soldaten");
        Button btnMaterialen = new Button("Materialen");
        Button btnVoertuigen = new Button("Voertuigen");
        Button btnKoppelingen = new Button("Koppelingen");

        // styling ophalen uit de style (nav-button uit de stylesheet)
        btnSoldaten.getStyleClass().add("nav-button");
        btnMaterialen.getStyleClass().add("nav-button");
        btnVoertuigen.getStyleClass().add("nav-button");
        btnKoppelingen.getStyleClass().add("nav-button");

        // ACTIE: Nu zetten we het scherm in het midden van onze BorderPane i.p.v. een nieuw venster
        btnSoldaten.setOnAction(e -> root.setCenter(new SoldatenScreen()));
        btnMaterialen.setOnAction(e -> root.setCenter(new MateriaalScreen()));
        btnVoertuigen.setOnAction(e -> root.setCenter(new VoertuigenScreen()));
        btnKoppelingen.setOnAction(e -> root.setCenter(new KoppelingenScreen()));

        navBar.getChildren().addAll(btnSoldaten, btnMaterialen, btnVoertuigen, btnKoppelingen);

        // set de navigatiebalk aan de bovenkant
        root.setTop(navBar);

        // startscherm instellen (bijvoorbeeld leeg of direct de soldaten)
        root.setCenter(new SoldatenScreen());

        // Scene setup - groter maken omdat alles nu in één venster gebeurt
        Scene homeScene = new Scene(root, 1000, 750);

        // css pad ophalen
        String cssPath = getClass().getResource("/com/example/jtech_beroepsproduct/stylesheets/style.css").toExternalForm();
        homeScene.getStylesheets().add(cssPath);

        stage.setTitle("J-Tac Beheersysteem");
        stage.setScene(homeScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}