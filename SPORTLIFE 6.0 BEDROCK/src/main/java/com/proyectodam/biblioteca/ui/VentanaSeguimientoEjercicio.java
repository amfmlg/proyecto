package com.proyectodam.biblioteca.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaSeguimientoEjercicio extends JFrame {

    private JTextField txtCarga;
    private JTextField txtRepeticiones;
    private JLabel lblContador;
    private Timer timer;
    private int segundos;

    public VentanaSeguimientoEjercicio(String ejercicio) {
        setTitle("Seguimiento de " + ejercicio);
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(255, 255, 255));
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Ejercicio:"));
        add(new JLabel(ejercicio));

        add(new JLabel("Carga (kg):"));
        txtCarga = new JTextField();
        add(txtCarga);

        add(new JLabel("Repeticiones:"));
        txtRepeticiones = new JTextField();
        add(txtRepeticiones);

        lblContador = new JLabel("0:00", SwingConstants.CENTER);
        add(new JLabel("Tiempo (minutos):"));
        add(lblContador);

        JButton btnIniciar = new JButton("Iniciar");
        btnIniciar.setBackground(new Color(50, 150, 250));
        btnIniciar.setForeground(Color.WHITE);
        btnIniciar.addActionListener(e -> iniciarContador());
        add(btnIniciar);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(50, 150, 250));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.addActionListener(e -> guardarDatos(ejercicio));
        add(btnGuardar);
    }

    private void iniciarContador() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
        segundos = 0;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                segundos++;
                int minutos = segundos / 60;
                int segundosRestantes = segundos % 60;
                lblContador.setText(String.format("%d:%02d", minutos, segundosRestantes));
            }
        });
        timer.start();
    }

    private void guardarDatos(String ejercicio) {
        String carga = txtCarga.getText();
        String repeticiones = txtRepeticiones.getText();
        int minutos = segundos / 60;
        int segundosRestantes = segundos % 60;
        String tiempo = String.format("%d:%02d", minutos, segundosRestantes);

        // Aquí puedes guardar los datos a una base de datos, archivo, etc.
        JOptionPane.showMessageDialog(this, "Datos guardados:\n" +
                "Ejercicio: " + ejercicio + "\n" +
                "Carga: " + carga + " kg\n" +
                "Repeticiones: " + repeticiones + "\n" +
                "Tiempo: " + tiempo);
    }
}
