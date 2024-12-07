/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.delimare.pmb.entity;

import java.util.Objects;

/**
 *
 * @author MyBook Z Series
 */
public class Fakultas {
    private String kode, nama;
    
    public Fakultas(String kode, String nama) {
        this.kode = kode;
        this.nama = nama;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.kode);
        hash = 71 * hash + Objects.hashCode(this.nama);
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
        final Fakultas other = (Fakultas) obj;
        if (!Objects.equals(this.kode, other.kode)) {
            return false;
        }
        return Objects.equals(this.nama, other.nama);
    }
    
    @Override
    public String toString() {
        return this.nama;
    }
}
