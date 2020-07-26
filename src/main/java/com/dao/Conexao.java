/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro
 */
public class Conexao {
    private final String IP = "127.0.0.1";
    private final String PORT = "3306";
    private final String USER = "root";
    private final String PASS = "pedro1995";
    private final String DB = "sigus";
    
    public Connection getConnection() {
        Connection conn = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://"+IP+":"+PORT+"/"+DB+"?useSSL=false", USER, PASS);
        }
        catch (SQLException e) {
            
        }
        
        return conn;
    }
}
