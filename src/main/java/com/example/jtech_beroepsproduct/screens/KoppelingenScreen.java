package com.example.jtech_beroepsproduct.screens;

import com.example.jtech_beroepsproduct.controller.*;
import com.example.jtech_beroepsproduct.model.*;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.time.LocalDateTime;

public class KoppelingenScreen extends VBox {

    private KoppelingController controller = new KoppelingController();
    private SoldaatController soldaatController = new SoldaatController();
    private TableView<Koppeling> tableView = new TableView<>();
    private TextField txtZoek = new TextField();

    private ComboBox<String> cbSoldaat = new ComboBox<>();
    private ComboBox<String> cbMateriaal = new ComboBox<>();
    private ComboBox<String> cbVoertuig = new ComboBox<>();

    public KoppelingenScreen() {
        this.setPadding(new Insets(20));
        this.setSpacing(20);

        Label titel = new Label("Koppel Soldaat aan Uitrusting");
        titel.getStyleClass().add("headline-1");

        // Zoekbalk toevoegen voor de filter-functionaliteit
        HBox zoekBalk = new HBox(10);
        txtZoek.setPromptText("Filter op soldaat, materiaal of kenteken...");
        txtZoek.setPrefWidth(300);
        zoekBalk.getChildren().addAll(new Label("Zoeken:"), txtZoek);

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
            }
        });

        btnVerwijderen.setOnAction(e -> {
            Koppeling geselecteerd = tableView.getSelectionModel().getSelectedItem();
            if (geselecteerd != null) {
                controller.verwijderen(geselecteerd.getId());
                refreshTable();
            }
        });

        this.getChildren().addAll(titel, zoekBalk, inputBalk, tableView);
    }

    private void refreshTable() {
        // we halen de data op via de simpele controller-methode
        FilteredList<Koppeling> filteredData = new FilteredList<>(controller.getAllKoppelingen(), p -> true);

        // de filter-logica die alleen in Java draait
        txtZoek.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(koppeling -> {
                if (newValue == null || newValue.isEmpty()) return true;
                String filter = newValue.toLowerCase();

                if (koppeling.getSoldaatnummer().toLowerCase().contains(filter)) return true;
                if (koppeling.getMateriaalnummer() != null && koppeling.getMateriaalnummer().toLowerCase().contains(filter)) return true;
                if (koppeling.getKenteken() != null && koppeling.getKenteken().toLowerCase().contains(filter)) return true;

                return false;
            });
        });

        tableView.setItems(filteredData);

        // Dropdowns verversen
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