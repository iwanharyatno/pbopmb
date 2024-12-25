/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.delimare.pmb.gui.tables;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.delimare.pmb.entity.Berkas;

/**
 *
 * @author DENDI
 */
public class JTableBerkas extends AbstractTableModel {
    private List<Berkas> list = new ArrayList<>();
    
    public void add (Berkas bk){
        list.add(bk);
        fireTableRowsInserted(getRowCount(), getColumnCount());
        
    }

    public void setList(List<Berkas> bklist) {
        this.list = bklist;
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
            case 0: return list.get(rowIndex).getId_berkas();
            case 1: return list.get(rowIndex).getId_calon();
            case 2: return list.get(rowIndex).getJenisberkas();
            case 3: return list.get(rowIndex).getNamaberkas();
            case 4: return list.get(rowIndex).getPathfile();
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "ID BERKAS";
            case 1: return "ID CALON";
            case 2: return "JENIS BERKAS";
            case 3: return "NAMA FILE";
            case 4: return "PATH FILE";

            
            default: return "";
        }
    }
}
