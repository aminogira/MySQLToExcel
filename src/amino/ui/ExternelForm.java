package amino.ui;
////
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;

import amino.ui.util.i18n;

import sw.CommonCons;
import sw.db.DB;
import sw.db.Envilop;
/**
 * @author amila giragama
 *
 */
public class ExternelForm extends JFrame implements ActionListener {//, ComponentListener {

	private static final long serialVersionUID = 200905280720L;
	
	//public ExternelForm(String title, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable) {
	public ExternelForm(String title,int formWidth,int formHeight) {
		super(title);
//		focusOrder=new Vector<Component>();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		addProjectModules(title);
		int w=formWidth;
		int h=formHeight;

		setBounds(screenSize.width/2 - w/2, screenSize.height/2 - h/2,w,h);				
	}

	public void setToolBar(ActionListener al,int buttons){
		add(UIToolCreator.createBasicToolBar("Tool Bar",al,buttons),BorderLayout.NORTH);
	}
/*	public void addComponent(Component com,int x,int y,int width,int height){
		add(com);
		//com.setBounds(x, y, width, height);
	}
*/
	public void actionPerformed(ActionEvent ae) {
		if(ae.getActionCommand().equals(i18n.getString("BasicToolBar.BACK"))){
			dispose();
		}
	}
	private void addProjectModules(String title){
		//if (proConst.isUserLogging.equals("TRUE")){
			
//			if (title.equals("Program Setting"))
//				return;
//			DB db=new DB();
//			ResultSet rs=null;
//
//			String checkAdded="SELECT * FROM umodules where name='" + title + "'";
//			rs=db.findRecord(checkAdded);
//			try {
//				if (rs.next()){
//					db.commitclose();
//					return;
//				}
//			} catch (SQLException e2) {
//				e2.printStackTrace();
//			}
//
//			//db.getNewNo(tblName, colName)
//			int displayOrder=0,id=0;
//
//			String newDOsql="SELECT max(displayOrder) +1 as lastOrder FROM umodules";
//			rs=db.findRecord(newDOsql);
//			try {
//				if(rs.next())
//					displayOrder=rs.getInt("lastOrder");
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//
//			String newIDsql="SELECT max(id)+1 as lastId FROM umodules";
//			rs=db.findRecord(newIDsql);
//			try {
//				if (rs.next())
//					id=rs.getInt("lastId");
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//
//			String sql="insert into umodules(id,name,catType,displayOrder,des) " +
//								 "values("+ id +",'"+ title +"','user',"+ displayOrder +",'')";
//			try {
//				db.updateData(new Envilop(sql));
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			db.commitclose();

		//}
	}	

	
}
