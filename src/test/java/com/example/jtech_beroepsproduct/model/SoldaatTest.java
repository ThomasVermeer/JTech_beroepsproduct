package com.example.jtech_beroepsproduct.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class SoldaatTest {

    @Test
    void testSoldaatOvererving() {
        // We maken een test-soldaat aan
        Soldaat s = new Soldaat("S123", "Thomas Vermeer", "Korporaal", LocalDate.now());

        // TEST 1: Check of het nummer via super() in BasisModel terechtkomt (Overerving)
        assertEquals("S123", s.getId(), "Het ID moet in de superklasse BasisModel worden opgeslagen.");
    }

    @Test
    void testSoldaatNaam() {
        Soldaat s = new Soldaat("S123", "Thomas Vermeer", "Korporaal", LocalDate.now());

        // TEST 2: Check of de naam goed wordt opgeslagen
        assertEquals("Thomas Vermeer", s.getNaam());
    }

    @Test
    void testSoldaatRang() {
        Soldaat s = new Soldaat("S123", "Thomas Vermeer", "Majoor", LocalDate.now());

        // TEST 3: Check of de rang goed wordt opgeslagen
        assertEquals("Majoor", s.getRang());
    }

    @Test
    void testGeboorteDatum() {
        LocalDate datum = LocalDate.of(2000, 1, 1);
        Soldaat s = new Soldaat("S123", "Thomas Vermeer", "Korporaal", datum);

        // TEST 4: Check of de datum goed wordt opgeslagen
        assertEquals(datum, s.getGeboorteDatum());
    }

    @Test
    void testNummerGetter() {
        Soldaat s = new Soldaat("S999", "Thomas Vermeer", "Korporaal", LocalDate.now());

        // TEST 5: Check of getNummer() inderdaad hetzelfde geeft als getId()
        assertEquals("S999", s.getNummer());
    }
}