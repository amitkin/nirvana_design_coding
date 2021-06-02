package com.mylearning.design.patterns.pluralsight.creational.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbSingleton {

    // Lazy load singleton
    private static DbSingleton instance = null;

    public static final String JDBC_DERBY_URL = "jdbc:derby:memory:codejava/webdb;create=true";

    private Connection connection = null;

    private DbSingleton() {
        try {
            DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DbSingleton getInstance() {
        if (instance == null) {
            // Ensure thread safety on initial load
            synchronized (DbSingleton.class) {
                if (instance == null) {
                    instance = new DbSingleton();
                }
            }
        }

        return instance;
    }

    public Connection getConnection() {
        if (connection == null) {
            synchronized (DbSingleton.class) {
                if (connection == null) {
                    try {
                        String dbUrl = JDBC_DERBY_URL;
                        connection = DriverManager.getConnection(dbUrl);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return connection;
    }

    public static void main(String[] args) {
        DbSingleton dbSingleton = DbSingleton.getInstance();
        Connection connection = dbSingleton.getConnection();

        Statement statement;
        try {
            statement = connection.createStatement();
            int count = statement.executeUpdate("CREATE TABLE Address (ID INT, ADDRESS1 VARCHAR(20), CITY VARCHAR(20))");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
