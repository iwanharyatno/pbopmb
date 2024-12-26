/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.delimare.pmb.entity;

/**
 *
 * @author MyBook Z Series
 */
public class Pengaturan {
    private String kunci, nilai;

    public Pengaturan(String kunci, String nilai) {
        this.kunci = kunci;
        this.nilai = nilai;
    }

    public String getKunci() {
        return kunci;
    }

    public void setKunci(String kunci) {
        this.kunci = kunci;
    }

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }
}
