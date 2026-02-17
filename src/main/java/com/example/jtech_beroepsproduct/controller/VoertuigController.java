package com.example.jtech_beroepsproduct.controller;

import com.example.jtech_beroepsproduct.database.DatabaseConnection;
import com.example.jtech_beroepsproduct.model.Voertuig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class VoertuigController {

    // haal alle voertuigen op uit de database
    public ObservableList<Voertuig> getAllVoertuigen() {
        ObservableList<Voertuig> lijst = FXCollections.observableArrayList();

        // hier voegen we de 5 clauses toe: SELECT, FROM, WHERE, ORDER BY, LIMIT
        String sql = "SELECT * FROM Voertuigen WHERE kenteken LIKE '%' ORDER BY voertuignaam ASC LIMIT 500";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lijst.add(new Voertuig(
                        rs.getString("kenteken"),
                        rs.getString("voertuignaam"),
                        rs.getString("soort")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lijst;
    }

    // voertuig opslaan of bewerken (UPSERT) (pas aan als info het zelfde is daardoor update mogelijk in een create
    public void opslaan(Voertuig v) {
        String sql = "INSERT INTO Voertuigen (kenteken, voertuignaam, soort) " +
                "VALUES (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE voertuignaam=?, soort=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, v.getKenteken());
            pstmt.setString(2, v.getVoertuignaam());
            pstmt.setString(3, v.getSoort());

            // Voor de UPDATE kant:
            pstmt.setString(4, v.getVoertuignaam());
            pstmt.setString(5, v.getSoort());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // voertuig verwijderen
    public void verwijderen(String kenteken) {
        String sql = "DELETE FROM Voertuigen WHERE kenteken = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, kenteken);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}