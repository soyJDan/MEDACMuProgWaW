package vistas;

import batallas.Ejercito;
import componentes.animales.Elefante;
import componentes.animales.Tigre;
import componentes.personas.Caballeria;
import componentes.personas.General;
import componentes.personas.Infanteria;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Arrays;
import java.util.Vector;

public class EjercitoVista {
    private JPanel panel;
    private JProgressBar pesoBar;
    private JLabel nameArmy;
    private JTable aboutArmy;
    private JPanel panelArmy;
    private JRadioButton nameArmyRad;
    private JRadioButton addInfRad;
    private JRadioButton addCabRad;
    private JRadioButton addGenRad;
    private JRadioButton addElefRad;
    private JRadioButton addTirgRad;
    private JRadioButton deleteUnit;
    private JButton confirmButton;
    private JButton button1;
    private JTable totalArmy;
    private JLabel pesoLabel;
    private JLabel totalLabel;

    private Vector<Vector<Object>> data;
    private Ejercito ejercito;

//    private final Batalla batalla = new Batalla();

    public static void main(String[] args) {
        JFrame frame = new JFrame("EjercitoVista");
        frame.setContentPane(new EjercitoVista().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        initComponents();

        pesoBar.setMinimum(0);
        pesoBar.setMaximum(Ejercito.getMaxPeso());
        pesoBar.setStringPainted(true);
        pesoBar.setString(ejercito.getSaldoPeso() + "/" + Ejercito.getMaxPeso());

        Vector<String> columnNames = new Vector<>(Arrays.asList("Nombre", "Tipo", "Ataque", "Defensa", "Salud"));

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        aboutArmy = new JTable(tableModel);

        nameArmyRad.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Introduce el nombre del ejercito");

            ejercito.asignarNombre(name);
            nameArmyRad.setEnabled(false);

            nameArmy.setText("Ejército: " + name);
        });

        confirmButton.addActionListener(e -> {
            if (!deleteUnit.isSelected()) {
                if (addInfRad.isSelected()) {
                    ejercito.setOpcion("b");
                    pesoBar.setValue(ejercito.getSaldoPeso());

                    getAboutUnit();
                }

                if (addCabRad.isSelected()) {
                    ejercito.setOpcion("c");
                    pesoBar.setValue(ejercito.getSaldoPeso());

                    getAboutUnit();
                }

                if (addGenRad.isSelected()) {
                    ejercito.setOpcion("d");
                    pesoBar.setValue(ejercito.getSaldoPeso());

                    getAboutUnit();
                }

                if (addElefRad.isSelected()) {
                    ejercito.setOpcion("e");
                    pesoBar.setValue(ejercito.getSaldoPeso());

                    getAboutUnit();
                }

                if (addTirgRad.isSelected()) {
                    ejercito.setOpcion("f");
                    pesoBar.setValue(ejercito.getSaldoPeso());

                    getAboutUnit();
                }

                tableModel.fireTableDataChanged();

                pesoBar.setString(ejercito.getSaldoPeso() + "/" + Ejercito.getMaxPeso());
            } else {
                pesoBar.setValue(ejercito.getSaldoPeso());
                data.removeLast();
            }
        });



    }

    private void initComponents() {
        confirmButton = new JButton();
        pesoBar = new JProgressBar();
        nameArmyRad = new JRadioButton();
        addInfRad = new JRadioButton();
        addCabRad = new JRadioButton();
        addGenRad = new JRadioButton();
        addElefRad = new JRadioButton();
        addTirgRad = new JRadioButton();
        deleteUnit = new JRadioButton();
        data = new Vector<>();
        ejercito = new Ejercito();
    }

    private void getAboutUnit() {
        data.add(new Vector<>(Arrays.asList(
                ejercito.getUnidades().getLast().getNombre(),
                ejercito.getUnidades().getLast().getClass().getSimpleName(),
                ejercito.getUnidades().getLast().getAtaque(),
                ejercito.getUnidades().getLast().getDefensa(),
                ejercito.getUnidades().getLast().getSalud()
        )));
    }
}
