package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MYSQL extends Database{

    public MYSQL() throws SQLException {
        setConnection();
    }

    private void setConnection() throws SQLException {

        connection = DriverManager.getConnection(
                DBConfig.getProperty("url"),
                DBConfig.getProperty("user"),
                DBConfig.getProperty("pass"));

    }

    @Override
    public Connection getConnection() throws SQLException {
        if(!checkConnection()){
            setConnection();
        }
        return connection;
    }
}
