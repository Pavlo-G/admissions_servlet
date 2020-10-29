package model.DAO.mysql;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.sql.DataSource;

public class ConnectionPoolHolder {

    private static volatile DataSource dataSource;


    public static DataSource getDataSource() {
        Properties properties = new Properties();
        try (InputStream in = ConnectionPoolHolder.class.getClassLoader().getResourceAsStream("db\\db.properties")) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl(properties.getProperty("connection.url"));
                    ds.setDriverClassName(properties.getProperty("connection.driver"));
                    ds.setUsername(properties.getProperty("connection.username"));
                    ds.setPassword(properties.getProperty("connection.password"));
                    ds.setMinIdle(5);
                    ds.setMaxIdle(10);
                    ds.setMaxOpenPreparedStatements(100);
                    dataSource = ds;
                }
            }
        }
        return dataSource;

    }


}
