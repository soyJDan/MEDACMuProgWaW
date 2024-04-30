package dao;

import batallas.Ejercito;
import models.score.TopScore;
import utilidades.MySqlConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TopScoreDao {

    private static List<TopScore> topScores = new ArrayList<>();

    private static String nameTable;

    public static void createTable(String name) {
        try (Connection connection = MySqlConnection.getConnection();
             Statement statement = connection.createStatement()) {
            String query = "CREATE TABLE IF NOT EXISTS `" + name + "` ("
                    + "ID INT AUTO_INCREMENT PRIMARY KEY,"
                    + "EJERCITO VARCHAR(100) NOT NULL,"
                    + "GENERAL FOREIGN KEY  NOT NULL,"
                    + "FECHA DATE NOT NULL"
                    + ");";

            nameTable = name;
            statement.executeUpdate(query);

            System.out.println("Tabla creada");
            System.out.println("Conexión cerrada");
        } catch (SQLException e) {
            System.err.println("Error en la creación de la tabla");
            System.err.println(e.getMessage());
        }
    }

    public static void dropTable(String name) {
        try (Connection connection = MySqlConnection.getConnection();
             Statement statement = connection.createStatement()) {
            String query = "DROP TABLE " + name + ";";
            statement.executeUpdate(query);

            System.out.println("Tabla eliminada");
            System.out.println("Conexión cerrada");
        } catch (SQLException e) {
            System.err.println("Error en la eliminación de la tabla");
            System.err.println(e.getMessage());
        }
    }

    public static void insertTopScore(TopScore topScore) {
        if (nameTable == null) {
            System.err.println("No se ha creado la tabla");
            return;
        } else {
            try (Connection connection = MySqlConnection.getConnection();
                 Statement statement = connection.createStatement()) {
                String query = "INSERT INTO " + nameTable + " (EJERCITO, FECHA) VALUES ('" + topScore.getEjercito().getNombre() + "', '" + topScore.getFecha() + "');";
                statement.executeUpdate(query);
                System.out.println("TopScore insertado");
                System.out.println("Conexión cerrada");
            } catch (SQLException e) {
                System.err.println("Error en la inserción del TopScore");
                System.err.println(e.getMessage());
            }
        }
    }

    public static void selectTopScores() {
        try (Connection connection = MySqlConnection.getConnection();
             Statement statement = connection.createStatement()) {
            String query = "SELECT * FROM " + nameTable + ";";
            statement.executeQuery(query);
            System.out.println("TopScores seleccionados");
            System.out.println("Conexión cerrada");
        } catch (SQLException e) {
            System.err.println("Error en la selección de los TopScores");
            System.err.println(e.getMessage());
        }
    }

    public static void selectBestTopScore(int limit) {
        try (Connection connection = MySqlConnection.getConnection();
             Statement statement = connection.createStatement()) {
            String query = "SELECT * FROM " + nameTable + " ORDER BY FECHA DESC LIMIT " + limit + ";";
            statement.executeQuery(query);

            Ejercito ejercito = new Ejercito();

            while (statement.getResultSet().next()) {
                TopScore topScore = new TopScore();
                topScore.setId(statement.getResultSet().getLong("ID"));
                ejercito.asignarNombre(statement.getResultSet().getString("EJERCITO"));
                topScore.setFecha(statement.getResultSet().getDate("FECHA"));
                topScore.setEjercito(ejercito);
                topScores.add(topScore);
            }

            System.out.println("Mejor TopScore seleccionado");
            System.out.println("Conexión cerrada");
        } catch (SQLException e) {
            System.err.println("Error en la selección del mejor TopScore");
            System.err.println(e.getMessage());
        }
    }

    public static List<TopScore> getTopScores() {
        return topScores;
    }
}
