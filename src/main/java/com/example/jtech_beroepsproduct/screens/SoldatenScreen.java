package com.example.jtech_beroepsproduct.screens;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

// Door 'extends VBox' te gebruiken, ziet Java dit als een Parent/Node
public class SoldatenScreen extends VBox {

    public SoldatenScreen() {
        // Maak een simpel tekstje aan
        Label tekst = new Label("soldatenscreen");

        // Voeg de tekst toe aan dit scherm
        this.getChildren().add(tekst);

        // Gebruik de headline stijl uit je styleguide
        tekst.getStyleClass().add("headline-1");
    }
}