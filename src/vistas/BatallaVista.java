package vistas;

import batallas.Batalla;
import batallas.Ronda;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Arrays;
import java.util.Vector;

public class BatallaVista extends JFrame {
    private JPanel panel;
    private JTextField txtGanador;
    private JTable battleTable;
    private JButton salirButton;
    private JButton serializarButton;
    private JButton XMLButton;

    private Vector<Object> columnNames;
    private Vector<Vector<Object>> data;

    public BatallaVista() {
        setContentPane(panel);
        setLocation(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void createUIComponents() {
        initComponents();

        txtGanador.setEnabled(false);

        columnNames.addAll(Arrays.asList("Ronda", "Atacante", "Defensor", "Resultado"));
        dataRonda();

        battleTable.setModel(new DefaultTableModel(data, columnNames));


    }

    private void initComponents() {
        battleTable = new JTable();
        txtGanador = new JTextField();

        columnNames = new Vector<>();
        data = new Vector<>();
    }

    public static void main(String[] args) {
        new BatallaVista();
    }

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
