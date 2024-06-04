package com.proyectodam.biblioteca.ui;

import com.proyectodam.biblioteca.dao.Conexion;
import com.proyectodam.biblioteca.dto.RegistroUsuario; // Importa la clase RegistroUsuario del paquete dto
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDAO {

    // Método para guardar un nuevo usuario en la base de datos
    public boolean guardarUsuario(RegistroUsuario usuario) {
        // Consulta SQL para insertar un nuevo usuario en la tabla Usuario
        String sql = "INSERT INTO Usuario (nombre, contraseña, correoElectronico, fechaDeNacimiento, peso, altura, frecuenciaDeEjercicio) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = Conexion.getConnection(); // Obtiene una conexión a la base de datos
             PreparedStatement statement = connection.prepareStatement(sql)) { // Prepara la consulta SQL
            // Establece los valores del nuevo usuario como parámetros en la consulta
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getContraseña());
            statement.setString(3, usuario.getCorreoElectronico());
            statement.setString(4, usuario.getFechaDeNacimiento());
            statement.setString(5, String.valueOf(usuario.getPeso())); // Convierte el peso de float a String
            statement.setString(6, String.valueOf(usuario.getAltura())); // Convierte la altura de float a String
            statement.setString(7, usuario.getFrecuenciaDeEjercicio());

            statement.executeUpdate(); // Ejecuta la consulta para insertar el nuevo usuario en la base de datos
            return true; // El usuario se guardó correctamente
        } catch (SQLException e) { // Maneja los errores de SQL, si ocurren
            e.printStackTrace(); // Imprime el error
            return false; // El usuario no se pudo guardar
        }
    }
}
