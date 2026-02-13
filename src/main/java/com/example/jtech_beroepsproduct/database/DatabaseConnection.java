package com.example.jtech_beroepsproduct.database; // Check of je package-naam precies zo heet

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
  // connectie met de database met mijn gegevens om te koppelen aan de jtech database
    private static final String URL = "jdbc:mysql://localhost:3306/JTech";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}