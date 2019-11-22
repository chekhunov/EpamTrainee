package ua.nure.chekhunov.summarytask4.db.connection_pool;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Pool singleton
 *
 * @author IC
 */
public class Pool {
    private static final Logger LOG = Logger.getLogger(Pool.class);
    private static final int GET_CONNECTION_MILLIS = 1000;
    private static final String PROPERTIES_PATH = "/pool-conf.properties";
    private static Pool INSTANCE;

    public static Pool getInstance() {
        if (INSTANCE == null)
            synchronized (Pool.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Pool();
                }
            }
        return INSTANCE;
    }

    private Map<Connection, Boolean> connections;

    private final String URL;
    private final String USER;
    private final String PASSWORD;

    /**
     * Creates pool: HashMap with connections as keys
     */
    private Pool() {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream(PROPERTIES_PATH));
            Class.forName(properties.getProperty("db.driver"));
            URL = properties.getProperty("db.url");
            USER = properties.getProperty("db.user");
            PASSWORD = properties.getProperty("db.password");

            int capacity = Integer.parseInt(properties.getProperty("db.poolsize"));
            connections = new HashMap<>(capacity);
            for (int i = 0; i < capacity; i++) {
                connections.put(createConnection(), true);
            }

        } catch (IOException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private PoolConnection createConnection() throws SQLException {
        return new PoolConnection(DriverManager.getConnection(URL, USER, PASSWORD), this);
    }

    @SuppressWarnings("finally")
    public Connection getConnection() {
        Connection result = null;
        for (Map.Entry<Connection, Boolean> entry : connections.entrySet()) {
            if (entry.getValue()) {
                synchronized (this) {
                    if (entry.getValue()) {
                        Connection key = entry.getKey();
                        connections.put(key, false);
                        result = key;
                    }
                }
            }
        }

        if (result == null) {
            try {
                Thread.sleep(GET_CONNECTION_MILLIS);
            } catch (InterruptedException e) {
                LOG.error(e.getMessage());
                LOG.info("Retrying to get connection.");
            } finally {
                return getConnection();
            }
        } else {
            return result;
        }
    }

    public void free(PoolConnection poolConnection) {
        connections.put(poolConnection, true);
    }
}
