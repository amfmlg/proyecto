package com.proyectodam.biblioteca.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static Connection connection = null;

    private static final String URL = "jdbc:mysql://localhost:3308/sportlife";
    private static final String USER = "pepe";
    private static final String PASSWORD = "1234";

    private Conexion() {
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            reconnect();
        }
        return connection;
    }

    private static void reconnect() throws SQLException {
        closeConnection();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver not found", e);
        }
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing the connection: " + e.getMessage());
            }
            connection = null;
        }
    }
}
