package dao.db4o;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.Db4oException;
import com.db4o.query.Query;
import models.condecorados.Condecorado;
import utilidades.Message;
import utilidades.db4o.Db4oConnection;

import java.util.*;
import java.util.stream.Collectors;

/**
 * CondecoradoDao class to make the CRUD operations in the database with the condecorados
 * using db4o connection and queries to select the condecorados in the database
 * and store the result in the condecorados list.
 *
 * @version 1.0
 * @since 1.0
 * @see Condecorado
 * @see Message
 * @see Db4oConnection
 *
 * @author Daniel Romero
 */
public class CondecoradoDao {

    /**
     * Db4o connection to connect with the database
     */
    private static ObjectContainer db;

    /**
     * List of condecorados to store the result of the selection of condecorados
     */
    private static final List<Condecorado> condecorados = new ArrayList<>();

    /**
     * Utility class to avoid the instantiation of the class and use the methods directly
     * with the class name and dot operator to access the methods.
     */
    private CondecoradoDao() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Insert a condecorado in the database using db4o connection and commit the
     * transaction after the insertion is done successfully
     *
     * @param condecorado condecorado to insert in the database
     */
    public static void insertCondecorado(Condecorado condecorado) {
        try {
            db = Db4oConnection.getConnection();

            if (db != null) {
                db.store(condecorado);
                db.commit();
            } else {
                System.err.println(Message.ERROR_DB_CONNECTION);
            }

            System.out.println("Insertion of condecorado successful");

        } catch (Db4oException e) {
            System.err.println("Error in the insertion of the condecorado");
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
     * Insert a list of condecorados in the database using db4o connection and commit
     * the transaction after the insertion of all the condecorados in the list is
     * done successfully
     *
     * @param condecorados list of condecorados to insert in the database
     */
    public static void insertCondecorado(List<Condecorado> condecorados) {
        try {
            db = Db4oConnection.getConnection();

            if (condecorados.isEmpty()) {
                System.out.println("The list is empty");
                return;
            }

            if (db != null) {
                for (Condecorado condecorado : condecorados) {
                    db.store(condecorado);
                }

                db.commit();
            } else {
                System.err.println(Message.ERROR_DB_CONNECTION);
            }

            System.out.println("Insertion of condecorados successful");
        } catch (Db4oException e) {
            System.err.println("Error in the insertion of the condecorado");
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
     * Delete a condecorado in the database using db4o connection and commit the
     * transaction after the deletion is done successfully
     *
     * @param condecorado condecorado to delete in the database
     */
    public static void deleteCondecorado(Condecorado condecorado) {
        try {
            db = Db4oConnection.getConnection();

            if (db != null) {
                db.delete(condecorado);
                db.commit();
            } else {
                System.err.println(Message.ERROR_DB_CONNECTION);
            }

            System.out.println("Delete condecorado successful");
        } catch (Db4oException e) {
            System.err.println("Error in the deletion of the condecorado");
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
     * Delete a list of condecorados in the database using db4o connection and commit
     * the transaction after the deletion of all the condecorados in the list is
     * done successfully
     *
     * @param condecorados list of condecorados to delete in the database
     */
    public static void deleteCondecorado(List<Condecorado> condecorados) {
        try {
            db = Db4oConnection.getConnection();

            if (condecorados.isEmpty()) {
                System.out.println("The list is empty");
                return;
            }

            if (db != null) {
                for (Condecorado condecorado : condecorados) {
                    db.delete(condecorado);
                }

                db.commit();
            } else {
                System.err.println(Message.ERROR_DB_CONNECTION);
            }

            System.out.println("Delete condecorados successful");
        } catch (Db4oException e) {
            System.err.println("Error in the deletion of the condecorado");
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
     * Select a condecorado in the database using db4o connection and commit the transaction
     * after the selection is done successfully and store the result in the condecorados list.
     *
     * @param condecorado condecorado to select in the database
     */
    public static void selectCondecorado(Condecorado condecorado) {
        try {
            db = Db4oConnection.getConnection();

            ObjectSet<Condecorado> result;
            if (db != null) {
                result = db.queryByExample(condecorado);
            } else {
                System.err.println(Message.ERROR_DB_CONNECTION);
                return;
            }

            condecorados.clear();

            if (result.hasNext()) {
                Condecorado condecoradoResult = result.next();
                condecorados.add(condecoradoResult);
            } else {
                System.out.println("Not found condecorado in the database");
            }
        } catch (Db4oException e) {
            System.err.println("Error in the search of condecorado");
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
     * Select all condecorados in the database using db4o connection and commit the transaction
     * after the selection is done successfully and store the result in the condecorados list.
     */
    public static void selectAllCondecorado() {
        try {
            db = Db4oConnection.getConnection();

            ObjectSet<Condecorado> result;
            if (db != null) {
                result = db.queryByExample(Condecorado.class);
            } else {
                System.err.println(Message.ERROR_DB_CONNECTION);
                return;
            }

            condecorados.clear();

            if (result.isEmpty()) {
                System.out.println("Not found condecorados in the database");
            } else {
                while (result.hasNext()) {
                    Condecorado condecorado = result.next();
                    condecorados.add(condecorado);
                }
            }
        } catch (Db4oException e) {
            System.err.println("Error in the search of condecorados");
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
     * Select a condecorado in the database using db4o connection and commit the transaction
     * after the selection is done successfully and store the result in the condecorados list.
     *
     * @param connection db4o connection
     * @param query query to select a condecorado in the database
     */
    public static void selectByQueryCondecorado(ObjectContainer connection, Query query) {
        try {
            db = connection;

            ObjectSet<Condecorado> result;
            if (db != null && query != null) {
                result = query.execute();
            } else {
                System.err.println(Message.ERROR_DB_CONNECTION);
                return;
            }

            condecorados.clear();

            if (result.isEmpty()) {
                System.out.println("Not found condecorados in the database");
            } else {
                while (result.hasNext()) {
                    Condecorado condecorado = result.next();
                    condecorados.add(condecorado);
                }
            }
        } catch (Db4oException e) {
            System.err.println("Error in the search of condecorados");
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
     * Get the condecorados list
     *
     * @return condecorados list
     */
    public static List<Condecorado> getCondecorados() {
        return condecorados.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Condecorado::getLastName)
                                .thenComparing(Condecorado::getFirstName))),
                        ArrayList::new
                ));
    }
}

