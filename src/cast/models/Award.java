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
public class Award {
    
    private int awardNo;
    private int numOfVotes;
    private int categoryId;
    private String candidateNo;
    
    public Award(){}
    
    public Award(int no, int score, int category, String candidate){
        this.awardNo = no;
        this.numOfVotes = score;
        this.categoryId = category;
        this.candidateNo = candidate;
    }

    /**
     * @return the awardNo
     */
    public int getAwardNo() {
        return awardNo;
    }

    /**
     * @param awardNo the awardNo to set
     */
    public void setAwardNo(int awardNo) {
        this.awardNo = awardNo;
    }

    /**
     * @return the numOfVotes
     */
    public int getNumOfVotes() {
        return numOfVotes;
    }

    /**
     * @param numOfVotes the numOfVotes to set
     */
    public void setNumOfVotes(int numOfVotes) {
        this.numOfVotes = numOfVotes;
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

}
