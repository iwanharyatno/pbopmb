/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.delimare.pmb.entitymanager;

import java.sql.ResultSet;
import java.util.ArrayList;
import org.delimare.pmb.entity.CalonMahasiswa;
import org.delimare.pmb.entity.ProgramStudi;
import org.delimare.pmb.function.Fungsi;
import org.delimare.pmb.function.Logger;

/**
 *
 * @author MyBook Z Series
 */
public class PesertaManager {
    public ArrayList<CalonMahasiswa> getSemua(String pencarian) {
        ArrayList<CalonMahasiswa> hasil = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM calon_mahasiswa "
                    + "INNER JOIN program_studi ON program_studi.kode_prodi = calon_mahasiswa.program_studi "
                    + "WHERE nama_lengkap LIKE '%" + pencarian + "%' OR nik_ktp = '" + pencarian + "';";
            ResultSet res = Fungsi.getResult(sql);
            
            while (res.next()) {
                CalonMahasiswa cm = new CalonMahasiswa();
                cm.setId(res.getInt("id_calon"));
                cm.setNamaLengkap(res.getString("nama_lengkap"));
                cm.setNoTelepon(res.getString("no_telepon"));
                cm.setEmail(res.getString("email"));
                cm.setProgramStudi(res.getString("program_studi"));
                cm.setStatusPendaftaran(res.getString("status_pendaftaran"));
                cm.setTahunDaftar(res.getInt("tahun_daftar"));
                cm.setCreatedAt(res.getTimestamp("created_at"));
                
                cm.setProgramStudiObj(new ProgramStudi(res.getString("kode_prodi"), res.getString("nama_prodi")));
                
                hasil.add(cm);
            }
        } catch (Exception e) {
            Logger.error(this, e.getMessage());
        }
        
        return hasil;
    }
}
