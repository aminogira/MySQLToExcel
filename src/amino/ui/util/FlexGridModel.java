package amino.ui.util;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

/**
 * @author amila giragama
 *
 */
public class FlexGridModel extends DefaultTableModel {

	private static final long serialVersionUID = 200905051200L;
	
	String colHead[];
	protected Object datum[][];
	protected int rows,cols;
	List<Object[]> lst;
	boolean editable[];
	
	public FlexGridModel(int rows,String colHead[],Object datum[][]){
		if (datum==null)
			this.rows=rows;
		else
			this.rows=datum.length;
		this.cols=colHead.length;
		this.colHead=colHead;
		if (datum==null){
			datum=new Object[this.rows][this.cols];
		}
		this.datum=datum;
		//.moveRow(0, end, to)
	}
	
	//##################################################
	//use this methods as a group message
	public void addDataInit(){
		lst=new ArrayList<Object[]>();
	}
	public void addData(Object[] obj){
		lst.add(obj);
	}
	public void addDataDone(){
		//datum = new Object[lst.size()][];
		int x=0;
		for( x=0;x<lst.size();x++){
			datum[x]=(Object[]) lst.get(x);
		}
		for(;x<datum.length;x++)
			for(int y=0;y<datum[x].length;y++)
				datum[x][y]="";
	}
	//##################################################
	public void addColumnData(Object[] obj,int colIndex){
		this.rows=obj.length;
		if (datum==null)
			datum=new Object[this.rows][this.cols];
		for(int r=0;r<this.rows;r++)
			datum[r][colIndex]=obj[r];
	}
	public void addColumnData(String[] str,int colIndex){
		Object obj[]=str;
		this.addColumnData(obj, colIndex);
	}
	//##################################################
	public int getRowCount(){
		return rows;
	}
	public int getColumnCount(){
		return cols;
	}
	public String getColumnName(int ind){
		return colHead[ind];
	}
	public Object getValueAt(int row,int col){
		if (datum!=null){
			if (datum[row][col]==null)
				return "";
			else
				return datum[row][col];
		}else{
			return "";
		}
	}
	public boolean isCellEditable(int row, int column){
		try{
			return editable[column];
		}catch(Exception e){
			return true;
		}
	}
	
	
	
	public void setCollEditable(boolean editable[]){
		this.editable=editable;
	}
	
	
	public void setValueAt(Object value, int row, int col) {
		if (value instanceof String)
			if (value.toString().trim().equals(""))
				return;
		datum[row][col]=value;
		//System.out.println( "in flexgridmodel " +  value);
		fireTableCellUpdated(row, col);
	}
	public void clearTbl(){
		for(int x=0;x<datum.length;x++){
			for(int y=0;y<datum[x].length;y++){
				datum[x][y]=null;
				setValueAt(null, x, y);
			}
		}
	}
}
