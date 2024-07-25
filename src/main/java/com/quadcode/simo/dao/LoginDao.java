package com.quadcode.simo.dao;


import java.sql.Connection;
import java.sql.DriverManager;


public class LoginDao {
    public Connection databaseLink;

    public Connection getConnection() {
        String user = "simo";
        String password = "simo";
        String database = "simo";
        String url = "jdbc:mariadb://localhost:3306/" + database;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, user, password);
        } catch (Exception e){
            e.printStackTrace();
        }

        return databaseLink;
    }
}
