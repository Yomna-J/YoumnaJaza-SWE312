package components;

import java.sql.*;

/**
 * A class that establishes connection with a database on a local host.
 *
 * @author youmna
 *
 */
public class DBConnection {

    /**
     * The URL to the database.
     */
    private static final String DB_URL = "jdbc:mysql://localhost/banking_sys";
    /**
     * The user name of the database to sign in.
     */
    private static final String USERNAME = "root";
    /**
     * The password of the database to sign in.
     */
    private static final String PASS = "";

    /**
     * Establish a connection with the database
     *
     * @return an object of @see {@link Connection}Connection class if the
     * connection is successful; null otherwise;
     */
    public static Connection connectDB() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASS);
            return connection;
        } catch (Exception exception) {
            return null;
        }
    }

}
