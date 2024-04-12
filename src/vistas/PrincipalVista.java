package vistas;

import controladores.ExploradorFicheros;
import controladores.GestorFichero;

import javax.swing.*;
import java.io.IOException;

public class PrincipalVista extends JFrame {
    private JPanel panel;
    private JLabel welcLabel;
    private JButton lucharButton;
    private JButton cargarGeneralButton;

    public PrincipalVista() {
        super("MEDAC Programación Batalla");
        setContentPane(panel);
        setLocation(500, 200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

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

    private void initComponents() {
        lucharButton = new JButton();
        cargarGeneralButton = new JButton();
    }
}
