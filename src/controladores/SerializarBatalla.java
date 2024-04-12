package controladores;

import batallas.Batalla;
import batallas.Ejercito;

import java.io.*;

/**
 * Clase que serializa y deserializa una batalla.
 */
public class SerializarBatalla {

    /**
     * Método que serializa una batalla en un fichero binario. El fichero se crea en la ruta especificada. Si el fichero ya existe, se sobreescribe.
     * @param batalla Batalla a serializar
     * @param archivoEjercito Ruta del fichero en el que se serializa la batalla
     * @throws IOException Si ocurre un error al escribir en el fichero
     */
    public static void serializar(Batalla batalla, String archivoEjercito) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivoEjercito))) {
            out.writeObject(batalla);
        }

    }

    /**
     * Método que deserializa una batalla de un fichero binario. La batalla se deserializa del fichero especificado.
     * @param archivoEjercito Ruta del fichero del que se deserializa la batalla
     * @return Batalla deserializada
     * @throws IOException Si ocurre un error al leer del fichero
     * @throws ClassNotFoundException Si la clase de la batalla no se encuentra
     */
    public static Ejercito deserializar(String archivoEjercito) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivoEjercito))) {
            return (Ejercito) in.readObject();
        }
    }
}
