package com.example.jtech_beroepsproduct.controller;

import com.example.jtech_beroepsproduct.database.DatabaseConnection;
import com.example.jtech_beroepsproduct.model.Soldaat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;

public class SoldaatController {

    // haal alle soldaten op uit de database
    public ObservableList<Soldaat> getAllSoldaten() {
        ObservableList<Soldaat> lijst = FXCollections.observableArrayList();

        // 5 clauses voor de VERPLICHTE EISEN: SELECT, FROM, WHERE, ORDER BY, LIMIT
        String sql = "SELECT * FROM Soldaat WHERE soldaatnaam LIKE '%' ORDER BY soldaatnaam ASC LIMIT 1000";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lijst.add(new Soldaat(
                        rs.getString("soldaatnummer"),
                        rs.getString("soldaatnaam"),
                        rs.getString("rang"),
                        rs.getDate("geboortedatum").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lijst;
    }

    // opslaan of bewerken
    public void opslaan(Soldaat s) {
        String sql = "INSERT INTO Soldaat (soldaatnummer, soldaatnaam, rang, geboortedatum) " +
                "VALUES (?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE soldaatnaam=?, rang=?, geboortedatum=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, s.getNummer());
            pstmt.setString(2, s.getNaam());
            pstmt.setString(3, s.getRang());
            pstmt.setDate(4, Date.valueOf(s.getGeboorteDatum()));

            // Voor de UPDATE kant:
            pstmt.setString(5, s.getNaam());
            pstmt.setString(6, s.getRang());
            pstmt.setDate(7, Date.valueOf(s.getGeboorteDatum()));

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // soldaat verwijderen
    public void verwijderen(String nummer) {
        String sql = "DELETE FROM Soldaat WHERE soldaatnummer = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nummer);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}