package com.fullintel.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/fullintel";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "mount21ucl";

    private static DataSource dataSourceObj;

    private DataSource() {
    }

    public static DataSource getDataSource() {
        if (dataSourceObj == null) {
            synchronized (DataSource.class) {
                if (dataSourceObj == null) {
                    dataSourceObj = new DataSource();
                }
            }
        }
        return dataSourceObj;
    }

    public Connection getConnection() {

        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
            return null;
        }
    }
}