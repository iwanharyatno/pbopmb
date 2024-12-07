/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.delimare.pmb.entitymanager;

import java.sql.ResultSet;
import java.util.ArrayList;
import org.delimare.pmb.entity.Fakultas;
import org.delimare.pmb.entity.ProgramStudi;
import org.delimare.pmb.function.Fungsi;
import org.delimare.pmb.function.Logger;

/**
 *
 * @author MyBook Z Series
 */
public class ProgramStudiManager {
    public int insert(ProgramStudi ps) {
        int result = 0;
        
        try {
            String sql = "INSERT INTO program_studi (kode_prodi, nama_prodi, fakultas, kuota) VALUES ('" + ps.getKode() + "', '" + ps.getNama() + "', '" + ps.getFakultas() + "', '" + ps.getKuota() + "');";
            result = Fungsi.EQuery(sql);
        } catch (Exception e) {
            result = -1;
            Logger.error(this, e.getMessage());
        }
        
        return result;
    }
    public int update(ProgramStudi ps) {
        int result = 0;
        
        try {
            String sql = "UPDATE program_studi SET nama_prodi = '" + ps.getNama()+ "', fakultas = '" + ps.getFakultas() + "', kuota = '" + ps.getKuota() + "' WHERE kode_prodi = '" + ps.getKode() + "'";
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
            String sql = "DELETE FROM program_studi WHERE kode_prodi = '" + kode + "'";
            result = Fungsi.EQuery(sql);
        } catch (Exception e) {
            result = -1;
            Logger.error(this, e.getMessage());
        }
        
        return result;
    }
    
    public ArrayList<ProgramStudi> getSemua(String pencarian) {
        ArrayList<ProgramStudi> hasil = new ArrayList<>();
        
        try {
            String sql = "SELECT kode_prodi, nama_prodi, fakultas, nama_fakultas, kuota FROM program_studi INNER JOIN fakultas ON fakultas.kode_fakultas = program_studi.fakultas WHERE nama_prodi LIKE '%" + pencarian + "%' OR fakultas LIKE '" + pencarian + "' OR kode_prodi = '" + pencarian + "'";
            ResultSet res = Fungsi.getResult(sql);
            
            while (res.next()) {
                hasil.add(new ProgramStudi(
                        res.getString("kode_prodi"),
                        res.getString("nama_prodi"),
                        res.getString("fakultas"),
                        res.getInt("kuota"),
                        new Fakultas(res.getString("fakultas"), res.getString("nama_fakultas"))
                ));
            }
        } catch (Exception e) {
            Logger.error(this, e.getMessage());
        }
        
        return hasil;
    }
}
