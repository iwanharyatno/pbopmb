/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.delimare.pmb.entity;

import java.util.Objects;

/**
 *
 * @author DENDI
 */
public class Berkas {
    public String id_berkas,id_calon ,jenisberkas ,namaberkas ,pathfile;

   public Berkas(String id_berkas, String id_calon, String jenisberkas, String namaberkas, String pathfile) {
        this.id_berkas = id_berkas;
        this.id_calon = id_calon;
        this.jenisberkas = jenisberkas;
        this.namaberkas = namaberkas;
        this.pathfile = pathfile;
    }

    

    public String getId_berkas() {
        return id_berkas;
    }

    public void setId_berkas(String id_berkas) {
        this.id_berkas = id_berkas;
    }

    public String getId_calon() {
        return id_calon;
    }

    public void setId_calon(String id_calon) {
        this.id_calon = id_calon;
    }

    public String getJenisberkas() {
        return jenisberkas;
    }

    public void setJenisberkas(String jenisberkas) {
        this.jenisberkas = jenisberkas;
    }

    public String getNamaberkas() {
        return namaberkas;
    }

    public void setNamaberkas(String namaberkas) {
        this.namaberkas = namaberkas;
    }

    public String getPathfile() {
        return pathfile;
    }

    public void setPathfile(String pathfile) {
        this.pathfile = pathfile;
    }
    
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Berkas berkas = (Berkas) obj;
        return Objects.equals(id_calon, berkas.id_calon) && 
               Objects.equals(jenisberkas, berkas.jenisberkas); 
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_calon, jenisberkas); 
    }


    
}
