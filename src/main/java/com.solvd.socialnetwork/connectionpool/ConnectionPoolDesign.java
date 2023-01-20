package com.solvd.socialnetwork.connectionpool;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.sql.Connection;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionPoolDesign {
    private String url;
    private String username;
    private String password;
    private int maximumPoolSize;
    private ConcurrentLinkedQueue<Connection> connections;
    private static volatile ConnectionPoolDesign connectionPool = null;


    private ConnectionPoolDesign(String driverClassName, String url, String username, String password, int maximumPoolSize) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, SQLException {
        Class<?> driverClass = Class.forName(driverClassName);
        Constructor<?> constructor = driverClass.getDeclaredConstructor();
        Driver driver = (Driver) constructor.newInstance();
        DriverManager.registerDriver(driver);

        this.url = url;
        this.username = username;
        this.password = password;
        this.maximumPoolSize = maximumPoolSize;
        this.connections = new ConcurrentLinkedQueue<>();
    }

    public static ConnectionPoolDesign getInstance() throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        if (connectionPool == null){
            synchronized (ConnectionPoolDesign.class){
                if (connectionPool == null){
                    Properties prop = new Properties();
                    prop.load(Files.newInputStream(Paths.get("/Users/parisamory/Documents/development/SocialNetwork/src/resources/config.properties")));
                    connectionPool = new ConnectionPoolDesign(prop.getProperty("driver"), prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"), 5);
                    // thread pool with 7 threads
                    ExecutorService executorService = Executors.newFixedThreadPool(7);
                    for (int i = 0; i < 7; i++) {
                        executorService.execute(() -> {
                            try {
                                Connection connection = connectionPool.createConnection();
                                connectionPool.connections.add(connection);
                                }
                            catch (SQLException | ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                            });
                        }
                    }
                }
            }
            return connectionPool;
        }


    public synchronized Connection getConnection() throws SQLException, ClassNotFoundException {
        // check if the connection pool is empty
        if (connections.isEmpty()){
            // create a new connection and add it to the pool
            Connection connection = createConnection();
            connections.add(connection);
        }
        return connections.poll();
    }

    public Connection getConnection(long timeout) throws SQLException, InterruptedException, ClassNotFoundException {
        Connection connection = null;
        long startTime = System.currentTimeMillis();
        while (connection == null && (System.currentTimeMillis() - startTime) < timeout){
            connection = connections.poll();
            if (connection == null){
                if (connections.size() == maximumPoolSize){
                    synchronized (ConnectionPoolDesign.class){
                        wait(timeout - (System.currentTimeMillis() - startTime));
                    }
                }
                else {
                    connection = createConnection();
                    connections.add(connection);
                }
            }
        }
        if (connection == null){
            throw new SQLException();
        }
        return connection;
    }

    private Connection createConnection() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/database_name";
        String username = "username";
        String password = "password";

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, username, password);
    }

    public void releaseConnection(Connection connection){
        synchronized (ConnectionPoolDesign.class){
            connections.offer(connection);
            this.notifyAll();
        }
    }


}

