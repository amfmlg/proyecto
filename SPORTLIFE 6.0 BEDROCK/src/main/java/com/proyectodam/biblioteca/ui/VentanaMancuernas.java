package com.proyectodam.biblioteca.ui;

import javax.swing.*;

import com.proyectodam.biblioteca.dao.datosMancuernas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaMancuernas extends JFrame {

    private final String[] ejercicios = {
        "Press de banca con mancuernas",
        "Curl de bíceps con mancuernas",
        "Sentadillas con mancuernas",
        "Press militar con mancuernas",
        "Remo con mancuernas"
    };

    private final String[] imagenes = {
        "imagenes/PRES DE BANCA CON MANCUERNA.gif",
        "imagenes/Curl de bíceps con dos mancuernas sentado.gif",
        "imagenes/sentadilla con mancuerna.gif",
        "imagenes/pres militar con mancuerna.gif",
        "imagenes/remo-con-mancuerna-a-un-brazo.gif"
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
    private JList<String> listEjercicios; // Cambiado el tipo de panelEjerciciosMancuernas a JList
    private boolean timerRunning; // Se utiliza en el método detenerTemporizador()

    public VentanaMancuernas() {
        setTitle("Ejercicios con Mancuernas");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(255, 255, 255));
        setLayout(new BorderLayout());

        listEjercicios = new JList<>(ejercicios); // Cambiado de JPanel a JList y establecido el modelo de datos
        listEjercicios.setBackground(new Color(255, 255, 255));

        listEjercicios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Configuración del modo de selección

        listEjercicios.addListSelectionListener(e -> { // Manejador de eventos de selección de lista
            if (!timerRunning) {
                mostrarImagen(listEjercicios.getSelectedIndex());
            }
        });

        JScrollPane scrollPane = new JScrollPane(listEjercicios); // Agregando la lista a un JScrollPane para desplazamiento

        imagenEjercicio = new JLabel();
        imagenEjercicio.setHorizontalAlignment(JLabel.CENTER);
        imagenEjercicio.setVerticalAlignment(JLabel.CENTER);

        JPanel panelConfiguracion = new JPanel();
        panelConfiguracion.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel lblSegundos = new JLabel("Segundos: ");
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
        spinnerDescanso = new JSpinner(new SpinnerNumberModel(0, 0, 600, 1)); // Hasta 10 minutos en segundos
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
                // Obtener el ID del usuario
                int usuarioId = obtenerIdUsuario(); // Reemplaza esto con el método que obtiene el ID del usuario
                // Guardar el ejercicio en la base de datos
                datosMancuernas.getInstance().guardarEjercicio(usuarioId, listEjercicios.getSelectedValue(), peso, repeticiones, (Integer) spinnerSegundos.getValue() * repeticiones);
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

        add(scrollPane, BorderLayout.WEST); // Cambiado de panelEjerciciosMancuernas a listEjercicios
        add(imagenEjercicio, BorderLayout.CENTER);
        add(panelConfiguracion, BorderLayout.NORTH);
        add(panelControl, BorderLayout.SOUTH);
    }

    private void mostrarImagen(int index) {
        String imagePath = imagenes[index];
        System.out.println("Intentando cargar la imagen desde: " + imagePath);
        ImageIcon icon = new ImageIcon(imagePath);
        if (icon.getIconWidth() == -1) {JOptionPane.showMessageDialog(this, "No se pudo cargar la imagen: " + imagePath, "Error", JOptionPane.ERROR_MESSAGE);
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

    timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            tiempoRestante--;
            if (tiempoRestante >= 0) {
                actualizarEtiquetaTiempo();
            } else {
                if (!enDescanso) {
                    enDescanso = true;
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(VentanaMancuernas.this, "¡Tiempo de ejercicio terminado!", "Fin de ejercicio", JOptionPane.INFORMATION_MESSAGE);
                    tiempoRestante = tiempoDescanso;
                    lblTiempo.setText("Descanso: " + tiempoRestante + " segundos, Peso: " + peso + " kg, Repeticiones: " + repeticiones);
                } else {
                    ((Timer) e.getSource()).stop();
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(VentanaMancuernas.this, "¡Tiempo de descanso terminado!", "Fin del descanso", JOptionPane.INFORMATION_MESSAGE);
                    timerRunning = false;
                }
            }
        }
    });
    timer.start();
    for (Component comp : listEjercicios.getComponents()) {
        comp.setEnabled(false);
    }
}

private void detenerTemporizador() {
    if (timer != null) {
        timer.stop();
        lblTiempo.setText("Temporizador detenido. Tiempo restante: " + tiempoRestante + " segundos, Peso: " + peso + " kg, Repeticiones: " + repeticiones);
        timerRunning = false;
        for (Component comp : listEjercicios.getComponents()) {
            comp.setEnabled(true);
        }
    }
}

private void actualizarEtiquetaTiempo() {
    lblTiempo.setText("Tiempo restante: " + tiempoRestante + " segundos, Peso: " + peso + " kg, Repeticiones: " + repeticiones);
}

private int obtenerIdUsuario() {
    // Aquí deberías implementar la lógica para obtener el ID del usuario actual
    // Por ejemplo, puedes tener una clase de usuario actualmente autenticado o una sesión de usuario
    // Por ahora, solo devolveré un valor ficticio para propósitos de demostración
    return 1;
}

public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        VentanaMancuernas frame = new VentanaMancuernas();
        frame.setVisible(true);
    });
}
}

           
