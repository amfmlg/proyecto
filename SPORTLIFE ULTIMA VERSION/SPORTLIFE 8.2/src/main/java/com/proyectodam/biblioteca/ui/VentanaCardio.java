package com.proyectodam.biblioteca.ui;

import javax.swing.*;
import com.proyectodam.biblioteca.dao.datosCardio;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCardio extends JFrame {

    private final String[] ejercicios = {
        "Correr",
        "Nadar",
        "Salto a la comba",
        "Entrenamiento de alta intensidad (HIIT)",
        "Ciclismo"
    };

    private final String[] imagenes = {
        "SPORTLIFE 8.2/imagenes/cardio/correr.gif",
        "SPORTLIFE 8.2/imagenes/cardio/nadar.gif",
        "SPORTLIFE 8.2/imagenes/cardio/salto_a_la_comba.gif",
        "SPORTLIFE 8.2/imagenes/cardio/hiit.gif",
        "SPORTLIFE 8.2/imagenes/cardio/ciclismo.gif"
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
    private JList<String> listEjercicios;
    private boolean timerRunning;

    public VentanaCardio() {
        setTitle("Ejercicios de Cardio");
        setSize(1800, 1200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(255, 255, 255));
        setLayout(new BorderLayout());

        listEjercicios = new JList<>(ejercicios);
        listEjercicios.setBackground(new Color(255, 255, 255));
        listEjercicios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listEjercicios.setSelectedIndex(0);


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
                int usuarioId = obtenerIdUsuario();
                datosCardio.getInstance().guardarEjercicio(usuarioId, listEjercicios.getSelectedValue(), 0, 0, (Integer) spinnerSegundos.getValue());
                iniciarTemporizador();
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos antes de iniciar.", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
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
        try {
            ImageIcon icon = new ImageIcon(imagePath);
            Image image = icon.getImage();
            Image scaledImage = image.getScaledInstance(500, 500, Image.SCALE_DEFAULT);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            imagenEjercicio.setIcon(scaledIcon);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar la imagen: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        timerRunning = true;

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tiempoRestante--;
                if (tiempoRestante >= 0) {
                    actualizarEtiquetaTiempo();
                } else {
                    ((Timer) e.getSource()).stop();
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(VentanaCardio.this, "¡Ejercicio terminado! Ahora empieza el descanso.", "Fin del ejercicio", JOptionPane.INFORMATION_MESSAGE);
                    iniciarDescanso();
                }
            }
        });
        timer.start();
        for (Component comp : listEjercicios.getComponents()) {
            comp.setEnabled(false);
        }
    }

    private void iniciarDescanso() {
        enDescanso = true;
        tiempoRestante = tiempoDescanso;
        actualizarEtiquetaTiempo();
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tiempoRestante--;
                if (tiempoRestante >= 0) {
                    lblTiempo.setText("Descanso: " + tiempoRestante + " segundos");
                } else {
                    ((Timer) e.getSource()).stop();
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(VentanaCardio.this, "¡Tiempo de descanso terminado!", "Fin del descanso", JOptionPane.INFORMATION_MESSAGE);
                    timerRunning = false;
                    for (Component comp : listEjercicios.getComponents()) {
                        comp.setEnabled(true);
                    }
                }
            }
        });
        timer.start();
    }

    private void detenerTemporizador() {
        if (timer != null) {
            timer.stop();
            lblTiempo.setText("Temporizador detenido. Tiempo restante: " + tiempoRestante + " segundos");
            timerRunning = false;
            for (Component comp : listEjercicios.getComponents()) {
                comp.setEnabled(true);
            }
        }
    }

    private void actualizarEtiquetaTiempo() {
        if (!enDescanso) {
            lblTiempo.setText("Tiempo restante: " + tiempoRestante + " segundos");
        } else {
            lblTiempo.setText("Descanso: " + tiempoRestante + " segundos");
        }
    }

    private int obtenerIdUsuario() {
        return 1; // Aquí deberías implementar la lógica para obtener el ID del usuario actual
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaCardio frame = new VentanaCardio();
            frame.setVisible(true);
        });
    }
}
