/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cast.tablemodels;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import cast.models.Judge;

/**
 *
 * @author Zedlav
 */
public class JudgeTableModel extends AbstractTableModel {

    private List<Judge> list;
    private String columnNames[] = {"Photo", "Name", "Status", "Username"};
     
    public JudgeTableModel(List<Judge> list) {
        this.list = new ArrayList<Judge>(list);
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
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Object value = "??";
        Judge row = list.get(rowIndex);
        switch (columnIndex) {
            
            case 0:
                value = row.getPhoto();
                break;
            case 1:
                value = row.getJudgeName();
                break;
            case 2:
                value = row.getStatus();
                break;
            case 3:
                value = row.getUsername();
                break;
        }

        return value;

    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
            case 1:
            case 2:
            case 3:
                return String.class;
        }
        return Object.class;
    }

    /* Override this if you want the values to be editable...
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //....
    }
    */

    /**
     * This will return the user at the specified row...
     * @param row
     * @return 
     */
    public Judge getRowAt(int row) {
        return list.get(row);
    }
}
