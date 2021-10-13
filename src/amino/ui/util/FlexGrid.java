package amino.ui.util;
//
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
/**
 * @author amila giragama
 *
 */
public class FlexGrid extends JTable implements KeyListener,PropertyChangeListener {

	JPopupMenu jpm;
	FoundItem fi[][];
	FindTree ft;
	//TextBox jt;
	
	private static final long serialVersionUID = 20090505;

	public FlexGrid(){
		super();
		addKeyListener(this);
		//jt=new TextBox();
	}
	public FlexGrid(int rows,int cols ){
		super(rows,cols);
		addKeyListener(this);
		//jt=new TextBox();
		fi=new FoundItem[cols][rows];

	}
	public FlexGrid(Object[][] data,Object[] colName){
		super(data,colName);
		addKeyListener(this);
		//jt=new TextBox();
		fi=new FoundItem[colName.length][data[0].length];
	}
	public FlexGrid(TableModel tm){
		super(tm);
		addKeyListener(this);
	//	jt=new TextBox();
		fi=new FoundItem[tm.getColumnCount()][tm.getRowCount()];
	}
	public FlexGrid(TableModel tm,TableColumnModel tcm){
		super(tm,tcm);
		addKeyListener(this);
		//jt=new TextBox();
		fi=new FoundItem[tm.getColumnCount()][tm.getRowCount()];
	}
	public FlexGrid(TableModel tm,TableColumnModel tcm,ListSelectionModel lsm){
		super(tm,tcm,lsm);
		addKeyListener(this);
	//	jt=new TextBox();
		fi=new FoundItem[tm.getColumnCount()][tm.getRowCount()];
	}

/*	public void setFoundItemList(FoundItem fi[],int columnIndex){
		this.fi[columnIndex]=fi;
	}
*/
	public void setFoundItemList(FoundItem fi[][]){
		//this.fi[0]=fi;
		this.fi=fi;
	}
	
//	public TableCellRenderer getCellRenderer(int row, int column) {
///*		if (column==0){ 
//			//jt= new TextBox();
//			jt.requestFocus();
//			return (TableCellRenderer) jt;
//		}else*/
//			return super.getCellRenderer(row, column);
//	}
	public void keyPressed(KeyEvent e) {
//		System.out.println(e.getKeyChar() + " \t " + e.getKeyCode());
//		System.out.println("press " + editingColumn + "," +editingRow);
//		//System.out.println(editingColumn);
//		if (e.getKeyCode()==KeyEvent.VK_F1){
//			
//			if ((fi[0]!=null)&&(fi[0].length>0)){ 
//				//setText("");
//				jpm=new JPopupMenu();
//				ft=new FindTree(fi[0]);
//				ft.addPropertyChangeListener(this);
//				jpm.add(ft);
//				jpm.show(this,this.getX(),this.getY());
//				jpm.requestFocus();
//				jpm.requestFocusInWindow();
//			}
//		}
	}
	
	public void keyReleased(KeyEvent e) {
//		System.out.println(e.getKeyChar() + " \t " + e.getKeyCode());
//		System.out.println("rele " + editingColumn + "," +editingRow);
//		System.out.println("================================");

		if (e.getKeyCode()==KeyEvent.VK_F1){
			if ((fi[editingColumn]!=null)&&(fi[editingColumn].length>0)){ 
				//setText("");
				jpm=new JPopupMenu();
				ft=new FindTree(fi[editingColumn]);
				ft.addPropertyChangeListener(this);
				jpm.add(ft);
				jpm.show(this,this.getX(),this.getY());
				jpm.requestFocus();
				jpm.requestFocusInWindow();
			}
		}else if (e.getKeyCode()==KeyEvent.VK_DELETE){
			FlexGridModel fgm= (FlexGridModel) getModel();
			for (int x=0; x<getColumnCount(); x++)
				fgm.datum[editingRow][x]="";
				//setValueAt("", editingRow, x);
			//setValueAt("", editingRow, editingColumn);
		}
		
		
	}
  public void refreshCheckBox(){
    TableColumn includeColumn=getColumnModel().getColumn(0);
    includeColumn.setCellEditor(new DefaultCellEditor(new JCheckBox()));
    includeColumn.setCellRenderer(new CheckBoxCellRenderer());
    includeColumn.setPreferredWidth(5);
  }


	public void keyTyped(KeyEvent e) {
	//	System.out.println(e.getKeyChar() + " \t " + e.getKeyCode());
	//	System.out.println("type " + editingColumn + "," +editingRow);
	}
	
	public void propertyChange(PropertyChangeEvent evt) {
		//System.out.println(editingColumn + "," +editingRow);
		//jpm.setVisible(false);
		if (evt.getPropertyName().equals(FindTree.selectedItemProperty)){
			setValueAt(evt.getNewValue(), editingRow, editingColumn);
			jpm.setVisible(false);
		}
	}
	
}
