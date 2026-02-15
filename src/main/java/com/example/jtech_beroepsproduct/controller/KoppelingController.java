package com.example.jtech_beroepsproduct.controller;

import com.example.jtech_beroepsproduct.database.DatabaseConnection;
import com.example.jtech_beroepsproduct.model.Koppeling;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class KoppelingController {

    public ObservableList<Koppeling> getAllKoppelingen() {
        ObservableList<Koppeling> lijst = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Koppeling";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lijst.add(new Koppeling(
                        rs.getInt("id"),
                        rs.getString("soldaatnummer"),
                        rs.getString("materiaalnummer"),
                        rs.getString("kenteken"),
                        rs.getTimestamp("aanmaakdatum") != null ? rs.getTimestamp("aanmaakdatum").toLocalDateTime() : null
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lijst;
    }

    // Nieuwe methode: Haal alleen materialen op die nog NIET in de Koppeling tabel staan
    public ObservableList<String> getBeschikbareMaterialen() {
        ObservableList<String> lijst = FXCollections.observableArrayList();
        String sql = "SELECT materiaalnummer FROM Materiaal WHERE materiaalnummer NOT IN " +
                "(SELECT materiaalnummer FROM Koppeling WHERE materiaalnummer IS NOT NULL)";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lijst.add(rs.getString("materiaalnummer"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lijst;
    }

    // Nieuwe methode: Haal alleen voertuigen op die nog NIET in de Koppeling tabel staan
    public ObservableList<String> getBeschikbareVoertuigen() {
        ObservableList<String> lijst = FXCollections.observableArrayList();
        String sql = "SELECT kenteken FROM Voertuigen WHERE kenteken NOT IN " +
                "(SELECT kenteken FROM Koppeling WHERE kenteken IS NOT NULL)";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lijst.add(rs.getString("kenteken"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lijst;
    }

    public void koppel(String sNummer, String mNummer, String kenteken) {
        String sql = "INSERT INTO Koppeling (soldaatnummer, materiaalnummer, kenteken) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, sNummer);

            if (mNummer == null || mNummer.isEmpty()) {
                pstmt.setNull(2, Types.VARCHAR);
            } else {
                pstmt.setString(2, mNummer);
            }

            if (kenteken == null || kenteken.isEmpty()) {
                pstmt.setNull(3, Types.VARCHAR);
            } else {
                pstmt.setString(3, kenteken);
            }

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void verwijderen(int id) {
        String sql = "DELETE FROM Koppeling WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}