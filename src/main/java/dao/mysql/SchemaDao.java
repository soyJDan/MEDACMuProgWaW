package dao.mysql;

import io.github.cdimascio.dotenv.Dotenv;
import utilidades.mysql.MySqlConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SchemaDao {

    /**
     * Carga las variables de entorno del archivo .env
     */
    private static final Dotenv dotenv = Dotenv.load();

    public static final String NAME_SCHEMA = dotenv.get("MYSQL_SCHEMA");

    /**
     * Conexión a la base de datos
     */
    private static final Connection connection = MySqlConnection.getConnection();

    public static void createSchema() {
        try (Statement statement = connection.createStatement()) {
            String query = "CREATE SCHEMA IF NOT EXISTS `" + NAME_SCHEMA + "` DEFAULT CHARACTER SET utf8;";
            statement.executeUpdate(query);

            System.out.println("Esquema creado");
            useSchema();
        } catch (SQLException e) {
            System.err.println("Error en la creación del esquema");
            System.err.println(e.getMessage());
        } finally {
            MySqlConnection.closeConnection(connection);
        }
    }

    public static void dropSchema() {
        try (Statement statement = connection.createStatement()) {
            String query = "DROP SCHEMA IF EXISTS `" + NAME_SCHEMA + "`;";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println("Error en la eliminación del esquema");
            System.err.println(e.getMessage());
        } finally {
            MySqlConnection.closeConnection(connection);
        }
    }

    private static void useSchema() {
        try (Statement statement = connection.createStatement()) {
            String query = "USE `" + NAME_SCHEMA + "`;";
            statement.executeUpdate(query);

            System.out.println("Esquema seleccionado");
        } catch (SQLException e) {
            System.err.println("Error en la selección del esquema");
            System.err.println(e.getMessage());
        } finally {
            MySqlConnection.closeConnection(connection);
        }
    }
}