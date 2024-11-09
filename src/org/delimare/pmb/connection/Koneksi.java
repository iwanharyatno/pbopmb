/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.delimare.pmb.connection;

import java.sql.*;
import org.delimare.pmb.function.Logger;

/**
 *
 * @author MyBook Z Series
 */
public class Koneksi {
    public Connection getConnection() {
        Connection con = null;
        
        try {
            String db = "pmb_mania";
            String sv = "jdbc:mysql://localhost:3306/" + db;
            String driver = "com.mysql.cj.jdbc.Driver";
            
            Class.forName(driver);
            con = DriverManager.getConnection(sv, "root", "");
            return con;
        } catch (Exception e) {
            Logger.error(this, e.getMessage());
            return null;
        }
    }
}
