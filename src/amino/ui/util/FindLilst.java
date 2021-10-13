package amino.ui.util;
//
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import sw.db.DB;
/**
 * @author amila giragama
 *
 */
public class FindLilst extends JPanel {

	private static final long serialVersionUID = 200904271112L;
	
	JTable jtb;
	
	ResultSet rs;
	String[] name,code;
	int[] id;
	
	public void setGrid(String tbl,String col){
		DB db=new DB();
		String sql="SELECT * FROM " + tbl; 
		rs=db.findRecord(sql);		
		jtb=new JTable(new tblModel());
		add(new JScrollPane(jtb));
		//add(jtb);
		setVisible(true);
	}
	
/*	public void clearGrid(){
		code=new String[0];
		name=new String[0];
		id=new int[0];
		remove(jtb);
	}
*/
	class tblModel extends AbstractTableModel{

		int rows;
		private static final long serialVersionUID = 200904271215L;

		
		public int getColumnCount() {
			return 3;
		}

		public int getRowCount() {
			int ans =0;
			try {
				rs.first();
				while(rs.next())ans++;

				rows=ans;
				name=new String[rows];
				code=new String[rows];
				id=new int[rows];
				
				rs.first();
				int x=0;
				while(rs.next()){
					code[x]=rs.getString("code");
					name[x]=rs.getString("name");
					id[x++]=rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return 10;
			}
			return ans;
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			if (columnIndex==0)
				return code[rowIndex];
			else if(columnIndex==1)
				return name[rowIndex];
			else
				return id[rowIndex];
		}
	}
}
