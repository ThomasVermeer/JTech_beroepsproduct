package com.example.jtech_beroepsproduct.model;

import java.time.LocalDate;

public class Soldaat {
    // de variabelen aangeven (private voor veiligheid)
    private String nummer;
    private String naam;
    private String rang;
    private LocalDate geboorteDatum;

    // constructor om een soldaat aan te kunnen maken
    public Soldaat(String nummer, String naam, String rang, LocalDate geboorteDatum) {
        this.nummer = nummer;
        this.naam = naam;
        this.rang = rang;
        this.geboorteDatum = geboorteDatum;
    }

    // getters die de tableview nodig heeft om de info uit te halen
    public String getNummer() { return nummer; }
    public String getNaam() { return naam; }
    public String getRang() { return rang; }
    public LocalDate getGeboorteDatum() { return geboorteDatum; }
}