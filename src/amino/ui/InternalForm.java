package amino.ui;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import java.awt.BorderLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.awt.event.MouseMotionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import sw.*;
import sw.db.DB;
import sw.db.Envilop;
import amino.ui.FocusTravesel;
import amino.ui.util.i18n;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
//import java.beans.PropertyChangeEvent;
//import java.beans.PropertyChangeListener;
/**
 * @author amila giragama
 *
 */
public abstract class InternalForm extends JInternalFrame implements ActionListener,FocusListener, ComponentListener{

	protected String dataPath;
	
	//protected int newId=0;
	protected int currentID=0;
	protected boolean editMode=false;
	protected boolean cannotEdit=false;
	
	protected Vector<Component> focusOrder;
	protected FocusTravesel ft;
	protected Component[] compOrderList;
	
	public InternalForm(String title, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable) {
		super(title,resizable,closable,maximizable,iconifiable);
		setupFont();
		addProjectModules(title);
//		
		focusOrder=new Vector<Component>();
		addComponentListener(this);
		System.out.println("call internel");
		setVisible(true);
	}
	
	
	private void setupFont(){
    System.out.println("font set set");
   if(!(i18n.getString("PrgCommen.DefaultFontStyle").equals("null"))){
    String fntStyle=i18n.getString("PrgCommen.DefaultFontStyle");
    int fntSize=i18n.getInt("PrgCommen.DefaultFontSize");
    int fntType=i18n.getInt("PrgCommen.DefaultFontType");
    //setFont(new Font(fntStyle,fntType,fntSize));
    BasicInternalFrameTitlePane titlePane = (BasicInternalFrameTitlePane)((BasicInternalFrameUI)this.getUI()).getNorthPane();
    //Font font = titlePane.getFont();
    titlePane.setFont(new Font(fntStyle,fntType,fntSize));
   }
	}
   
     //BasicInternalFrameTitlePane titlePane = (BasicInternalFrameTitlePane)((BasicInternalFrameUI)this.getUI()).getNorthPane();
	
	
	private void addProjectModules(String title){
		//if (proConst.isUserLogging.equals("TRUE")){
			
//			if (title.equals("Program Setting"))
////				return;
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
	
	
	protected void setFocusOrder(Vector<Component> order,Container containtr){
		ft=new FocusTravesel(order, containtr);		
	}
	protected void setFocusOrder(Vector<Component> order){
		ft=new FocusTravesel(order, this);		
	}
	protected void setFocusOrder(Component[] compList){
		for (int x=0;x<compList.length;x++){
			focusOrder.add(compList[x]);
		}
		ft=new FocusTravesel(focusOrder, this);
	}
	protected void addFocusFlow(Component com){
		focusOrder.add(com);
		ft=new FocusTravesel(focusOrder, this);
	}
	
	public void setToolBar(ActionListener al,int s){
		add(UIToolCreator.createBasicToolBar("Tool Bar",al,s),BorderLayout.NORTH);
	}


	public void actionPerformed(ActionEvent ae){
		if(ae.getActionCommand().equals(i18n.getString("BasicToolBar.BACK"))){
			dispose();
		}
	}
	
	
	//

	public void componentHidden(ComponentEvent e) {}

	public void componentResized(ComponentEvent e) {
		//repaint();
	}

	public void componentShown(ComponentEvent e) {
		//repaint();
	}	
	
	public void focusGained(FocusEvent e) {}

	public void focusLost(FocusEvent e) {
		if (e.getComponent() instanceof JTextField ){
			if (((JTextField)(e.getComponent())).getText().trim().length()>0)
				validateTextBox((JTextField)e.getComponent());
			else
				validateTextBox(null);
		}
	}
	
	public abstract void validateTextBox(JTextField jtf);

//	public void componentMoved(ComponentEvent e) {
//		// TODO Auto-generated method stub
//		
//	}


	



}	
	
//	public abstract void setFindList(JTextField jtf);
//	public abstract void removeFindList();
	

