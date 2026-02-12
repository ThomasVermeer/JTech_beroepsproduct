package com.example.jtech_beroepsproduct.model;

import java.time.LocalDateTime;

public class Koppeling {
    private int id;
    private String soldaatnummer;
    private String materiaalnummer;
    private String kenteken;
    private LocalDateTime aanmaakdatum;

    public Koppeling(int id, String soldaatnummer, String materiaalnummer, String kenteken, LocalDateTime aanmaakdatum) {
        this.id = id;
        this.soldaatnummer = soldaatnummer;
        this.materiaalnummer = materiaalnummer;
        this.kenteken = kenteken;
        this.aanmaakdatum = aanmaakdatum;
    }

    // getters voor de TableView
    public int getId() { return id; }
    public String getSoldaatnummer() { return soldaatnummer; }
    public String getMateriaalnummer() { return materiaalnummer; }
    public String getKenteken() { return kenteken; }
    public LocalDateTime getAanmaakdatum() { return aanmaakdatum; }
}