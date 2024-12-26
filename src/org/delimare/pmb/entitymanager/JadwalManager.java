/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.delimare.pmb.entitymanager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import org.delimare.pmb.entity.Jadwal;
import org.delimare.pmb.function.Fungsi;
import org.delimare.pmb.function.Logger;

/**
 *
 * @author MyBook Z Series
 */
public class JadwalManager {
    
    public RuanganManager manager = new RuanganManager();

    public void insert(Jadwal jadwal) throws SQLException {
        String sql = "SELECT"
                + "     (SELECT nilai FROM pengaturan WHERE kunci = \"tanggal_mulai_ujian\") as \"tanggal_mulai_ujian\", "
                + "     (SELECT nilai FROM pengaturan WHERE kunci = \"durasi_ujian\") as \"durasi_ujian\", "
                + "     (SELECT nilai FROM pengaturan WHERE kunci = \"durasi_jeda\") as \"durasi_jeda\" "
                + "FROM pengaturan LIMIT 1; SELECT COUNT(id_jadwal) as count FROM jadwal WHERE YEAR(waktu) = YEAR(CURRENT_DATE());";
        Statement result = Fungsi.getResults(sql);
        if (result != null) {
            ResultSet res = result.getResultSet();
            Date tglMulai = res.getDate("tanggal_mulai_ujian");
            int durasiUjian = res.getInt("durasi_ujian");
            int durasiJeda = res.getInt("durasi_jeda");
            res.close();
            
            result.getMoreResults();
            
            res = result.getResultSet();
            
            int rows = res.getInt("count");
            
            if (rows == 0) {
                
            }
        }
    }
}
