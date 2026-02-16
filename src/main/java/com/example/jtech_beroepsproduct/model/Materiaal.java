package com.example.jtech_beroepsproduct.model;

public class Materiaal extends BasisModel {
    // variabalen aangeven
    private String type;
    private String doel;

    // constructor van materialen
    public Materiaal(String materiaalnummer, String type, String doel) {
        super(materiaalnummer); // Stuur het nummer naar de moederklasse BasisModel
        this.type = type;
        this.doel = doel;
    }

    // getters die de tableView nodig heeft om de kolommen te vullen
    public String getMateriaalnummer() { return getId(); }
    public String getType() { return type; }
    public String getDoel() { return doel; }
}