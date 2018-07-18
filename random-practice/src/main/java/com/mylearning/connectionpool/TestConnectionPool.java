package com.mylearning.connectionpool;

import java.util.Collections;

public class TestConnectionPool {
    public static void main(String[] args) {
        // Typical Client code
        // We can't be sure the connections are returned, and we can't be sure they aren't closed and then returned,
        // or that you don't return a connection which came from another source altogether.
        ConnectionPool connectionPool = new MyConnectionPool(Collections.emptyList());
        Connection con = connectionPool.getConnection();
        try {
            con.execute(null);
        } finally {
            con.close();
        }
    }
}
