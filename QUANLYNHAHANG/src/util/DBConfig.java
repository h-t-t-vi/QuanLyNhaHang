/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class DBConfig {
    public static Connection getConnection(){
        try {
            
            //tai driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QUANLYNHAHANG2", "sa", "123");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConfig.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
