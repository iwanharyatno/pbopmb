/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.delimare.pmb.function;

import java.sql.*;
import org.delimare.pmb.connection.Koneksi;

/**
 *
 * @author MyBook Z Series
 */
public class Fungsi {
    private static Koneksi db = new Koneksi();
    
    public static int EQuery(String sql) {
        int i = 0;
        try {
            Connection con = db.getConnection();
            Statement st = con.createStatement();
            i = st.executeUpdate(sql);
        } catch (Exception e) {
            Logger.error(Fungsi.class, e.getMessage());
        }
        
        return i;
    }
    
    public static int EQueryReturnsID(String sql) {
        int i = 0;
        try {
            Connection con = db.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                i = rs.getInt(1);
            }
        } catch (Exception e) {
            Logger.error(Fungsi.class, e.getMessage());
        }
        
        return i;
    }
    
    public static ResultSet getResult(String sql) {
        int i = 0;
        
        ResultSet result = null;
        
        try {
            Connection con = db.getConnection();
            Statement st = con.createStatement();
            result = st.executeQuery(sql);
        } catch (Exception e) {
            Logger.error(Fungsi.class, e.getMessage());
        }
        
        return result;
    }
}
