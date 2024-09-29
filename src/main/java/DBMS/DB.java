package DBMS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author dazzl
 */
public class DB {
    // Making the global variables of the class, i.e this is all the things required ot connect to my personal rain cloud. 
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://127.0.0.1:3306/sys";
    private static final String user = "darren";
    private static final String pass = "Reddam2021";
    private static PreparedStatement statement;
    private static ResultSet resultSet;
    private static Connection conn;

    //Connects and ensures the connection to the database is working
    public static void connect() throws ClassNotFoundException, SQLException {
        if (conn == null || conn.isClosed()) {
            Class.forName(driver);
            System.out.println("Driver found");
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Database connected");
        }
    }

    // INSERT, UPDATE, or DELETE
    public static void update(String query) throws SQLException {
        statement = conn.prepareStatement(query);
        statement.executeUpdate();
        statement.close();
    }

    // SELECT
    public static ResultSet query(String query) throws SQLException {
        statement = conn.prepareStatement(query);
        resultSet = statement.executeQuery();
        return resultSet;
    }
}