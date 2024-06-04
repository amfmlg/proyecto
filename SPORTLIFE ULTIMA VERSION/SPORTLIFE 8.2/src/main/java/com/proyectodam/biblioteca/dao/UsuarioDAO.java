package com.proyectodam.biblioteca.dao;

import com.proyectodam.biblioteca.dto.RegistroUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class UsuarioDAO {

    public boolean guardarUsuario(RegistroUsuario usuario) {
        String sql = "INSERT INTO Usuario (nombre, contraseña, correoElectronico, fechaDeNacimiento, peso, altura, frecuenciaDeEjercicio) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
             
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getContraseña());
            statement.setString(3, usuario.getCorreoElectronico());
            statement.setString(4, usuario.getFechaDeNacimiento());
            statement.setString(5, String.valueOf(usuario.getPeso())); // Convertir float a String
            statement.setString(6, String.valueOf(usuario.getAltura())); // Convertir float a String
            statement.setString(7, usuario.getFrecuenciaDeEjercicio());

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}