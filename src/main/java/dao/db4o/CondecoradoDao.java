package dao.db4o;

import com.db4o.ObjectContainer;
import com.db4o.ext.Db4oException;
import models.condecorados.Condecorado;
import utilidades.db4o.Db4oConnection;

public class CondecoradoDao {

    public static void insertCondecorado(Condecorado condecorado) {
        try {
            ObjectContainer db = Db4oConnection.getConnection();

            db.store(condecorado);
            db.commit();

            System.out.println("General insertado");

            Db4oConnection.closeConnection(db);
            System.out.println("Conexión cerrada");
        } catch (Db4oException e) {
            System.err.println("Error en la inserción del general");
            System.err.println(e.getMessage());
        }
    }
}
