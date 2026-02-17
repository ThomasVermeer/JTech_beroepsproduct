package com.example.jtech_beroepsproduct.controller;

import com.example.jtech_beroepsproduct.database.DatabaseConnection;
import com.example.jtech_beroepsproduct.model.Materiaal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class MateriaalController {

    // haal alle materialen op uit de database
    public ObservableList<Materiaal> getAllMateriaal() {
        ObservableList<Materiaal> lijst = FXCollections.observableArrayList();

        // 5 clauses voor de eisen van de opdracht: SELECT, FROM, WHERE, ORDER BY, LIMIT
        String sql = "SELECT * FROM Materiaal WHERE type LIKE '%' ORDER BY type ASC LIMIT 500";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lijst.add(new Materiaal(
                        rs.getString("materiaalnummer"),
                        rs.getString("type"),
                        rs.getString("doel")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lijst;
    }

    // materiaal opslaan of bewerken
    public void opslaan(Materiaal m) {
        String sql = "INSERT INTO Materiaal (materiaalnummer, type, doel) " +
                "VALUES (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE type=?, doel=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, m.getMateriaalnummer());
            pstmt.setString(2, m.getType());
            pstmt.setString(3, m.getDoel());

            // Voor de UPDATE kant:
            pstmt.setString(4, m.getType());
            pstmt.setString(5, m.getDoel());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // materiaal verwijderen
    public void verwijderen(String nummer) {
        String sql = "DELETE FROM Materiaal WHERE materiaalnummer = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nummer);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}