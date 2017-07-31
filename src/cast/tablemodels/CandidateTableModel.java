/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cast.tablemodels;

import cast.Cast;
import cast.models.Candidate;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Zedlav
 */
public class CandidateTableModel extends AbstractTableModel {

    private List<Candidate> list;
    
    private String columnNames[] = {"Candidate", "Number", "First Name", "Last Name", "Area"};
    
    public CandidateTableModel(List<Candidate> list) {
        this.list = new ArrayList<Candidate>(list);
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
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Object value = "??";
        Candidate row = list.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                String path = Cast.getSystemDirectory()+"/photos/";
                Image img = new ImageIcon(path+row.getPhoto()).getImage();
                Image newimg = img.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH);
                value = new ImageIcon(newimg);
                break;
            case 1:
                value = row.getCandidateNoLabel();
                break;
            case 2:
                value = row.getCandidateFname();
                break;
            case 3:
                value = row.getCandidateLname();
                break;
            case 4:
                value = row.getArea();
                break;
            
            
        }

        return value;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return ImageIcon.class;
               
            case 1:
            case 2:
            case 3:
            case 4:
                return String.class;
            
        }
        return Object.class;
    }

    /**
     * This will return the user at the specified row...
     * @param row
     * @return 
     */
    public Candidate getRowAt(int row) {
        return list.get(row);
    }
}

