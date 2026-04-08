package kr.or.ddit.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionFactory {
    static String url;
    static String username;
    static String password;

    static {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String baseName = "kr.or.ddit.db.DbInfo";
        ResourceBundle bundle = ResourceBundle.getBundle(baseName);
        url = bundle.getString("url");
        username = bundle.getString("username");
        password = bundle.getString("password");
    }

    public static Connection createConnection() throws SQLException {
        
        return DriverManager.getConnection(url, username, password);
    }
}