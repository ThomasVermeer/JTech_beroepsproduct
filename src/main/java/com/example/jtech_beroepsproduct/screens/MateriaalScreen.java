package com.example.jtech_beroepsproduct.screens;

import com.example.jtech_beroepsproduct.model.Materiaal;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MateriaalScreen extends VBox {

    public MateriaalScreen() {
        this.setPadding(new Insets(20));
        this.setSpacing(20);

        Label titel = new Label("Materiaal Beheer");
        titel.getStyleClass().add("headline-1");

        HBox inputBalk = new HBox(10);
        TextField txtMateriaalNummer = new TextField();
        txtMateriaalNummer.setPromptText("materiaal nummer");
        TextField txtType = new TextField();
        txtType.setPromptText("type");
        TextField txtDoel = new TextField();
        txtDoel.setPromptText("doel");

        Button btnOpslaan = new Button("materiaal toevoegen");
        btnOpslaan.getStyleClass().add("action-button");
        inputBalk.getChildren().addAll(txtMateriaalNummer, txtType, txtDoel, btnOpslaan);

        // tabel met Generics om warnings te voorkomen (je geeft gewoon aan welk datatype het is)
        TableView<Materiaal> tableView = new TableView<>();

        TableColumn<Materiaal, String> colNummer = new TableColumn<>("materiaal nummer");
        TableColumn<Materiaal, String> colType = new TableColumn<>("type");
        TableColumn<Materiaal, String> colDoel = new TableColumn<>("doel");
        TableColumn<Materiaal, String> colActies = new TableColumn<>("acties");

        tableView.getColumns().addAll(colNummer, colType, colDoel, colActies);
        tableView.setPrefHeight(400);

        this.getChildren().addAll(titel, inputBalk, tableView);
    }
}