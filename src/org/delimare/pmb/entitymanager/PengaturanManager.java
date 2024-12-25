/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.delimare.pmb.entitymanager;

import java.sql.ResultSet;
import java.util.ArrayList;
import org.delimare.pmb.entity.Pengaturan;
import org.delimare.pmb.function.Fungsi;
import org.delimare.pmb.function.Logger;

/**
 *
 * @author MyBook Z Series
 */
public class PengaturanManager {

    public int insert(Pengaturan p) {
        int result = 0;

        try {
            String sql = "INSERT INTO pengaturan (kunci, nilai) "
                    + "VALUES ('" + p.getKunci()+ "', '" + p.getNilai()+ "');";
            result = Fungsi.EQuery(sql);
        } catch (Exception e) {
            result = -1;
            Logger.error(this, e.getMessage());
        }

        return result;
    }

    public int update(Pengaturan p) {
        int result = 0;

        try {
            String sql = "UPDATE pengaturan SET nilai = '" + p.getNilai()+ "' "
                    + "WHERE kunci = '" + p.getKunci()+ "'";
            result = Fungsi.EQuery(sql);
        } catch (Exception e) {
            result = -1;
            Logger.error(this, e.getMessage());
        }

        return result;
    }

    public ArrayList<Pengaturan> getSemua() {
        ArrayList<Pengaturan> hasil = new ArrayList<>();

        try {
            String sql = "SELECT kunci, nilai FROM pengaturan ";
            ResultSet res = Fungsi.getResult(sql);

            while (res.next()) {
                hasil.add(new Pengaturan(
                        res.getString("kunci"),
                        res.getString("nilai")
                ));
            }
        } catch (Exception e) {
            Logger.error(this, e.getMessage());
        }

        return hasil;
    }
}
