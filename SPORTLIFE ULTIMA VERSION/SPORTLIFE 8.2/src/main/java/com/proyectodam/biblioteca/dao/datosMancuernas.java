package com.proyectodam.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class datosMancuernas {
    private static final String INSERT_EJERCICIO_QUERY = "INSERT INTO datosMancuernas (usuario_id, ejercicio, repeticiones, peso, duracion) VALUES (?, ?, ?, ?, ?)";

    private datosMancuernas() {
    }

    public static datosMancuernas getInstance() {
        return new datosMancuernas();
    }

    public void guardarEjercicio(int usuarioId, String ejercicio, int repeticiones, int peso, int duracion) {
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_EJERCICIO_QUERY)) {
            statement.setInt(1, usuarioId);
            statement.setString(2, ejercicio);
            statement.setInt(3, repeticiones);
            statement.setInt(4, peso);
            statement.setInt(5, duracion);
            
            // Mensaje de registro para imprimir los valores que se están insertando
            System.out.println("Insertando ejercicio de Mancuernas en la base de datos:");
            System.out.println("Usuario ID: " + usuarioId);
            System.out.println("Ejercicio: " + ejercicio);
            System.out.println("Repeticiones: " + repeticiones);
            System.out.println("Peso: " + peso);
            System.out.println("Duración: " + duracion);

            statement.executeUpdate();
            System.out.println("Ejercicio de Mancuernas guardado en la base de datos.");
        } catch (SQLException e) {
            System.err.println("Error al guardar el ejercicio de Mancuernas en la base de datos: " + e.getMessage());
        }
    }
}