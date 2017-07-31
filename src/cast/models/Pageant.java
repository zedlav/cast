/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cast.models;

import java.io.Serializable;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import cast.Cast;

/**
 *
 * @author Zedlav
 */
public class Pageant extends Object implements Operation, Serializable{
    
    private int pageantId;
    private String name;
    private Date date;

    public Pageant(){}
    
    public Pageant(int id, String name, Date date){
        this.pageantId = id;
        this.name = name;
        this.date = date;
    }
    
   
    @Override
    public boolean Add() throws SQLException{
        DAObject db = Cast.getDatabase();
        db.Connect();
        QueryRunner run = new QueryRunner();
        int inserts = run.update( db.getConn(), "INSERT INTO Pageant (pageantId, name, date) VALUES (?,?,?)",
                                  getPageantId(), getName(), getDate());
        db.Disconnect();
        if (inserts == 1) {
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean Edit() throws SQLException{
        
        DAObject db = Cast.getDatabase();
        db.Connect();
        QueryRunner run = new QueryRunner();
        
        int inserts = run.update( db.getConn(), "UPDATE Pageant SET name = ?, date = ? WHERE pageantId = ?",
                                  getName(), getDate(), getPageantId());
        db.Disconnect();
        if (inserts == 1) {
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean Delete() throws SQLException{
        DAObject db = Cast.getDatabase();
        db.Connect();
        QueryRunner run = new QueryRunner();
        int inserts = run.update( db.getConn(), "DELETE FROM Pageant WHERE pageantId = ?",
                                  getPageantId());
        db.Disconnect();
        if (inserts == 1) {
            return true;
        }else{
            return false;
        }
    }
    //get all pageants 
    public static List<Pageant> getAll() throws SQLException{
        QueryRunner run = new QueryRunner();
        DAObject db = Cast.getDatabase();
        db.Connect();
        ResultSetHandler<List<Pageant>> h = new BeanListHandler<Pageant>(Pageant.class);
        List<Pageant> pageants = run.query(db.getConn(), "SELECT * FROM Pageant", h);
        db.Disconnect();
        return pageants;
    }
    //get pageants based on condition
    public static List<Pageant> getWhere(String condition) throws SQLException{
        QueryRunner run = new QueryRunner();
        DAObject db = Cast.getDatabase();
        db.Connect();
        ResultSetHandler<List<Pageant>> h = new BeanListHandler<Pageant>(Pageant.class);
        List<Pageant> pageants = run.query(db.getConn(), "SELECT * FROM Pageant WHERE "+condition, h);
        db.Disconnect();
        return pageants;
    }
    //get pageant specific id
    public static Pageant getPageant(int pid) throws SQLException{
        QueryRunner run = new QueryRunner();
        DAObject db = Cast.getDatabase();
        db.Connect();
        ResultSetHandler<Pageant> h = new BeanHandler<Pageant>(Pageant.class);
        Pageant pageant = run.query(db.getConn(), "SELECT * FROM Pageant WHERE pageantId = ?", h, pid);
        db.Disconnect();
        return pageant;
    }
    //get list of remaining candidates (not elliminated) based on current pageant and category 
    public List<Candidate> getRemainingCandidates(Category category) throws SQLException{
        QueryRunner run = new QueryRunner();
        DAObject db = Cast.getDatabase();
        db.Connect();
        ResultSetHandler<List<Candidate>> h = new BeanListHandler<Candidate>(Candidate.class);
        List<Candidate> objs = run.query(db.getConn(), "SELECT * FROM Candidate WHERE pageantId = ? AND categoryId = ? AND isElliminated = ? ORDER BY categoryId ASC", h, getPageantId(), category.getCategoryId(), false);
        db.Disconnect();
        return objs;
    }
    //get list of remaining candidates (not elliminated) based on current pageant and category and gender
    public List<Candidate> getRemainingCandidatesGender(Category category, String g) throws SQLException{
        QueryRunner run = new QueryRunner();
        DAObject db = Cast.getDatabase();
        db.Connect();
        ResultSetHandler<List<Candidate>> h = new BeanListHandler<Candidate>(Candidate.class);
        List<Candidate> objs = run.query(db.getConn(), "SELECT * FROM Candidate WHERE pageantId = ? AND categoryId = ? AND isElliminated = ? AND gender = ? ORDER BY categoryId ASC", h, getPageantId(), category.getCategoryId(), false, g);
        db.Disconnect();
        return objs;
    }
    
    //get list of candidates in a pageant
    public List<Candidate> getCandidates() throws SQLException{
        QueryRunner run = new QueryRunner();
        DAObject db = Cast.getDatabase();
        db.Connect();
        ResultSetHandler<List<Candidate>> h = new BeanListHandler<Candidate>(Candidate.class);
        List<Candidate> objs = run.query(db.getConn(), "SELECT * FROM Candidate WHERE pageantId = ? ORDER BY candidateNoLabel ASC", h, getPageantId());
        db.Disconnect();
        return objs;
    }
    //get list of all candidates by gender
    public List<Candidate> getCandidatesGender(String g) throws SQLException{
        QueryRunner run = new QueryRunner();
        DAObject db = Cast.getDatabase();
        db.Connect();
        ResultSetHandler<List<Candidate>> h = new BeanListHandler<Candidate>(Candidate.class);
        List<Candidate> objs = run.query(db.getConn(), "SELECT * FROM Candidate WHERE pageantId = ? AND gender = ? ORDER BY categoryId ASC", h, getPageantId(), g);
        db.Disconnect();
        return objs;
    }
    //get list of candidates in a pageant
    public List<Candidate> getCandidates(String s) throws SQLException{
        QueryRunner run = new QueryRunner();
        DAObject db = Cast.getDatabase();
        db.Connect();
        ResultSetHandler<List<Candidate>> h = new BeanListHandler<Candidate>(Candidate.class);
        List<Candidate> objs = run.query(db.getConn(), "SELECT * FROM Candidate WHERE pageantId = ? AND (candidateFname LIKE ? OR candidateLname LIKE ?) ORDER BY candidateNoLabel ASC", h, getPageantId(), "%"+s+"%", "%"+s+"%");
        db.Disconnect();
        return objs;
    }
    //get all judges for a pageant
    public List<Judge> getJudges() throws SQLException{
        QueryRunner run = new QueryRunner();
        DAObject db = Cast.getDatabase();
        db.Connect();
        ResultSetHandler<List<Judge>> h = new BeanListHandler<Judge>(Judge.class);
        List<Judge> objs = run.query(db.getConn(), "SELECT * FROM Judge WHERE pageantId = ? ORDER BY judgeNo ASC", h, getPageantId());
        db.Disconnect();
        return objs;
    }
    //get all categories
    public List<Category> getCategories() throws SQLException{
        QueryRunner run = new QueryRunner();
        DAObject db = Cast.getDatabase();
        db.Connect();
        ResultSetHandler<List<Category>> h = new BeanListHandler<Category>(Category.class);
        List<Category> objs = run.query(db.getConn(), "SELECT * FROM Category WHERE pageantId = ? ORDER BY categoryId ASC", h, getPageantId());
        db.Disconnect();
        return objs;
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

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
    
    public boolean equals(Pageant p){
        if (this.getPageantId() == p.getPageantId() 
                && this.getName().equals(p.getName()) 
                && this.getDate() == p.getDate()) {
            return true;
        }else{
            return false;
        }
    }
}
