package com.example.jtech_beroepsproduct.screens;

import com.example.jtech_beroepsproduct.controller.VoertuigController;
import com.example.jtech_beroepsproduct.model.Voertuig;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class VoertuigenScreen extends VBox {

    private VoertuigController controller = new VoertuigController();
    private TableView<Voertuig> tableView = new TableView<>();

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

        Button btnVerwijderen = new Button("verwijderen");
        btnVerwijderen.getStyleClass().add("delete-button");

        inputBalk.getChildren().addAll(txtKenteken, txtNaam, txtSoort, btnOpslaan, btnVerwijderen);

        // De tabel met Generics om warnings te voorkomen
        TableColumn<Voertuig, String> colKenteken = new TableColumn<>("kenteken");
        colKenteken.setCellValueFactory(new PropertyValueFactory<>("kenteken"));

        TableColumn<Voertuig, String> colNaam = new TableColumn<>("voertuignaam");
        colNaam.setCellValueFactory(new PropertyValueFactory<>("voertuignaam"));

        TableColumn<Voertuig, String> colSoort = new TableColumn<>("soort");
        colSoort.setCellValueFactory(new PropertyValueFactory<>("soort"));

        tableView.getColumns().addAll(colKenteken, colNaam, colSoort);
        tableView.setPrefHeight(400);

        refreshTable();

        // de heerlijke functie om met een simpele click data te kunnen bewerken zoals in professionele programma's word gedaan
        tableView.setOnMouseClicked(e -> {
            Voertuig geselecteerd = tableView.getSelectionModel().getSelectedItem();
            if (geselecteerd != null) {
                txtKenteken.setText(geselecteerd.getKenteken());
                txtNaam.setText(geselecteerd.getVoertuignaam());
                txtSoort.setText(geselecteerd.getSoort());
            }
        });

        btnOpslaan.setOnAction(e -> {
            if (!txtKenteken.getText().isEmpty()) {
                Voertuig v = new Voertuig(
                        txtKenteken.getText(),
                        txtNaam.getText(),
                        txtSoort.getText()
                );
                controller.opslaan(v);
                refreshTable();
                txtKenteken.clear();
                txtNaam.clear();
                txtSoort.clear();
            }
        });

        btnVerwijderen.setOnAction(e -> {
            Voertuig geselecteerd = tableView.getSelectionModel().getSelectedItem();
            if (geselecteerd != null) {
                controller.verwijderen(geselecteerd.getKenteken());
                refreshTable();
                txtKenteken.clear();
                txtNaam.clear();
                txtSoort.clear();
            }
        });

        this.getChildren().addAll(titel, inputBalk, tableView);
    }

    private void refreshTable() {
        tableView.setItems(controller.getAllVoertuigen());
    }
}