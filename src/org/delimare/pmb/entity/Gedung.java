
package org.delimare.pmb.entity;

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

    public String getKodegedung() {
        return kodegedung;
    }

    public void setKodegedung(String kodegedung) {
        this.kodegedung = kodegedung;
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
}
