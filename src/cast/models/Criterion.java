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
public class Criterion {
    
    private int criterionNo;
    private String criterionName;
    private int weight;
    private int categoryId;
    
    public Criterion(){}
    
    public Criterion(int no, String crit, int w, int c){
        criterionNo = no;
        criterionName = crit;
        weight = w;
        categoryId = c;
    }
    
    /**
     * @return the criterionNo
     */
    public int getCriterionNo() {
        return criterionNo;
    }

    /**
     * @param criterionNo the criterionNo to set
     */
    public void setCriterionNo(int criterionNo) {
        this.criterionNo = criterionNo;
    }

    /**
     * @return the criterionName
     */
    public String getCriterionName() {
        return criterionName;
    }

    /**
     * @param criterionName the criterionName to set
     */
    public void setCriterionName(String criterionName) {
        this.criterionName = criterionName;
    }

    /**
     * @return the weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(int weight) {
        this.weight = weight;
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
}
