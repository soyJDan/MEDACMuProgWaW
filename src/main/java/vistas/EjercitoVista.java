package vistas;

import batallas.Batalla;
import batallas.Ejercito;
import dao.db4o.HeroeDao;
import models.componentes.personas.Heroe;
import utilidades.Message;
import models.componentes.Componentes;
import models.componentes.personas.General;
import dao.db4o.GeneralDao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/**
 * Clase que representa la vista de la aplicación para la creación de un ejército. Permite al usuario crear un
 * ejército y añadirle unidades. Además, se puede visualizar el ejército creado y las unidades que lo componen.
 * También se puede visualizar el peso total del ejército y el peso máximo permitido. Por último, se puede visualizar
 * el ataque, defensa y salud total del ejército.
 */
public class EjercitoVista extends JFrame {

    /**
     * Panel principal de la vista
     */
    private JPanel panel;

    /**
     * Barra de progreso que muestra el peso del ejército
     */
    private JProgressBar pesoBar;

    /**
     * Etiqueta que muestra el nombre del ejército
     */
    private JLabel nameArmy;

    /**
     * Tabla que muestra las unidades del ejército
     */
    private JTable aboutArmy;

    /**
     * Panel que muestra el ejército
     */
    private JPanel panelArmy;

    /**
     * Etiqueta que muestra el peso total del ejército
     */
    private JRadioButton nameArmyRad;

    /**
     * Botón para añadir una unidad de infantería
     */
    private JRadioButton addInfRad;

    /**
     * Botón para añadir una unidad de caballería
     */
    private JRadioButton addCabRad;

    /**
     * Botón para añadir un elefante
     */
    private JRadioButton addElefRad;

    /**
     * Botón para añadir un tigre
     */
    private JRadioButton addTirgRad;

    /**
     * Botón para eliminar una unidad
     */
    private JRadioButton deleteUnit;

    /**
     * Botón para confirmar la acción
     */
    private JButton confirmButton;

    /**
     * Botón para finalizar la creación del ejército
     */
    private JButton endButton;

    /**
     * Tabla que muestra el ataque, defensa y salud total del ejército
     */
    private JTable totalArmy;

    /**
     * Etiqueta que muestra el peso total del ejército
     */
    private JLabel pesoLabel;

    /**
     * Etiqueta que muestra el ataque, defensa y salud total del ejército
     */
    private JLabel totalLabel;

    /**
     * ComboBox que muestra los generales disponibles
     */
    private JComboBox<String> addGeneralC;
    private JComboBox<String> addHeroeC;

    /**
     * Panel que muestra el ataque, defensa y salud total del ejército
     */
    private JScrollPane panelTotal;

    /**
     * Grupo de botones
     */
    private ButtonGroup buttonGroup;

    /**
     * Vector que almacena los datos de las unidades del ejército
     */
    private Vector<Vector<Object>> data;

    /**
     * Vector que almacena los datos del ejército
     */
    private Vector<Vector<Object>> dataTotal;

    /**
     * Objeto que representa el ejército
     */
    private Ejercito ejercito;

    /**
     * Objeto que representa la batalla
     */
    private static final Batalla batalla = new Batalla();

    /**
     * Objeto que representa el general seleccionado
     */
    private static General generalSeleccionado = new General();
    private static Heroe heroeSeleccionado = new Heroe();

    private static final List<Heroe> heroes = new ArrayList<>();
    private static final List<General> generales = new ArrayList<>();

    private static final List<Heroe> heroesSeleccionados = new ArrayList<>();

    /**
     * Constructor de la clase EjercitoVista. Inicializa los models.score.componentes de la vista y la muestra por pantalla.
     */
    public EjercitoVista() {
        setContentPane(panel);
        setLocation(500, 200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }


    /**
     * Método que inicializa los models.score.componentes de la vista.
     */
    private void createUIComponents() {
        initComponents();
        addGeneralC.setEditable(false);
        addHeroeC.setEditable(false);

        addGeneralC.setEnabled(true);
        addHeroeC.setEnabled(true);

        // Método de agrupación de botones
        setButtonGroup();

        // Método de establecer los parámetros del pesoBar
        setPesoBar();

        if (heroes.isEmpty()) {
            HeroeDao.selectAllHeroes();
            heroes.addAll(HeroeDao.getHeroes());
        }

        if (generales.isEmpty()) {
            GeneralDao.selectAllGeneral();
            generales.addAll(GeneralDao.getGenerales());
        }

        Vector<String> columnNames = new Vector<>(Arrays.asList("Nombre", "Tipo", "Ataque", "Defensa", "Salud"));

        DefaultTableModel tableArmy = new DefaultTableModel(data, columnNames);
        aboutArmy = new JTable(tableArmy);

        Vector<String> columnNamesTotal = new Vector<>(Arrays.asList("Ataque", "Defensa", "Salud"));
        dataTotal.addFirst(new Vector<>(Arrays.asList("Ataque", "Defensa", "Salud")));


        DefaultTableModel tableTotal = new DefaultTableModel(dataTotal, columnNamesTotal);
        totalArmy = new JTable(tableTotal);

        for (Heroe heroe : heroes) {
            addHeroeC.addItem(heroe.getNombre());
        }
        addHeroeC.setSelectedIndex(-1);

        for (General general : generales) {
            addGeneralC.addItem(general.getNombre());
        }
        addGeneralC.setSelectedIndex(-1);

        aboutArmy.setEnabled(false);
        totalArmy.setEnabled(false);

        // --------------------------------------------
        confirmButton.addActionListener(e -> {
            if (!deleteUnit.isSelected()) {
                if (nameArmyRad.isSelected()) {
                    String name = JOptionPane.showInputDialog("Introduce el nombre del ejercito");

                    ejercito.asignarNombre(name);

                    if (ejercito.getResultExecute() == 1) {
                        if (name.isEmpty()) {
                            JOptionPane.showMessageDialog(null, Message.ERROR_NAME_EMPTY);
                        } else {
                            nameArmyRad.setSelected(false);
                            nameArmyRad.setEnabled(false);
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

                if (addGeneralC.getSelectedIndex() != -1) {

                    generalSeleccionado = generales.get(addGeneralC.getSelectedIndex());
                    ejercito.menu("d");
                    pesoBar.setValue(ejercito.getSaldoPeso());
                    addGeneralC.setSelectedIndex(-1);

                    if (ejercito.getResultExecute() == 1) {
                        getAboutUnit();
                        ejercito.setResultExecute(0);
                    }
                }


                if (addHeroeC.getSelectedIndex() != -1) {

                    heroeSeleccionado = heroes.get(addHeroeC.getSelectedIndex());
                    heroesSeleccionados.add(heroeSeleccionado);
                    ejercito.menu("e");
                    pesoBar.setValue(ejercito.getSaldoPeso());
                    addHeroeC.setSelectedIndex(-1);

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
                    deleteUnit.setSelected(false);
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
        // --------------------------------------------

        endButton.addActionListener(e -> {

            ejercito.menu("i");

            generales.remove(generalSeleccionado);

            for (int i = 0; i < generales.size(); i++) {
                if (generales.get(i).getNombre().equals(generalSeleccionado.getNombre())) {
                    addGeneralC.remove(i);
                }
            }

            heroes.removeAll(heroesSeleccionados);

            for (int i = 0; i < heroes.size(); i++) {
                if (heroes.get(i).getNombre().equals(heroeSeleccionado.getNombre())) {
                    addHeroeC.remove(i);
                }
            }

            if (ejercito.getResultExecute() == 1 && !nameArmyRad.isEnabled()) {
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
                    JOptionPane.showMessageDialog(null, Message.EJERCITO_VACIO);
                }

                dispose();
            } else {
                JOptionPane.showMessageDialog(null, Message.EJERCITO_SIN_NOMBRE);
            }
        });
    }

    /**
     * Método que inicializa los models.score.componentes de la vista.
     */
    private void initComponents() {
        confirmButton = new JButton();
        pesoBar = new JProgressBar();
        nameArmyRad = new JRadioButton();
        addInfRad = new JRadioButton();
        addCabRad = new JRadioButton();
        addHeroeC = new JComboBox<>();
        addGeneralC = new JComboBox<>();
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

    /**
     * Método que obtiene los datos de las unidades del ejército.
     */
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

    /**
     * Método que obtiene la batalla.
     *
     * @return batalla
     */
    public static Batalla getBatalla() {
        return batalla;
    }

    public static General getGeneralSeleccionado() {
        return generalSeleccionado;
    }

    public static Heroe getHeroeSeleccionado() {
        return heroeSeleccionado;
    }

    private void setButtonGroup() {
        buttonGroup.add(nameArmyRad);
        buttonGroup.add(addCabRad);
        buttonGroup.add(addInfRad);
        buttonGroup.add(addElefRad);
        buttonGroup.add(addTirgRad);
        buttonGroup.add(deleteUnit);
    }

    private void setPesoBar() {
        pesoBar.setMinimum(0);
        pesoBar.setMaximum(Ejercito.getMaxPeso());
        pesoBar.setStringPainted(true);
        pesoBar.setString(ejercito.getSaldoPeso() + "/" + Ejercito.getMaxPeso());
    }
}
