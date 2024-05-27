package com.proyectodam.biblioteca.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.proyectodam.biblioteca.dao.GestorUsuarios;
import com.proyectodam.biblioteca.dao.UsuarioDAO;
import com.proyectodam.biblioteca.dto.RegistroUsuario;

import java.awt.*;
import java.util.prefs.Preferences;

public class SportLife extends JFrame {

    private JFrame ventanaRegistro; // Declaración del campo ventanaRegistro
    private GestorUsuarios gestorUsuarios; // Agregar el campo gestorUsuarios

    private Preferences prefs;

    public SportLife() {
        super("SportLife - Biblioteca");
        setSize(1200,800);
        gestorUsuarios = new GestorUsuarios(); // Inicializar el gestorUsuarios
        prefs = Preferences.userNodeForPackage(SportLife.class);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(242, 242, 242)); // Color de fondo

        // Agregar la imagen como logo en la parte superior de la ventana de inicio de sesión
        ImageIcon icono = new ImageIcon("imagenes/Diseño sin título (1).png"); // Reemplaza "imagenes/Diseño sin título (1).png" con la ubicación de tu imagen
        Image imagenOriginal = icono.getImage();
        // Redimensionar la imagen a un tamaño más pequeño
        Image imagenRedimensionada = imagenOriginal.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        // Crear un nuevo ImageIcon con la imagen redimensionada
        ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);
        // Crear un JLabel con el icono redimensionado
        JLabel labelLogo = new JLabel(iconoRedimensionado);
        // Añadir margen superior al JLabel
        labelLogo.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0)); // Margen de 20 píxeles hacia arriba
        // Agregar el logo a la parte superior de la ventana
        add(labelLogo, BorderLayout.NORTH);

        // Panel de login
        JPanel panelLogin = new JPanel(new GridBagLayout());
        panelLogin.setBackground(new Color(242, 242, 242)); // Color de fondo

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Márgenes entre componentes

        JLabel lblUsuario = new JLabel("Nombre de Usuario:");
        lblUsuario.setForeground(new Color(75, 75, 75)); // Color de texto
        JTextField txtUsuarioNombre = new JTextField(prefs.get("usuarioNombre", ""));
        txtUsuarioNombre.setFont(new Font("Arial", Font.PLAIN, 20)); // Tamaño de fuente aumentado

        JLabel lblContraseña = new JLabel("Contraseña:");
        lblContraseña.setForeground(new Color(75, 75, 75)); // Color de texto
        JPasswordField txtContraseña = new JPasswordField();
        txtContraseña.setFont(new Font("Arial", Font.PLAIN, 20)); // Tamaño de fuente aumentado

        JCheckBox chkRecordar = new JCheckBox("Recordar usuario");
        chkRecordar.setForeground(new Color(75, 75, 75)); // Color de texto
        chkRecordar.setSelected(!txtUsuarioNombre.getText().isEmpty());

        JButton btnAcceder = new JButton("Acceder");
        btnAcceder.setBackground(new Color(50, 150, 250)); // Color de fondo del botón
        btnAcceder.setForeground(Color.WHITE); // Color del texto del botón
        btnAcceder.setFont(new Font("Arial", Font.BOLD, 20)); // Tamaño de fuente aumentado

        JLabel lblNuevoUsuario = new JLabel("¿Nuevo Usuario?");
        lblNuevoUsuario.setForeground(new Color(75, 75, 75)); // Color de texto

        JButton btnRegistro = new JButton("Registrarse");
        btnRegistro.setBackground(new Color(50, 150, 250)); // Color de fondo del botón
        btnRegistro.setForeground(Color.WHITE); // Color del texto del botón
        btnRegistro.setFont(new Font("Arial", Font.BOLD, 20)); // Tamaño de fuente aumentado

        // Añadir los componentes al panel de login
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

        // Crear un panel exterior con BorderLayout y añadir márgenes
        JPanel panelExterior = new JPanel(new BorderLayout());
        panelExterior.setBackground(new Color(242, 242, 242)); // Color de fondo
        panelExterior.setBorder(new EmptyBorder(100, 100, 100, 100)); // Ajustar los márgenes aquí
        panelExterior.add(panelLogin, BorderLayout.CENTER);

        // Añadir el panel exterior al centro del BorderLayout
        add(panelExterior, BorderLayout.CENTER);

        // Agregar ActionListener al botón de registro
        btnRegistro.addActionListener(e -> abrirVentanaRegistro());

        // Agregar ActionListener al botón de acceso
        btnAcceder.addActionListener(e -> {
            String nombreUsuario = txtUsuarioNombre.getText();
            String contraseña = new String(txtContraseña.getPassword());

            // Autenticar al usuario utilizando el método autenticarUsuario en GestorUsuarios
            if (gestorUsuarios.autenticarUsuario(nombreUsuario, contraseña)) {
                if (chkRecordar.isSelected()) {
                    prefs.put("usuarioNombre", nombreUsuario);
                } else {
                    prefs.remove("usuarioNombre");
                }
                JOptionPane.showMessageDialog(this, "Acceso exitoso.");
                abrirVentanaPrincipal();
                dispose(); // Cerrar la ventana de inicio de sesión
            } else {
                JOptionPane.showMessageDialog(this, "Nombre de usuario o contraseña incorrectos.");
            }
        });
    }

    private void abrirVentanaPrincipal() {
        JFrame ventanaPrincipal = new JFrame("Ventana Principal");
        ventanaPrincipal.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ventanaPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH); // Configura la ventana a pantalla completa
        ventanaPrincipal.getContentPane().setBackground(new Color(242, 242, 242)); // Color de fondo

        // Crear un JTabbedPane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(new Color(242, 242, 242)); // Color de fondo del tabbedPane

        // Crear paneles para las pestañas
        JPanel panelEjercicios = createPanelEjercicios();
        panelEjercicios.setBackground(new Color(255, 255, 255)); // Color de fondo del panel

        JPanel panelEstadisticas = new JPanel();
        panelEstadisticas.setBackground(new Color(255, 255, 255)); // Color de fondo del panel
        panelEstadisticas.add(new JLabel("Aquí irían las estadísticas"));

        JPanel panelConfiguracion = new JPanel();
        panelConfiguracion.setBackground(new Color(255, 255, 255)); // Color de fondo del panel
        panelConfiguracion.add(new JLabel("Aquí irían las configuraciones"));

        // Añadir los paneles al JTabbedPane
        tabbedPane.addTab("Ejercicios", panelEjercicios);
        tabbedPane.addTab("Estadísticas", panelEstadisticas);
        tabbedPane.addTab("Configuraciones", panelConfiguracion);
        // Añadir el JTabbedPane al frame principal
        ventanaPrincipal.add(tabbedPane, BorderLayout.CENTER);

        // Hacer visible el frame
        ventanaPrincipal.setVisible(true);
    }
    private JPanel createPanelEjercicios() {
        JPanel panelEjercicios = new JPanel(new GridLayout(3, 2, 10, 10));
        panelEjercicios.setBackground(new Color(255, 255, 255)); // Color de fondo del panel
    
        // Agregar diferentes tipos de ejercicios
        String[] tiposEjercicios = { "Cardio", "Mancuernas", "Estiramientos", "Yoga", "Pilates", "Crossfit" };
        for (String tipo : tiposEjercicios) {
            JButton btnEjercicio = new JButton(tipo);
            btnEjercicio.setBackground(new Color(50, 150, 250)); // Color de fondo del botón
            btnEjercicio.setForeground(Color.WHITE); // Color del texto del botón
            btnEjercicio.addActionListener(e -> {
                switch (tipo) {
                    case "Mancuernas":
                        // Abrir la nueva ventana con los ejercicios de mancuernas
                        new ventanaMancuernas().setVisible(true);
                        break;
                    case "Yoga":
                        // Abrir la nueva ventana con los ejercicios de yoga
                        new ventanaYoga().setVisible(true);
                        break;
                    case "Cardio":
                        // Lógica para ejercicios de cardio
                        JOptionPane.showMessageDialog(panelEjercicios, "Has seleccionado Cardio.");
                        break;
                    case "Estiramientos":
                        // Lógica para ejercicios de estiramientos
                        JOptionPane.showMessageDialog(panelEjercicios, "Has seleccionado Estiramientos.");
                        break;
                    case "Pilates":
                        // Lógica para ejercicios de pilates
                        JOptionPane.showMessageDialog(panelEjercicios, "Has seleccionado Pilates.");
                        break;
                    case "Crossfit":
                        // Lógica para ejercicios de crossfit
                        JOptionPane.showMessageDialog(panelEjercicios, "Has seleccionado Crossfit.");
                        break;
                    default:
                        JOptionPane.showMessageDialog(panelEjercicios, "Has seleccionado " + tipo + ".");
                }
            });
            panelEjercicios.add(btnEjercicio);
        }
    
        return panelEjercicios;
    }
    
    

    private void abrirVentanaRegistro() {
        ventanaRegistro = new JFrame("Registro de Nuevo Usuario");
        ventanaRegistro.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ventanaRegistro.setExtendedState(JFrame.MAXIMIZED_BOTH); // Configura la ventana a pantalla completa
        ventanaRegistro.getContentPane().setBackground(new Color(242, 242, 242)); // Color de fondo

        // Crear componentes para el registro de usuario
        JPanel panelRegistro = createRegistroPanel();
        panelRegistro.setBackground(new Color(255, 255, 255)); // Color de fondo del panel

        // Crear un panel exterior con BorderLayout y añadir márgenes
        JPanel panelExterior = new JPanel(new BorderLayout());
        panelExterior.setBackground(new Color(242, 242, 242)); // Color de fondo
        panelExterior.setBorder(new EmptyBorder(100, 100, 100, 100)); // Ajustar los márgenes aquí
        panelExterior.add(panelRegistro, BorderLayout.CENTER);

        // Añadir el panel exterior a la ventana de registro
        ventanaRegistro.add(panelExterior);
        ventanaRegistro.setVisible(true);
    }

    private JPanel createRegistroPanel() {
        JPanel panelRegistro = new JPanel(new GridLayout(8, 2, 10, 10));
        panelRegistro.setBackground(new Color(255, 255, 255)); // Color de fondo del panel
        JTextField txtNombre = new JTextField();
        JPasswordField txtContraseña = new JPasswordField();
        JTextField txtCorreo = new JTextField();
        JTextField txtFechaNacimiento = new JTextField();
        JTextField txtPeso = new JTextField();
        JTextField txtAltura = new JTextField();

        // Crear JComboBox para la frecuencia de ejercicio
        String[] opcionesFrecuencia = { "Diariamente", "Una o dos veces por semana", "Alguna que otra vez", "Nunca" };
        JComboBox<String> comboFrecuenciaEjercicio = new JComboBox<>(opcionesFrecuencia);
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(50, 150, 250)); // Color de fondo del botón
        btnGuardar.setForeground(Color.WHITE); // Color del texto del botón
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 20)); // Tamaño de fuente aumentado

        // Agregar componentes al panel de registro
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
        panelRegistro.add(new JLabel()); // Espacio vacío para alineación
        panelRegistro.add(btnGuardar);

        // Agregar ActionListener al botón de guardar
        btnGuardar.addActionListener(e -> {
            String nombre = txtNombre.getText();
            String contraseña = new String(txtContraseña.getPassword());
            String correo = txtCorreo.getText();
            String fechaNacimiento = txtFechaNacimiento.getText();
            String peso = txtPeso.getText();
            String altura = txtAltura.getText();
            String frecuenciaEjercicio = (String) comboFrecuenciaEjercicio.getSelectedItem();

            // Validar que todos los campos estén completos
            if (nombre.isEmpty() || contraseña.isEmpty() || correo.isEmpty() || fechaNacimiento.isEmpty() ||
                    peso.isEmpty() || altura.isEmpty() || frecuenciaEjercicio == null) {
                JOptionPane.showMessageDialog(panelRegistro, "Todos los campos deben estar completos.");
                return;
            }

            // Crear un objeto RegistroUsuario
            RegistroUsuario usuario = new RegistroUsuario(0, nombre, contraseña, correo, fechaNacimiento, peso, altura, frecuenciaEjercicio);

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            // Guardar el usuario en la base de datos
            if (usuarioDAO.guardarUsuario(usuario)) {
                JOptionPane.showMessageDialog(panelRegistro, "Datos guardados correctamente");
                ventanaRegistro.dispose(); // Cerrar la ventanaRegistro y regresar al inicio de sesión
                new SportLife().setVisible(true); // Reabrir la ventana de inicio de sesión
            } else {
                JOptionPane.showMessageDialog(panelRegistro, "Error al guardar los datos");
            }
        });
        return panelRegistro;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SportLife frame = new SportLife();
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Configura la ventana a pantalla completa
            frame.setVisible(true);
        });
    }
}
