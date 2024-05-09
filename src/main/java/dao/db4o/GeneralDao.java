package dao.db4o;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.Db4oException;
import com.db4o.query.Constraint;
import com.db4o.query.Query;
import models.componentes.personas.General;
import utilidades.Db4oConnection;

import java.util.ArrayList;
import java.util.List;

public class GeneralDao {

    private static List<General> generalList = new ArrayList<>();

    /**
     *
     * @param general
     */
    public static void insertGeneral(General general) {
        try {
            ObjectContainer db = Db4oConnection.getConnection();
            db.store(general);
            db.commit();

            System.out.println("General insertado");
            Db4oConnection.closeConnection(db);
            System.out.println("Conexión cerrada");
        } catch (Db4oException e) {
            System.err.println("Error en la inserción del general");
            System.err.println(e.getMessage());
        }
    }

    public static void deleteGeneral(General general) {
        try {
            ObjectContainer db = Db4oConnection.getConnection();
            db.delete(general);
            db.commit();

            System.out.println("General insertado");
            Db4oConnection.closeConnection(db);
            System.out.println("Conexión cerrada");
        } catch (Db4oException e){
            System.err.println("Error en la inserción del general");
            System.err.println(e.getMessage());
        }
    }


    public static void selectAllGeneral() {
        try {
            ObjectContainer db = Db4oConnection.getConnection();

            General general = new General(0, null, 0, 0, 0, 0);
            ObjectSet<General> objectSet = db.queryByExample(general);

            while (objectSet.hasNext()) {
                generalList.add(objectSet.next());
            }

            Db4oConnection.closeConnection(db);
            System.out.println("Conexión cerrada");
        } catch (Db4oException e){
            System.err.println("Error en la inserción del general");
            System.err.println(e.getMessage());
        }
    }

    public static void selectGeneral(long id) {
        try {
            ObjectContainer db = Db4oConnection.getConnection();

            Query query = db.query();

            query.constrain(General.class);
            Constraint constraint = query.descend("id").constrain(id);

            ObjectSet<General> objectSet = db.queryByExample(constraint);

            while (objectSet.hasNext()) {
                generalList.add(objectSet.next());
            }

            Db4oConnection.closeConnection(db);
            System.out.println("Conexión cerrada");
        } catch (Db4oException e){
            System.err.println("Error en la inserción del general");
            System.err.println(e.getMessage());
        }
    }
}
