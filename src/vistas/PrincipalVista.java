package vistas;

import controladores.ExploradorFicheros;
import controladores.GestorFichero;

import javax.swing.*;
import java.io.IOException;

/**
 * Clase que crea la vista principal de la aplicación y sus componentes gráficos. Esta vista es la primera que se
 * muestra al usuario y le permite cargar un general y pasar a la vista de la batalla.
 */
public class PrincipalVista extends JFrame {

    /**
     * Panel principal de la vista.
     */
    private JPanel panel;

    /**
     * Etiqueta de bienvenida.
     */
    private JLabel welcLabel;

    /**
     * Botón para pasar a la vista de la batalla.
     */
    private JButton lucharButton;

    /**
     * Botón para cargar un general.
     */
    private JButton cargarGeneralButton;

    /**
     * Constructor de la clase. Crea la vista principal y sus componentes gráficos.
     */
    public PrincipalVista() {
        super("World At War");
        setContentPane(panel);
        setLocation(500, 200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    /**
     * Método que inicializa los componentes gráficos de la vista y añade los listeners a los botones.
     */
    private void createUIComponents() {
        initComponents();

        lucharButton.addActionListener(e -> {

            if (ExploradorFicheros.getRuta() == null) {
                JOptionPane.showMessageDialog(null, "No se ha cargado un general");
            } else {
                new EjercitoVista();

                dispose();
            }

        });

        cargarGeneralButton.addActionListener(e -> {
            ExploradorFicheros.obtenerRuta();

            try {
                GestorFichero.obtenerNombreGeneral(ExploradorFicheros.getRuta());
            } catch (IOException ex) {
                System.out.printf(ex.getMessage());
            }
        });
    }

    /**
     * Método que inicializa los componentes gráficos de la vista.
     */
    private void initComponents() {
        lucharButton = new JButton();
        cargarGeneralButton = new JButton();
    }
}
