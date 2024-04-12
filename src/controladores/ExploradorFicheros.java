package controladores;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExploradorFicheros {

    private static final JFileChooser chooser = new JFileChooser();

    private static String ruta;

    public static String obtenerRuta() {
        if (chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION) {
            try {
                ruta = chooser.getSelectedFile().getAbsolutePath();
            } catch (NullPointerException e) {
                System.out.println("No se ha seleccionado ningún fichero");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return ruta;
    }

    public static String leerFichero(String path) {

        StringBuilder contenido = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }

        return contenido.toString();
    }

    public static String getRuta() {
        return ruta;
    }
}