package com.proyectodam.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class datosMancuernas {

    private static datosMancuernas instancia = null;
    private datosMancuernas() {
        // Constructor privado para evitar instanciación externa
    }

    public static datosMancuernas getInstance() {
        if (instancia == null) {
            instancia = new datosMancuernas();
        }
        return instancia;
    }

    public void guardarEjercicio(String nombreEjercicio, int peso, int repeticiones, int tiempoTotal) {
        Connection connection = null;
        try {
            connection = Conexion.getConnection(); // Obtener conexión del Singleton
            String query = "INSERT INTO ejerciciosMancuernas (nombre, peso, repeticiones, tiempoTotal) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, nombreEjercicio);
                statement.setInt(2, peso);
                statement.setInt(3, repeticiones);
                statement.setInt(4, tiempoTotal);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
