/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.delimare.pmb.entitymanager;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.delimare.pmb.entity.Gedung;
import org.delimare.pmb.function.Fungsi;
import org.delimare.pmb.function.Logger;

/**
 *
 * @author satri
 */
public class GedungManager {
    public int insert(Gedung gd) {
        int result = 0;
        
        try {
            String sql = "INSERT INTO gedung (id_gedung, nama_gedung, alamat_gedung) VALUES ('" + gd.getKodegedung() + "', '" + gd.getNamagedung() + "','"+ gd.getAlamatgedung()+"')";;
            result = Fungsi.EQuery(sql);
        } catch (Exception e) {
            result = -1;
            Logger.error(this, e.getMessage());
        }
        
        return result;
    }

    public int update(Gedung gd) {
        int result = 0;
        
        try {
            String sql = "UPDATE gedung SET alamat_gedung = '" + gd.getAlamatgedung()+ "' WHERE id_gedung = '" + gd.getKodegedung() + "'";
            result = Fungsi.EQuery(sql);
        } catch (Exception e) {
            result = -1;
            Logger.error(this, e.getMessage());
        }
        
        return result;
    }
    
    public int delete(String id) {
        int result = 0;
        
        try {
            String sql = "DELETE FROM gedung WHERE id_gedung = '" + id + "'";
            result = Fungsi.EQuery(sql);
        } catch (Exception e) {
            result = -1;
            Logger.error(this, e.getMessage());
        }
        
        return result;
    }
    public List<Gedung> getSemua(String cari) {
        ArrayList<Gedung> hasil = new ArrayList<>();
        
        try {
            String sql = "SELECT id_gedung, nama_gedung,alamat_gedung FROM gedung WHERE nama_gedung LIKE '%" +cari+ "%' OR id_gedung = '" + cari + "'";
            String sql = "SELECT id_gedung, nama_gedung,alamat_gedung FROM gedung WHERE nama_gedung LIKE '%" + cari + "%' OR id_gedung LIKE '%" + cari +  "%'" ;
            ResultSet res = Fungsi.getResult(sql);
            
            while (res.next()) {
                hasil.add(new Gedung(
                        res.getString("id_gedung"),
                        res.getString("nama_gedung"),
                        res.getString("alamat_gedung")
                ));
            }
        } catch (Exception e) {
            Logger.error(this, e.getMessage());
        }
        
        return hasil;
    }
}
