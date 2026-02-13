package com.example.jtech_beroepsproduct.screens;

import com.example.jtech_beroepsproduct.controller.SoldaatController;
import com.example.jtech_beroepsproduct.model.Soldaat;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SoldatenScreen extends VBox {

    private SoldaatController controller = new SoldaatController();
    private TableView<Soldaat> tableView = new TableView<>();

    public SoldatenScreen() {
        //instellingen voor de layout (padding en ruimte tussen elementen)
        this.setPadding(new Insets(20));
        this.setSpacing(20);

        // titel en headline 1 ophalen uit onze stylesheet
        Label titel = new Label("Soldaten Beheer");
        titel.getStyleClass().add("headline-1");

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

        inputBalk.getChildren().addAll(txtNummer, txtNaam, txtRang, dateGeboorte, btnOpslaan);

        // 4. De Tabel
        //TableView tableView = new TableView();

        // TableColumn colNummer = new TableColumn("soldaat nummer");
        // TableColumn colNaam = new TableColumn("soldaat naam");
        // TableColumn colRang = new TableColumn("soldaat rang");
        // TableColumn colGeboorte = new TableColumn("geboorte datum");
        // TableColumn colActies = new TableColumn("acties");

// code hier onder gebruiken wanneer soldaten model gemaakt en ingevuld is (huidige code puur voor visueel dus
// geen zorgen over de warnings)

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

        this.getChildren().addAll(titel, inputBalk, tableView);
    }

    private void refreshTable() {
        tableView.setItems(controller.getAllSoldaten());
    }
}