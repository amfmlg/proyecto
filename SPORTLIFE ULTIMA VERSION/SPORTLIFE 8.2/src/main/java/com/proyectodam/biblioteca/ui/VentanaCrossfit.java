package com.proyectodam.biblioteca.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCrossfit extends JFrame {

    private final String[] ejercicios = {
            "Man Makers",
            "Muscle Ups",
            "Burpees",
            "Sentadilla a una pierna",
            "Arrancada"
    };

    private final String[] imagenes = {
            "SPORTLIFE 8.2/imagenes/crossfit/man-makers.gif",
            "SPORTLIFE 8.2/imagenes/crossfit/muscle-ups.gif",
            "SPORTLIFE 8.2/imagenes/crossfit/burpees.gif",
            "SPORTLIFE 8.2/imagenes/crossfit/sentadilla-una-pierna.gif",
            "SPORTLIFE 8.2/imagenes/crossfit/arrancada.gif"
    };

    private JLabel imagenEjercicio;
    private JSpinner spinnerSegundos;
    private JSpinner spinnerRepeticiones;
    private JSpinner spinnerPeso;
    private JSpinner spinnerDescanso;
    private JLabel lblSegundosSeleccionados;
    private JLabel lblRepeticionesSeleccionadas;
    private JLabel lblPesoSeleccionado;
    private JLabel lblDescansoSeleccionado;
    private JLabel lblTiempo;
    private Timer timer;
    private int tiempoRestante;
    private boolean enDescanso;
    private int peso;
    private int repeticiones;
    private int tiempoDescanso;
    private int repeticionActual;
    private JList<String> listEjercicios;
    private boolean timerRunning;

    public VentanaCrossfit() {
        setTitle("Ejercicios de Crossfit");
        setSize(1800, 1200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(255, 255, 255));
        setLayout(new BorderLayout());

        listEjercicios = new JList<>(ejercicios);
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
        panelConfiguracion.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel lblSegundos = new JLabel("Segundos por repetición: ");
        spinnerSegundos = new JSpinner(new SpinnerNumberModel(0, 0, 7200, 1));
        lblSegundosSeleccionados = new JLabel("0 segundos seleccionados");

        spinnerSegundos.addChangeListener(e -> {
            int segundos = (Integer) spinnerSegundos.getValue();
            lblSegundosSeleccionados.setText(segundos + " segundos seleccionados");
        });

        JLabel lblRepeticiones = new JLabel("Repeticiones: ");
        spinnerRepeticiones = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        lblRepeticionesSeleccionadas = new JLabel("0 repeticiones seleccionadas");

        spinnerRepeticiones.addChangeListener(e -> {
            repeticiones = (Integer) spinnerRepeticiones.getValue();
            lblRepeticionesSeleccionadas.setText(repeticiones + " repeticiones seleccionadas");
        });

        JLabel lblPeso = new JLabel("Peso (kg): ");
        spinnerPeso = new JSpinner(new SpinnerNumberModel(0, 0, 200, 1));
        lblPesoSeleccionado = new JLabel("0 kg seleccionados");

        spinnerPeso.addChangeListener(e -> {
            peso = (Integer) spinnerPeso.getValue();
            lblPesoSeleccionado.setText(peso + " kg seleccionados");
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
        lblTiempo = new JLabel("Tiempo restante: 0 segundos, Peso: 0 kg, Repeticiones: 0");

        btnStart.addActionListener(e -> {
            if (verificarCamposObligatorios()) {
                int usuarioId = obtenerIdUsuario();
                DatosCrossfit.getInstance().guardarEjercicio(usuarioId, listEjercicios.getSelectedValue(), peso, repeticiones, (Integer) spinnerSegundos.getValue() * repeticiones);
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
        panelConfiguracion.add(lblRepeticiones);
        panelConfiguracion.add(spinnerRepeticiones);
        panelConfiguracion.add(lblPeso);
        panelConfiguracion.add(spinnerPeso);
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
        ImageIcon icon = new ImageIcon(imagePath);
        if (icon.getIconWidth() == -1) {
            JOptionPane.showMessageDialog(this, "No se pudo cargar la imagen: " + imagePath, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            imagenEjercicio.setIcon(icon);
        }
    }

    private boolean verificarCamposObligatorios() {
        int segundos = (Integer) spinnerSegundos.getValue();
        repeticiones = (Integer) spinnerRepeticiones.getValue();
        peso = (Integer) spinnerPeso.getValue();
        tiempoDescanso = (Integer) spinnerDescanso.getValue();
        return segundos > 0 && repeticiones > 0 && peso > 0 && tiempoDescanso > 0;
    }

    private void iniciarTemporizador() {
        tiempoRestante = (Integer) spinnerSegundos.getValue();
        actualizarEtiquetaTiempo();
        enDescanso = false;
        timerRunning = true;
        repeticionActual = 1; // Initialize repetition counter
    
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tiempoRestante--;
                if (tiempoRestante >= 0) {
                    actualizarEtiquetaTiempo();
                } else {
                    repeticionActual++;
                    if (repeticionActual <= repeticiones) {
                        tiempoRestante = (Integer) spinnerSegundos.getValue();
                        lblTiempo.setText("Repetición " + repeticionActual + "/" + repeticiones + ": Tiempo restante: " + tiempoRestante + " segundos, Peso: " + peso + " kg");
                    } else {
                        ((Timer) e.getSource()).stop();
                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(VentanaCrossfit.this, "¡Ejercicio terminado! Ahora empieza el descanso.", "Fin del ejercicio", JOptionPane.INFORMATION_MESSAGE);
                        iniciarDescanso();
                    }
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
                    lblTiempo.setText("Descanso: " + tiempoRestante + " segundos, Peso: " + peso + " kg, Repeticiones: " + repeticiones);
                } else {
                    ((Timer) e.getSource()).stop();
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(VentanaCrossfit.this, "¡Tiempo de descanso terminado!", "Fin del descanso", JOptionPane.INFORMATION_MESSAGE);
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
            timerRunning = false;
            for (Component comp : listEjercicios.getComponents()) {
                comp.setEnabled(true);
            }
        }
    }

    private void actualizarEtiquetaTiempo() {
        if (!enDescanso) {
            lblTiempo.setText("Tiempo restante: " + tiempoRestante + " segundos, Peso: " + peso + " kg, Repeticiones: " + repeticionActual + "/" + repeticiones);
        } else {
            lblTiempo.setText("Descanso: " + tiempoRestante + " segundos, Peso: " + peso + " kg, Repeticiones: " + repeticiones);
        }
    }

    private int obtenerIdUsuario() {
        return 1; // Cambia esto con la lógica para obtener el ID del usuario
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaCrossfit frame = new VentanaCrossfit();
            frame.setVisible(true);
        });
    }
}

class DatosCrossfit {
    private static DatosCrossfit instance;

    private DatosCrossfit() {
        // Constructor privado para evitar instanciación externa
    }

    public static synchronized DatosCrossfit getInstance() {
        if (instance == null) {
            instance = new DatosCrossfit();
        }
        return instance;
    }

    public void guardarEjercicio(int usuarioId, String ejercicio, int peso, int repeticiones, int tiempoTotal) {
        // Aquí va la lógica para guardar el ejercicio
        System.out.println("Guardando ejercicio para el usuario " + usuarioId + ": " + ejercicio + ", Peso: " + peso + " kg, Repeticiones: " + repeticiones + ", Tiempo total: " + tiempoTotal + " segundos");
    }
}
