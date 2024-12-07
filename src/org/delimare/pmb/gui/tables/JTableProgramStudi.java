/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.delimare.pmb.gui.tables;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.delimare.pmb.entity.ProgramStudi;

/**
 *
 * @author MyBook Z Series
 */
public class JTableProgramStudi extends AbstractTableModel {
    
    private List<ProgramStudi> list = new ArrayList<>();
    
    public void add(ProgramStudi ps) {
        list.add(ps);
        fireTableRowsInserted(getRowCount(), getColumnCount());
    }
    
    public void setList(List<ProgramStudi> psList) {
        this.list = psList;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: return list.get(rowIndex).getKode();
            case 1: return list.get(rowIndex).getNama();
            case 2: return list.get(rowIndex).getFakultas();
            case 3: return list.get(rowIndex).getKuota();
            
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "KODE";
            case 1: return "NAMA";
            case 2: return "FAKULTAS";
            case 3: return "KUOTA";
            
            default: return "";
        }
    }
    
    public void removeRow(int i, int j) {
        list.remove(i);
        fireTableRowsDeleted(i, j);
    }
}
