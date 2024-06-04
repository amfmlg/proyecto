package com.proyectodam.biblioteca.ui;

import javax.swing.*;
import java.awt.*;

public class VentanaYoga extends JFrame {

    private final String[] ejercicios = {
        "Postura del perro boca abajo",
        "Postura del niño",
        "Postura del guerrero",
        "Postura del árbol",
        "Postura del gato-vaca"
    };

    private final String[] imagenes = {
        "SPORTLIFE 8.2/imagenes/yoga/perro_bocaabajo.jpg",
        "SPORTLIFE 8.2/imagenes/yoga/niño.jpg",
        "SPORTLIFE 8.2/imagenes/yoga/guerrero.jpg",
        "SPORTLIFE 8.2/imagenes/yoga/arbol.jpg",
        "SPORTLIFE 8.2/imagenes/yoga/gato_vaca.jpg"
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

    public VentanaYoga() {
        setTitle("Ejercicios de Yoga");
        setSize(1800, 1200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(255, 255, 255));
        setLayout(new BorderLayout());

        // Panel con los ejercicios de yoga en una lista
        JList<String> listEjercicios = new JList<>(ejercicios);
        listEjercicios.setFont(new Font("Arial", Font.PLAIN, 18));
        listEjercicios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listEjercicios.setVisibleRowCount(5);
        listEjercicios.setFixedCellHeight(50);
        listEjercicios.setFixedCellWidth(200);
        listEjercicios.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambiar el cursor a mano
        listEjercicios.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int index = listEjercicios.getSelectedIndex();
                mostrarImagen(index);
            }
        });

        // Scroll pane para la lista de ejercicios
        JScrollPane scrollPane = new JScrollPane(listEjercicios);
        scrollPane.setPreferredSize(new Dimension(250, 400));

        // Panel para mostrar la imagen explicativa
        imagenEjercicio = new JLabel();
        imagenEjercicio.setHorizontalAlignment(JLabel.CENTER);
        imagenEjercicio.setVerticalAlignment(JLabel.CENTER);

        JPanel panelConfiguracion = new JPanel();
        panelConfiguracion.setLayout(new GridLayout(2, 2, 10, 10));

        JLabel lblSegundos = new JLabel("Segundos: ");
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
            if (!timerRunning) {
                iniciarTemporizador();
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
        // Verificar si la imagen existe
        if (index >= 0 && index < imagenes.length) {
            String imagePath = imagenes[index];
            System.out.println("Intentando cargar la imagen desde: " + imagePath);
            ImageIcon icon = new ImageIcon(imagePath);
            if (icon.getIconWidth() == -1) { // Si la imagen no se carga correctamente
                JOptionPane.showMessageDialog(this, "No se pudo cargar la imagen: " + imagePath, "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                imagenEjercicio.setIcon(icon);
            }
        }
    }

    private void iniciarTemporizador() {
        tiempoRestante = (Integer) spinnerSegundos.getValue();
        actualizarEtiquetaTiempo();
        enDescanso = false;
        timerRunning = true;

        timer = new Timer(1000, e -> {
            tiempoRestante--;
            if (tiempoRestante >= 0) {
                actualizarEtiquetaTiempo();
            } else {
                if (!enDescanso) {
                    enDescanso = true;
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(this, "¡Tiempo de ejercicio terminado!", "Fin de ejercicio", JOptionPane.INFORMATION_MESSAGE);
                    tiempoRestante = tiempoDescanso;
                    lblTiempo.setText("Descanso: " + tiempoRestante + " segundos");
                } else {
                    ((Timer) e.getSource()).stop();
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(this, "¡Tiempo de descanso terminado!", "Fin del descanso", JOptionPane.INFORMATION_MESSAGE);
                    timerRunning = false;
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
        }
    }

    private void actualizarEtiquetaTiempo() {
        lblTiempo.setText("Tiempo restante: " + tiempoRestante + " segundos");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaYoga frame = new VentanaYoga();
            frame.setVisible(true);
        });
    }
}
