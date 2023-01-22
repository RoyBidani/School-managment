package com.company;

import com.mysql.cj.xdevapi.Collection;
import org.intellij.lang.annotations.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Database {

    protected Connection connection;

    protected Database() {
        this.connection = null;
    }

    protected boolean checkConnection() throws SQLException {

        return connection != null && !connection.isClosed();
    }

    public abstract Connection getConnection() throws SQLException;

    public void closeConnection() throws SQLException {
        if (checkConnection()) {
            connection.close();
        }
    }

    public ResultSet query(@Language("SQL") String query) throws SQLException {
        if (!checkConnection()) {
            connection = getConnection();
        }
        PreparedStatement statement = connection.prepareStatement(query);
        return statement.executeQuery();
    }

    public void query(@Language("SQL") String query, SQLConsumer<ResultSet> consumer) throws SQLException {
        ResultSet rs = query(query);
        consumer.accept(rs);
        rs.close();
    }

    public void updateQuery(@Language("SQL") String query) throws SQLException {
        if (!checkConnection()) {
            connection = getConnection();
        }
        PreparedStatement statement = connection.prepareStatement(query);
        statement.execute();
        System.out.println("\n");
        System.out.println("Database updated!");
        System.out.println("\n");
    }

    public boolean perform(String query) throws SQLException {
        if (!checkConnection()) {
            connection = getConnection();
        }
        PreparedStatement statement = connection.prepareStatement(query);
        return statement.execute();
    }
}
