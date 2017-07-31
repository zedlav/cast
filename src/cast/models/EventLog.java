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
public class EventLog {
    
    private int logNo;
    private int voteId;
    
    public EventLog(){}
    
    public EventLog(int no, int vote){
        this.logNo = no;
        this.voteId = vote;
    }

    /**
     * @return the logNo
     */
    public int getLogNo() {
        return logNo;
    }

    /**
     * @param logNo the logNo to set
     */
    public void setLogNo(int logNo) {
        this.logNo = logNo;
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


}
