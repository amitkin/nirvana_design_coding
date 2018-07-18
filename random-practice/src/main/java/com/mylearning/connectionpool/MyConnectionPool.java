package com.mylearning.connectionpool;

import java.util.List;

public class MyConnectionPool implements ConnectionPool{

    private List<Connection> freeConnections;

    MyConnectionPool(List<Connection> connections) {
        freeConnections = connections;
    }

    @Override
    public Connection getConnection() {
        // The solution is another layer of indirection : Instead of returning the "real" connection object from the pool,
        // a wrapper is returned which gives the pool control of connection life cycle, instead of the client.
        final Connection realConnection = borrowConnection();
        return new Connection(){
            private boolean closed = false;
            public Object execute(Object object) {
                if (closed){
                    throw new IllegalStateException("Connection closed");
                }
                return realConnection.execute(object);
            }
            public void close() {
                if (!closed) {
                    closed = true;
                    returnConnection(realConnection);
                }
            }
            protected void finalize() throws Throwable {
                try {
                    close();
                } finally {
                    super.finalize();
                }
            }
        };
    }

    private Connection borrowConnection(){
        if (freeConnections.isEmpty()){
            throw new IllegalStateException("No free connections");
        }
        return freeConnections.remove(0);
    }
    private void returnConnection(Connection conn){
        freeConnections.add(conn);
    }
}
