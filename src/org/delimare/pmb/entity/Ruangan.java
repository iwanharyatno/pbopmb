/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.delimare.pmb.entity;

import org.delimare.pmb.gui.tables.JTableRuangan;
import java.util.Objects;

/**
 *
 * @author smart user
 */
public class Ruangan {

   
    private String idRuang, NamaRuang,Gedung;
    private int Kapasitas;
    private Gedung gedungObj;
    
     public Ruangan(String idRuang, String NamaRuang, int Kapasitas, String Gedung) {
        this.idRuang = idRuang;
        this.NamaRuang = NamaRuang;
        this.Kapasitas = Kapasitas;
        this.Gedung = Gedung;
        
       
    }
     public Ruangan (String idRuang, String NamaRuang, int Kapasitas, String Gedung , Gedung gedungObj){
          this.idRuang = idRuang;
        this.NamaRuang = NamaRuang;
        this.Kapasitas = Kapasitas;
        this.Gedung = Gedung;
        this.gedungObj = gedungObj;
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

    public String getGedung() {
        return Gedung;
    }

    public void setGedung(String Gedung) {
        this.Gedung = Gedung;
    }

    public int getKapasitas() {
        return Kapasitas;
    }

    public void setKapasitas(int Kapasitas) {
        this.Kapasitas = Kapasitas;
    }

    public Gedung getGedungObj() {
        return gedungObj;
    }

    public void setGedungObj(Gedung gedungObj) {
        this.gedungObj = gedungObj;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.idRuang);
        hash = 97 * hash + Objects.hashCode(this.NamaRuang);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ruangan other = (Ruangan) obj;
        if (!Objects.equals(this.idRuang, other.idRuang)) {
            return false;
        }
        if (!Objects.equals(this.NamaRuang, other.NamaRuang)) {
            return false;
        }
        return true;
    }

    
    

   
}
