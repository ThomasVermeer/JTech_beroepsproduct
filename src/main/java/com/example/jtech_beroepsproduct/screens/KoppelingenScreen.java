package com.example.jtech_beroepsproduct.screens;

import com.example.jtech_beroepsproduct.controller.*;
import com.example.jtech_beroepsproduct.model.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.time.LocalDateTime;

public class KoppelingenScreen extends VBox {

    private KoppelingController controller = new KoppelingController();
    private SoldaatController soldaatController = new SoldaatController();
    private MateriaalController materiaalController = new MateriaalController();
    private VoertuigController voertuigController = new VoertuigController();

    private TableView<Koppeling> tableView = new TableView<>();

    // comboBoxen globaal maken zodat refreshTable erbij kan
    private ComboBox<String> cbSoldaat = new ComboBox<>();
    private ComboBox<String> cbMateriaal = new ComboBox<>();
    private ComboBox<String> cbVoertuig = new ComboBox<>();

    public KoppelingenScreen() {
        this.setPadding(new Insets(20));
        this.setSpacing(20);

        Label titel = new Label("Koppel Soldaat aan Uitrusting");
        titel.getStyleClass().add("headline-1");

        HBox inputBalk = new HBox(10);

        cbSoldaat.setPromptText("Kies soldaat");
        cbMateriaal.setPromptText("Kies materiaal (optioneel)");
        cbVoertuig.setPromptText("Kies voertuig (optioneel)");

        Button btnKoppel = new Button("koppeling maken");
        btnKoppel.getStyleClass().add("action-button");

        Button btnVerwijderen = new Button("verwijderen");
        btnVerwijderen.getStyleClass().add("delete-button");

        inputBalk.getChildren().addAll(cbSoldaat, cbMateriaal, cbVoertuig, btnKoppel, btnVerwijderen);

        TableColumn<Koppeling, String> colSoldaat = new TableColumn<>("soldaat");
        colSoldaat.setCellValueFactory(new PropertyValueFactory<>("soldaatnummer"));

        TableColumn<Koppeling, String> colMateriaal = new TableColumn<>("materiaal");
        colMateriaal.setCellValueFactory(new PropertyValueFactory<>("materiaalnummer"));

        TableColumn<Koppeling, String> colVoertuig = new TableColumn<>("voertuig");
        colVoertuig.setCellValueFactory(new PropertyValueFactory<>("kenteken"));

        TableColumn<Koppeling, LocalDateTime> colDatum = new TableColumn<>("datum");
        colDatum.setCellValueFactory(new PropertyValueFactory<>("aanmaakdatum"));

        tableView.getColumns().addAll(colSoldaat, colMateriaal, colVoertuig, colDatum);
        tableView.setPrefHeight(400);

        refreshTable();

        btnKoppel.setOnAction(e -> {
            if (cbSoldaat.getValue() != null && (cbMateriaal.getValue() != null || cbVoertuig.getValue() != null)) {
                controller.koppel(cbSoldaat.getValue(), cbMateriaal.getValue(), cbVoertuig.getValue());
                refreshTable();

                cbSoldaat.setValue(null);
                cbMateriaal.setValue(null);
                cbVoertuig.setValue(null);
            } else {
                System.out.println("Selecteer een soldaat en minstens één item (materiaal of voertuig).");
            }
        });

        btnVerwijderen.setOnAction(e -> {
            Koppeling geselecteerd = tableView.getSelectionModel().getSelectedItem();
            if (geselecteerd != null) {
                controller.verwijderen(geselecteerd.getId());
                refreshTable();
            }
        });

        this.getChildren().addAll(titel, inputBalk, tableView);
    }

    private void refreshTable() {
        // update de tabel
        tableView.setItems(controller.getAllKoppelingen());

        // update de dropdowns zodat reeds gekoppelde items verdwijnen
        cbSoldaat.getItems().clear();
        soldaatController.getAllSoldaten().forEach(s -> cbSoldaat.getItems().add(s.getNummer()));

        cbMateriaal.getItems().clear();
        cbMateriaal.getItems().add(null);
        cbMateriaal.getItems().addAll(controller.getBeschikbareMaterialen());

        cbVoertuig.getItems().clear();
        cbVoertuig.getItems().add(null);
        cbVoertuig.getItems().addAll(controller.getBeschikbareVoertuigen());
    }
}