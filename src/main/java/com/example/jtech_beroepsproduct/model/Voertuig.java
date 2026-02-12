package com.example.jtech_beroepsproduct.model;

public class Voertuig {
    private String kenteken;
    private String voertuignaam;
    private String soort;

    public Voertuig(String kenteken, String voertuignaam, String soort) {
        this.kenteken = kenteken;
        this.voertuignaam = voertuignaam;
        this.soort = soort;
    }

    // Getters: Deze zijn essentieel voor de TableView straks!
    public String getKenteken() { return kenteken; }
    public String getVoertuignaam() { return voertuignaam; }
    public String getSoort() { return soort; }
}