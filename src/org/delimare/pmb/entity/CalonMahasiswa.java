/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.delimare.pmb.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author MyBook Z Series
 */
public class CalonMahasiswa {
    private int id, jumlahAnak, tahunDaftar, idAlamat;
    private String nikKtp, namaLengkap, tempatLahir, nisn, noTelepon, email, programStudi, agama, statusPerkawinan, statusPendaftaran, jenisKelamnin;
    private Date tanggalLahir, createdAt, updatedAt;
    
    private Alamat alamat;
    private BiodataOrangTua biodataOrangTua;
    private ProgramStudi programStudiObj;
    
    private ArrayList<RiwayatPendidikan> riwayatPendidikan;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJumlahAnak() {
        return jumlahAnak;
    }

    public void setJumlahAnak(int jumlahAnak) {
        this.jumlahAnak = jumlahAnak;
    }

    public int getTahunDaftar() {
        return tahunDaftar;
    }

    public void setTahunDaftar(int tahunDaftar) {
        this.tahunDaftar = tahunDaftar;
    }

    public int getIdAlamat() {
        return idAlamat;
    }

    public void setIdAlamat(int idAlamat) {
        this.idAlamat = idAlamat;
    }

    public String getNikKtp() {
        return nikKtp;
    }

    public void setNikKtp(String nikKtp) {
        this.nikKtp = nikKtp;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getNisn() {
        return nisn;
    }

    public void setNisn(String nisn) {
        this.nisn = nisn;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProgramStudi() {
        return programStudi;
    }

    public void setProgramStudi(String programStudi) {
        this.programStudi = programStudi;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getStatusPerkawinan() {
        return statusPerkawinan;
    }

    public void setStatusPerkawinan(String statusPerkawinan) {
        this.statusPerkawinan = statusPerkawinan;
    }

    public String getStatusPendaftaran() {
        return statusPendaftaran;
    }

    public void setStatusPendaftaran(String statusPendaftaran) {
        this.statusPendaftaran = statusPendaftaran;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getJenisKelamnin() {
        return jenisKelamnin;
    }

    public void setJenisKelamnin(String jenisKelamnin) {
        this.jenisKelamnin = jenisKelamnin;
    }

    public Alamat getAlamat() {
        return alamat;
    }

    public void setAlamat(Alamat alamat) {
        this.alamat = alamat;
    }

    public BiodataOrangTua getBiodataOrangTua() {
        return biodataOrangTua;
    }

    public void setBiodataOrangTua(BiodataOrangTua biodataOrangTua) {
        this.biodataOrangTua = biodataOrangTua;
    }

    public ProgramStudi getProgramStudiObj() {
        return programStudiObj;
    }

    public void setProgramStudiObj(ProgramStudi programStudiObj) {
        this.programStudiObj = programStudiObj;
    }

    public ArrayList<RiwayatPendidikan> getRiwayatPendidikan() {
        return riwayatPendidikan;
    }

    public void setRiwayatPendidikan(ArrayList<RiwayatPendidikan> riwayatPendidikan) {
        this.riwayatPendidikan = riwayatPendidikan;
    }
    
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
