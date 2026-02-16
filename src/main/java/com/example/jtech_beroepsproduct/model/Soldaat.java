package com.example.jtech_beroepsproduct.model;

import java.time.LocalDate;

public class Soldaat extends BasisModel {
    // variabelen aangeven
    private String naam;
    private String rang;
    private LocalDate geboorteDatum;

    // constructor van soldaten
    public Soldaat(String nummer, String naam, String rang, LocalDate geboorteDatum) {
        super(nummer); // "Hey vader-model, hier is het ID dat jij voor mij mag onthouden"
        this.naam = naam;
        this.rang = rang;
        this.geboorteDatum = geboorteDatum;
    }

    // getters die de tableView nodig heeft om de kolommen te vullen
    public String getNummer() { return getId(); }
    public String getNaam() { return naam; }
    public String getRang() { return rang; }
    public LocalDate getGeboorteDatum() { return geboorteDatum; }
}