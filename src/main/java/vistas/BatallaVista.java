package vistas;

import batallas.Ronda;
import controladores.ExploradorFicheros;
import controladores.GestorFichero;
import controladores.SerializarBatalla;
import dao.mysql.TopScoreDao;
import models.score.TopScore;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

/**
 * Clase BatallaVista que muestra la vista de la batalla y permite serializarla en un fichero binario
 * o en un fichero XML y salir de la aplicación.
 */
public class BatallaVista extends JFrame {

    /**
     * panel de la vista de la batalla
     */
    private JPanel panel;

    /**
     * Campo de texto que muestra el nombre del ejército ganador
     */
    private JTextField txtGanador;

    /**
     * Tabla que muestra las rondas de la batalla
     */
    private JTable battleTable;

    /**
     * Botón para salir de la aplicación
     */
    private JButton salirButton;

    /**
     * Botón para serializar la batalla en un fichero binario
     */
    private JButton serializarButton;

    /**
     * Botón para serializar la batalla en un fichero XML
     */
    private JButton xmlButton;

    /**
     * Vector que contiene los nombres de las columnas de la tabla
     */
    private Vector<Object> columnNames;

    /**
     * Vector que contiene los datos de la tabla
     */
    private Vector<Vector<Object>> data;

    private static final TopScore topScore = new TopScore();

    /**
     * Constructor de la clase BatallaVista que inicializa la vista de la batalla
     */
    public BatallaVista() {
        setContentPane(panel);
        setLocation(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    /**
     * Método que crea los models.score.componentes de la vista de la batalla
     */
    private void createUIComponents() {
        initComponents();

        String winner = EjercitoVista.getBatalla().getGanador().getNombre();
        txtGanador.setEnabled(false);

        columnNames.addAll(Arrays.asList("Ronda", "Atacante", "Defensor", "Resultado"));
        dataRonda();

        battleTable.setModel(new DefaultTableModel(data, columnNames));

        battleTable.setEnabled(false);

        txtGanador.setText(winner);

        int resultadoTotal = 0;

        for (Ronda ronda : EjercitoVista.getBatalla().getRondas()) {
            resultadoTotal += ronda.getResultado();
        }

        topScore.setEjercito(EjercitoVista.getBatalla().getGanador());
        topScore.setGeneral(EjercitoVista.getBatalla().getGanador().getGeneral());
        topScore.setResultado(resultadoTotal);
        topScore.setFecha(new java.sql.Date(new java.util.Date().getTime()));

        TopScoreDao.insertTopScore(topScore);

        serializarButton.addActionListener(e -> {
            ExploradorFicheros.obtenerRuta();

            try {
                SerializarBatalla.serializar(EjercitoVista.getBatalla(), ExploradorFicheros.getRuta());
            } catch (IOException ex) {
                System.out.printf(ex.getMessage());
            }
        });

        xmlButton.addActionListener(e -> {
            ExploradorFicheros.obtenerRuta();

            GestorFichero.writeXmlBatalla(EjercitoVista.getBatalla(), ExploradorFicheros.getRuta());
        });

        salirButton.addActionListener(e -> System.exit(0));

    }

    /**
     * Método que inicializa los models.score.componentes de la vista de la batalla
     */
    private void initComponents() {
        battleTable = new JTable();
        txtGanador = new JTextField();
        serializarButton = new JButton();
        xmlButton = new JButton();
        salirButton = new JButton();

        columnNames = new Vector<>();
        data = new Vector<>();
    }

    /**
     * Método que añade los datos de las rondas de la batalla a la tabla
     */
    private void dataRonda() {

        for (Ronda ronda : EjercitoVista.getBatalla().getRondas()) {
            Vector<Object> row = new Vector<>();
            row.add(ronda.getNumRonda() + 1);
            row.add(ronda.getAtacante().getNombre());
            row.add(ronda.getDefensor().getNombre());
            row.add(ronda.getResultado());
            data.add(row);
        }
    }
}
