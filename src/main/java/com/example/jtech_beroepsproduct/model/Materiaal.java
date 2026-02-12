package com.example.jtech_beroepsproduct.model;

public class Materiaal {
    // variabalen aangeven
    private String materiaalnummer;
    private String type;
    private String doel;

    // constructor van materialen
    public Materiaal(String materiaalnummer, String type, String doel) {
        this.materiaalnummer = materiaalnummer;
        this.type = type;
        this.doel = doel;
    }

    // getters die de tableView nodig heeft om de kolommen te vullen
    public String getMateriaalnummer() { return materiaalnummer; }
    public String getType() { return type; }
    public String getDoel() { return doel; }
}