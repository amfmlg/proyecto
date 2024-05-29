package com.proyectodam.biblioteca.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import com.proyectodam.biblioteca.dto.RegistroUsuario;
import com.proyectodam.biblioteca.dao.UsuarioDAO;

public class Registro extends JFrame {
    private JTextField txtUsuarioNombre;
    private JPasswordField txtUsuarioContraseña;
    private JTextField txtCorreoElectronico;
    private JTextField txtFechaDeNacimiento;
    private JTextField txtPeso;
    private JTextField txtAltura;
    private JComboBox<String> comboBoxFrecuenciaEjercicio;
    private JButton btnAgregarUsuario;

    private UsuarioDAO usuarioDAO; // Agrega una instancia de UsuarioDAO

    public Registro() {
        super("Ventana de Registro de Usuario");
        setSize(800, 600); // Corrige el tamaño
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(8, 2));

        panel.add(new JLabel("Nombre:"));
        txtUsuarioNombre = new JTextField();
        panel.add(txtUsuarioNombre);

        panel.add(new JLabel("Contraseña:"));
        txtUsuarioContraseña = new JPasswordField();
        panel.add(txtUsuarioContraseña);

        panel.add(new JLabel("Correo Electrónico:"));
        txtCorreoElectronico = new JTextField();
        panel.add(txtCorreoElectronico);

        panel.add(new JLabel("Fecha de Nacimiento:"));
        txtFechaDeNacimiento = new JTextField();
        panel.add(txtFechaDeNacimiento);

        panel.add(new JLabel("Peso:"));
        txtPeso = new JTextField();
        panel.add(txtPeso);

        panel.add(new JLabel("Altura:"));
        txtAltura = new JTextField();
        panel.add(txtAltura);

        // Agregar el JComboBox para la frecuencia de ejercicio
        panel.add(new JLabel("Frecuencia de Ejercicio:"));
        String[] opcionesFrecuencia = {"Una vez a la semana", "Dos veces a la semana", "Más de dos veces a la semana", "Diariamente"};
        comboBoxFrecuenciaEjercicio = new JComboBox<>(opcionesFrecuencia);
        panel.add(comboBoxFrecuenciaEjercicio);

        btnAgregarUsuario = new JButton("Agregar Usuario");
        btnAgregarUsuario.addActionListener(this::agregarUsuario);

        add(panel, BorderLayout.CENTER);
        add(btnAgregarUsuario, BorderLayout.SOUTH);

        usuarioDAO = new UsuarioDAO(); // Inicializa la instancia de UsuarioDAO
    }

    private void agregarUsuario(ActionEvent event) {
        String nombre = txtUsuarioNombre.getText();
        String contraseña = new String(txtUsuarioContraseña.getPassword());
        String correoElectronico = txtCorreoElectronico.getText();
        String fechaDeNacimiento = txtFechaDeNacimiento.getText();
        String peso = txtPeso.getText();
        String altura = txtAltura.getText();
        String frecuenciaDeEjercicio = (String) comboBoxFrecuenciaEjercicio.getSelectedItem();
        RegistroUsuario registroUsuario = new RegistroUsuario(0, nombre, contraseña, correoElectronico, fechaDeNacimiento, peso, altura, frecuenciaDeEjercicio);

        if (nombre.isEmpty() || contraseña.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingresa tanto el nombre como la contraseña del usuario.");
            return;
        }
    
        if (usuarioDAO.guardarUsuario(registroUsuario)) {
            JOptionPane.showMessageDialog(this, "Usuario agregado exitosamente.");
        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar usuario.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Registro registro = new Registro();
            registro.setVisible(true);
        });
    }
}
