/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.delimare.pmb.entitymanager;

import java.sql.*;
import org.delimare.pmb.function.Fungsi;
import org.delimare.pmb.function.Logger;

/**
 *
 * @author MyBook Z Series
 */
public class AdminManager {
    public boolean login(String username, String password) {
        try {
            String sql = "SELECT * FROM admin WHERE username = '" + username + "' AND password = '" + password + "'";
            ResultSet result = Fungsi.getResult(sql);
            
            return result.first();
        } catch (Exception e) {
            Logger.error(this, e.getMessage());
            return false;
        }
    }
}
