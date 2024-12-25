/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.delimare.pmb.entity;

import org.delimare.pmb.gui.tables.JTableRuangan;

/**
 *
 * @author smart user
 */
public class Ruangan {

    public static void insert(Ruangan ruangan) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void setModel(JTableRuangan tableModel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private String idRuang, NamaRuang;
    private int Kapasitas;
     public Ruangan(String idRuang, String NamaRuang, int Kapasitas) {
        this.idRuang = idRuang;
        this.NamaRuang = NamaRuang;
        this.Kapasitas = Kapasitas;
       
    }

    public String getIdRuang() {
        return idRuang;
    }

    public void setIdRuang(String idRuang) {
        this.idRuang = idRuang;
    }

    public String getNamaRuang() {
        return NamaRuang;
    }

    public void setNamaRuang(String NamaRuang) {
        this.NamaRuang = NamaRuang;
    }

    public int getKapasitas() {
        return Kapasitas;
    }

    public void setKapasitas(int Kapasitas) {
        this.Kapasitas = Kapasitas;
    }

   
}
