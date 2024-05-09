package utilidades;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ext.Db4oException;
import io.github.cdimascio.dotenv.Dotenv;

/**
 * Clase que gestiona la conexión con la base de datos de db4o.
 */
public class Db4oConnection {

    /**
     * Carga las variables de entorno del archivo .env
     */
    private static final Dotenv dotenv = Dotenv.load();

    /**
     * Nombre del fichero de la base de datos
     */
    private static final String DB4O_FILE = dotenv.get("DB4O_FILE");

    /**
     * Constructor privado para evitar instanciación. Clase de utilidad.
     */
    private Db4oConnection() {
        throw new IllegalStateException("Utility class");
    }

    public static ObjectContainer getConnection() {
        try {
            Class.forName("com.db4o.Db4o");
        } catch (ClassNotFoundException e) {
            System.err.println("Error en el driver. No se encuentra la clase");
            System.err.println(e.getMessage());
        }

        try {
            return Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DB4O_FILE);
        } catch (Db4oException e) {
            System.err.println("Error en la conexión con el driver");
            System.err.println(e.getMessage());
            return null;
        }
    }

    public static void closeConnection(ObjectContainer db) {
        try {
            db.close();
        } catch (Db4oException e) {
            System.err.println("Error cerrando la conexión");
            System.err.println(e.getMessage());
        }
    }
}
