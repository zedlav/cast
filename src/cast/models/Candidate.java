/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cast.models;

import cast.Cast;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

/**
 *
 * @author Zedlav
 */
public class Candidate extends Object implements Serializable, Operation{
    
    private String candidateNo;
    private String candidateFname;
    private String candidateLname;
    private int candidateNoLabel;
    private String area;
    private String gender;
    private String photo;
    private boolean isElliminated;
    private int pageantId;
    
    
    public Candidate(){}
    
    public Candidate(String n, String f, String l, String g, String a, String p, boolean e, int pg, int cnl){
        this.candidateNo = n;
        this.candidateFname = f;
        this.candidateLname = l;
        this.area = a;
        this.photo = p;
        this.isElliminated = e;
        this.pageantId = pg;
        this.gender = g;
        this.candidateNoLabel = cnl;
    }

    /**
     * @return the candidateNo
     */
    public String getCandidateNo() {
        return candidateNo;
    }

    /**
     * @param candidateNo the candidateNo to set
     */
    public void setCandidateNo(String candidateNo) {
        this.candidateNo = candidateNo;
    }

    /**
     * @return the candidateFname
     */
    public String getCandidateFname() {
        return candidateFname;
    }

    /**
     * @param candidateFname the candidateFname to set
     */
    public void setCandidateFname(String candidateFname) {
        this.candidateFname = candidateFname;
    }

    /**
     * @return the candidateLname
     */
    public String getCandidateLname() {
        return candidateLname;
    }

    /**
     * @param candidateLname the candidateLname to set
     */
    public void setCandidateLname(String candidateLname) {
        this.candidateLname = candidateLname;
    }

    /**
     * @return the area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * @return the photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * @param photo the photo to set
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * @return the isElliminated
     */
    public boolean getIsElliminated() {
        return isElliminated;
    }

    /**
     * @param isElliminated the isElliminated to set
     */
    public void setIsElliminated(boolean isElliminated) {
        this.isElliminated = isElliminated;
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
    public boolean Add() throws SQLException{
        DAObject db = Cast.getDatabase();
        db.Connect();
        QueryRunner run = new QueryRunner();
        int inserts = run.update( db.getConn(), "INSERT INTO Candidate "
                + "(gender, photo, candidateFname, candidateLname, "
                + "area, candidateNoLabel, pageantId) "
                + "VALUES "
                + "(?,?,?,?,"
                + "?,?,?)",
                   getGender(), getPhoto(), getCandidateFname() , getCandidateLname(), 
                   getArea(), getCandidateNoLabel(), getPageantId());
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
        int inserts = run.update( db.getConn(), "UPDATE Candidate "
                + "SET candidateFname = ?, candidateLname = ?, gender = ?,"
                + "photo = ?, area = ?, candidateNoLabel = ? WHERE candidateNo = ?",
                   getCandidateFname(), getCandidateLname(), getGender(), 
                   getPhoto(), getArea(), getCandidateNoLabel(), getCandidateNo());
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
        int inserts = run.update( db.getConn(), "DELETE FROM Candidate WHERE candidateNo = ?",
                                  getCandidateNo());
        db.Disconnect();
        if (inserts == 1) {
            return true;
        }else{
            return false;
        }
    }
    
    public boolean setElliminated() throws SQLException{
        
        DAObject db = Cast.getDatabase();
        db.Connect();
        QueryRunner run = new QueryRunner();
        int inserts = run.update( db.getConn(), "UPDATE Candidate SET isElliminated = ? WHERE candidateNo = ?",
                  true, getCandidateNo());
        db.Disconnect();
        if (inserts == 1) {
            return true;
        }else{
            return false;
        }
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public boolean equals(Candidate c){
        if (this.getCandidateNo().equals(c.getCandidateNo()) 
                && this.getGender().equals(c.getGender()) 
                && this.getPhoto().equals(c.getPhoto()) 
                && this.getCandidateFname().equals(c.getCandidateFname())
                && this.getCandidateLname().equals(c.getCandidateLname()) 
                && this.getArea().equals(c.getArea()) 
                && this.getIsElliminated() == c.getIsElliminated()
                && this.getCandidateNoLabel() == c.getCandidateNoLabel()
                && this.getPageantId() == c.getPageantId()) {
            return true;
        }else{
            return false;
        }
    }

    /**
     * @return the candidateNoLabel
     */
    public int getCandidateNoLabel() {
        return candidateNoLabel;
    }

    /**
     * @param candidateNoLabel the candidateNoLabel to set
     */
    public void setCandidateNoLabel(int candidateNoLabel) {
        this.candidateNoLabel = candidateNoLabel;
    }
    
    public static int getLastID(String id, String table) throws SQLException{
        ResultSetHandler<Object[]> h = new ResultSetHandler<Object[]>() {
        public Object[] handle(ResultSet rs) throws SQLException {
        if (!rs.next()) {
        return null;
        }
        ResultSetMetaData meta = rs.getMetaData();
        int cols = meta.getColumnCount();
        Object[] result = new Object[cols];
        for (int i = 0; i < cols; i++) {
        result[i] = rs.getObject(i + 1);
        }
        return result;
        }
        };
        DAObject db = Cast.getDatabase();
        db.Connect();
        QueryRunner run = new QueryRunner();
        Object[] a = run.query(db.getConn(), "SELECT "+id+" FROM "+table+" ORDER BY "+id+" DESC LIMIT 0,1",h);
        return (int) a[0];
    }
}
