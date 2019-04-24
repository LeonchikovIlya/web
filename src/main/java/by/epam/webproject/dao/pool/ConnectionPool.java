package by.epam.webproject.dao.pool;

import by.epam.webproject.dao.pool.dbManager.DBManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;

public class ConnectionPool {

    private static ConnectionPool INSTANCE;

    private String driverName;
    private String url;
    private String user;
    private String password;
    private int poolSize;

    private ArrayBlockingQueue<PooledConnection> availableConnections;
    private ArrayBlockingQueue<PooledConnection> allConnections;
    private volatile boolean isPoolReleased = false;


    private ConnectionPool() {

        this.driverName = DBManager.getValue(DBManager.DRIVER);
        this.url = DBManager.getValue(DBManager.URL);
        this.user = DBManager.getValue(DBManager.USER);
        this.password = DBManager.getValue(DBManager.PASSWORD);

        try {
            this.poolSize = Integer.parseInt(DBManager.getValue(DBManager.POOL_SIZE));
        } catch (NumberFormatException e) {
            System.out.println(e);
        }

        this.availableConnections = new ArrayBlockingQueue<>(poolSize);
        this.allConnections = new ArrayBlockingQueue<>(poolSize);

        initPoolData();
    }

    public ArrayBlockingQueue<PooledConnection> getAvailableConnections() {
        return availableConnections;
    }

    public ArrayBlockingQueue<PooledConnection> getAllConnections() {
        return allConnections;
    }

    public int getPoolSize() {
        return poolSize;
    }

    public boolean isPoolReleased() {
        return isPoolReleased;
    }

    private void initPoolData() {

        try {

            Class.forName(driverName);

            for (int i = 0; i < poolSize; i++) {

                Connection connection = DriverManager.getConnection(url, user, password);
                PooledConnection pooledConnection = new PooledConnection(connection);

                availableConnections.offer(pooledConnection);
                allConnections.offer(pooledConnection);
            }

        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    /**
     * Provides instance of connection pool
     *
     * @return instance of connection pool
     */
    public static ConnectionPool getInstance() {
        if (INSTANCE == null) {
            synchronized (ConnectionPool.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ConnectionPool();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Provides main access to database available Connections
     * @return {@link Connection} to work with database
     */
    public Connection getConnection() {

        PooledConnection connection = null;

        if (!isPoolReleased) {
            connection = availableConnections.poll();
        } else {
            System.out.println("CAnnot get connection");
        }
        return connection;
    }

    public void returnConnection(Connection connection){

        if (connection instanceof PooledConnection){
            try {
                connection.setAutoCommit(true);
                availableConnections.put((PooledConnection) connection);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.getMessage();
            }
        }
    }

    /**
     * Releases connection pool and closes all connections
     */
    public void releasePool(){

        isPoolReleased = true;

        for (PooledConnection connection : allConnections){
            try {
                connection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
