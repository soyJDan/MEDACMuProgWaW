package dao;

import componentes.personas.General;
import utilidades.MySqlConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GeneralDao {


    private static List<General> generales = new ArrayList<>();

    private static String nameTable;

    public static void createTable(String name) {
        try (Connection connection = MySqlConnection.getConnection();
             Statement statement = connection.createStatement()) {
            String query = "CREATE TABLE IF NOT EXISTS `" + name + "` ("
                    + "ID INT AUTO_INCREMENT PRIMARY KEY,"
                    + "NAME VARCHAR(100) NOT NULL,"
                    + "HEALTH INT NOT NULL,"
                    + "ATTACK INT NOT NULL,"
                    + "DEFEND INT NOT NULL,"
                    + "WEIGHT INT NOT NULL"
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

    public static void insertGeneral(General general) {
        if (nameTable == null) {
            System.err.println("No se ha creado la tabla");
            return;
        } else {
            try (Connection connection = MySqlConnection.getConnection();
                 Statement statement = connection.createStatement()) {
                String query = "INSERT INTO GENERAL(NAME, HEALTH, ATTACK, DEFEND, WEIGHT) VALUES("
                        + "'" + general.getNombre() + "', "
                        + general.getSalud() + ", "
                        + general.getAtaque() + ", "
                        + general.getDefensa() + ", "
                        + General.PESO_GENERAL
                        + ");";

                statement.executeUpdate(query);

                System.out.println("Inserción realizada");
                System.out.println("Conexión cerrada");
            } catch (SQLException e) {
                System.err.println("Error en la inserción");
                System.err.println(e.getMessage());
            }
        }
    }

    public static void updateGeneral(General general, long id) {
        if (nameTable == null) {
            System.err.println("No se ha creado la tabla");
            return;
        } else {
            try (Connection connection = MySqlConnection.getConnection();
                 Statement statement = connection.createStatement()) {
                String query = "UPDATE " + nameTable + " SET "
                        + "NAME = '" + general.getNombre() + "', "
                        + "HEALTH = " + general.getSalud() + ", "
                        + "ATTACK = " + general.getAtaque() + ", "
                        + "DEFEND = " + general.getDefensa() + ", "
                        + "WEIGHT = " + General.PESO_GENERAL + " "
                        + "WHERE ID = " + id + ";";

                statement.executeUpdate(query);

                System.out.println("Se ha actualizado el registro con ID: " + id + " de la tabla " + nameTable);
                System.out.println("Conexión cerrada");
            } catch (SQLException e) {
                System.err.println("Error en la actualización");
                System.err.println(e.getMessage());
            }
        }
    }

    public static void deleteGeneral(long id) {

        if (nameTable == null) {
            System.err.println("No se ha creado la tabla");
            return;
        } else {
            try (Connection connection = MySqlConnection.getConnection();
                 Statement statement = connection.createStatement()) {
                String query = "DELETE FROM " + nameTable + " WHERE ID = " + id + ";";
                statement.executeUpdate(query);

                System.out.println("Se ha eliminado el registro con ID: " + id + " de la tabla " + nameTable);
                System.out.println("Conexión cerrada");
            } catch (SQLException e) {
                System.err.println("Error en la eliminación");
                System.err.println(e.getMessage());
            }
        }
    }

    public static void selectGeneral() {

        if (nameTable == null) {
            System.err.println("No se ha creado la tabla");
            return;
        } else {
            try (Connection connection = MySqlConnection.getConnection();
                 Statement statement = connection.createStatement()) {
                String query = "SELECT * FROM " + nameTable + ";";
                statement.executeQuery(query);

                while (statement.getResultSet().next()) {
                    General general = new General();
                    general.setNombre(statement.getResultSet().getString("NAME"));
                    general.setSalud(statement.getResultSet().getInt("HEALTH"));
                    general.setAtaque(statement.getResultSet().getInt("ATTACK"));
                    general.setDefensa(statement.getResultSet().getInt("DEFEND"));
                    generales.add(general);
                }

                System.out.println("Selección realizada");
                System.out.println("Conexión cerrada");
            } catch (SQLException e) {
                System.err.println("Error en la selección");
                System.err.println(e.getMessage());
            }
        }
    }

    public static String getNameTable() {
        return nameTable;
    }

    public static List<General> getGenerales() {
        return generales;
    }
}
