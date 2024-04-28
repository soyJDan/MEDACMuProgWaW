package controladores;

import batallas.Batalla;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GestorFichero {

    private static final Random random = new Random();
    private String rutaFichero;
    private BufferedReader br;
    private static List<String> nombreGeneral = new ArrayList<>();
    private static String nombreDeGeneral;

    private GestorFichero() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * @param rutaFichero Ruta del fichero que se quiere leer
     */
    public static void obtenerNombreGeneral(String rutaFichero) {
        try (FileReader fileReader = new FileReader(new File(rutaFichero));
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
                nombreGeneral.add(linea);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el fichero de nombres de generales.");
        }
    }

    public static void writeXmlBatalla(Batalla batalla, String nameFile) {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element root = document.createElement(batalla.getClass().getSimpleName());
            document.appendChild(root);
            Field[] fields = batalla.getClass().getDeclaredFields();
            Field[] var8 = fields;
            int var9 = fields.length;

            for(int var10 = 0; var10 < var9; ++var10) {
                Field field = var8[var10];
                field.setAccessible(true);
                Object value = field.get(batalla);
                Element fieldElement = document.createElement(field.getName());
                fieldElement.appendChild(document.createTextNode(value.toString()));
                root.appendChild(fieldElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", 2);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty("indent", "yes");
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(nameFile + ".xml"));
            transformer.transform(domSource, streamResult);
            System.out.println("Serialized data is saved in " + nameFile + ".xml");
        } catch (Exception var14) {
            Exception e = var14;
            e.getMessage();
        }

    }

    public static String getNombreDeGeneral() {
        return nombreDeGeneral;
    }

    public static List<String> getNombreGeneral() {
        return nombreGeneral;
    }
}
