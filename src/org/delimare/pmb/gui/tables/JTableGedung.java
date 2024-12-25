
package org.delimare.pmb.gui.tables;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.delimare.pmb.entity.Gedung;
/**
 *
 * @author satri
 */
public class JTableGedung extends AbstractTableModel {
    private List<Gedung> list = new ArrayList<>();
    
    public void add (Gedung gd){
        list.add(gd);
        fireTableRowsInserted(getRowCount(), getColumnCount());
        
    }

    public void setList(List<Gedung> gdlist) {
        this.list = gdlist;
        fireTableDataChanged();
    }
     @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: return list.get(rowIndex).getKodegedung();
            case 1: return list.get(rowIndex).getNamagedung();
            case 2: return list.get(rowIndex).getAlamatgedung();
            default: return null;
        }
    }
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "KODE GEDUNG";
            case 1: return "NAMA GEDUNG";
            case 2: return "ALAMAT GEDUNG";

            
            default: return "";
        }
    }
}
