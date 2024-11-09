/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.delimare.pmb.entitymanager;

import java.sql.*;
import org.delimare.pmb.entity.Admin;
import org.delimare.pmb.function.Fungsi;
import org.delimare.pmb.function.Logger;

/**
 *
 * @author MyBook Z Series
 */
public class AdminManager {
    public Admin login(String username, String password) {
        Admin admin = null;
        
        try {
            String sql = "SELECT * FROM admin WHERE username = '" + username + "' AND password = '" + password + "'";
            ResultSet result = Fungsi.getResult(sql);
            
            if (result.next()) {
                admin = new Admin();
                admin.setId(result.getInt("id_admin"));
                admin.setUsername(result.getString("username"));
                admin.setNamaLengkap(result.getString("nama_lengkap"));
            }
            
        } catch (Exception e) {
            Logger.error(this, e.getMessage());
        }
        
        return admin;
    }
}
