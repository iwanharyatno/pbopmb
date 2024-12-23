package org.delimare.pmb.entitymanager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.delimare.pmb.entity.CalonMahasiswa;
import org.delimare.pmb.function.Fungsi;
import org.delimare.pmb.function.Logger;

/**
 *
 * @author MyBook Z Series
 */
public class CalonMahasiswaManager {

    public int insert(CalonMahasiswa cm) {
        int result = 0;
        
        try {
            String sql = "INSERT INTO calon_mahasiswa (nik_ktp, nama_lengkap, tempat_lahir, tanggal_lahir, nisn, no_telepon, email, program_studi, agama, status_perkawinan, status_pendaftaran, jumlah_anak, tahun_daftar, id_alamat, created_at, updated_at) VALUES ('"
                    + cm.getNikKtp() + "', '"
                    + cm.getNamaLengkap() + "', '"
                    + cm.getTempatLahir() + "', '"
                    + new java.sql.Date(cm.getTanggalLahir().getTime()) + "', '"
                    + cm.getNisn() + "', '"
                    + cm.getNoTelepon() + "', '"
                    + cm.getEmail() + "', '"
                    + cm.getProgramStudi() + "', '"
                    + cm.getAgama() + "', '"
                    + cm.getStatusPerkawinan() + "', '"
                    + cm.getStatusPendaftaran() + "', "
                    + cm.getJumlahAnak() + ", "
                    + cm.getTahunDaftar() + ", "
                    + cm.getIdAlamat() + ", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);";
            result = Fungsi.EQueryReturnsID(sql);
        } catch (Exception e) {
            result = -1;
            Logger.error(this, e.getMessage());
        }
        
        return result;
    }
    
    public int insert(CalonMahasiswa cm, Connection con) {
        int result = 0;
        
        try {
            String sql = "INSERT INTO calon_mahasiswa (nik_ktp, nama_lengkap, tempat_lahir, tanggal_lahir, nisn, no_telepon, email, program_studi, agama, status_perkawinan, status_pendaftaran, jumlah_anak, tahun_daftar, id_alamat, created_at, updated_at) VALUES ('"
                    + cm.getNikKtp() + "', '"
                    + cm.getNamaLengkap() + "', '"
                    + cm.getTempatLahir() + "', '"
                    + new java.sql.Date(cm.getTanggalLahir().getTime()) + "', '"
                    + cm.getNisn() + "', '"
                    + cm.getNoTelepon() + "', '"
                    + cm.getEmail() + "', '"
                    + cm.getProgramStudi() + "', '"
                    + cm.getAgama() + "', '"
                    + cm.getStatusPerkawinan() + "', '"
                    + cm.getStatusPendaftaran() + "', "
                    + cm.getJumlahAnak() + ", "
                    + cm.getTahunDaftar() + ", "
                    + cm.getIdAlamat() + ", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);";
            result = Fungsi.EQueryReturnsID(sql, con);
        } catch (Exception e) {
            result = -1;
            Logger.error(this, e.getMessage());
        }
        
        return result;
    }

    public int update(CalonMahasiswa cm) {
        int result = 0;
        
        try {
            String sql = "UPDATE calon_mahasiswa SET nama_lengkap = '" + cm.getNamaLengkap()
                    + "', tempat_lahir = '" + cm.getTempatLahir()
                    + "', tanggal_lahir = '" + new java.sql.Date(cm.getTanggalLahir().getTime())
                    + "', nisn = '" + cm.getNisn()
                    + "', no_telepon = '" + cm.getNoTelepon()
                    + "', email = '" + cm.getEmail()
                    + "', program_studi = '" + cm.getProgramStudi()
                    + "', agama = '" + cm.getAgama()
                    + "', status_perkawinan = '" + cm.getStatusPerkawinan()
                    + "', status_pendaftaran = '" + cm.getStatusPendaftaran()
                    + "', jumlah_anak = " + cm.getJumlahAnak()
                    + ", tahun_daftar = " + cm.getTahunDaftar()
                    + ", id_alamat = " + cm.getIdAlamat()
                    + ", updated_at = CURRENT_TIMESTAMP"
                    + " WHERE id = " + cm.getId() + ";";
            result = Fungsi.EQuery(sql);
        } catch (Exception e) {
            result = -1;
            Logger.error(this, e.getMessage());
        }
        
        return result;
    }
    
    public int updateStatusPendaftaran(int id, String status) {
        int result = 0;
        
        try {
            String sql = "UPDATE calon_mahasiswa SET status_pendaftaran = '" + status
                    + "', updated_at = CURRENT_TIMESTAMP"
                    + " WHERE id_calon = " + id + ";";
            result = Fungsi.EQuery(sql);
        } catch (Exception e) {
            result = -1;
            Logger.error(this, e.getMessage());
        }
        
        return result;
    }
    
    public int update(CalonMahasiswa cm, Connection con) {
        int result = 0;
        
        try {
            String sql = "UPDATE calon_mahasiswa SET nama_lengkap = '" + cm.getNamaLengkap()
                    + "', tempat_lahir = '" + cm.getTempatLahir()
                    + "', tanggal_lahir = '" + new java.sql.Date(cm.getTanggalLahir().getTime())
                    + "', nisn = '" + cm.getNisn()
                    + "', no_telepon = '" + cm.getNoTelepon()
                    + "', email = '" + cm.getEmail()
                    + "', program_studi = '" + cm.getProgramStudi()
                    + "', agama = '" + cm.getAgama()
                    + "', status_perkawinan = '" + cm.getStatusPerkawinan()
                    + "', status_pendaftaran = '" + cm.getStatusPendaftaran()
                    + "', jumlah_anak = " + cm.getJumlahAnak()
                    + ", tahun_daftar = " + cm.getTahunDaftar()
                    + ", id_alamat = " + cm.getIdAlamat()
                    + ", updated_at = CURRENT_TIMESTAMP"
                    + " WHERE id = " + cm.getId() + ";";
            result = Fungsi.EQuery(sql, con);
        } catch (Exception e) {
            result = -1;
            Logger.error(this, e.getMessage());
        }
        
        return result;
    }

    public int delete(int id) {
        int result = 0;
        
        try {
            String sql = "DELETE FROM calon_mahasiswa WHERE id = " + id + ";";
            result = Fungsi.EQuery(sql);
        } catch (Exception e) {
            result = -1;
            Logger.error(this, e.getMessage());
        }
        
        return result;
    }
    
    public int delete(int id, Connection con) {
        int result = 0;
        
        try {
            String sql = "DELETE FROM calon_mahasiswa WHERE id = " + id + ";";
            result = Fungsi.EQuery(sql, con);
        } catch (Exception e) {
            result = -1;
            Logger.error(this, e.getMessage());
        }
        
        return result;
    }

    public ArrayList<CalonMahasiswa> getSemua(String pencarian) {
        ArrayList<CalonMahasiswa> hasil = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM calon_mahasiswa WHERE nama_lengkap LIKE '%" + pencarian + "%' OR nik_ktp = '" + pencarian + "';";
            ResultSet res = Fungsi.getResult(sql);
            
            while (res.next()) {
                CalonMahasiswa cm = new CalonMahasiswa();
                cm.setId(res.getInt("id"));
                cm.setNikKtp(res.getString("nik_ktp"));
                cm.setNamaLengkap(res.getString("nama_lengkap"));
                cm.setTempatLahir(res.getString("tempat_lahir"));
                cm.setTanggalLahir(res.getDate("tanggal_lahir"));
                cm.setNisn(res.getString("nisn"));
                cm.setNoTelepon(res.getString("no_telepon"));
                cm.setEmail(res.getString("email"));
                cm.setProgramStudi(res.getString("program_studi"));
                cm.setAgama(res.getString("agama"));
                cm.setStatusPerkawinan(res.getString("status_perkawinan"));
                cm.setStatusPendaftaran(res.getString("status_pendaftaran"));
                cm.setJumlahAnak(res.getInt("jumlah_anak"));
                cm.setTahunDaftar(res.getInt("tahun_daftar"));
                cm.setIdAlamat(res.getInt("id_alamat"));
                cm.setCreatedAt(res.getTimestamp("created_at"));
                cm.setUpdatedAt(res.getTimestamp("updated_at"));
                
                hasil.add(cm);
            }
        } catch (Exception e) {
            Logger.error(this, e.getMessage());
        }
        
        return hasil;
    }
}