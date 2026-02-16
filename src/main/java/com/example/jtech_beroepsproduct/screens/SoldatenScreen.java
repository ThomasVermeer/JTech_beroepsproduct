package com.example.jtech_beroepsproduct.screens;

import com.example.jtech_beroepsproduct.controller.SoldaatController;
import com.example.jtech_beroepsproduct.model.Soldaat;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SoldatenScreen extends VBox {

    private SoldaatController controller = new SoldaatController();
    private TableView<Soldaat> tableView = new TableView<>();
    // Het nieuwe zoekveld maken we aan als variabele zodat we er overal bij kunnen
    private TextField txtZoek = new TextField();

    public SoldatenScreen() {
        //instellingen voor de layout (padding en ruimte tussen elementen)
        this.setPadding(new Insets(20));
        this.setSpacing(20);

        // titel en headline 1 ophalen uit onze stylesheet
        Label titel = new Label("Soldaten Beheer");
        titel.getStyleClass().add("headline-1");

        // Zoekbalk toevoegen boven de inputvelden
        HBox zoekBalk = new HBox(10);
        txtZoek.setPromptText("Zoek op nummer, naam, rang of datum...");
        txtZoek.setPrefWidth(300);
        zoekBalk.getChildren().addAll(new Label("Zoeken:"), txtZoek);

        // invoer velden maken met placeholder tekst
        HBox inputBalk = new HBox(10);

        TextField txtNummer = new TextField();
        txtNummer.setPromptText("soldaat nummer");

        TextField txtNaam = new TextField();
        txtNaam.setPromptText("soldaat naam");

        TextField txtRang = new TextField();
        txtRang.setPromptText("soldaat rang");

        DatePicker dateGeboorte = new DatePicker();
        dateGeboorte.setPromptText("geboorte datum");

        Button btnOpslaan = new Button("soldaat maken of bijwerken");
        btnOpslaan.getStyleClass().add("action-button");

        Button btnVerwijderen = new Button("verwijderen");
        btnVerwijderen.getStyleClass().add("delete-button"); // Je kunt dit stylen in je CSS

        inputBalk.getChildren().addAll(txtNummer, txtNaam, txtRang, dateGeboorte, btnOpslaan, btnVerwijderen);

        // 4. De Tabel
        //setcellvalue factory en propertyvaluefactory zijn dingen die specifiek worden gebruikt bij een table in java

        TableColumn<Soldaat, String> colNummer = new TableColumn<>("soldaat nummer");
        colNummer.setCellValueFactory(new PropertyValueFactory<>("nummer"));

        TableColumn<Soldaat, String> colNaam = new TableColumn<>("soldaat naam");
        colNaam.setCellValueFactory(new PropertyValueFactory<>("naam"));

        TableColumn<Soldaat, String> colRang = new TableColumn<>("soldaat rang");
        colRang.setCellValueFactory(new PropertyValueFactory<>("rang"));

        TableColumn<Soldaat, String> colGeboorte = new TableColumn<>("geboorte datum");
        colGeboorte.setCellValueFactory(new PropertyValueFactory<>("geboorteDatum"));

        tableView.getColumns().addAll(colNummer, colNaam, colRang, colGeboorte);

        //gewenste hoogte instellen van de tabel (kan nog aanpassen moet wel dan bij elke page het zelfde zijn
        tableView.setPrefHeight(400);

        refreshTable();

        // de heerlijke functie om met een simpele click data te kunnen bewerken zoals in professionele programma's word gedaan
        tableView.setOnMouseClicked(e -> {
            Soldaat geselecteerd = tableView.getSelectionModel().getSelectedItem();
            if (geselecteerd != null) {
                txtNummer.setText(geselecteerd.getNummer());
                txtNaam.setText(geselecteerd.getNaam());
                txtRang.setText(geselecteerd.getRang());
                dateGeboorte.setValue(geselecteerd.getGeboorteDatum());
            }
        });

        btnOpslaan.setOnAction(e -> {
            if (!txtNummer.getText().isEmpty() && dateGeboorte.getValue() != null) {
                Soldaat s = new Soldaat(
                        txtNummer.getText(),
                        txtNaam.getText(),
                        txtRang.getText(),
                        dateGeboorte.getValue()
                );
                controller.opslaan(s);
                refreshTable();
                txtNummer.clear();
                txtNaam.clear();
                txtRang.clear();
                dateGeboorte.setValue(null);
            }
        });

        btnVerwijderen.setOnAction(e -> {
            Soldaat geselecteerd = tableView.getSelectionModel().getSelectedItem();
            if (geselecteerd != null) {
                controller.verwijderen(geselecteerd.getNummer());
                refreshTable();
                txtNummer.clear();
                txtNaam.clear();
                txtRang.clear();
                dateGeboorte.setValue(null);
            }
        });

        // We voegen de zoekBalk toe aan de layout
        this.getChildren().addAll(titel, zoekBalk, inputBalk, tableView);
    }

    private void refreshTable() {
        // We halen eerst alle soldaten op in een FilteredList
        FilteredList<Soldaat> filteredData = new FilteredList<>(controller.getAllSoldaten(), p -> true);

        // De listener die kijkt of er tekst in het zoekveld verandert
        txtZoek.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(soldaat -> {
                // Als het zoekveld leeg is laten we alles zien
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                // We maken van de geboortedatum een String zodat we die ook kunnen doorzoeken
                String geboorteString = soldaat.getGeboorteDatum() != null ? soldaat.getGeboorteDatum().toString() : "";

                // Checken of de tekst voorkomt in ALLE velden
                if (soldaat.getNummer().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (soldaat.getNaam().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (soldaat.getRang().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (geboorteString.contains(lowerCaseFilter)) {
                    // Hiermee zoek je ook op de datum (bijv. "1995" of "05-12")
                    return true;
                }
                return false; // Geen match gevonden
            });
        });

        // De tabel vullen met de gefilterde lijst
        tableView.setItems(filteredData);
    }
}