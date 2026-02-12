package com.example.jtech_beroepsproduct.screens;

import com.example.jtech_beroepsproduct.model.Voertuig;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class VoertuigenScreen extends VBox {

    public VoertuigenScreen() {
        this.setPadding(new Insets(20));
        this.setSpacing(20);

        Label titel = new Label("Voertuigen Beheer");
        titel.getStyleClass().add("headline-1");

        // invoervelden
        HBox inputBalk = new HBox(10);

        TextField txtKenteken = new TextField();
        txtKenteken.setPromptText("kenteken");

        TextField txtNaam = new TextField();
        txtNaam.setPromptText("voertuignaam");

        TextField txtSoort = new TextField();
        txtSoort.setPromptText("soort (bijv. Tank, Jeep)");

        Button btnOpslaan = new Button("voertuig maken of bijwerken");
        btnOpslaan.getStyleClass().add("action-button");

        inputBalk.getChildren().addAll(txtKenteken, txtNaam, txtSoort, btnOpslaan);

        // De tabel met Generics om warnings te voorkomen
        TableView<Voertuig> tableView = new TableView<>();

        TableColumn<Voertuig, String> colKenteken = new TableColumn<>("kenteken");
        TableColumn<Voertuig, String> colNaam = new TableColumn<>("voertuignaam");
        TableColumn<Voertuig, String> colSoort = new TableColumn<>("soort");
        TableColumn<Voertuig, String> colActies = new TableColumn<>("acties");

        tableView.getColumns().addAll(colKenteken, colNaam, colSoort, colActies);
        tableView.setPrefHeight(400);

        this.getChildren().addAll(titel, inputBalk, tableView);
    }
}