/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.delimare.pmb.entitymanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.delimare.pmb.connection.Koneksi;
import org.delimare.pmb.entity.Jadwal;
import org.delimare.pmb.function.Fungsi;

/**
 *
 * @author MyBook Z Series
 */
public class JadwalManager {
    
    public RuanganManager manager = new RuanganManager();
    
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd MMMM yyy, HH:mm");

    public void insert(int calonId, Connection con) throws SQLException {
        String sql = "SELECT"
                + "     (SELECT nilai FROM pengaturan WHERE kunci = \"tanggal_mulai_ujian\") as \"tanggal_mulai_ujian\", "
                + "     (SELECT nilai FROM pengaturan WHERE kunci = \"durasi_ujian\") as \"durasi_ujian\", "
                + "     (SELECT nilai FROM pengaturan WHERE kunci = \"durasi_jeda\") as \"durasi_jeda\" "
                + "FROM pengaturan LIMIT 1;";
        ResultSet res = Fungsi.getResult(sql, con);
        if (res.next()) {
            Date tglMulai = res.getTimestamp("tanggal_mulai_ujian");
            int durasiUjian = res.getInt("durasi_ujian");
            int durasiJeda = res.getInt("durasi_jeda");
            
            String idRuangan = null;
            int nomorMeja = 1;
            Date tglTerakhir = tglMulai;
            
            sql = "SELECT waktu FROM jadwal ORDER BY waktu DESC LIMIT 1";
            res = Fungsi.getResult(sql, con);
            
            if (res.next()) {
                tglTerakhir = res.getTimestamp("waktu");
            }
            
            sql = "SELECT ruangan.id_ruangan, ruangan.Kapasitas, waktu, "
                    + "COUNT(ruangan.id_ruangan) as penghuni, "
                    + "MAX(jadwal.nomor_meja) nomor_meja_terakhir "
                    + "FROM ruangan LEFT JOIN jadwal ON jadwal.id_ruangan = ruangan.id_ruangan "
                    + "AND waktu = (SELECT waktu FROM jadwal ORDER BY waktu DESC LIMIT 1) "
                    + "GROUP BY ruangan.id_ruangan HAVING penghuni < ruangan.Kapasitas LIMIT 1;";
            res = Fungsi.getResult(sql, con);
            
            if (res.next()) {
                idRuangan = res.getString("id_ruangan");
                nomorMeja = res.getInt("nomor_meja_terakhir") + 1;
            } else {
                sql = "SELECT id_ruangan FROM ruangan LIMIT 1";
                res = Fungsi.getResult(sql, con);
                if (res.next()) {
                    idRuangan = res.getString("id_ruangan");
                }
                
                Calendar calTglTerakhir = Calendar.getInstance();
                calTglTerakhir.setTime(tglTerakhir);
                calTglTerakhir.add(Calendar.MINUTE, durasiJeda + durasiUjian);
                tglTerakhir = calTglTerakhir.getTime();
            }
            
            sql = "INSERT INTO jadwal (`id_calon`, `id_ruangan`, `nomor_meja`, `waktu`) "
                    + "VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, calonId);
            preparedStatement.setString(2, idRuangan);
            preparedStatement.setInt(3, nomorMeja);
            preparedStatement.setTimestamp(4, new java.sql.Timestamp(tglTerakhir.getTime()));
            preparedStatement.execute();
        }
    }
    
    public ArrayList<Jadwal> getSemua() throws SQLException {
        ArrayList<Jadwal> list = new ArrayList<>();
        
        String sql = "SELECT * FROM jadwal GROUP BY waktu;";
        ResultSet result = Fungsi.getResult(sql);
        
        while (result.next()) {
            Jadwal jad = new Jadwal();
            jad.setId(result.getInt("id_jadwal"));
            jad.setIdCalon(result.getInt("id_calon"));
            jad.setIdRuangan(result.getString("id_ruangan"));
            jad.setNomorMeja(result.getInt("nomor_meja"));
            jad.setWaktu(result.getTimestamp("waktu"));
            list.add(jad);
        }
        
        return list;
    }
    
    public ArrayList<String> getSemuaSesi() throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        
        String sql = "SELECT DISTINCT waktu FROM jadwal;";
        ResultSet result = Fungsi.getResult(sql);
        
        while (result.next()) {
            list.add(DATE_FORMAT.format(result.getTimestamp("waktu")));
        }
        
        return list;
    }
    
    public static void main(String[] args) {
        JadwalManager manager = new JadwalManager();
        Koneksi k = new Koneksi();
        try {
            manager.insert(35, k.getConnection());
        } catch (SQLException ex) {
            Logger.getLogger(JadwalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}