package com.proyectodam.biblioteca.ui;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SportLife gui = new SportLife();
            gui.setVisible(true);
        });
    }
}