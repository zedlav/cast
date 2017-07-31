/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cast.models;

/**
 *
 * @author Zedlav
 */
public class Vote extends Object{
    
    private int voteId;     
    private int score;
    private String candidateNo;
    private int judgeNo;
    private int criterionNo;

    public Vote(){}
    
    public Vote(int no, int score, String candidate, int judge, int categoryNo){
        this.voteId = no;
        this.score = score;
        this.candidateNo = candidate;
        this.judgeNo = judge;
        this.criterionNo = categoryNo;
    }

    /**
     * @return the voteId
     */
    public int getVoteId() {
        return voteId;
    }

    /**
     * @param voteId the voteId to set
     */
    public void setVoteId(int voteId) {
        this.voteId = voteId;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
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
     * @return the categoryId
     */
    public int getCriterionNo() {
        return criterionNo;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(int categoryId) {
        this.criterionNo = categoryId;
    }
    
}
