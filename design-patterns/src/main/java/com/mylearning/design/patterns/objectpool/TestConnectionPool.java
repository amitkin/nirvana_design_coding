package com.mylearning.design.patterns.objectpool;

import java.sql.Connection;

public class TestConnectionPool {
    public static void main(String[] args) {
        // Create the ConnectionPool:
        JDBCConnectionPool pool = new JDBCConnectionPool(
                "com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/mydb",
                "root", "root");

        // Get a connection:
        Connection connection = pool.getObject();

        // Use the connection
        //...

        // Return the connection:
        pool.releaseObject(connection);
    }
}
