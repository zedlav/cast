/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cast.models;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import cast.Cast;

/**
 *
 * @author Zedlav
 */
public class Judge extends Object implements Serializable, Operation{
    
    private int judgeNo;
    private String judgeName;
    private String photo;
    private String status;
    private String username;
    private String password;
    private int pageantId;
    
    public Judge(){}
    
    public Judge(int no, String name, String photo, String status, String user, String pass, int pageant){
        this.judgeNo = no;
        this.judgeName = name;
        this.photo = photo;
        this.status = status;
        this.username = user;
        this.password = pass;
        this.pageantId = pageant;
    }

    /**
     * @return the judgeNo
     */
    public int getJudgeNo() {
        return judgeNo;
    }

    /**
     * @param judgeNo the judgeNo to set
     */
    public void setJudgeNo(int judgeNo) {
        this.judgeNo = judgeNo;
    }

    /**
     * @return the judgeName
     */
    public String getJudgeName() {
        return judgeName;
    }

    /**
     * @param judgeName the judgeName to set
     */
    public void setJudgeName(String judgeName) {
        this.judgeName = judgeName;
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
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
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
        int inserts = run.update( db.getConn(), "INSERT INTO Judge (judgeName, photo, status, username, password, pageantId) VALUES (?,?,?,?,?,?)",
                                  getJudgeName(), getPhoto(), getStatus(), getUsername(), getPassword(), getPageantId());
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
        
        int inserts = run.update( db.getConn(), "UPDATE Judge SET judgeName = ?, photo = ?, status = ?, username = ?, password = ? "
                + "WHERE judgeNo = ?",
                                  getJudgeName(), getPhoto(), getStatus(), getUsername(), getPassword(), getJudgeNo());
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
        int inserts = run.update( db.getConn(), "DELETE FROM Judge WHERE judgeNo = ?",
                                  getJudgeNo());
        db.Disconnect();
        if (inserts == 1) {
            return true;
        }else{
            return false;
        }
    }
    
    public boolean castVote(Candidate candidate, Criterion criterion, int score) throws SQLException{
        DAObject db = Cast.getDatabase();
        db.Connect();
        QueryRunner run = new QueryRunner();
        int inserts = run.update( db.getConn(), 
                "INSERT INTO Vote (score, candidateNo, judgeNo, criterionNo) VALUES (?,?,?,?)",
                                  score, candidate.getCandidateNo(), candidate.getGender(), getJudgeNo(), criterion.getCriterionNo());
        db.Disconnect();
        if (inserts == 1) {
            return true;
        }else{
            return false;
        }
    }
    
    public boolean hasVotedOn(Candidate candidate, Criterion criterion) throws SQLException{
        QueryRunner run = new QueryRunner();
        DAObject db = Cast.getDatabase();
        db.Connect();
        ResultSetHandler<Vote> h = new BeanHandler<Vote>(Vote.class);
        Vote obj = run.query(db.getConn(), "SELECT * FROM Vote WHERE judgeNo = ? AND candidateNo = ? AND criterionNo = ?", 
                    h, getJudgeNo(), candidate.getCandidateNo(), criterion.getCriterionNo());
        db.Disconnect();
        if (obj != null) {
            return true;
        }else{
            return false;
        }
    }
    
    public List<Vote> getMyVote(Candidate candidate, Category category) throws SQLException{
        List<Vote> obj = new ArrayList<>();
        QueryRunner run = new QueryRunner();
        DAObject db = Cast.getDatabase();
        db.Connect();
        List<Criterion> criteria = category.getCriterions();
        for (Criterion crit : criteria){
            ResultSetHandler<Vote> h = new BeanHandler<Vote>(Vote.class);
            Vote v = run.query(db.getConn(), 
                    "SELECT * FROM Vote WHERE criterionNo = ? AND judgeNo = ? AND candidateNo = ?", h, 
                     crit.getCriterionNo(), getJudgeNo(), candidate.getCandidateNo());
            obj.add(v);
        }
        db.Disconnect();
        return (List<Vote>)obj;
    }
    
    public boolean equals(Judge j){
        if (this.getJudgeNo() == j.getJudgeNo() 
                && this.getJudgeName().equals(j.getJudgeName()) 
                && this.getUsername().equals(j.getUsername()) 
                && this.getPassword().equals(j.getPassword()) 
                && this.getPhoto().equals(j.getPhoto()) 
                && this.getStatus().equals(j.getStatus())
                && this.getPageantId() == j.getPageantId()){
            return true;
        }else{
            return false;
        }
    }
}
