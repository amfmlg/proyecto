package com.proyectodam.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestorUsuarios {

    public boolean autenticarUsuario(String nombre, String contraseña) {
        try (Connection connection = Conexion.getConnection()) {
            String query = "SELECT * FROM Usuario WHERE nombre = ? AND contraseña = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nombre);
            statement.setString(2, contraseña);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Devuelve true si hay al menos un resultado, lo que significa que el usuario está autenticado
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
