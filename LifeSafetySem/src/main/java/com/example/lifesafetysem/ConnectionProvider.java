package com.example.lifesafetysem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    private static ConnectionProvider conn;
    private static Connection connection;
    private final static String URL = "jdbc:postgresql://localhost:5432/semestrovka";
    private final static String USERNAME = "postgres";
    private final static String PASSWORD = "1234";

    public static ConnectionProvider getConn() {
        if (conn == null) {
            conn = new ConnectionProvider();
        }
        return conn;
    }
    public Connection getConnection() {
        return connection;
    }
    public ConnectionProvider() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
