/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.delimare.pmb.entitymanager;


import java.sql.ResultSet;
import java.util.ArrayList;
import org.delimare.pmb.entity.Ruangan;
import org.delimare.pmb.function.Fungsi;
import org.delimare.pmb.function.Logger;

/**
 *
 * @author smart user
 */
public class RuanganManager {
     public int insert(Ruangan r ) {
        int result = 0;
        
        try {
            String sql = "INSERT INTO ruangan (id_ruangan, nama_ruangan, Kapasitas) VALUES ('" + r.getIdRuang()+ "', '" + r.getNamaRuang()+ "', '" + r.getKapasitas()+ "');";
            result = Fungsi.EQuery(sql);
        } catch (Exception e) {
            result = -1;
            Logger.error(this, e.getMessage());
        }
        
        return result;
}
     public ArrayList<Ruangan> getSemua(String pencarian) {
        ArrayList<Ruangan> hasil = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM ruangan " ;
            ResultSet res = Fungsi.getResult(sql);
            
            while (res.next()) {
                hasil.add(new Ruangan(
                        res.getString("id_ruangan"),
                        res.getString("nama_ruangan"),
                        res.getInt("kapasitas")
                      
                ));
            }
        } catch (Exception e) {
            Logger.error(this, e.getMessage());
        }
        
        return hasil;
    }
}
