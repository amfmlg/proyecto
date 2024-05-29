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

    public void guardarEjercicio(int usuarioId, String nombreEjercicio, int peso, int repeticiones, int tiempoTotal) {
        Connection connection = null;
        try {
            connection = Conexion.getConnection(); // Obtener conexión del Singleton
            System.out.println("Conexión establecida: " + (connection != null));
            
            String query = "INSERT INTO EjercicioMancuernas (usuarioId, nombreEjercicio, peso, repeticiones, tiempoTotal) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, usuarioId);
                statement.setString(2, nombreEjercicio);
                statement.setInt(3, peso);
                statement.setInt(4, repeticiones);
                statement.setInt(5, tiempoTotal);
                
                System.out.println("Ejecutando consulta: " + statement);
                
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Inserción exitosa: " + rowsAffected + " fila(s) afectada(s).");
                } else {
                    System.out.println("No se insertó ninguna fila.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al guardar el ejercicio: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Conexión cerrada.");
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexión: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }
}
