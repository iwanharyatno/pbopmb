/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.delimare.pmb.gui.tables;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import org.delimare.pmb.entity.CalonMahasiswa;

/**
 *
 * @author MyBook Z Series
 */
public class JTablePeserta extends AbstractTableModel {
    
    private ArrayList<CalonMahasiswa> pesertaList;
    
    public void setList(ArrayList<CalonMahasiswa> pesertaList) {
        this.pesertaList = pesertaList;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return pesertaList.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: return pesertaList.get(rowIndex).getId();
            case 1: return pesertaList.get(rowIndex).getNamaLengkap();
            case 2: return pesertaList.get(rowIndex).getNoTelepon();
            case 3: return pesertaList.get(rowIndex).getEmail();
            case 4: return pesertaList.get(rowIndex).getProgramStudi();
            case 5: return pesertaList.get(rowIndex).getStatusPendaftaran();
            case 6: return pesertaList.get(rowIndex).getTahunDaftar();
            case 7: return pesertaList.get(rowIndex).getCreatedAt();
            
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "ID";
            case 1: return "NAMA LENGKAP";
            case 2: return "NO TELP";
            case 3: return "EMAIL";
            case 4: return "PROGRAM STUDI";
            case 5: return "STATUS PENDAFTARAN";
            case 6: return "TAHUN DAFTAR";
            case 7: return "TANGGAL PENDAFTARAN";
            default: return "";
        }
    }
    
    public void removeRow(int i, int j) {
        pesertaList.remove(i);
        fireTableRowsDeleted(i, j);
    }
}
