package utilidades.mysql;

import dao.mysql.SchemaDao;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class MySqlConnection {

    /**
     * Carga las variables de entorno del archivo .env
     */
    private static final Dotenv dotenv = Dotenv.load();

    /**
     * Usuario de la base de datos
     */
    private static final String USER = dotenv.get("MYSQL_USER");

    /**
     * Contraseña de la base de datos
     */
    private static final String PASSWORD = dotenv.get("MYSQL_PASSWORD");

    /**
     * URL de conexión a la base de datos
     */
    private static final String URL_CONNECTION = dotenv.get("MYSQL_URL");

    /**
     * Constructor privado para evitar instanciación. Clase de utilidad.
     */
    private MySqlConnection() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Método para obtener la conexión a la base de datos.
     *
     * <p>Se obtiene la URL de conexión de las variables de entorno.</p>
     *
     * @return Connection
     */
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Registramos el Driver
        } catch (ClassNotFoundException e) {
            System.err.println("Error en el driver. No se encuentra la clase");
            System.err.println(e.getMessage());
        }

        try {
            return DriverManager.getConnection(URL_CONNECTION + "/" + SchemaDao.NAME_SCHEMA, USER, PASSWORD); // Devolver Connection
        } catch (SQLException e) {
            System.err.println("Error en la conexión con el driver");
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * Cierra la conexión con la base de datos.
     * @param connection Conexión a cerrar.
     */
    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println("Error cerrando la conexión");
            System.err.println(e.getMessage());
        }
    }
}
