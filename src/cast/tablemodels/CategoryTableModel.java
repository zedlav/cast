/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cast.tablemodels;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import cast.models.Category;

/**
 *
 * @author Zedlav
 */
public class CategoryTableModel  extends AbstractTableModel {
    
    private List<Category> list;
    private String columnNames[] = {"Category", "Name of Award", "Sequence"};
    
    public CategoryTableModel(List<Category> list) {
        this.list = new ArrayList<Category>(list);
    }

    @Override
    public String getColumnName(int col){
        return columnNames[col];
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

        Object value = "??";
        Category row = list.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                value = row.getCategoryName();
                break;
            case 1:
                value = row.getNameOfAward();
                break;
            case 2:
                value = row.getSequence();
                break;    
        }
        return value;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
            case 1:
                return String.class;
            case 2:
                return Integer.class;
        }
        return Object.class;
    }

    /**
     * This will return the user at the specified row...
     * @param row
     * @return 
     */
    public Category getRowAt(int row) {
        return list.get(row);
    }
}