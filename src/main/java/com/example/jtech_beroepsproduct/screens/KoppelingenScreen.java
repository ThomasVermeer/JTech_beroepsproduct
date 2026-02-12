package com.example.jtech_beroepsproduct.screens;

import com.example.jtech_beroepsproduct.model.Koppeling;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class KoppelingenScreen extends VBox {

    public KoppelingenScreen() {
        this.setPadding(new Insets(20));
        this.setSpacing(20);

        Label titel = new Label("Koppel Soldaat aan Uitrusting");
        titel.getStyleClass().add("headline-1");

        // Invoergedeelte op basis van ERD
        HBox inputBalk = new HBox(10);

        TextField txtSoldaat = new TextField();
        txtSoldaat.setPromptText("soldaatnummer");

        TextField txtMateriaal = new TextField();
        txtMateriaal.setPromptText("materiaalnummer");

        TextField txtKenteken = new TextField();
        txtKenteken.setPromptText("kenteken");

        Button btnKoppel = new Button("koppeling maken");
        btnKoppel.getStyleClass().add("action-button");

        inputBalk.getChildren().addAll(txtSoldaat, txtMateriaal, txtKenteken, btnKoppel);

        // De Tabel met Generics
        TableView<Koppeling> tableView = new TableView<>();

        TableColumn<Koppeling, String> colSoldaat = new TableColumn<>("soldaat");
        TableColumn<Koppeling, String> colMateriaal = new TableColumn<>("materiaal");
        TableColumn<Koppeling, String> colVoertuig = new TableColumn<>("voertuig");
        TableColumn<Koppeling, String> colDatum = new TableColumn<>("datum");

        tableView.getColumns().addAll(colSoldaat, colMateriaal, colVoertuig, colDatum);
        tableView.setPrefHeight(400);

        this.getChildren().addAll(titel, inputBalk, tableView);
    }
}