/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.delimare.pmb.gui.tables;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.delimare.pmb.entity.Fakultas;

/**
 *
 * @author MyBook Z Series
 */
public class JTableFakultas extends AbstractTableModel {
    
    private List<Fakultas> list = new ArrayList<>();
    
    public void add(Fakultas ps) {
        list.add(ps);
        fireTableRowsInserted(getRowCount(), getColumnCount());
    }
    
    public void setList(List<Fakultas> psList) {
        this.list = psList;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: return list.get(rowIndex).getKode();
            case 1: return list.get(rowIndex).getNama();
            
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "KODE";
            case 1: return "NAMA";
            
            default: return "";
        }
    }
    
    public void removeRow(int i, int j) {
        list.remove(i);
        fireTableRowsDeleted(i, j);
    }
    
}
