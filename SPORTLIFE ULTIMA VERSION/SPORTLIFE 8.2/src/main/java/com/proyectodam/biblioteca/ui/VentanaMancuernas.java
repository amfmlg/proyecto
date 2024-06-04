package com.proyectodam.biblioteca.ui;

import javax.swing.*;
import com.proyectodam.biblioteca.dao.datosMancuernas;
import java.awt.*;

public class VentanaMancuernas extends JFrame {

    private final String[] ejercicios = {
            "Press de banca con mancuernas",
            "Curl de bíceps con mancuernas",
            "Sentadillas con mancuernas",
            "Press militar con mancuernas",
            "Remo con mancuernas"
    };

    private final String[] imagenes = {
            "SPORTLIFE 8.2/imagenes/MANCUERNAS/PRES DE BANCA CON MANCUERNA.gif",
            "SPORTLIFE 8.2/imagenes/MANCUERNAS/CURL DE BICEPS CON MANCUERNA",
            "SPORTLIFE 8.2/imagenes/MANCUERNAS/sentadilla con mancuerna.gif",
            "SPORTLIFE 8.2/imagenes/MANCUERNAS/pres militar con mancuerna.gif",
            "SPORTLIFE 8.2/imagenes/MANCUERNAS/remo-con-mancuerna-a-un-brazo.gif"
    };

    private JLabel imagenEjercicio;
    private JSpinner spinnerRepeticiones;
    private JSpinner spinnerPeso;
    private JSpinner spinnerDescanso;
    private JSpinner spinnerSegundosPorRepeticion;
    private JLabel lblRepeticionesSeleccionadas;
    private JLabel lblPesoSeleccionado;
    private JLabel lblDescansoSeleccionado;
    private JLabel lblTiempo;
    private Timer timer;
    private int tiempoRestante;
    private int peso;
    private int repeticiones;
    private int tiempoDescanso;
    private int segundosPorRepeticion;
    private int repeticionesRestantes;
    private boolean timerRunning;
    private JList<String> listEjercicios;

    public VentanaMancuernas() {
        setTitle("Ejercicios con Mancuernas");
        setSize(1800, 1200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(255, 255, 255));
        setLayout(new BorderLayout());

        // Lista de ejercicios
        // Lista de ejercicios
        listEjercicios = new JList<>(ejercicios); // Asignando la lista al campo de la clase
        listEjercicios.setFont(new Font("Arial", Font.PLAIN, 18));
        listEjercicios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listEjercicios.setVisibleRowCount(5);
        listEjercicios.setFixedCellHeight(50);
        listEjercicios.setFixedCellWidth(200);
        listEjercicios.setCursor(new Cursor(Cursor.HAND_CURSOR));

        listEjercicios.addListSelectionListener(e -> {
            if (!timerRunning) {
                mostrarImagen(listEjercicios.getSelectedIndex());
            }
        });

        JScrollPane scrollPane = new JScrollPane(listEjercicios);
        scrollPane.setPreferredSize(new Dimension(250, 400));

        // Panel para mostrar la imagen explicativa
        imagenEjercicio = new JLabel();
        imagenEjercicio.setHorizontalAlignment(JLabel.CENTER);
        imagenEjercicio.setVerticalAlignment(JLabel.CENTER);

        JPanel panelConfiguracion = new JPanel();
        panelConfiguracion.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel lblRepeticiones = new JLabel("Repeticiones: ");
        spinnerRepeticiones = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        lblRepeticionesSeleccionadas = new JLabel("0 repeticiones seleccionadas");

        spinnerRepeticiones.addChangeListener(e -> {
            repeticiones = (Integer) spinnerRepeticiones.getValue();
            repeticionesRestantes = repeticiones;
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

        JLabel lblSegundosPorRepeticion = new JLabel("Segundos por repetición: ");
        spinnerSegundosPorRepeticion = new JSpinner(new SpinnerNumberModel(0, 0, 600, 1));
        JLabel lblSegundosPorRepeticionSeleccionado = new JLabel("0 segundos por repetición seleccionados");

        spinnerSegundosPorRepeticion.addChangeListener(e -> {
            segundosPorRepeticion = (Integer) spinnerSegundosPorRepeticion.getValue();
            lblSegundosPorRepeticionSeleccionado
                    .setText(segundosPorRepeticion + " segundos por repetición seleccionados");
        });

        JPanel panelControl = new JPanel();
        panelControl.setLayout(new FlowLayout());

        JButton btnStart = new JButton("Start");
        JButton btnStop = new JButton("Stop");
        lblTiempo = new JLabel("Tiempo restante: 0 segundos, Peso: 0 kg, Repeticiones: 0");

        btnStart.addActionListener(e -> {
            if (verificarCamposObligatorios()) {
                int usuarioId = obtenerIdUsuario();
                datosMancuernas.getInstance().guardarEjercicio(usuarioId, listEjercicios.getSelectedValue(), peso,
                        repeticiones, tiempoDescanso);
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

        panelConfiguracion.add(lblRepeticiones);
        panelConfiguracion.add(spinnerRepeticiones);
        panelConfiguracion.add(lblPeso);
        panelConfiguracion.add(spinnerPeso);
        panelConfiguracion.add(lblDescanso);
        panelConfiguracion.add(spinnerDescanso);
        panelConfiguracion.add(lblSegundosPorRepeticion);
        panelConfiguracion.add(spinnerSegundosPorRepeticion);
        panelConfiguracion.add(lblSegundosPorRepeticionSeleccionado);

        add(scrollPane, BorderLayout.WEST);
        add(imagenEjercicio, BorderLayout.CENTER);
        add(panelConfiguracion, BorderLayout.NORTH);
        add(panelControl, BorderLayout.SOUTH);
    }

    private void mostrarImagen(int index) {
        if (index >= 0 && index < imagenes.length) {
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
    }

    private boolean verificarCamposObligatorios() {
        repeticiones = (Integer) spinnerRepeticiones.getValue();
        peso = (Integer) spinnerPeso.getValue();
        tiempoDescanso = (Integer) spinnerDescanso.getValue();
        segundosPorRepeticion = (Integer) spinnerSegundosPorRepeticion.getValue();
        return repeticiones > 0 && peso > 0 && tiempoDescanso > 0 && segundosPorRepeticion > 0;
    }

    private void iniciarTemporizador() {
        repeticionesRestantes = repeticiones;
        tiempoRestante = segundosPorRepeticion;
        actualizarEtiquetaTiempo();
        timerRunning = true;
    
        timer = new Timer(1000, e -> {
            tiempoRestante--;
            if (tiempoRestante >= 0) {
                actualizarEtiquetaTiempo();
            } else {
                if (repeticionesRestantes > 0) {
                    repeticionesRestantes--;
                    if (repeticionesRestantes > 0) {
                        // Si quedan repeticiones, reiniciamos el temporizador con el tiempo de descanso
                        tiempoRestante = tiempoDescanso;
                    } else {
                        // Si no quedan repeticiones, mostramos el mensaje y detenemos el temporizador
                        ((Timer) e.getSource()).stop();
                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(this, "¡Ejercicio terminado!", "Fin del ejercicio", JOptionPane.INFORMATION_MESSAGE);
                        timerRunning = false;
                    }
                }
            }
        });
        timer.start();
        listEjercicios.setEnabled(false);
    }
    

    private void detenerTemporizador() {
        if (timer != null) {
            timer.stop();
            if (repeticionesRestantes > 0) {
                lblTiempo.setText("Temporizador detenido. Tiempo restante: " + tiempoRestante + " segundos, Peso: " + peso + " kg, Repeticiones restantes: " + repeticionesRestantes);
            } else {
                lblTiempo.setText("Temporizador detenido. Tiempo de descanso: " + tiempoDescanso + " segundos");
            }
            timerRunning = false;
            listEjercicios.setEnabled(true);
        }
    }
    

    private void actualizarEtiquetaTiempo() {
        lblTiempo.setText("Tiempo restante: " + tiempoRestante + " segundos, Peso: " + peso
                + " kg, Repeticiones restantes: " + repeticionesRestantes);
    }

    private int obtenerIdUsuario() {
        return 1; // Aquí deberías implementar la lógica para obtener el ID del usuario actual
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaMancuernas frame = new VentanaMancuernas();
            frame.setVisible(true);
        });
    }
}
