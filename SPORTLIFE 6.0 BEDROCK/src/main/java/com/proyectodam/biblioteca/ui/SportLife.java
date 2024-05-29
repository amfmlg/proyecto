package com.proyectodam.biblioteca.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import com.proyectodam.biblioteca.dto.mancuernas;
import com.proyectodam.biblioteca.dto.RegistroUsuario;
import com.proyectodam.biblioteca.dao.UsuarioDAO;
import com.proyectodam.biblioteca.dao.GestorUsuarios;
import java.awt.*;
import java.util.prefs.Preferences;
import java.util.List;

public class SportLife extends JFrame {

    private JFrame ventanaRegistro;
    private GestorUsuarios gestorUsuarios;
    private Preferences prefs;

    public SportLife() {
        super("SportLife - Biblioteca");
        setSize(1200, 800);
        gestorUsuarios = new GestorUsuarios();
        prefs = Preferences.userNodeForPackage(SportLife.class);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(242, 242, 242));

        ImageIcon icono = new ImageIcon("imagenes/Diseño sin título (1).png");
        Image imagenOriginal = icono.getImage();
        Image imagenRedimensionada = imagenOriginal.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);
        JLabel labelLogo = new JLabel(iconoRedimensionado);
        labelLogo.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        add(labelLogo, BorderLayout.NORTH);

        JPanel panelLogin = new JPanel(new GridBagLayout());
        panelLogin.setBackground(new Color(242, 242, 242));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lblUsuario = new JLabel("Nombre de Usuario:");
        lblUsuario.setForeground(new Color(75, 75, 75));
        JTextField txtUsuarioNombre = new JTextField(prefs.get("usuarioNombre", ""));
        txtUsuarioNombre.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel lblContraseña = new JLabel("Contraseña:");
        lblContraseña.setForeground(new Color(75, 75, 75));
        JPasswordField txtContraseña = new JPasswordField();
        txtContraseña.setFont(new Font("Arial", Font.PLAIN, 20));

        JCheckBox chkRecordar = new JCheckBox("Recordar usuario");
        chkRecordar.setForeground(new Color(75, 75, 75));
        chkRecordar.setSelected(!txtUsuarioNombre.getText().isEmpty());

        JButton btnAcceder = new JButton("Acceder");
        btnAcceder.setBackground(new Color(50, 150, 250));
        btnAcceder.setForeground(Color.WHITE);
        btnAcceder.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel lblNuevoUsuario = new JLabel("¿Nuevo Usuario?");
        lblNuevoUsuario.setForeground(new Color(75, 75, 75));

        JButton btnRegistro = new JButton("Registrarse");
        btnRegistro.setBackground(new Color(50, 150, 250));
        btnRegistro.setForeground(Color.WHITE);
        btnRegistro.setFont(new Font("Arial", Font.BOLD, 20));

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelLogin.add(lblUsuario, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panelLogin.add(txtUsuarioNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panelLogin.add(lblContraseña, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panelLogin.add(txtContraseña, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panelLogin.add(chkRecordar, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        panelLogin.add(btnAcceder, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        panelLogin.add(btnRegistro, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        panelLogin.add(lblNuevoUsuario, gbc);

        JPanel panelExterior = new JPanel(new BorderLayout());
        panelExterior.setBackground(new Color(242, 242, 242));
        panelExterior.setBorder(new EmptyBorder(100, 100, 100, 100));
        panelExterior.add(panelLogin, BorderLayout.CENTER);

        add(panelExterior, BorderLayout.CENTER);

        btnRegistro.addActionListener(e -> abrirVentanaRegistro());

        btnAcceder.addActionListener(e -> {
            String nombreUsuario = txtUsuarioNombre.getText();
            String contraseña = new String(txtContraseña.getPassword());

            if (gestorUsuarios.autenticarUsuario(nombreUsuario, contraseña)) {
                if (chkRecordar.isSelected()) {
                    prefs.put("usuarioNombre", nombreUsuario);
                } else {
                    prefs.remove("usuarioNombre");
                }
                JOptionPane.showMessageDialog(this, "Acceso exitoso.");
                abrirVentanaPrincipal();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Nombre de usuario o contraseña incorrectos.");
            }
        });
    }

    private void abrirVentanaPrincipal() {
        JFrame ventanaPrincipal = new JFrame("Ventana Principal");
        ventanaPrincipal.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ventanaPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ventanaPrincipal.getContentPane().setBackground(new Color(242, 242, 242));

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(new Color(242, 242, 242));

        JPanel panelEjercicios = createPanelEjercicios();
        panelEjercicios.setBackground(new Color(255, 255, 255));

        JPanel panelEstadisticas = createPanelEstadisticas();
        panelEstadisticas.setBackground(new Color(255, 255, 255));

        JPanel panelConfiguracion = new JPanel();
        panelConfiguracion.setBackground(new Color(255, 255, 255));
        panelConfiguracion.add(new JLabel("Aquí irían las configuraciones"));

        tabbedPane.addTab("Ejercicios", panelEjercicios);
        tabbedPane.addTab("Estadísticas", panelEstadisticas);
        tabbedPane.addTab("Configuraciones", panelConfiguracion);

        ventanaPrincipal.add(tabbedPane, BorderLayout.CENTER);
        ventanaPrincipal.setVisible(true);
    }

    private JPanel createPanelEjercicios() {
        JPanel panelEjercicios = new JPanel(new GridLayout(3, 2, 10, 10));
        panelEjercicios.setBackground(new Color(255, 255, 255));

        String[] tiposEjercicios = { "Cardio", "Mancuernas", "Estiramientos", "Yoga", "Pilates", "Crossfit" };
        for (String tipo : tiposEjercicios) {
            JButton btnEjercicio = new JButton(tipo);
            btnEjercicio.setBackground(new Color(50, 150, 250));
            btnEjercicio.setForeground(Color.WHITE);
            btnEjercicio.addActionListener(e -> {
                System.out.println("Botón " + tipo + " presionado.");
                if (tipo.equals("Mancuernas")) {
                    System.out.println("Intentando abrir VentanaMancuernas");
                    VentanaMancuernas ventanaMancuernas = new VentanaMancuernas();
                    ventanaMancuernas.setVisible(true);
                    System.out.println("VentanaMancuernas debería estar visible ahora.");
                } else {
                    JOptionPane.showMessageDialog(panelEjercicios, "Has seleccionado " + tipo + ".");
                }
            });
            panelEjercicios.add(btnEjercicio);
        }
        return panelEjercicios;
    }

    private JPanel createPanelEstadisticas() {
        JPanel panelEstadisticas = new JPanel();
        panelEstadisticas.setLayout(new BorderLayout());
    
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
    
        panelEstadisticas.add(scrollPane, BorderLayout.CENTER);
    
        // Obtén el usuario actual
        RegistroUsuario usuario = gestorUsuarios.obtenerUsuarioActual();
        if (usuario != null) {
            textArea.setText("Estadísticas de usuario:\n\n");
            textArea.append("Nombre: " + usuario.getNombre() + "\n");
            textArea.append("Peso: " + usuario.getPeso() + " kg\n");
            textArea.append("Altura: " + usuario.getAltura() + " cm\n");
            textArea.append("Fecha de Nacimiento: " + usuario.getFechaDeNacimiento() + "\n");
    
            // Mostrar estadísticas de mancuernas si existen
            List<mancuernas> ejerciciosMancuernas = mancuernas.getEjerciciosMancuernas(); // Corrección aquí
            if (ejerciciosMancuernas != null && !ejerciciosMancuernas.isEmpty()) {
                textArea.append("\nEstadísticas de Mancuernas:\n");
                for (mancuernas m : ejerciciosMancuernas) {
                    textArea.append("Nombre: " + m.getNombre() + "\n");
                    textArea.append("Peso: " + m.getPeso() + " kg\n");
                    textArea.append("Repeticiones: " + m.getRepeticiones() + "\n");
                    textArea.append("Tiempo Total: " + m.getTiempoTotal() + " segundos\n");
                    textArea.append("\n");
                }
            } else {
                textArea.append("No se encontraron registros de ejercicios con mancuernas.\n");
            }
            // Agrega más características aquí según sea necesario
        } else {
            textArea.setText("No se encontraron estadísticas para este usuario.");
        }
        return panelEstadisticas;
    }
    

    private void abrirVentanaRegistro() {
        ventanaRegistro = new JFrame("Registro de Nuevo Usuario");
        ventanaRegistro.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ventanaRegistro.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ventanaRegistro.getContentPane().setBackground(new Color(242, 242, 242));

        JPanel panelRegistro = createRegistroPanel();
        panelRegistro.setBackground(new Color(255, 255, 255));

        JPanel panelExterior = new JPanel(new BorderLayout());
        panelExterior.setBackground(new Color(242, 242, 242));
        panelExterior.setBorder(new EmptyBorder(100, 100, 100, 100));
        panelExterior.add(panelRegistro, BorderLayout.CENTER);

        ventanaRegistro.add(panelExterior);
        ventanaRegistro.setVisible(true);
    }

    private JPanel createRegistroPanel() {
        JPanel panelRegistro = new JPanel(new GridLayout(8, 2, 10, 10));
        panelRegistro.setBackground(new Color(255, 255, 255));

        JTextField txtNombre = new JTextField();
        JPasswordField txtContraseña = new JPasswordField();
        JTextField txtCorreo = new JTextField();
        JTextField txtFechaNacimiento = new JTextField();
        JTextField txtPeso = new JTextField();
        JTextField txtAltura = new JTextField();

        String[] opcionesFrecuencia = { "Diariamente", "Una o dos veces por semana", "Alguna que otra vez", "Nunca" };
        JComboBox<String> comboFrecuenciaEjercicio = new JComboBox<>(opcionesFrecuencia);
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(50, 150, 250));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 20));

        panelRegistro.add(new JLabel("Nombre de Usuario:"));
        panelRegistro.add(txtNombre);
        panelRegistro.add(new JLabel("Contraseña:"));
        panelRegistro.add(txtContraseña);
        panelRegistro.add(new JLabel("Correo Electrónico:"));
        panelRegistro.add(txtCorreo);
        panelRegistro.add(new JLabel("Fecha de Nacimiento:"));
        panelRegistro.add(txtFechaNacimiento);
        panelRegistro.add(new JLabel("Peso:"));
        panelRegistro.add(txtPeso);
        panelRegistro.add(new JLabel("Altura:"));
        panelRegistro.add(txtAltura);
        panelRegistro.add(new JLabel("Frecuencia de Ejercicio:"));
        panelRegistro.add(comboFrecuenciaEjercicio);
        panelRegistro.add(new JLabel());
        panelRegistro.add(btnGuardar);

        btnGuardar.addActionListener(e -> {
            String nombre = txtNombre.getText();
            String contraseña = new String(txtContraseña.getPassword());
            String correo = txtCorreo.getText();
            String fechaNacimiento = txtFechaNacimiento.getText();
            String peso = txtPeso.getText();
            String altura = txtAltura.getText();
            String frecuenciaEjercicio = (String) comboFrecuenciaEjercicio.getSelectedItem();

            if (nombre.isEmpty() || contraseña.isEmpty() || correo.isEmpty() || fechaNacimiento.isEmpty() ||
                    peso.isEmpty() || altura.isEmpty() || frecuenciaEjercicio == null) {
                JOptionPane.showMessageDialog(panelRegistro, "Todos los campos deben estar completos.");
                return;
            }

            RegistroUsuario usuario = new RegistroUsuario(0, nombre, contraseña, correo, fechaNacimiento, peso, altura,
                    frecuenciaEjercicio);

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            if (usuarioDAO.guardarUsuario(usuario)) {
                JOptionPane.showMessageDialog(panelRegistro, "Datos guardados correctamente");
                ventanaRegistro.dispose();
                new SportLife().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(panelRegistro, "Error al guardar los datos");
            }
        });
        return panelRegistro;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SportLife frame = new SportLife();
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);
        });
    }
}
