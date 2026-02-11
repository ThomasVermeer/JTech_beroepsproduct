package com.example.jtech_beroepsproduct.screens;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import com.example.jtech_beroepsproduct.model.Soldaat;

public class SoldatenScreen extends VBox {

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

        TableView<Soldaat> tableView = new TableView<>();

        TableColumn<Soldaat, String> colNummer = new TableColumn<>("soldaat nummer");
        TableColumn<Soldaat, String> colNaam = new TableColumn<>("soldaat naam");
        TableColumn<Soldaat, String> colRang = new TableColumn<>("soldaat rang");
        TableColumn<Soldaat, String> colGeboorte = new TableColumn<>("geboorte datum");
        TableColumn<Soldaat, String> colActies = new TableColumn<>("acties");

        tableView.getColumns().addAll(colNummer, colNaam, colRang, colGeboorte, colActies);

        //gewenste hoogte instellen van de tabel (kan nog aanpassen moet wel dan bij elke page het zelfde zijn
        tableView.setPrefHeight(400);


        this.getChildren().addAll(titel, inputBalk, tableView);
    }
}