/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.delimare.pmb.entity;

/**
 *
 * @author MyBook Z Series
 */
public class RiwayatPendidikan {
    private int id, idCalon, tahunLulus;
    private String sekolah, namaSekolah, kabupaten, provinsi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCalon() {
        return idCalon;
    }

    public void setIdCalon(int idCalon) {
        this.idCalon = idCalon;
    }

    public int getTahunLulus() {
        return tahunLulus;
    }

    public void setTahunLulus(int tahunLulus) {
        this.tahunLulus = tahunLulus;
    }

    public String getSekolah() {
        return sekolah;
    }

    public void setSekolah(String sekolah) {
        this.sekolah = sekolah;
    }

    public String getNamaSekolah() {
        return namaSekolah;
    }

    public void setNamaSekolah(String namaSekolah) {
        this.namaSekolah = namaSekolah;
    }

    public String getKabupaten() {
        return kabupaten;
    }

    public void setKabupaten(String kabupaten) {
        this.kabupaten = kabupaten;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }
    
    
}