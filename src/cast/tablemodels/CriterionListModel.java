/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cast.tablemodels;

import cast.models.Criterion;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author Zedlav
 */
public class CriterionListModel extends AbstractListModel {
  List<Criterion> list ;

  public CriterionListModel(List<Criterion> list) {
    this.list = new ArrayList<Criterion>(list);
  }

  @Override
  public int getSize() {
    return list.size();
  }

  @Override
  public String getElementAt(int index) {
    return list.get(index).getCriterionName()+" - "+list.get(index).getWeight();
  }
  
  public Criterion getCriterionAt(int index){
      return list.get(index);
  }
}
