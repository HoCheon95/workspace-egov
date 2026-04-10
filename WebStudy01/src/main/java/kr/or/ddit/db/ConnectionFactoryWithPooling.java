package kr.or.ddit.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

import oracle.jdbc.datasource.impl.OracleDataSource;

public class ConnectionFactoryWithPooling {
    static String url;
    static String username;
    static String password;
    static DataSource dataSource;

    static {
        String baseName = "kr.or.ddit.db.DbInfo";
        ResourceBundle bundle = ResourceBundle.getBundle(baseName);
        url = bundle.getString("url");
        username = bundle.getString("username");
        password = bundle.getString("password");

        HikariDataSource hds = new HikariDataSource();
        dataSource = hds;
        hds.setJdbcUrl(url);
        hds.setUsername(username);
        hds.setPassword(password);

        int maxPoolSize = Integer.parseInt(bundle.getString("maxPoolSize"));
        int minIdle = Integer.parseInt(bundle.getString("minIdle"));
        long connectionTimeoutMs = Long.parseLong(bundle.getString("connectionTimeoutMs"));

        hds.setMaximumPoolSize(maxPoolSize);
        hds.setMinimumIdle(minIdle);
        hds.setConnectionTimeout(connectionTimeoutMs);
    }

    public static Connection createConnection() throws SQLException {
        // return DriverManager.getConnection(url, username, password);
        return dataSource.getConnection();
    }
}