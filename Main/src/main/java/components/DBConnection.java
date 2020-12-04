package components;

import java.sql.*;

/**
 * The <b>DBConnection</b> class that establishes connection with a database on a local host.
 */
public class DBConnection {

    /**
     * The URL of the database.
     */
    private static final String DB_URL = "jdbc:mysql://localhost/banking_sys";
    /**
     * The username of the database to sign in.
     */
    private static final String USERNAME = "root";
    /**
     * The password of the database to sign in.
     */
    private static final String PASS = "";

    /**
     * Establishes a connection with the database.
     *
     * @return an object of {@link Connection}Connection class if the
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
