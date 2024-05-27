package com.proyectodam.biblioteca.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ventanaYoga extends JFrame {

    // Mapa de ejercicios a imágenes (puedes cambiar las rutas de las imágenes)
    private final String[] ejercicios = {
        "Postura del perro boca abajo",
        "Postura del niño",
        "Postura del guerrero ",
        "Postura del árbol ",
        "Postura del gato-vaca "
    };

    private final String[] imagenes = {
        "imagenes/perro_bocaabajo.jpg",
        "imagenes/niño.jpg",
        "imagenes/guerrero.jpg",
        "imagenes/arbol.jpg",
        "imagenes/gato_vaca.jpg"
    };

    private JLabel imagenEjercicio;

    public ventanaYoga() {
        setTitle("Ejercicios de Yoga");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(255, 255, 255));
        setLayout(new BorderLayout());

        // Panel con los ejercicios de yoga
        JPanel panelEjerciciosYoga = new JPanel(new GridLayout(5, 1, 10, 10));
        panelEjerciciosYoga.setBackground(new Color(255, 255, 255));

        for (int i = 0; i < ejercicios.length; i++) {
            JLabel lblEjercicio = new JLabel(ejercicios[i]);
            lblEjercicio.setFont(new Font("Arial", Font.PLAIN, 18));
            lblEjercicio.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); // Añadir borde
            lblEjercicio.setHorizontalAlignment(JLabel.CENTER); // Centrar el texto
            lblEjercicio.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambiar el cursor a mano
            final int index = i;
            lblEjercicio.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    mostrarImagen(index);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    lblEjercicio.setForeground(Color.BLUE); // Cambiar el color al pasar el ratón
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    lblEjercicio.setForeground(Color.BLACK); // Restaurar el color cuando el ratón salga
                }
            });
            panelEjerciciosYoga.add(lblEjercicio);
        }

        // Panel para mostrar la imagen explicativa
        imagenEjercicio = new JLabel();
        imagenEjercicio.setHorizontalAlignment(JLabel.CENTER);
        imagenEjercicio.setVerticalAlignment(JLabel.CENTER);

        add(panelEjerciciosYoga, BorderLayout.WEST);
        add(imagenEjercicio, BorderLayout.CENTER);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ventanaYoga frame = new ventanaYoga();
            frame.setVisible(true);
        });
    }
}
