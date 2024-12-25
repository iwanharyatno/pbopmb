package org.delimare.pmb.entity;

import java.util.Date;
import java.util.Objects;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MyBook Z Series
 */
public class ProgramStudi {

    private int kuota;
    private String kode, nama, fakultas;
    private Date createdAt, updatedAt;
    private Fakultas fakultasObj;
    
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

    public ProgramStudi(String kode, String nama) {
        this.kode = kode;
        this.nama = nama;
    }
    
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

    @Override
    public String toString() {
        return this.nama;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.kode);
        hash = 89 * hash + Objects.hashCode(this.nama);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProgramStudi other = (ProgramStudi) obj;
        if (!Objects.equals(this.kode, other.kode)) {
            return false;
        }
        return Objects.equals(this.nama, other.nama);
    }
}
