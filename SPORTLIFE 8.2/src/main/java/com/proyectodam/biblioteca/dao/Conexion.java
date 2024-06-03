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
            System.out.println("Conexión establecida a la base de datos.");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver not found", e);
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            throw e;
        }
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.out.println("Error closing the connection: " + e.getMessage());
            }
            connection = null;
        }
    }

    public static void main(String[] args) {
        try (Connection connection = Conexion.getConnection()) {
            if (connection != null) {
                System.out.println("Conexión establecida correctamente.");
            } else {
                System.out.println("Fallo en la conexión.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

