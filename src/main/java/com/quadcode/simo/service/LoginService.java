package com.quadcode.simo.service;

import com.quadcode.simo.dao.LoginDao;
import com.quadcode.simo.model.UserLogin;


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


public class LoginService {
    private LoginDao loginDao;

    public LoginService(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    public UserLogin authenticate(String username, String password){
        try {
            UserLogin userLogin = loginDao.getUserByUsername(username);
            if (userLogin != null && userLogin.getPassword().equals(password)) {
                return userLogin;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
