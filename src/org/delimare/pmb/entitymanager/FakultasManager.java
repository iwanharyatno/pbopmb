/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.delimare.pmb.entitymanager;

import java.sql.ResultSet;
import java.util.ArrayList;
import org.delimare.pmb.entity.Fakultas;
import org.delimare.pmb.function.Fungsi;
import org.delimare.pmb.function.Logger;

/**
 *
 * @author MyBook Z Series
 */
public class FakultasManager {
    public int insert(Fakultas fk) {
        int result = 0;
        
        try {
            String sql = "INSERT INTO fakultas (kode_fakultas, nama_fakultas) VALUES ('" + fk.getKode() + "', '" + fk.getNama() + "');";
            result = Fungsi.EQuery(sql);
        } catch (Exception e) {
            result = -1;
            Logger.error(this, e.getMessage());
        }
        
        return result;
    }
    public int update(Fakultas fk) {
        int result = 0;
        
        try {
            String sql = "UPDATE fakultas SET nama_fakultas = '" + fk.getNama()+ "' WHERE kode_fakultas = '" + fk.getKode() + "'";
            result = Fungsi.EQuery(sql);
        } catch (Exception e) {
            result = -1;
            Logger.error(this, e.getMessage());
        }
        
        return result;
    }
    public int delete(String kode) {
        int result = 0;
        
        try {
            String sql = "DELETE FROM fakultas WHERE kode_fakultas = '" + kode + "'";
            result = Fungsi.EQuery(sql);
        } catch (Exception e) {
            result = -1;
            Logger.error(this, e.getMessage());
        }
        
        return result;
    }
    
    public ArrayList<Fakultas> getSemua(String pencarian) {
        ArrayList<Fakultas> hasil = new ArrayList<>();
        
        try {
            String sql = "SELECT kode_fakultas, nama_fakultas FROM fakultas WHERE nama_fakultas LIKE '%" + pencarian + "%' OR kode_fakultas = '" + pencarian + "'";
            ResultSet res = Fungsi.getResult(sql);
            
            while (res.next()) {
                hasil.add(new Fakultas(
                        res.getString("kode_fakultas"),
                        res.getString("nama_fakultas")
                ));
            }
        } catch (Exception e) {
            Logger.error(this, e.getMessage());
        }
        
        return hasil;
    }
}
