package com.quadcode.simo.dao;

import org.mariadb.jdbc.Connection;

import java.sql.PreparedStatement;

public class ConfigDao {
    private Connection connection;

    public ConfigDao() {
        try {
            this.connection = (Connection) DatabaseConnection.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modificarContra(String user, String password){
        String sql = "UPDATE usuarios SET Contraseña = ? WHERE Usuario = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, password);
            ps.setString(2, user);
            ps.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
