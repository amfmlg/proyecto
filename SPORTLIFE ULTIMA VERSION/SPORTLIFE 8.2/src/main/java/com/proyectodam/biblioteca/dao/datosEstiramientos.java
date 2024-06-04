package com.proyectodam.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class datosEstiramientos {
    private static final String INSERT_EJERCICIO_QUERY = "INSERT INTO datosEstiramientos (usuario_id, ejercicio, duracion) VALUES (?, ?, ?)";

    private datosEstiramientos() {
    }

    public static datosEstiramientos getInstance() {
        return new datosEstiramientos();
    }

    public void guardarEjercicio(int usuarioId, String ejercicio, int duracion) {
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_EJERCICIO_QUERY)) {
            statement.setInt(1, usuarioId);
            statement.setString(2, ejercicio);
            statement.setInt(3, duracion);
            statement.executeUpdate();
            System.out.println("Ejercicio de estiramientos guardado en la base de datos.");
        } catch (SQLException e) {
            System.err.println("Error al guardar el ejercicio de estiramientos en la base de datos: " + e.getMessage());
        }
    }
}