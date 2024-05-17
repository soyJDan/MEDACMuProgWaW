package dao.db4o;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.Db4oException;
import com.db4o.query.Query;
import models.score.TopScore;
import utilidades.Message;
import utilidades.db4o.Db4oConnection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * TopScoreDao class to make the CRUD operations in the database with the TopScore
 * using db4o connection and queries to select the TopScores in the database
 * and store the result in the topScores list.
 *
 * @version 1.0
 * @since 1.0
 * @see TopScore
 * @see Message
 * @see Db4oConnection
 *
 * @author Daniel Romero
 */
public class TopScoreDao {

    /**
     * Db4o connection to connect with the database
     */
    private static ObjectContainer db;

    /**
     * List of TopScores to store the result of the queries in the database
     */
    private static final List<TopScore> topScores = new ArrayList<>();

    /**
     * Utility class to avoid the instantiation of the class and use the methods directly
     * with the class name and dot operator to access the methods.
     */
    private TopScoreDao() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Insert a score in the database using db4o connection and commit the
     * transaction after the insertion is done successfully
     *
     * @param topScore score to insert in the database
     */
    public static void insertTopScore(TopScore topScore) {
        try {
            db = Db4oConnection.getConnection();

            if (db != null)  {
                db.store(topScore);
                db.commit();
            } else {
                System.err.println(Message.ERROR_DB_CONNECTION);
            }

            System.out.println("Insert TopScore successful");

        } catch (Db4oException e) {
            System.err.println("Error in the insertion of the TopScore");
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
     * Insert a list of TopScore in the database using db4o connection and commit
     * the transaction after the insertion of all the scores in the list is
     * done successfully
     *
     * @param topScores list of scores to insert in the database
     */
    public static void insertTopScore(List<TopScore> topScores) {
        try {
            db = Db4oConnection.getConnection();

            if (topScores.isEmpty()) {
                System.out.println("The list of TopScores is empty");
                return;
            }

            if (db != null)  {
                for (TopScore topScore : topScores) {
                    db.store(topScore);
                }

                db.commit();
            } else {
                System.err.println(Message.ERROR_DB_CONNECTION);
            }

            System.out.println("Insert TopScores successful");

        } catch (Db4oException e) {
            System.err.println("Error in the insertion of the TopScores");
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
     * Delete a score in the database using db4o connection and commit the
     * transaction after the deletion is done successfully
     *
     * @param topScore score to delete in the database
     */
    public static void deleteTopScore(TopScore topScore) {
        try {
            db = Db4oConnection.getConnection();

            if (db != null)  {
                db.delete(topScore);
                db.commit();
            } else {
                System.err.println(Message.ERROR_DB_CONNECTION);
            }

            System.out.println("Delete TopScore successful");
        } catch (Db4oException e) {
            System.err.println("Error in the deletion of the TopScore");
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
     * Delete a list of TopScore in the database using db4o connection and commit
     * the transaction after the deletion of all the scores in the list is
     * done successfully
     *
     * @param topScores list of scores to delete in the database
     */
    public static void deleteTopScore(List<TopScore> topScores) {
        try {
            db = Db4oConnection.getConnection();

            if (topScores.isEmpty()) {
                System.out.println("The list of TopScores is empty");
                return;
            }

            if (db != null)  {
                for (TopScore topScore : topScores) {
                    db.delete(topScore);
                }

                db.commit();
            } else {
                System.err.println(Message.ERROR_DB_CONNECTION);
            }

            System.out.println("Delete TopScores successful");
        } catch (Db4oException e) {
            System.err.println("Error in the deletion of the TopScores");
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
     * Select a score in the database using db4o connection and commit the transaction
     * after the selection is done successfully and store the result in the topScores list.
     *
     * @param topScore score to select in the database
     */
    public static void selectTopScore(TopScore topScore) {
        try {
            db = Db4oConnection.getConnection();

            ObjectSet<TopScore> result;
            if (db != null) {
                result = db.queryByExample(topScore);
            } else {
                System.err.println(Message.ERROR_DB_CONNECTION);
                return;
            }

            topScores.clear();

            if (result.hasNext()) {
                TopScore topScoreResult = result.next();
                topScores.add(topScoreResult);
            } else {
                System.out.println("Not found TopScore");
            }
        } catch (Db4oException e) {
            System.err.println("Error in the search of the TopScore");
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
     * Select all scores in the database using db4o connection and commit the transaction
     * after the selection is done successfully and store the result in the topScores list.
     */
    public static void selectAllTopScore() {
        try {
            db = Db4oConnection.getConnection();

            ObjectSet<TopScore> result;
            if (db != null) {
                result = db.queryByExample(TopScore.class);
            } else {
                System.err.println(Message.ERROR_DB_CONNECTION);
                return;
            }

            topScores.clear();

            if (result.isEmpty()) {
                System.out.println("Not found TopScores in the database");
            } else {
                while (result.hasNext()) {
                    TopScore topScore = result.next();
                    topScores.add(topScore);
                }
            }
        } catch (Db4oException e) {
            System.err.println("Error in the search of the TopScores");
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
     * Select a scores in the database using db4o connection and commit the transaction
     * after the selection is done successfully and store the result in the topScores list.
     *
     * @param connection db4o connection
     * @param query query to select a score in the database
     */
    public static void selectByQueryTopScore(ObjectContainer connection, Query query) {
        try {
            db = connection;

            ObjectSet<TopScore> result;
            if (db != null && query != null) {
                result = query.execute();
            } else {
                System.err.println(Message.ERROR_DB_CONNECTION);
                return;
            }

            topScores.clear();

            if (result.isEmpty()) {
                System.out.println("Not found TopScores in the database");
            } else {
                while (result.hasNext()) {
                    TopScore topScore = result.next();
                    topScores.add(topScore);
                }
            }
        } catch (Db4oException e) {
            System.err.println("Error in the search of the TopScores");
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
     * Get the topScores list
     *
     * @return topScores list
     */
    public static List<TopScore> getTopScores() {
        topScores.sort(Comparator.comparingInt(o -> o.getGeneral().getSalud()));
        return topScores;
    }
}