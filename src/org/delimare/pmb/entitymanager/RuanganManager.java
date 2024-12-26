/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.delimare.pmb.entitymanager;


import java.sql.ResultSet;
import java.util.ArrayList;
import org.delimare.pmb.entity.Gedung;
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
            String sql = "INSERT INTO ruangan (id_ruangan, nama_ruangan, Kapasitas,id_gedung ) VALUES  ('" + r.getIdRuang()+ "', '" + r.getNamaRuang()+ "', '" + r.getKapasitas()+ "','" + r.getGedung()+ "');";
            result = Fungsi.EQuery(sql);
        } catch (Exception e) {
            result = -1;
            Logger.error(this, e.getMessage());
        }
        
        return result;
}
      public int update(Ruangan r) {
        int result = 0;
        
        try {
            String sql = "UPDATE ruangan SET nama_ruangan = '" + r.getNamaRuang()+ "', Kapasitas = '" + r.getKapasitas() + "' " + "WHERE id_ruangan = '" + r.getIdRuang() + "'";
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
        String sql = "SELECT ruangan.id_ruangan, ruangan.nama_ruangan, ruangan.Kapasitas, ruangan.id_gedung, gedung.nama_gedung " +
             "FROM ruangan " +
             "INNER JOIN gedung ON gedung.id_gedung = ruangan.id_gedung " +
             "WHERE ruangan.nama_ruangan LIKE '%" + pencarian + "%' " +
             "OR gedung.nama_gedung LIKE '%" + pencarian + "%' " +
             "OR ruangan.id_ruangan LIKE '%" + pencarian + "%'";


        ResultSet res = Fungsi.getResult(sql);
        while (res.next()) {
            hasil.add(new Ruangan(
                res.getString("id_ruangan"),
                res.getString("nama_ruangan"),
                res.getInt("kapasitas"), 
                res.getString("id_gedung"),
                new Gedung(res.getString("id_gedung"), res.getString("nama_gedung"))
            ));
        }
    } catch (Exception e) {
        Logger.error(this, "Error: " + e.getMessage());
        
    }
    return hasil;
}

    
}
