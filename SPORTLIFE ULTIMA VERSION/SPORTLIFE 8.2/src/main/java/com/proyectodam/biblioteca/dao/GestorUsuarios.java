package com.proyectodam.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.proyectodam.biblioteca.dto.RegistroUsuario;

public class GestorUsuarios {

    private RegistroUsuario usuarioActual;

    // Método para autenticar un usuario
    public boolean autenticarUsuario(String nombre, String contraseña) {
        try (Connection connection = Conexion.getConnection()) {
            String query = "SELECT * FROM Usuario WHERE nombre = ? AND contraseña = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nombre);
            statement.setString(2, contraseña);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String correoElectronico = resultSet.getString("correoElectronico");
                String fechaNacimiento = resultSet.getString("fechaDeNacimiento");
                float peso = resultSet.getFloat("peso");
                float altura = resultSet.getFloat("altura");
                String frecuenciaEjercicio = resultSet.getString("frecuenciaDeEjercicio");
                usuarioActual = new RegistroUsuario(nombre, contraseña, correoElectronico, fechaNacimiento, String.valueOf(peso), String.valueOf(altura), frecuenciaEjercicio);
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener el usuario actual
    public RegistroUsuario obtenerUsuarioActual() {
        return usuarioActual;
    }

}