package com.quadcode.simo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DatabaseConnection.java
 * --------------------------------------------------------------
 * Conexion con la base de datos de SIMO en mariadb, en localhost
 * ---------------------------------------------------------------
 * @author: Avila Carrillo Jorge Armando                           |
 * Fecha de creacion: 25 - JULIO - 2024                            |
 * Ultima Actualizacion: 25 - JULIO - 2024                        |
 * ---------------------------------------------------------------
 */

public class DatabaseConnection {
  // Atributos finales de la conexion a nuestra base de datos
  private static final String URL = "jdbc:mariadb://localhost:3306/simo";
  private static final String USER = "simo";
  private static final String PASSWORD = "simo";

    /**
     * Realiza la conexion con la base de datos, con los atributos dados
     *
     * @return  La conexion con la base de datos
     * @throws SQLException Si la conexion falla
     * @throws ClassNotFoundException Si no encuentra el driver de mariadb
     */
  public static Connection getConnection() throws SQLException, ClassNotFoundException {
      Class.forName("org.mariadb.jdbc.Driver");
      return DriverManager.getConnection(URL, USER, PASSWORD);
  }
}
