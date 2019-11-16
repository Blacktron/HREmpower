package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Created by Terrax on 28-Feb-2016.
 */
public class DBConnectionHandler {

    /**
     * Opens a connection to the database and returns it as instance.
     */
    public static Connection openDatabaseConnection() throws SQLException {
        //String url = "jdbc:mysql://localhost:3306/";  // For local database.
        String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
        String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
        String url = "jdbc:mysql://" + host + ":" + port;
        String dbName = "/hrempower";
        String driver = "com.mysql.jdbc.Driver";
        String dbUsername = "adminpulgZe3";
        String dbPassword = "ANUEUKcQNMeN";

        try {
            Class.forName(driver).newInstance();
            System.out.println("Opening connection");
            return DriverManager.getConnection(url + dbName, dbUsername, dbPassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }
}