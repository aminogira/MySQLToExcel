/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package amino.ui;

//import amino.ui.QueryTableModel;

/**
 *
 * @author Amila Giragama
 */
public class QueryTableModelCheckable extends QueryTableModel {
  public boolean isCellEditable(int row, int column){
    if ((column==0))
      return true;
    else
      return false;
  }
  public void setValueAt(Object value, int row, int col) {
    if(col==0){
      String rowValues[]=new String[colCount];

      rowValues[0]=value.toString();
      for(int x=1;x<colCount;x++){
        rowValues[x]=((String[]) cache.elementAt(row))[x];
      }
      cache.setElementAt(rowValues, row);
      fireTableCellUpdated(row, col);
    }else{
      super.setValueAt(value,row,col);
    }
	}
}
