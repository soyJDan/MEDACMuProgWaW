package vistas;

import componentes.personas.General;
import controladores.ExploradorFicheros;
import controladores.GestorFichero;
import dao.GeneralDao;

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

            GeneralDao.selectGeneral();

            if (!GeneralDao.getGenerales().isEmpty()) {
                ExploradorFicheros.setRuta("");
            }

            if (ExploradorFicheros.getRuta() == null) {
                JOptionPane.showMessageDialog(null, "No se ha cargado un general");
            } else {
                new EjercitoVista();

                for (General general : GeneralDao.getGenerales()) {
                    System.out.println(general);
                }

                dispose();
            }

        });

        cargarGeneralButton.addActionListener(e -> {

            GeneralDao.selectGeneral();

            if (!GeneralDao.getGenerales().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ya se ha cargado un general");
                ExploradorFicheros.setRuta("");
                return;
            }

            ExploradorFicheros.obtenerRuta();

            GestorFichero.obtenerNombreGeneral(ExploradorFicheros.getRuta());

            for (int i = 0; i < GestorFichero.getNombreGeneral().size(); i++) {
                General general = new General();
                general.setNombre(GestorFichero.getNombreGeneral().get(i));
                GeneralDao.insertGeneral(general);
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
