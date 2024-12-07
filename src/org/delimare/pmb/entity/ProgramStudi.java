package org.delimare.pmb.entity;

import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MyBook Z Series
 */
public class ProgramStudi {

    public ProgramStudi(String kode, String nama, String fakultas, int kuota) {
        this.kuota = kuota;
        this.kode = kode;
        this.nama = nama;
        this.fakultas = fakultas;
    }
    
    public ProgramStudi(String kode, String nama, String fakultas, int kuota, Fakultas fakultasObj) {
        this.kuota = kuota;
        this.kode = kode;
        this.nama = nama;
        this.fakultas = fakultas;
        this.fakultasObj = fakultasObj;
    }
    private int kuota;
    private String kode, nama, fakultas;
    private Date createdAt, updatedAt;
    private Fakultas fakultasObj;

    public Fakultas getFakultasObj() {
        return fakultasObj;
    }

    public void setFakultasObj(Fakultas fakultasObj) {
        this.fakultasObj = fakultasObj;
    }

    public int getKuota() {
        return kuota;
    }

    public void setKuota(int kuota) {
        this.kuota = kuota;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getFakultas() {
        return fakultas;
    }

    public void setFakultas(String fakultas) {
        this.fakultas = fakultas;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    
}
