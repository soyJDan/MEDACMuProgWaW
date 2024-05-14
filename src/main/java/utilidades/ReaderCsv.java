package utilidades;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class ReaderCsv {

    private static final List<List<String>> list = new ArrayList<>();

    private ReaderCsv() {
        throw new IllegalStateException("Utility class");
    }

    public static void readCsv(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");

                List<String> listValues = new ArrayList<>();

                listValues.add(values[0]);
                listValues.add(values[1]);
                listValues.add(values[5]);
                listValues.add(values[13]);
                listValues.add(values[23]);

                list.add(listValues);
            }

            list.removeFirst();
        } catch (IOException e) {
            System.err.println("Error reading the file");
            System.err.println(e.getMessage());
        }
    }

    public static List<List<String>> getList() {
        return list;
    }
}