package com.quadcode.simo.dao;


import com.quadcode.simo.model.UserLogin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * LoginDao.java
 * --------------------------------------------------------------
 * Clase par realizar la query, primero para validar si el usuario existe.
 * Se usa PreparedStamentent. Para proteccion contra SQLi
 * ---------------------------------------------------------------
 * @author: Avila Carrillo Jorge Armando                           |
 * Fecha de creacion: 25 - JULIO - 2024                            |
 * Ultima Actualizacion: 25 - JULIO - 2024                        |
 * ---------------------------------------------------------------
 */

public class LoginDao {

    /**
     * Verifica si el nombre de usuario existe en nuestra base de datos.
     *
     * @param username Nombre de usuario
     * @return  En caso de verdadero, retorna el usuario de la base de datos con su contraseña
     * @throws SQLException Si ocurre algun error durante la consulta  al la base de datos
     * @throws ClassNotFoundException Si ocurre algun error enconcontrando el controlador de mariadb
     */

    public UserLogin getUserByUsername(String username) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM usuarios WHERE Usuario = ?";

        // Intenta hacer la conexion con la base de datos, y valida los datos de entrada
        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query)){
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Si encuentra el usuario retorna los datos de la base de datos
                return new UserLogin(rs.getInt("Id"), rs.getString("Usuario"), rs.getString("Contraseña"));
            }
            return null;
        }
        }
    }
