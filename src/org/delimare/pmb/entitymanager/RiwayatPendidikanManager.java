package org.delimare.pmb.entitymanager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.delimare.pmb.entity.RiwayatPendidikan;
import org.delimare.pmb.function.Fungsi;
import org.delimare.pmb.function.Logger;

/**
 *
 * @author MyBook Z Series
 */
public class RiwayatPendidikanManager {

    public int insert(RiwayatPendidikan riwayat) {
        int result = 0;

        try {
            String sql = "INSERT INTO riwayat_pendidikan (id_calon, sekolah, nama_sekolah, kabupaten, provinsi, tahun_lulus) "
                       + "VALUES (" + riwayat.getIdCalon() + ", '" + riwayat.getSekolah() + "', '" + riwayat.getNamaSekolah() + "', "
                       + "'" + riwayat.getKabupaten() + "', '" + riwayat.getProvinsi() + "', " + riwayat.getTahunLulus() + ");";
            result = Fungsi.EQuery(sql);
        } catch (Exception e) {
            result = -1;
            Logger.error(this, e.getMessage());
        }

        return result;
    }
    
    public int insert(RiwayatPendidikan riwayat, Connection con) {
        int result = 0;

        try {
            String sql = "INSERT INTO riwayat_pendidikan (id_calon, sekolah, nama_sekolah, kabupaten, provinsi, tahun_lulus) "
                       + "VALUES (" + riwayat.getIdCalon() + ", '" + riwayat.getSekolah() + "', '" + riwayat.getNamaSekolah() + "', "
                       + "'" + riwayat.getKabupaten() + "', '" + riwayat.getProvinsi() + "', " + riwayat.getTahunLulus() + ");";
            result = Fungsi.EQuery(sql, con);
        } catch (Exception e) {
            result = -1;
            Logger.error(this, e.getMessage());
        }

        return result;
    }

    public int update(RiwayatPendidikan riwayat) {
        int result = 0;

        try {
            String sql = "UPDATE riwayat_pendidikan SET id_calon = " + riwayat.getIdCalon() + ", sekolah = '" + riwayat.getSekolah() + "', "
                       + "nama_sekolah = '" + riwayat.getNamaSekolah() + "', kabupaten = '" + riwayat.getKabupaten() + "', "
                       + "provinsi = '" + riwayat.getProvinsi() + "', tahun_lulus = " + riwayat.getTahunLulus() + " "
                       + "WHERE id = " + riwayat.getId() + ";";
            result = Fungsi.EQuery(sql);
        } catch (Exception e) {
            result = -1;
            Logger.error(this, e.getMessage());
        }

        return result;
    }
    
    public int update(RiwayatPendidikan riwayat, Connection con) {
        int result = 0;

        try {
            String sql = "UPDATE riwayat_pendidikan SET id_calon = " + riwayat.getIdCalon() + ", sekolah = '" + riwayat.getSekolah() + "', "
                       + "nama_sekolah = '" + riwayat.getNamaSekolah() + "', kabupaten = '" + riwayat.getKabupaten() + "', "
                       + "provinsi = '" + riwayat.getProvinsi() + "', tahun_lulus = " + riwayat.getTahunLulus() + " "
                       + "WHERE id = " + riwayat.getId() + ";";
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
            String sql = "DELETE FROM riwayat_pendidikan WHERE id = " + id + ";";
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
            String sql = "DELETE FROM riwayat_pendidikan WHERE id = " + id + ";";
            result = Fungsi.EQuery(sql, con);
        } catch (Exception e) {
            result = -1;
            Logger.error(this, e.getMessage());
        }

        return result;
    }

    public ArrayList<RiwayatPendidikan> getSemua(String pencarian) {
        ArrayList<RiwayatPendidikan> hasil = new ArrayList<>();

        try {
            String sql = "SELECT id, id_calon, sekolah, nama_sekolah, kabupaten, provinsi, tahun_lulus FROM riwayat_pendidikan "
                       + "WHERE sekolah LIKE '%" + pencarian + "%' OR nama_sekolah LIKE '%" + pencarian + "%' "
                       + "OR kabupaten LIKE '%" + pencarian + "%' OR provinsi LIKE '%" + pencarian + "%';";
            ResultSet res = Fungsi.getResult(sql);

            while (res.next()) {
                RiwayatPendidikan riwayat = new RiwayatPendidikan();
                riwayat.setId(res.getInt("id"));
                riwayat.setIdCalon(res.getInt("id_calon"));
                riwayat.setSekolah(res.getString("sekolah"));
                riwayat.setNamaSekolah(res.getString("nama_sekolah"));
                riwayat.setKabupaten(res.getString("kabupaten"));
                riwayat.setProvinsi(res.getString("provinsi"));
                riwayat.setTahunLulus(res.getInt("tahun_lulus"));
                hasil.add(riwayat);
            }
        } catch (Exception e) {
            Logger.error(this, e.getMessage());
        }

        return hasil;
    }
    
    public ArrayList<RiwayatPendidikan> getDariCalon(int idCalon) {
        ArrayList<RiwayatPendidikan> hasil = new ArrayList<>();

        try {
            String sql = "SELECT id, id_calon, sekolah, nama_sekolah, kabupaten, provinsi, tahun_lulus FROM riwayat_pendidikan "
                       + "WHERE id_calon = " + idCalon + ";";
            ResultSet res = Fungsi.getResult(sql);

            while (res.next()) {
                RiwayatPendidikan riwayat = new RiwayatPendidikan();
                riwayat.setId(res.getInt("id"));
                riwayat.setIdCalon(res.getInt("id_calon"));
                riwayat.setSekolah(res.getString("sekolah"));
                riwayat.setNamaSekolah(res.getString("nama_sekolah"));
                riwayat.setKabupaten(res.getString("kabupaten"));
                riwayat.setProvinsi(res.getString("provinsi"));
                riwayat.setTahunLulus(res.getInt("tahun_lulus"));
                hasil.add(riwayat);
            }
        } catch (Exception e) {
            Logger.error(this, e.getMessage());
        }

        return hasil;
    }
}