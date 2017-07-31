/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cast.models;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import cast.Cast;

/**
 *
 * @author Zedlav
 */
public class Category extends Object implements Serializable, Operation{
    
    private int categoryId;
    private String categoryName;
    private String nameOfAward;
    private int sequence;
    private int pageantId; 

    public Category(){}
    
    public Category(int id, String name, String aname, int no, int pageant){
        this.categoryId = id;
        this.categoryName = name;
        this.nameOfAward = aname;
        this.sequence = no;
        this.pageantId = pageant;
    }

    /**
     * @return the categoryId
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return the categoryName
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * @param categoryName the categoryName to set
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * @return the nameOfAward
     */
    public String getNameOfAward() {
        return nameOfAward;
    }

    /**
     * @param nameOfAward the nameOfAward to set
     */
    public void setNameOfAward(String nameOfAward) {
        this.nameOfAward = nameOfAward;
    }
    
    /**
     * @return the weight
     */
    public int getSequence() {
        return sequence;
    }

    /**
     * @param weight the weight to set
     */
    public void setSequence(int weight) {
        this.sequence = weight;
    }

    /**
     * @return the pageantId
     */
    public int getPageantId() {
        return pageantId;
    }

    /**
     * @param pageantId the pageantId to set
     */
    public void setPageantId(int pageantId) {
        this.pageantId = pageantId;
    }

    @Override
    public boolean Add() throws SQLException {
        DAObject db = Cast.getDatabase();
        db.Connect();
        QueryRunner run = new QueryRunner();
        int inserts = run.update( db.getConn(), "INSERT INTO Category "
                + "(categoryName, nameOfAward, sequence, pageantId) "
                + "VALUES (?,?,?,?)",
                   getCategoryName(), getNameOfAward(), getSequence(), getPageantId());
        db.Disconnect();
        if (inserts == 1) {
            return true;
        }else{
            return false;
        }
    }

    public boolean addPrerequisite() throws SQLException{
        DAObject db = Cast.getDatabase();
        db.Connect();
        QueryRunner run = new QueryRunner();
        int inserts = run.update( db.getConn(), "INSERT INTO Prerequisite (categoryId) VALUES (?)",
                                  getCategoryId());
        db.Disconnect();
        if (inserts == 1) {
            return true;
        }else{
            return false;
        }
    }
    
    public boolean addCriterion(Criterion c) throws SQLException{
        DAObject db = Cast.getDatabase();
        QueryRunner run = new QueryRunner();
        int inserts = run.update( db.getConn(), "INSERT INTO Criterion (criterionName, weight, categoryId) VALUES (?,?,?)",
                                  c.getCriterionName(), c.getWeight(), getCategoryId());
        db.Disconnect();
        if (inserts == 1) {
            return true;
        }else{
            return false;
        }
    }

    public List<Criterion> getCriterions() throws SQLException{
        QueryRunner run = new QueryRunner();
        DAObject db = Cast.getDatabase();
        ResultSetHandler<List<Criterion>> h = new BeanListHandler<Criterion>(Criterion.class);
        List<Criterion> obj = run.query(db.getConn(), "SELECT * FROM criterion WHERE categoryId = ? ", h, getCategoryId());
        db.Disconnect();
        return obj;
    }
    
    public boolean DeleteCriterions() throws SQLException {
        DAObject db = Cast.getDatabase();
        db.Connect(); 
        QueryRunner run = new QueryRunner();
        int inserts = run.update( db.getConn(), "DELETE FROM Criterion WHERE categoryId = ?",
                                  getCategoryId());
        db.Disconnect();
        if (inserts == 1) {
            return true;
        }else{
            return false;
        }
    }
    
    public boolean DeleteCriterion(Criterion c) throws SQLException {
        DAObject db = Cast.getDatabase();
        db.Connect();
        QueryRunner run = new QueryRunner();
        int inserts = run.update( db.getConn(), "DELETE FROM Criterion WHERE criterionNo = ?",
                                  c.getCriterionNo());
        db.Disconnect();
        if (inserts == 1) {
            return true;
        }else{
            return false;
        }
    }
        
    @Override
    public boolean Edit() throws SQLException {
        DAObject db = Cast.getDatabase();
        db.Connect();
        QueryRunner run = new QueryRunner();
        
        int inserts = run.update( db.getConn(), 
                "UPDATE category SET categoryName = ?, nameOfAward = ?, sequence = ? WHERE categoryId = ?",
                                  getCategoryName(), getNameOfAward(), getSequence(), getCategoryId());
        db.Disconnect();
        if (inserts == 1) {
            return true;
        }else{
            return false;
        }
    }
    
    @Override
    public boolean Delete() throws SQLException {
        DAObject db = Cast.getDatabase();
        db.Connect();
        QueryRunner run = new QueryRunner();
        int inserts = run.update( db.getConn(), "DELETE FROM Category WHERE categoryId = ?",
                                  getCategoryId());
        db.Disconnect();
        if (inserts == 1) {
            return true;
        }else{
            return false;
        }
    }
}
