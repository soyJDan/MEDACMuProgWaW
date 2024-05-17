package dao.db4o;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.Db4oException;
import com.db4o.query.Query;
import models.componentes.personas.Heroe;
import utilidades.Message;
import utilidades.db4o.Db4oConnection;

import java.util.ArrayList;
import java.util.List;

/**
 * HeroeDao class to make the CRUD operations in the database with the heroes
 * using db4o connection and queries to select the heroes in the database
 * and store the result in the heroes list.
 *
 * @version 1.0
 * @since 1.0
 * @see Heroe
 * @see Message
 * @see Db4oConnection
 *
 * @author Daniel Romero
 */
public class HeroeDao {

    /**
     * Db4o connection to connect with the database
     */
    private static ObjectContainer db;

    /**
     * List of heroes to store the result of the queries in the database
     */
    private static final List<Heroe> heroes = new ArrayList<>();

    /**
     * Utility class to avoid the instantiation of the class and use the methods directly
     * with the class name and dot operator to access the methods.
     */
    private HeroeDao() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Insert a hero in the database using db4o connection and commit the
     * transaction after the insertion is done successfully
     *
     * @param heroe hero to insert in the database
     */
    public static void insertHeroe(Heroe heroe) {
        try {
            db = Db4oConnection.getConnection();

            if (db != null) {
                db.store(heroe);
                db.commit();
            } else {
                System.err.println(Message.ERROR_DB_CONNECTION);
            }

            System.out.println("Insert hero successful");

        } catch (Db4oException e) {
            System.err.println("Error in the insertion of the hero");
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
     * Insert a list of heroes in the database using db4o connection and commit
     * the transaction after the insertion of all the heroes in the list is
     * done successfully
     *
     * @param heroes list of heroes to insert in the database
     */
    public static void insertHeroe(List<Heroe> heroes) {
        try {
            db = Db4oConnection.getConnection();

            if (heroes.isEmpty()) {
                System.out.println("The list of heroes is empty");
                return;
            }

            if (db != null) {
                for (Heroe heroe : heroes) {
                    db.store(heroe);
                }

                db.commit();
            } else {
                System.err.println(Message.ERROR_DB_CONNECTION);
            }

            System.out.println("Insert heroes successful");

        } catch (Db4oException e) {
            System.err.println("Error in the insertion of the hero");
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
     * Delete a hero in the database using db4o connection and commit the
     * transaction after the deletion is done successfully
     *
     * @param heroe hero to delete in the database
     */
    public static void deleteHeroe(Heroe heroe) {
        try {
            db = Db4oConnection.getConnection();

            if (db != null) {
                db.delete(heroe);
                db.commit();
            } else {
                System.err.println(Message.ERROR_DB_CONNECTION);
            }

            System.out.println("Delete hero successful");
        } catch (Db4oException e) {
            System.err.println("Error in the deletion of the hero");
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
     * Delete a list of heroes in the database using db4o connection and commit
     * the transaction after the deletion of all the heroes in the list is
     * done successfully
     *
     * @param heroes list of heroes to delete in the database
     */
    public static void deleteHeroe(List<Heroe> heroes) {
        try {
            db = Db4oConnection.getConnection();

            if (heroes.isEmpty()) {
                System.out.println("The list of heroes is empty");
                return;
            }

            if (db != null) {
                for (Heroe heroe : heroes) {
                    db.delete(heroe);
                }

                db.commit();
            } else {
                System.err.println(Message.ERROR_DB_CONNECTION);
            }

            System.out.println("Delete heroes successful");
        } catch (Db4oException e) {
            System.err.println("Error in the deletion of the hero");
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
     * Select a hero in the database using db4o connection and commit the transaction
     * after the selection is done successfully and store the result in the heroes list.
     *
     * @param heroe hero to select in the database
     */
    public static void selectHeroe(Heroe heroe) {
        try {
            db = Db4oConnection.getConnection();

            ObjectSet<Heroe> result;
            if (db != null) {
                result = db.queryByExample(heroe);
            } else {
                System.err.println(Message.ERROR_DB_CONNECTION);
                return;
            }

            heroes.clear();

            if (result.hasNext()) {
                Heroe heroeResult = result.next();
                heroes.add(heroeResult);
            } else {
                System.out.println("Not found the hero");
            }
        } catch (Db4oException e) {
            System.err.println("Error in the search for the hero");
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
     * Select all heroes in the database using db4o connection and commit the transaction
     * after the selection is done successfully and store the result in the heroes list.
     */
    public static void selectAllHeroes() {
        try {
            db = Db4oConnection.getConnection();

            ObjectSet<Heroe> result;
            if (db != null) {
                result = db.queryByExample(Heroe.class);
            } else {
                System.err.println(Message.ERROR_DB_CONNECTION);
                return;
            }

            heroes.clear();

            if (result.isEmpty()) {
                System.out.println("Not found heroes in the database");
            } else {
                while (result.hasNext()) {
                    Heroe heroe = result.next();
                    heroes.add(heroe);
                }
            }
        } catch (Db4oException e) {
            System.err.println("Error in the search for the heroes");
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
     * Select a hero in the database using db4o connection and commit the transaction
     * after the selection is done successfully and store the result in the heroes list.
     *
     * @param connection db4o connection
     * @param query query to select a hero in the database
     */
    public static void selectByQueryHeroe(ObjectContainer connection, Query query) {
        try {
            db = connection;

            ObjectSet<Heroe> result;
            if (db != null && query != null) {
                result = query.execute();
            } else {
                System.err.println(Message.ERROR_DB_CONNECTION);
                return;
            }

            heroes.clear();

            if (result.isEmpty()) {
                System.out.println("Not found heroes in the database");
            } else {
                while (result.hasNext()) {
                    Heroe heroe = result.next();
                    heroes.add(heroe);
                }
            }
        } catch (Db4oException e) {
            System.err.println("Error in the search for the hero");
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
     * Get the heroes list
     *
     * @return heroes list
     */
    public static List<Heroe> getHeroes() {
        return heroes;
    }
}
