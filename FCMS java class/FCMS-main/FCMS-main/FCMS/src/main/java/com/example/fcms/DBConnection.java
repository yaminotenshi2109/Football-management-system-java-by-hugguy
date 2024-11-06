package com.example.fcms;

import java.sql.*;
import java.sql.Connection;

public class DBConnection {
    Statement statement;
    Connection connection;

    public DBConnection() {
        try {
//        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://MYLAP:1433;databaseName=master";
            String user = "sa";
            String pass = "1234";
            connection = DriverManager.getConnection(url, user, pass);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }


}
