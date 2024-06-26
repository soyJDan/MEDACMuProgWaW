package dao.db4o;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.Db4oException;
import com.db4o.query.Query;
import models.componentes.personas.General;
import utilidades.Message;
import utilidades.db4o.Db4oConnection;

import java.util.ArrayList;
import java.util.List;

/**
 * GeneralDao class to make the CRUD operations in the database with the generals
 * using db4o connection and queries to select the generals in the database
 * and store the result in the generales list.
 *
 * @version 1.0
 * @since 1.0
 * @see General
 * @see Message
 * @see Db4oConnection
 *
 * @author Daniel Romero
 */
public class GeneralDao {

    /**
     * Db4o connection to connect with the database
     */
    private static ObjectContainer db;

    /**
     * List of generals to store the result of the queries in the database
     */
    private static final List<General> generales = new ArrayList<>();

    /**
     * Utility class to avoid the instantiation of the class and use the methods directly
     * with the class name and dot operator to access the methods.
     */
    private GeneralDao() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Insert a general in the database using db4o connection and commit the
     * transaction after the insertion is done successfully
     *
     * @param general general to insert in the database
     */
    public static void insertGeneral(General general) {
        try {
            db = Db4oConnection.getConnection();

            if (db != null)  {
                db.store(general);
                db.commit();
            } else {
                System.err.println(Message.ERROR_DB_CONNECTION);
            }

            System.out.println("Insertion of general successful");

        } catch (Db4oException e) {
            System.err.println("Error in the insertion of the general");
            System.err.println(e.getMessage());
        } finally {
            if (db != null) {
                Db4oConnection.closeConnection(db);
                System.out.println(Message.CONNECTION_CLOSED);
            } else {
                System.err.println(Message.ERROR_CLOSING_CONNECTION);
            }
        }
    }

    /**
     * Insert a list of generals in the database using db4o connection and commit
     * the transaction after the insertion of all the generals in the list is
     * done successfully
     *
     * @param generales list of generals to insert in the database
     */
    public static void insertGeneral(List<General> generales) {
        try {
            db = Db4oConnection.getConnection();

            if (generales.isEmpty()) {
                System.out.println("The list of generals is empty");
                return;
            }

            if (db != null)  {
                for (General general : generales) {
                    db.store(general);
                }

                db.commit();
            } else {
                System.err.println(Message.ERROR_DB_CONNECTION);
            }

            System.out.println("Insertion of generals successful");
        } catch (Db4oException e) {
            System.err.println("Error in the insertion of the generals");
            System.err.println(e.getMessage());
        } finally {
            if (db != null) {
                Db4oConnection.closeConnection(db);
                System.out.println(Message.CONNECTION_CLOSED);
            } else {
                System.err.println(Message.ERROR_CLOSING_CONNECTION);
            }
        }
    }

    /**
     * Delete a general in the database using db4o connection and commit the
     * transaction after the deletion is done successfully
     *
     * @param general general to delete in the database
     */
    public static void deleteGeneral(General general) {
        try {
            db = Db4oConnection.getConnection();

            if (db != null)  {
                db.delete(general);
                db.commit();
            } else {
                System.err.println(Message.ERROR_DB_CONNECTION);
            }

            System.out.println("Deletion of general successful");
        } catch (Db4oException e) {
            System.err.println("Error in the deletion of the general");
            System.err.println(e.getMessage());
        } finally {
            if (db != null) {
                Db4oConnection.closeConnection(db);
                System.out.println(Message.CONNECTION_CLOSED);
            } else {
                System.err.println(Message.ERROR_CLOSING_CONNECTION);
            }
        }
    }

    /**
     * Delete a list of generals in the database using db4o connection and commit
     * the transaction after the deletion of all the generals in the list is
     * done successfully
     *
     * @param generales list of generals to delete in the database
     */
    public static void deleteGeneral(List<General> generales) {
        try {
            db = Db4oConnection.getConnection();

            if (db != null)  {
                for (General general : generales) {
                    db.delete(general);
                }

                db.commit();
            } else {
                System.err.println(Message.ERROR_DB_CONNECTION);
            }

            System.out.println("Deletion of generals successful");
        } catch (Db4oException e) {
            System.err.println("Error in the deletion of the generals");
            System.err.println(e.getMessage());
        } finally {
            if (db != null) {
                Db4oConnection.closeConnection(db);
                System.out.println(Message.CONNECTION_CLOSED);
            } else {
                System.err.println(Message.ERROR_CLOSING_CONNECTION);
            }
        }
    }

    /**
     * Select a general in the database using db4o connection and commit the transaction
     * after the selection is done successfully and store the result in the generales list.
     *
     * @param general general to select in the database
     */
    public static void selectGeneral(General general) {
        try {
            db = Db4oConnection.getConnection();

            ObjectSet<General> result;
            if (db != null) {
                result = db.queryByExample(general);
            } else {
                System.err.println(Message.ERROR_DB_CONNECTION);
                return;
            }

            generales.clear();

            if (result.hasNext()) {
                General generalResult = result.next();
                generales.add(generalResult);
            } else {
                System.out.println("Not found general in the database");
            }
        } catch (Db4oException e) {
            System.err.println("Error in the search of the general");
            System.err.println(e.getMessage());
        } finally {
            if (db != null) {
                Db4oConnection.closeConnection(db);
                System.out.println(Message.CONNECTION_CLOSED);
            } else {
                System.err.println(Message.ERROR_CLOSING_CONNECTION);
            }
        }
    }

    /**
     * Select all generals in the database using db4o connection and commit the transaction
     * after the selection is done successfully and store the result in the generales list.
     */
    public static void selectAllGeneral() {
        try {
            db = Db4oConnection.getConnection();

            ObjectSet<General> result;
            if (db != null) {
                result = db.queryByExample(General.class);
            } else {
                System.err.println(Message.ERROR_DB_CONNECTION);
                return;
            }

            generales.clear();

            if (result.isEmpty()) {
                System.out.println("Not found generals in the database");
            } else {
                while (result.hasNext()) {
                    General general = result.next();
                    generales.add(general);
                }
            }
        } catch (Db4oException e) {
            System.err.println("Error in the search of the generals");
            System.err.println(e.getMessage());
        } finally {
            if (db != null) {
                Db4oConnection.closeConnection(db);
                System.out.println(Message.CONNECTION_CLOSED);
            } else {
                System.err.println(Message.ERROR_CLOSING_CONNECTION);
            }
        }
    }

    /**
     * Select a general in the database using db4o connection and commit the transaction
     * after the selection is done successfully and store the result in the generales list.
     *
     * @param connection db4o connection
     * @param query query to select a general in the database
     */
    public static void selectByQueryGeneral(ObjectContainer connection, Query query) {
        try {
            db = connection;

            ObjectSet<General> result;
            if (db != null && query != null) {
                result = query.execute();
            } else {
                System.err.println(Message.ERROR_DB_CONNECTION);
                return;
            }

            generales.clear();

            if (result.isEmpty()) {
                System.out.println("No hay generales en la base de datos");
            } else {
                while (result.hasNext()) {
                    General general = result.next();
                    generales.add(general);
                }
            }
        } catch (Db4oException e) {
            System.err.println("Error en la búsqueda de generales");
            System.err.println(e.getMessage());
        } finally {
            if (db != null) {
                Db4oConnection.closeConnection(db);
                System.out.println(Message.CONNECTION_CLOSED);
            } else {
                System.err.println(Message.ERROR_CLOSING_CONNECTION);
            }
        }
    }

    /**
     * Get the generales list
     *
     * @return generales list
     */
    public static List<General> getGenerales() {
        return generales;
    }
}