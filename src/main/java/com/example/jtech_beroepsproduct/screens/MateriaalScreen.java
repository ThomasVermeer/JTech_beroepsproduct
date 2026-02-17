package com.example.jtech_beroepsproduct.screens;

import com.example.jtech_beroepsproduct.controller.MateriaalController;
import com.example.jtech_beroepsproduct.model.Materiaal;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MateriaalScreen extends VBox {

    private MateriaalController controller = new MateriaalController();
    private TableView<Materiaal> tableView = new TableView<>();
    private TextField txtZoek = new TextField();

    public MateriaalScreen() {
        this.setPadding(new Insets(20));
        this.setSpacing(20);

        Label titel = new Label("Materiaal Beheer");
        titel.getStyleClass().add("headline-1");

        // Zoekbalk toevoegen
        HBox zoekBalk = new HBox(10);
        txtZoek.setPromptText("Zoek op nummer, type of doel...");
        txtZoek.setPrefWidth(300);
        zoekBalk.getChildren().addAll(new Label("Zoeken:"), txtZoek);

        HBox inputBalk = new HBox(10);
        TextField txtMateriaalNummer = new TextField();
        txtMateriaalNummer.setPromptText("materiaal nummer");
        TextField txtType = new TextField();
        txtType.setPromptText("type");
        TextField txtDoel = new TextField();
        txtDoel.setPromptText("doel");

        Button btnOpslaan = new Button("materiaal maken of bijwerken");
        btnOpslaan.getStyleClass().add("action-button");

        Button btnVerwijderen = new Button("verwijderen");
        btnVerwijderen.getStyleClass().add("delete-button");

        inputBalk.getChildren().addAll(txtMateriaalNummer, txtType, txtDoel, btnOpslaan, btnVerwijderen);

        // tabel met generics om warnings te voorkomen
        TableColumn<Materiaal, String> colNummer = new TableColumn<>("materiaal nummer");
        colNummer.setCellValueFactory(new PropertyValueFactory<>("materiaalnummer"));

        TableColumn<Materiaal, String> colType = new TableColumn<>("type");
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Materiaal, String> colDoel = new TableColumn<>("doel");
        colDoel.setCellValueFactory(new PropertyValueFactory<>("doel"));

        tableView.getColumns().addAll(colNummer, colType, colDoel);
        tableView.setPrefHeight(400);

        refreshTable();

        // de heerlijke functie om met een simpele click data te kunnen bewerken zoals in professionele programma's word gedaan
        tableView.setOnMouseClicked(e -> {
            Materiaal geselecteerd = tableView.getSelectionModel().getSelectedItem();
            if (geselecteerd != null) {
                txtMateriaalNummer.setText(geselecteerd.getMateriaalnummer());
                txtType.setText(geselecteerd.getType());
                txtDoel.setText(geselecteerd.getDoel());
            }
        });

        btnOpslaan.setOnAction(e -> {
            if (!txtMateriaalNummer.getText().isEmpty()) {
                Materiaal m = new Materiaal(
                        txtMateriaalNummer.getText(),
                        txtType.getText(),
                        txtDoel.getText()
                );
                controller.opslaan(m);
                refreshTable();
                txtMateriaalNummer.clear();
                txtType.clear();
                txtDoel.clear();
            }
        });

        btnVerwijderen.setOnAction(e -> {
            Materiaal geselecteerd = tableView.getSelectionModel().getSelectedItem();
            if (geselecteerd != null) {
                controller.verwijderen(geselecteerd.getMateriaalnummer());
                refreshTable();
                txtMateriaalNummer.clear();
                txtType.clear();
                txtDoel.clear();
            }
        });

        this.getChildren().addAll(titel, zoekBalk, inputBalk, tableView);
    }

    private void refreshTable() {
        FilteredList<Materiaal> filteredData = new FilteredList<>(controller.getAllMateriaal(), p -> true);

        txtZoek.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(materiaal -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (materiaal.getMateriaalnummer().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (materiaal.getType().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (materiaal.getDoel().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        tableView.setItems(filteredData);
    }
}