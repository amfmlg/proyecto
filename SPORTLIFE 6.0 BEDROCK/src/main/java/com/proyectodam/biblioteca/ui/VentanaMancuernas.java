package com.proyectodam.biblioteca.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaMancuernas extends JFrame {

    // Mapa de ejercicios a imágenes (puedes cambiar las rutas de las imágenes)
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
    private JSpinner spinnerMinutos;
    private JSpinner spinnerRepeticiones;
    private JSpinner spinnerPeso;
    private JLabel lblMinutosSeleccionados;
    private JLabel lblRepeticionesSeleccionadas;
    private JLabel lblPesoSeleccionado;
    private JLabel lblTiempo;
    private Timer timer;
    private int tiempoRestante;
    private boolean enDescanso;
    private int peso;
    private int repeticiones;

    public VentanaMancuernas() {
        setTitle("Ejercicios con Mancuernas");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(255, 255, 255));
        setLayout(new BorderLayout());

        // Panel con los ejercicios de mancuernas
        JPanel panelEjerciciosMancuernas = new JPanel(new GridLayout(5, 1, 10, 10));
        panelEjerciciosMancuernas.setBackground(new Color(255, 255, 255));

        for (int i = 0; i < ejercicios.length; i++) {
            JLabel lblEjercicio = new JLabel(ejercicios[i]);
            lblEjercicio.setFont(new Font("Arial", Font.PLAIN, 18));
            lblEjercicio.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); // Añadir borde
            lblEjercicio.setHorizontalAlignment(JLabel.CENTER); // Centrar el texto
            lblEjercicio.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambiar el cursor a mano
            final int index = i;
            lblEjercicio.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    mostrarImagen(index);
                }

                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    lblEjercicio.setForeground(Color.BLUE); // Cambiar el color al pasar el ratón
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    lblEjercicio.setForeground(Color.BLACK); // Restaurar el color cuando el ratón salga
                }
            });
            panelEjerciciosMancuernas.add(lblEjercicio);
        }

        // Panel para mostrar la imagen explicativa
        imagenEjercicio = new JLabel();
        imagenEjercicio.setHorizontalAlignment(JLabel.CENTER);
        imagenEjercicio.setVerticalAlignment(JLabel.CENTER);

        // Panel para el selector de minutos
        JPanel panelConfiguracion = new JPanel();
        panelConfiguracion.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel lblMinutos = new JLabel("Minutos: ");
        spinnerMinutos = new JSpinner(new SpinnerNumberModel(0, 0, 120, 1));
        lblMinutosSeleccionados = new JLabel("0 minutos seleccionados");

        spinnerMinutos.addChangeListener(e -> {
            int minutos = (Integer) spinnerMinutos.getValue();
            lblMinutosSeleccionados.setText(minutos + " minutos seleccionados");
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

        // Panel para los botones de control y el tiempo restante
        JPanel panelControl = new JPanel();
        panelControl.setLayout(new FlowLayout());

        JButton btnStart = new JButton("Start");
        JButton btnStop = new JButton("Stop");
        lblTiempo = new JLabel("Tiempo restante: 0 segundos, Peso: 0 kg, Repeticiones: 0");

        btnStart.addActionListener(e -> iniciarTemporizador());
        btnStop.addActionListener(e -> detenerTemporizador());

        panelControl.add(btnStart);
        panelControl.add(btnStop);
        panelControl.add(lblTiempo);

        panelConfiguracion.add(lblMinutos);
        panelConfiguracion.add(spinnerMinutos);
        panelConfiguracion.add(lblRepeticiones);
        panelConfiguracion.add(spinnerRepeticiones);
        panelConfiguracion.add(lblPeso);
        panelConfiguracion.add(spinnerPeso);
        
        add(panelEjerciciosMancuernas, BorderLayout.WEST);
        add(imagenEjercicio, BorderLayout.CENTER);
        add(panelConfiguracion, BorderLayout.NORTH);
        add(panelControl, BorderLayout.SOUTH);
    }

    private void mostrarImagen(int index) {
        // Verificar si la imagen existe
        String imagePath = imagenes[index];
        System.out.println("Intentando cargar la imagen desde: " + imagePath);
        ImageIcon icon = new ImageIcon(imagePath);
        if (icon.getIconWidth() == -1) { // Si la imagen no se carga correctamente
            JOptionPane.showMessageDialog(this, "No se pudo cargar la imagen: " + imagePath, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            imagenEjercicio.setIcon(icon);
        }
    }

    private void iniciarTemporizador() {
        tiempoRestante = (Integer) spinnerMinutos.getValue() * 60;
        actualizarEtiquetaTiempo();
        enDescanso = false;

        timer = new Timer(1000
, new ActionListener() { // 1000 ms = 1 segundo
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
                        tiempoRestante = 30;
                        lblTiempo.setText("Descanso: " + tiempoRestante + " segundos, Peso: " + peso + " kg, Repeticiones: " + repeticiones);
                    } else {
                        ((Timer) e.getSource()).stop();
                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(VentanaMancuernas.this, "¡Tiempo de descanso terminado!", "Fin del descanso", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        timer.start();
    }

    private void detenerTemporizador() {
        if (timer != null) {
            timer.stop();
            lblTiempo.setText("Temporizador detenido. Tiempo restante: " + tiempoRestante + " segundos, Peso: " + peso + " kg, Repeticiones: " + repeticiones);
        }
    }

    private void actualizarEtiquetaTiempo() {
        lblTiempo.setText("Tiempo restante: " + tiempoRestante + " segundos, Peso: " + peso + " kg, Repeticiones: " + repeticiones);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaMancuernas frame = new VentanaMancuernas();
            frame.setVisible(true);
        });
    }
}
