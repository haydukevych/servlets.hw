package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
    private static String USER_NAME = "root";
    private static String USER_PASSWORD = "oles21";
    private static String URL = "jdbc:mysql://localhost/journal_shop";

    public static Connection openConnection() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName ("com.mysql.cj.jdbc.Driver").newInstance ();
        return DriverManager.getConnection (URL, USER_NAME, USER_PASSWORD);
    }
}
