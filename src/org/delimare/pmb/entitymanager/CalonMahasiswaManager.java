package org.delimare.pmb.entitymanager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.delimare.pmb.entity.Alamat;
import org.delimare.pmb.entity.BiodataOrangTua;
import org.delimare.pmb.entity.CalonMahasiswa;
import org.delimare.pmb.entity.ProgramStudi;
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
                    + " WHERE id_calon = " + cm.getId() + ";";
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
                    + " WHERE id_calon = " + cm.getId() + ";";
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
            String sql = "SELECT * FROM calon_mahasiswa INNER JOIN program_studi ON program_studi.kode_prodi = calon_mahasiswa.program_studi WHERE nama_lengkap LIKE '%" + pencarian + "%' OR nik_ktp = '" + pencarian + "';";
            ResultSet res = Fungsi.getResult(sql);

            while (res.next()) {
                CalonMahasiswa cm = new CalonMahasiswa();
                cm.setId(res.getInt("id_calon"));
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
                
                cm.setProgramStudiObj(new ProgramStudi(res.getString("kode_prodi"), res.getString("nama_prodi")));

                hasil.add(cm);
            }
        } catch (Exception e) {
            Logger.error(this, e.getMessage());
        }

        return hasil;
    }

    public CalonMahasiswa getSingle(int calonId) {
        CalonMahasiswa hasil = null;

        try {
            String sql = "SELECT * FROM calon_mahasiswa WHERE id_calon = " + calonId + ";";
            ResultSet res = Fungsi.getResult(sql);

            if (res.next()) {
                hasil = new CalonMahasiswa();
                hasil.setId(res.getInt("id_calon"));
                hasil.setNikKtp(res.getString("nik_ktp"));
                hasil.setNamaLengkap(res.getString("nama_lengkap"));
                hasil.setTempatLahir(res.getString("tempat_lahir"));
                hasil.setTanggalLahir(res.getDate("tanggal_lahir"));
                hasil.setNisn(res.getString("nisn"));
                hasil.setNoTelepon(res.getString("no_telepon"));
                hasil.setEmail(res.getString("email"));
                hasil.setProgramStudi(res.getString("program_studi"));
                hasil.setAgama(res.getString("agama"));
                hasil.setStatusPerkawinan(res.getString("status_perkawinan"));
                hasil.setStatusPendaftaran(res.getString("status_pendaftaran"));
                hasil.setJumlahAnak(res.getInt("jumlah_anak"));
                hasil.setTahunDaftar(res.getInt("tahun_daftar"));
                hasil.setIdAlamat(res.getInt("id_alamat"));
                hasil.setCreatedAt(res.getTimestamp("created_at"));
                hasil.setUpdatedAt(res.getTimestamp("updated_at"));
            }
        } catch (Exception e) {
            Logger.error(this, e.getMessage());
        }

        return hasil;
    }

    public CalonMahasiswa getSingleWithRelations(int calonId) {
        CalonMahasiswa hasil = null;

        try {
            String sql = "SELECT"
                    + " cm.id_calon, cm.nik_ktp, cm.nama_lengkap, cm.tempat_lahir, cm.tanggal_lahir, cm.agama, cm.status_perkawinan, cm.jumlah_anak,"
                    + " cm.nisn, cm.jenis_kelamin, cm.no_telepon, cm.email, cm.program_studi, cm.tahun_daftar, cm.status_pendaftaran, cm.created_at,"
                    + " cm.updated_at,"
                    
                    + " bt.id as 'id_biodata_orangtua', bt.nama_ayah, bt.pendidikan_ayah, bt.pekerjaan_ayah, bt.status_ayah, bt.no_hp_ayah, bt.nip_ayah,"
                    + " bt.pangkat_ayah, bt.jabatan_ayah, bt.instansi_ayah, bt.nama_ibu, bt.pendidikan_ibu, bt.pekerjaan_ibu, bt.status_ibu, bt.no_hp_ibu,"
                    + " bt.nip_ibu, bt.pangkat_ibu, bt.jabatan_ibu, bt.instansi_ibu,"
                    
                    + " a_cm.id AS 'id_alamat_calon', a_cm.rt AS 'rt_calon', a_cm.rw AS 'rw_calon', a_cm.kecamatan AS 'kecamatan_calon',"
                    + " a_cm.kabupaten AS 'kabupaten_calon', a_cm.provinsi AS 'provinsi_calon', a_cm.kode_pos AS 'kode_pos_calon',"
                    + " a_cm.alamat AS 'alamat_calon',"
                    
                    + " a_ibu.id AS 'id_alamat_ibu', a_ibu.alamat AS 'alamat_ibu',"
                    + " a_ayah.id AS 'id_alamat_ayah', a_ayah.alamat AS 'alamat_ayah',"
                    
                    + " ps.nama_prodi "
                    + "FROM calon_mahasiswa cm "
                    + "INNER JOIN alamat a_cm ON a_cm.id = cm.id_alamat "
                    + "INNER JOIN biodata_orangtua bt ON bt.id_calon = cm.id_calon "
                    + "INNER JOIN alamat a_ibu ON a_ibu.id = bt.id_alamat_ibu "
                    + "INNER JOIN alamat a_ayah ON a_ayah.id = bt.id_alamat_ayah "
                    + "INNER JOIN program_studi ps ON ps.kode_prodi = cm.program_studi "
                    + "WHERE cm.id_calon = " + calonId + ";";
            ResultSet res = Fungsi.getResult(sql);

            if (res.next()) {
                hasil = new CalonMahasiswa();
                hasil.setId(res.getInt("id_calon"));
                hasil.setNikKtp(res.getString("nik_ktp"));
                hasil.setNamaLengkap(res.getString("nama_lengkap"));
                hasil.setTempatLahir(res.getString("tempat_lahir"));
                hasil.setTanggalLahir(res.getDate("tanggal_lahir"));
                hasil.setNisn(res.getString("nisn"));
                hasil.setNoTelepon(res.getString("no_telepon"));
                hasil.setEmail(res.getString("email"));
                hasil.setProgramStudi(res.getString("program_studi"));
                hasil.setAgama(res.getString("agama"));
                hasil.setStatusPerkawinan(res.getString("status_perkawinan"));
                hasil.setStatusPendaftaran(res.getString("status_pendaftaran"));
                hasil.setJumlahAnak(res.getInt("jumlah_anak"));
                hasil.setTahunDaftar(res.getInt("tahun_daftar"));
                hasil.setIdAlamat(res.getInt("id_alamat_calon"));
                hasil.setCreatedAt(res.getTimestamp("created_at"));
                hasil.setUpdatedAt(res.getTimestamp("updated_at"));
                hasil.setJenisKelamnin(res.getString("jenis_kelamin"));

                Alamat alamat = new Alamat();
                alamat.setId(res.getInt("id_alamat_calon"));
                alamat.setRt(res.getInt("rt_calon"));
                alamat.setRw(res.getInt("rw_calon"));
                alamat.setKecamatan(res.getString("kecamatan_calon"));
                alamat.setKabupaten(res.getString("kabupaten_calon"));
                alamat.setProvinsi(res.getString("provinsi_calon"));
                alamat.setKodePos(res.getInt("kode_pos_calon"));
                alamat.setAlamat(res.getString("alamat_calon"));
                hasil.setAlamat(alamat);

                BiodataOrangTua biodataOrtu = new BiodataOrangTua();
                biodataOrtu.setId(res.getInt("id_biodata_orangtua"));
                biodataOrtu.setIdCalon(res.getInt("id_calon"));
                biodataOrtu.setIdAlamatAyah(res.getInt("id_alamat_ayah"));
                biodataOrtu.setIdAlamatIbu(res.getInt("id_alamat_ibu"));

                biodataOrtu.setNamaAyah(res.getString("nama_ayah"));
                biodataOrtu.setPendidikanAyah(res.getString("pendidikan_ayah"));
                biodataOrtu.setPekerjaanAyah(res.getString("pekerjaan_ayah"));
                biodataOrtu.setStatusAyah(res.getString("status_ayah"));
                biodataOrtu.setNoHpAyah(res.getString("no_hp_ayah"));
                biodataOrtu.setNipAyah(res.getString("nip_ayah"));
                biodataOrtu.setPangkatAyah(res.getString("pangkat_ayah"));
                biodataOrtu.setJabatanAyah(res.getString("jabatan_ayah"));
                biodataOrtu.setInstansiAyah(res.getString("instansi_ayah"));

                biodataOrtu.setNamaIbu(res.getString("nama_ibu"));
                biodataOrtu.setPendidikanIbu(res.getString("pendidikan_ibu"));
                biodataOrtu.setPekerjaanIbu(res.getString("pekerjaan_ibu"));
                biodataOrtu.setStatusIbu(res.getString("status_ibu"));
                biodataOrtu.setNoHpIbu(res.getString("no_hp_ibu"));
                biodataOrtu.setNipIbu(res.getString("nip_ibu"));
                biodataOrtu.setPangkatIbu(res.getString("pangkat_ibu"));
                biodataOrtu.setJabatanIbu(res.getString("jabatan_ibu"));
                biodataOrtu.setInstansiIbu(res.getString("instansi_ibu"));

                Alamat alamatAyah = new Alamat();
                alamatAyah.setId(res.getInt("id_alamat_ayah"));
                alamatAyah.setAlamat(res.getString("alamat_ayah"));

                Alamat alamatIbu = new Alamat();
                alamatIbu.setId(res.getInt("id_alamat_ibu"));
                alamatIbu.setAlamat(res.getString("alamat_ibu"));

                biodataOrtu.setAlamatAyah(alamatAyah);
                biodataOrtu.setAlamatIbu(alamatIbu);

                hasil.setBiodataOrangTua(biodataOrtu);
                
                ProgramStudi programStudi = new ProgramStudi(res.getString("program_studi"), res.getString("nama_prodi"));
                hasil.setProgramStudiObj(programStudi);
            }
        } catch (SQLException e) {
            Logger.error(this, e.getMessage());
        }

        return hasil;
    }
}
