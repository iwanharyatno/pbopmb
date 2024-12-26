/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.delimare.pmb.function;

import java.sql.*;
import org.delimare.pmb.connection.Koneksi;

/**
 *
 * @author MyBook Z Series
 */
public class Fungsi {

    private static Koneksi db = new Koneksi();

    public static int EQuery(String sql) throws SQLException {
        int i = 0;
        Connection con = db.getConnection();
        Statement st = con.createStatement();
        i = st.executeUpdate(sql);

        return i;
    }

    public static int EQuery(String sql, Connection con) throws SQLException {
        int i = 0;
        Statement st = con.createStatement();
        i = st.executeUpdate(sql);

        return i;
    }

    public static int EQueryReturnsID(String sql) throws SQLException {
        int i = 0;
        Connection con = db.getConnection();
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            i = rs.getInt(1);
        }

        return i;
    }

    public static int EQueryReturnsID(String sql, Connection con) throws SQLException {
        int i = 0;
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            i = rs.getInt(1);
        }

        return i;
    }

    public static ResultSet getResult(String sql) throws SQLException {
        int i = 0;

        ResultSet result = null;

        Connection con = db.getConnection();
        Statement st = con.createStatement();
        result = st.executeQuery(sql);

        return result;
    }
    
    public static Statement getResults(String sql) throws SQLException {
        int i = 0;

        boolean result;

        Connection con = db.getConnection();
        PreparedStatement st = con.prepareStatement(sql);
        result = st.execute();

        return st;
    }
}
