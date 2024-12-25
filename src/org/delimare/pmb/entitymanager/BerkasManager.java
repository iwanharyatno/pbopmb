/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.delimare.pmb.entitymanager;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.delimare.pmb.entity.Berkas;
import org.delimare.pmb.function.Fungsi;
import org.delimare.pmb.function.Logger;

/**
 *
 * @author DENDI
 */
public class BerkasManager {
    public int insert(Berkas bk) {
        int result = 0;
        
        try {
            String sql = "INSERT INTO berkas_pendaftaran (id_calon,jenis_berkas, nama_file, path_file) VALUES ('"+ bk.getId_calon()+"', '" + bk.getJenisberkas() + "', '" + bk.getNamaberkas() + "','"+ bk.getPathfile()+"')";;
            result = Fungsi.EQuery(sql);
        } catch (Exception e) {
            result = -1;
            Logger.error(this, e.getMessage());
        }
        
        return result;
    }

    public int delete(Berkas bk) {
        int result = 0;
        
        try {
        // Gunakan bk.getId() untuk mengambil ID
        String sql = "DELETE FROM berkas_pendaftaran WHERE id_berkas = '" + bk.getId_berkas()+ "'";
        result = Fungsi.EQuery(sql);
    } catch (Exception e) {
        result = -1;
        Logger.error(this, e.getMessage());
    }
    
    return result;
    }
    public List<Berkas> getSemua(String cari) {
        ArrayList<Berkas> hasil = new ArrayList<>();
        
        try {
            String sql = "SELECT id_berkas, id_calon,jenis_berkas, nama_file,path_file FROM berkas_pendaftaran WHERE id_berkas LIKE '%" + cari + "%' OR id_calon = '" + cari + "'";
            ResultSet res = Fungsi.getResult(sql);
            
            while (res.next()) {
                hasil.add(new Berkas(
                        res.getString("id_berkas"),
                        res.getString("id_calon"),
                        res.getString("jenis_berkas"),
                        res.getString("nama_file"),
                        res.getString("path_file")        
                ));
            }
        } catch (Exception e) {
            Logger.error(this, e.getMessage());
        }
        
        return hasil;
    }
    
    public List<Integer> getAllIdCalon() {
    List<Integer> idCalonList = new ArrayList<>();
    try {
        String sql = "SELECT DISTINCT id_calon FROM calon_mahasiswa";
        ResultSet res = Fungsi.getResult(sql);
        
        while (res.next()) {
            idCalonList.add(res.getInt("id_calon"));
        }
    } catch (Exception e) {
        Logger.error(this, e.getMessage());
    }
    return idCalonList;
    }
    
    public String getNamaCalonById(Integer idCalon) {
    String namaCalon = null;
    try {
        // SQL query tanpa parameter placeholder
        String sql = "SELECT nama_lengkap FROM calon_mahasiswa WHERE id_calon = '" + idCalon + "'";
        
        // Menjalankan query menggunakan Statement
        ResultSet res = Fungsi.getResult(sql);
        
        // Jika ada hasil, ambil nama calon
        if (res.next()) {
            namaCalon = res.getString("nama_lengkap");
        }
        
    } catch (Exception e) {
        Logger.error(this, "Error mendapatkan nama calon: " + e.getMessage());
    }
    return namaCalon;
    }



}
