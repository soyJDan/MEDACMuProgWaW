package vistas;

import batallas.Batalla;
import batallas.Ejercito;
import batallas.Message;
import componentes.Componentes;
import componentes.animales.Elefante;
import componentes.animales.Tigre;
import componentes.personas.General;
import controladores.ExploradorFicheros;
import controladores.GestorFichero;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class EjercitoVista extends JFrame {
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
    private JButton endButton;
    private JTable totalArmy;
    private JLabel pesoLabel;
    private JLabel totalLabel;
    private JScrollPane panelTotal;
    private ButtonGroup buttonGroup;

    private Vector<Vector<Object>> data;
    private Vector<Vector<Object>> dataTotal;
    private Ejercito ejercito;

    private static final Batalla batalla = new Batalla();

    public EjercitoVista() {
        setContentPane(panel);
        setLocation(500, 200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public EjercitoVista(boolean isBatalla) {
    }

    public static void main(String[] args) {
        new EjercitoVista();
    }

    private void createUIComponents() {
        initComponents();

        buttonGroup.add(nameArmyRad);
        buttonGroup.add(addCabRad);
        buttonGroup.add(addInfRad);
        buttonGroup.add(addGenRad);
        buttonGroup.add(addElefRad);
        buttonGroup.add(addTirgRad);
        buttonGroup.add(deleteUnit);

        pesoBar.setMinimum(0);
        pesoBar.setMaximum(Ejercito.getMaxPeso());
        pesoBar.setStringPainted(true);
        pesoBar.setString(ejercito.getSaldoPeso() + "/" + Ejercito.getMaxPeso());

        Vector<String> columnNames = new Vector<>(Arrays.asList("Nombre", "Tipo", "Ataque", "Defensa", "Salud"));

        DefaultTableModel tableArmy = new DefaultTableModel(data, columnNames);
        aboutArmy = new JTable(tableArmy);

        Vector<String> columnNamesTotal = new Vector<>(Arrays.asList("Ataque", "Defensa", "Salud"));
        dataTotal.addFirst(new Vector<>(Arrays.asList("Ataque", "Defensa", "Salud")));


        DefaultTableModel tableTotal = new DefaultTableModel(dataTotal, columnNamesTotal);
        totalArmy = new JTable(tableTotal);

        confirmButton.addActionListener(e -> {
            if (!deleteUnit.isSelected()) {
                if (nameArmyRad.isSelected()) {
                    String name = JOptionPane.showInputDialog("Introduce el nombre del ejercito");

                    ejercito.asignarNombre(name);

                    if (ejercito.getResultExecute() == 1) {
                        if (name.isEmpty()) {
                            JOptionPane.showMessageDialog(null, Message.ERROR_NAME_EMPTY);
                        } else {
                            nameArmyRad.setEnabled(false);
                            nameArmyRad.setSelected(false);
                            nameArmy.setText("Ejército: " + name);
                        }
                    }
                }

                if (addInfRad.isSelected()) {
                    ejercito.menu("b");
                    pesoBar.setValue(ejercito.getSaldoPeso());

                    if (ejercito.getResultExecute() == 1) {
                        getAboutUnit();
                        ejercito.setResultExecute(0);
                    }
                }

                if (addCabRad.isSelected()) {
                    ejercito.menu("c");
                    pesoBar.setValue(ejercito.getSaldoPeso());

                    if (ejercito.getResultExecute() == 1) {
                        getAboutUnit();
                        ejercito.setResultExecute(0);
                    }
                }

                if (addGenRad.isSelected()) {
                    ejercito.menu("d");
                    pesoBar.setValue(ejercito.getSaldoPeso());

                    if (ejercito.getResultExecute() == 1) {
                        getAboutUnit();
                        ejercito.setResultExecute(0);
                    }

                }

                if (addElefRad.isSelected()) {
                    ejercito.menu("e");
                    pesoBar.setValue(ejercito.getSaldoPeso());

                    if (ejercito.getResultExecute() == 1) {
                        getAboutUnit();
                        ejercito.setResultExecute(0);
                    }
                }

                if (addTirgRad.isSelected()) {
                    ejercito.menu("f");
                    pesoBar.setValue(ejercito.getSaldoPeso());

                    if (ejercito.getResultExecute() == 1) {
                        getAboutUnit();
                        ejercito.setResultExecute(0);
                    }
                }

                pesoBar.setString(ejercito.getSaldoPeso() + "/" + Ejercito.getMaxPeso());
            } else {
                if (ejercito.getUnidades().isEmpty()) {
                    JOptionPane.showMessageDialog(null, Message.EJERCITO_VACIO);
                } else {
                    String nameUnit = JOptionPane.showInputDialog("Introduce el nombre del ejercito");

                    ejercito.eliminarUnidad(nameUnit);
                    pesoBar.setValue(ejercito.getSaldoPeso());

                    getAboutUnit();
                }
            }

            ejercito.actualizarEjercito();

            if (dataTotal.size() > 1) {
                dataTotal.remove(1);
            }

            dataTotal.insertElementAt(new Vector<>(Arrays.asList(ejercito.getAtaque(),
                    ejercito.getDefensa(), ejercito.getSalud())), 1);

            tableArmy.fireTableDataChanged();
            tableTotal.fireTableDataChanged();
        });

        endButton.addActionListener(e -> {

            ejercito.menu("i");

            if (ejercito.getResultExecute() == 1 && ejercito.getNombre() != null) {
                if (batalla.getEjercito1().getUnidades().isEmpty()) {
                    batalla.setEjercito1(ejercito);
                    System.out.println(ejercito);

                    new EjercitoVista();
                } else if (batalla.getEjercito2().getUnidades().isEmpty()) {
                    batalla.setEjercito2(ejercito);
                    batalla.luchar();

                    new BatallaVista();

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        System.out.println(ex.getMessage());
                    }
                } else {
                    if (ejercito.getNombre() == null) {
                        JOptionPane.showMessageDialog(null, Message.EJERCITO_SIN_NOMBRE);
                    } else {
                        JOptionPane.showMessageDialog(null, Message.EJERCITO_VACIO);
                    }
                }

                dispose();

                try {
                    GestorFichero.obtenerNombreGeneral(ExploradorFicheros.getRuta());
                } catch (IOException ex) {
                    System.out.printf(ex.getMessage());
                }
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
        endButton = new JButton();
        buttonGroup = new ButtonGroup();
        panelTotal = new JScrollPane();
        data = new Vector<>();
        dataTotal = new Vector<>();
        ejercito = new Ejercito();
    }

    private void getAboutUnit() {
        data.clear();

        for (Componentes componente : ejercito.getUnidades()) {
            data.add(new Vector<>(Arrays.asList(
                    componente.getNombre(),
                    componente.getClass().getSimpleName(),
                    componente.getAtaque(),
                    componente.getDefensa(),
                    componente.getSalud()
            )));
        }
    }

    public static Batalla getBatalla() {
        return batalla;
    }
}
