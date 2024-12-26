
package org.delimare.pmb.entity;
import java.util.Objects;
/**
 *
 * @author satri
 */
public class Gedung {
    private String kodegedung, namagedung,alamatgedung;
   

    public Gedung(String kodegedung, String namagedung,String alamatgedung) {
        this.kodegedung = kodegedung;
        this.namagedung = namagedung;
        this.alamatgedung=alamatgedung;
    }

    public Gedung(String id_gedung, String nama_gedung ) {
        this.kodegedung = id_gedung ;
        this.namagedung = nama_gedung;//To change body of generated methods, choose Tools | Templates.
    }

    public String getKodegedung() {
        return kodegedung;
    }

    public void setKodegedung(String kodegedung) {
        this.kodegedung = kodegedung;
    }

    @Override
    public String toString() {
        return namagedung; //To change body of generated methods, choose Tools | Templates.
    }
    
    public String getNamagedung() {
        return namagedung;
    }

    public void setNamagedung(String namagedung) {
        this.namagedung = namagedung;
    }

    public String getAlamatgedung() {
        return alamatgedung;
    }

    public void setAlamatgedung(String alamatgedung) {
        this.alamatgedung = alamatgedung;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.kodegedung);
        hash = 29 * hash + Objects.hashCode(this.namagedung);
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
        final Gedung other = (Gedung) obj;
        if (!Objects.equals(this.kodegedung, other.kodegedung)) {
            return false;
        }
        if (!Objects.equals(this.namagedung, other.namagedung)) {
            return false;
        }
        return true;
    }
    
}
