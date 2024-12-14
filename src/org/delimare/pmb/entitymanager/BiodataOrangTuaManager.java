package org.delimare.pmb.entitymanager;

import java.sql.ResultSet;
import java.util.ArrayList;
import org.delimare.pmb.entity.BiodataOrangTua;
import org.delimare.pmb.function.Fungsi;
import org.delimare.pmb.function.Logger;

/**
 *
 * @author MyBook Z Series
 */
public class BiodataOrangTuaManager {

    public int insert(BiodataOrangTua bot) {
        int result = 0;

        try {
            String sql = "INSERT INTO biodata_orangtua (id_calon, id_alamat_ayah, id_alamat_ibu, nama_ayah, pendidikan_ayah, pekerjaan_ayah, status_ayah, pangkat_ayah, jabatan_ayah, instansi_ayah, no_hp_ayah, nip_ayah, nama_ibu, pendidikan_ibu, pekerjaan_ibu, status_ibu, pangkat_ibu, jabatan_ibu, instansi_ibu, no_hp_ibu, nip_ibu) VALUES ("
                    + bot.getIdCalon() + ", "
                    + bot.getIdAlamatAyah() + ", "
                    + bot.getIdAlamatIbu() + ", '"
                    + bot.getNamaAyah() + "', '"
                    + bot.getPendidikanAyah() + "', '"
                    + bot.getPekerjaanAyah() + "', '"
                    + bot.getStatusAyah() + "', '"
                    + bot.getPangkatAyah() + "', '"
                    + bot.getJabatanAyah() + "', '"
                    + bot.getInstansiAyah() + "', '"
                    + bot.getNoHpAyah() + "', '"
                    + bot.getNipAyah() + "', '"
                    + bot.getNamaIbu() + "', '"
                    + bot.getPendidikanIbu() + "', '"
                    + bot.getPekerjaanIbu() + "', '"
                    + bot.getStatusIbu() + "', '"
                    + bot.getPangkatIbu() + "', '"
                    + bot.getJabatanIbu() + "', '"
                    + bot.getInstansiIbu() + "', '"
                    + bot.getNoHpIbu() + "', '"
                    + bot.getNipIbu() + "');";
            result = Fungsi.EQuery(sql);
        } catch (Exception e) {
            result = -1;
            Logger.error(this, e.getMessage());
        }

        return result;
    }

    public int update(BiodataOrangTua bot) {
        int result = 0;

        try {
            String sql = "UPDATE biodata_orangtua SET id_calon = " + bot.getIdCalon()
                    + ", id_alamat_ayah = " + bot.getIdAlamatAyah()
                    + ", id_alamat_ibu = " + bot.getIdAlamatIbu()
                    + ", nama_ayah = '" + bot.getNamaAyah() + "'"
                    + ", pendidikan_ayah = '" + bot.getPendidikanAyah() + "'"
                    + ", pekerjaan_ayah = '" + bot.getPekerjaanAyah() + "'"
                    + ", status_ayah = '" + bot.getStatusAyah() + "'"
                    + ", pangkat_ayah = '" + bot.getPangkatAyah() + "'"
                    + ", jabatan_ayah = '" + bot.getJabatanAyah() + "'"
                    + ", instansi_ayah = '" + bot.getInstansiAyah() + "'"
                    + ", no_hp_ayah = '" + bot.getNoHpAyah() + "'"
                    + ", nip_ayah = '" + bot.getNipAyah() + "'"
                    + ", nama_ibu = '" + bot.getNamaIbu() + "'"
                    + ", pendidikan_ibu = '" + bot.getPendidikanIbu() + "'"
                    + ", pekerjaan_ibu = '" + bot.getPekerjaanIbu() + "'"
                    + ", status_ibu = '" + bot.getStatusIbu() + "'"
                    + ", pangkat_ibu = '" + bot.getPangkatIbu() + "'"
                    + ", jabatan_ibu = '" + bot.getJabatanIbu() + "'"
                    + ", instansi_ibu = '" + bot.getInstansiIbu() + "'"
                    + ", no_hp_ibu = '" + bot.getNoHpIbu() + "'"
                    + ", nip_ibu = '" + bot.getNipIbu() + "'"
                    + " WHERE id = " + bot.getId() + ";";
            result = Fungsi.EQuery(sql);
        } catch (Exception e) {
            result = -1;
            Logger.error(this, e.getMessage());
        }

        return result;
    }

    public int delete(int id) {
        int result = 0;

        try {
            String sql = "DELETE FROM biodata_orangtua WHERE id = " + id + ";";
            result = Fungsi.EQuery(sql);
        } catch (Exception e) {
            result = -1;
            Logger.error(this, e.getMessage());
        }

        return result;
    }

    public ArrayList<BiodataOrangTua> getSemua(String pencarian) {
        ArrayList<BiodataOrangTua> hasil = new ArrayList<>();

        try {
            String sql = "SELECT * FROM biodata_orangtua WHERE nama_ayah LIKE '%" + pencarian + "%' OR nama_ibu LIKE '%" + pencarian + "%';";
            ResultSet res = Fungsi.getResult(sql);

            while (res.next()) {
                BiodataOrangTua bot = new BiodataOrangTua();
                bot.setId(res.getInt("id"));
                bot.setIdCalon(res.getInt("id_calon"));
                bot.setIdAlamatAyah(res.getInt("id_alamat_ayah"));
                bot.setIdAlamatIbu(res.getInt("id_alamat_ibu"));
                bot.setNamaAyah(res.getString("nama_ayah"));
                bot.setPendidikanAyah(res.getString("pendidikan_ayah"));
                bot.setPekerjaanAyah(res.getString("pekerjaan_ayah"));
                bot.setStatusAyah(res.getString("status_ayah"));
                bot.setPangkatAyah(res.getString("pangkat_ayah"));
                bot.setJabatanAyah(res.getString("jabatan_ayah"));
                bot.setInstansiAyah(res.getString("instansi_ayah"));
                bot.setNoHpAyah(res.getString("no_hp_ayah"));
                bot.setNipAyah(res.getString("nip_ayah"));
                bot.setNamaIbu(res.getString("nama_ibu"));
                bot.setPendidikanIbu(res.getString("pendidikan_ibu"));
                bot.setPekerjaanIbu(res.getString("pekerjaan_ibu"));
                bot.setStatusIbu(res.getString("status_ibu"));
                bot.setPangkatIbu(res.getString("pangkat_ibu"));
                bot.setJabatanIbu(res.getString("jabatan_ibu"));
                bot.setInstansiIbu(res.getString("instansi_ibu"));
                bot.setNoHpIbu(res.getString("no_hp_ibu"));
                bot.setNipIbu(res.getString("nip_ibu"));

                hasil.add(bot);
            }
        } catch (Exception e) {
            Logger.error(this, e.getMessage());
        }

        return hasil;
    }
}
