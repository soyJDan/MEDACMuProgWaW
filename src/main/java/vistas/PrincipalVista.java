package vistas;

import com.db4o.ObjectContainer;
import com.db4o.query.Constraint;
import com.db4o.query.Query;
import dao.db4o.CondecoradoDao;
import controladores.ExploradorFicheros;
import dao.db4o.GeneralDao;
import dao.db4o.HeroeDao;
import dao.db4o.TopScoreDao;
import models.componentes.personas.General;
import models.componentes.personas.Heroe;
import models.condecorados.Condecorado;
import models.score.TopScore;
import utilidades.ReaderCsv;
import utilidades.db4o.Db4oConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Clase que crea la vista principal de la aplicación y sus models.score.componentes gráficos. Esta vista es la primera que se
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
    private JButton cargarCondecoradoButton;
    private JTable topScoreTable;
    private JScrollPane scrollBest;

    /**
     * Constructor de la clase. Crea la vista principal y sus models.score.componentes gráficos.
     */
    public PrincipalVista() {
        super("World At War");
        setContentPane(panel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    /**
     * Método que inicializa los models.score.componentes gráficos de la vista y añade los listeners a los botones.
     */
    private void createUIComponents() {
        initComponents();

        CondecoradoDao.selectAllCondecorado();

        cargarCondecoradoButton.setEnabled(CondecoradoDao.getCondecorados().isEmpty());

        TopScoreDao.selectAllTopScore();

        if (TopScoreDao.getTopScores().isEmpty()) {
            System.out.println("No hay TopScores disponibles.");
        } else {
            for (TopScore topScore : TopScoreDao.getTopScores()) {
                System.out.println(topScore);
            }
        }

        Vector<Vector<Object>> data = new Vector<>();
        Vector<Object> columnNames = new Vector<>();

        columnNames.add("ID");
        columnNames.add("Ejército");
        columnNames.add("General");
        columnNames.add("Salud");
        columnNames.add("Fecha");

        for (int i = 0; i < TopScoreDao.getTopScores().size(); i++) {
            Vector<Object> row = new Vector<>();
            row.add(TopScoreDao.getTopScores().get(i).getId());
            row.add(TopScoreDao.getTopScores().get(i).getEjercito().getNombre());
            row.add(TopScoreDao.getTopScores().get(i).getGeneral());
            row.add(TopScoreDao.getTopScores().get(i).getGeneral().getSalud());
            row.add(TopScoreDao.getTopScores().get(i).getFecha());

            data.add(row);
        }

        topScoreTable.setModel(new DefaultTableModel(data, columnNames));

        topScoreTable.setEnabled(false);

        lucharButton.addActionListener(e -> {
            if (CondecoradoDao.getCondecorados().isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se ha cargado un condecorado");
            } else {
                new EjercitoVista();
                dispose();
            }
        });

        cargarCondecoradoButton.addActionListener(e -> {
            ExploradorFicheros.obtenerRuta();
            ReaderCsv.readCsv(ExploradorFicheros.getRuta());

            List<Condecorado> condecorados = getCondecorado();
            CondecoradoDao.insertCondecorado(condecorados);

            getGeneral();
            getHeroe();

            cargarCondecoradoButton.setEnabled(false);
        });
    }

    /**
     * Método que inicializa los models.score.componentes gráficos de la vista.
     */
    private void initComponents() {
        lucharButton = new JButton();
        cargarCondecoradoButton = new JButton();
        topScoreTable = new JTable();
        scrollBest = new JScrollPane();
    }

    private void getGeneral() {
        ObjectContainer db = Db4oConnection.getConnection();
        Query query = db.query();

        query.constrain(Condecorado.class);
        Constraint constraint = query.descend("award").constrain("BRONZE STAR MEDAL");
        Constraint constraint1 = query.descend("gradeMerit").constrain("HEROIC").and(constraint);
        Constraint constraint2 = query.descend("award").constrain("MEDAL OF HONOR").or(constraint1);

        CondecoradoDao.selectByQueryCondecorado(db, query);

        List<General> listTemp = new ArrayList<>();

        for (Condecorado condecorado : CondecoradoDao.getCondecorados()) {
            General general = new General();
            general.setNombre(condecorado.getFirstName() + " " + condecorado.getLastName());

            listTemp.add(general);
        }

        GeneralDao.insertGeneral(listTemp);
    }

    private void getHeroe() {
        ObjectContainer db = Db4oConnection.getConnection();
        Query query = db.query();

        query.constrain(Condecorado.class);
        Constraint constraint = query.descend("rank").constrain("ENLISTED");

        CondecoradoDao.selectByQueryCondecorado(db, query);

        List<Heroe> listTemp = new ArrayList<>();

        for (Condecorado condecorado : CondecoradoDao.getCondecorados()) {
            Heroe heroe = new Heroe();
            heroe.setNombre(condecorado.getFirstName() + " " + condecorado.getLastName());

            listTemp.add(heroe);
        }

        HeroeDao.insertHeroe(listTemp);
    }


    private List<Condecorado> getCondecorado() {
        List<Condecorado> listTemp = new ArrayList<>();

        for (List<String> list : ReaderCsv.getList()) {
            Condecorado condecorado = new Condecorado();
            condecorado.setLastName(list.get(0));
            condecorado.setFirstName(list.get(1));
            condecorado.setRank(list.get(2));
            condecorado.setGradeMerit(list.get(3));
            condecorado.setAward(list.get(4));

            listTemp.add(condecorado);
        }

        return listTemp;
    }
}
