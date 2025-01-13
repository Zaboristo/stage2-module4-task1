package com.mjc.stage2.impl;

import com.mjc.stage2.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class H2ConnectionFactory implements ConnectionFactory {

    private final Instance instanceProps = new Instance();

    @Override
    public Connection createConnection() {
        try {
            return DriverManager.getConnection(instanceProps.url);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static class Instance {
        private final String driver;
        private final String url;
        private final String name;
        private final String password;

        private Instance() {
            Properties props = new Properties();
            try {
                props.load(H2ConnectionFactory.class.getClassLoader().getResourceAsStream("h2database.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.driver = props.getProperty("jdbc_driver");
            this.name = props.getProperty("user");
            this.url = props.getProperty("db_url");
            this.password = props.getProperty("password");
            try {
                Class.forName(this.driver);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

