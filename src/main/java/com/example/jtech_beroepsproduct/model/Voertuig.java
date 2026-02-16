package com.example.jtech_beroepsproduct.model;

public class Voertuig extends BasisModel {
    // variabelen aangeven
    private String voertuignaam;
    private String soort;

    // constructor van voertuigen
    public Voertuig(String kenteken, String voertuignaam, String soort) {
        super(kenteken); // Kenteken is de identificatie in BasisModel
        this.voertuignaam = voertuignaam;
        this.soort = soort;
    }

    // getters die de tableView nodig heeft om de kolommen te vullen
    public String getKenteken() { return getId(); }
    public String getVoertuignaam() { return voertuignaam; }
    public String getSoort() { return soort; }
}