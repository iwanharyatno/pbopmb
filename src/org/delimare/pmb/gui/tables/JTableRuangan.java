/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.delimare.pmb.gui.tables;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import org.delimare.pmb.entity.Ruangan;

/**
 *
 * @author smart user
 */
public abstract class JTableRuangan extends AbstractTableModel{
    private List<Ruangan> list = new ArrayList<>();
    
    public void add(Ruangan r) {
        list.add(r);
        fireTableRowsInserted(getRowCount(), getColumnCount());
    }
    
    public void setList(List<Ruangan> rList) {
        this.list = rList;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: return list.get(rowIndex).getIdRuang();
            case 1: return list.get(rowIndex).getNamaRuang();
            case 2: return list.get(rowIndex).getKapasitas();
            case 3: return list.get(rowIndex).getGedung();
            case 4: return list.get(rowIndex).getGedungObj().getNamagedung();    
          
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "KODE RUANG";
            case 1: return "NAMA RUANGAN";
            case 2: return "KAPASITAS";
            case 3: return "KODE GEDUNG";
             case 4: return "GEDUNG";    
            default: return "";
        }
    }
    
    public void removeRow(int i, int j) {
        list.remove(i);
        fireTableRowsDeleted(i, j);
    }
}
