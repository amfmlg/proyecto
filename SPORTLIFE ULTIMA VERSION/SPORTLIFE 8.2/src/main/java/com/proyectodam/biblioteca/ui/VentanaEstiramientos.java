package com.proyectodam.biblioteca.ui;

import javax.swing.*;
import java.awt.*;
import com.proyectodam.biblioteca.dao.datosEstiramientos;

public class VentanaEstiramientos extends JFrame {

    private final String[] ejercicios = {
            "Estiramientos de Brazos",
            "Estiramientos de Piernas",
            "Estiramientos de Cuello",
            "Estiramientos de Torso",
            "Estiramientos de Espalda"
    };

    private final String[] imagenes = {
            "SPORTLIFE 8.2/imagenes/ESTIRAMIENTOS/hombros.gif",
            "SPORTLIFE 8.2/imagenes/ESTIRAMIENTOS/gemelos.gif",
            "SPORTLIFE 8.2/imagenes/ESTIRAMIENTOS/cuello.gif",
            "SPORTLIFE 8.2/imagenes/ESTIRAMIENTOS/torso",
            "SPORTLIFE 8.2/imagenes/ESTIRAMIENTOS/espalda.gif"
    };

    private JLabel imagenEjercicio;
    private JSpinner spinnerSegundos;
    private JSpinner spinnerDescanso;
    private JLabel lblSegundosSeleccionados;
    private JLabel lblDescansoSeleccionado;
    private JLabel lblTiempo;
    private Timer timer;
    private int tiempoRestante;
    private boolean enDescanso;
    private int tiempoDescanso;
    private boolean timerRunning;

    public VentanaEstiramientos() {
        setTitle("Ventana de Estiramientos");
        setSize(1800, 1200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(255, 255, 255));
        setLayout(new BorderLayout());

        // Lista con los ejercicios de estiramiento
        JList<String> listEjercicios = new JList<>(ejercicios);
        listEjercicios.setBackground(new Color(255, 255, 255));
        listEjercicios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listEjercicios.addListSelectionListener(e -> {
            if (!timerRunning) {
                mostrarImagen(listEjercicios.getSelectedIndex());
            }
        });
        JScrollPane scrollPane = new JScrollPane(listEjercicios);

        imagenEjercicio = new JLabel();
        imagenEjercicio.setHorizontalAlignment(JLabel.CENTER);
        imagenEjercicio.setVerticalAlignment(JLabel.CENTER);

        JPanel panelConfiguracion = new JPanel();
        panelConfiguracion.setLayout(new GridLayout(2, 2, 10, 10));

        JLabel lblSegundos = new JLabel("Segundos de ejercicio: ");
        spinnerSegundos = new JSpinner(new SpinnerNumberModel(0, 0, 7200, 1));
        lblSegundosSeleccionados = new JLabel("0 segundos seleccionados");
        spinnerSegundos.addChangeListener(e -> {
            int segundos = (Integer) spinnerSegundos.getValue();
            lblSegundosSeleccionados.setText(segundos + " segundos seleccionados");
        });

        JLabel lblDescanso = new JLabel("Tiempo de descanso (segundos): ");
        spinnerDescanso = new JSpinner(new SpinnerNumberModel(0, 0, 600, 1));
        lblDescansoSeleccionado = new JLabel("0 segundos seleccionados");
        spinnerDescanso.addChangeListener(e -> {
            tiempoDescanso = (Integer) spinnerDescanso.getValue();
            lblDescansoSeleccionado.setText(tiempoDescanso + " segundos seleccionados");
        });

        JPanel panelControl = new JPanel();
        panelControl.setLayout(new FlowLayout());

        JButton btnStart = new JButton("Start");
        JButton btnStop = new JButton("Stop");
        lblTiempo = new JLabel("Tiempo restante: 0 segundos");

        btnStart.addActionListener(e -> {
            if (verificarCamposObligatorios()) {
                iniciarTemporizador();
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos antes de iniciar.",
                        "Campos incompletos", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnStop.addActionListener(e -> detenerTemporizador());

        panelControl.add(btnStart);
        panelControl.add(btnStop);
        panelControl.add(lblTiempo);

        panelConfiguracion.add(lblSegundos);
        panelConfiguracion.add(spinnerSegundos);
        panelConfiguracion.add(lblDescanso);
        panelConfiguracion.add(spinnerDescanso);

        add(scrollPane, BorderLayout.WEST);
        add(imagenEjercicio, BorderLayout.CENTER);
        add(panelConfiguracion, BorderLayout.NORTH);
        add(panelControl, BorderLayout.SOUTH);
    }

    private void mostrarImagen(int index) {
        String imagePath = imagenes[index];
        System.out.println("Intentando cargar la imagen desde: " + imagePath);
        ImageIcon icon = new ImageIcon(
                new ImageIcon(imagePath).getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));
        if (icon.getIconWidth() == -1) {
            JOptionPane.showMessageDialog(this, "No se pudo cargar la imagen: " + imagePath, "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            imagenEjercicio.setIcon(icon);
        }
    }

    private boolean verificarCamposObligatorios() {
        int segundos = (Integer) spinnerSegundos.getValue();
        tiempoDescanso = (Integer) spinnerDescanso.getValue();
        return segundos > 0 && tiempoDescanso > 0;
    }

    private void iniciarTemporizador() {
        tiempoRestante = (Integer) spinnerSegundos.getValue();
        actualizarEtiquetaTiempo();
        enDescanso = false;

        timer = new Timer(1000, e -> {
            tiempoRestante--;
            if (tiempoRestante >= 0) {
                actualizarEtiquetaTiempo();
            } else {
                ((Timer) e.getSource()).stop();
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(VentanaEstiramientos.this,
                        "¡Tiempo de ejercicio terminado! Ahora empieza el descanso.", "Fin del ejercicio",
                        JOptionPane.INFORMATION_MESSAGE);
                datosEstiramientos.getInstance().guardarEjercicio(1, "Ejercicio de estiramiento",
                        (Integer) spinnerSegundos.getValue());
                iniciarDescanso();
            }
        });
        timer.start();
    }

    private void iniciarDescanso() {
        enDescanso = true;
        tiempoRestante = tiempoDescanso;
        actualizarEtiquetaTiempo();
        timer = new Timer(1000, e -> {
            tiempoRestante--;
            if (tiempoRestante >= 0) {
                lblTiempo.setText("Descanso: " + tiempoRestante + " segundos");
            } else {
                ((Timer) e.getSource()).stop();
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(VentanaEstiramientos.this, "¡Tiempo de descanso terminado!",
                        "Fin del descanso", JOptionPane.INFORMATION_MESSAGE);
                datosEstiramientos.getInstance().guardarEjercicio(1, "Descanso", (Integer) spinnerDescanso.getValue());
            }
        });
        timer.start();
    }

    private void detenerTemporizador() {
        if (timer != null) {
            timer.stop();
            lblTiempo.setText("Temporizador detenido. Tiempo restante: " + tiempoRestante + " segundos");
        }
    }

    private void actualizarEtiquetaTiempo() {
        if (!enDescanso) {
            lblTiempo.setText("Tiempo restante: " + tiempoRestante + " segundos");
        } else {
            lblTiempo.setText("Descanso: " + tiempoRestante + " segundos");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaEstiramientos frame = new VentanaEstiramientos();
            frame.setVisible(true);
        });
    }
}
