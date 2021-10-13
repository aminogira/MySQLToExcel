package amino.ui;
//
//import amino.ui.InternalForm;

//import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import amino.ui.PanelGridBag;
import amino.ui.util.ALabel;
import amino.ui.util.TextBoxIntiger;
import amino.ui.util.i18n;

import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import sw.CommonCons;
import sw.LogConst;
import sw.proConst;
import sw.db.DB;
import sw.db.Envilop;
import sw.db.ctrlData;
import tbs.TBSConst;
/**
 * @author amila giragama
 *
 */
public abstract class TransactionForm extends InternalForm {// implements KeyListener
	PanelGridBag gbp;
	PanelFlow fp;
	JPanel jp,jpProgress;
	
	protected String transTableName,transTableIdColumnName;
	
	protected JTextField jtrefNum;//,jtId;
	protected JDateChooser jdcDate;	
	protected TextBoxIntiger jtId;
			
	public TransactionForm(String title, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable) {
		super(title,resizable,closable,maximizable,iconifiable);
		setToolBar(this,CommonCons.SAVEBTN+CommonCons.CANCELBTN+CommonCons.TRASHBTN+CommonCons.BACKBTN);
		
		//transTableName,transTableIdColumnName
		
		gbp=new PanelGridBag();
					
		jtrefNum=new JTextField();
		//jtId=new JTextField();
		jtId=new TextBoxIntiger();
		jdcDate=new JDateChooser();
		
		jpProgress=new JPanel();
		jtId.addFocusListener(this);
		
//		if (proConst.isUserLogging.equals("TRUE")){
////			if (LogConst.currentUser.edit(title)){
////				jtId.setEditable(true);
////				jdcDate.setEnabled(true);
////			}	else {
//				jtId.setEditable(false);
//				jdcDate.setEnabled(false);
////			}
//		}else{
			if (proConst.dateeditable){
				jtId.setEditable(true);
				jdcDate.setEnabled(true);
			}	else {
				jtId.setEditable(false);
				jdcDate.setEnabled(false);
			}
//		}
		
		//this for move transaction Detail to right
		//gbp.add(new JLabel(""),0,0,47,3,1,1);
    gbp.add(jpProgress,0,0,47,3,1,1);

		gbp.add(new ALabel(i18n.getString("CommenTransForm.LabelNo")),47,0,1,1,0,0);
		gbp.add(new ALabel(i18n.getString("CommenTransForm.LabelDate")),47,1,1,1,0,0);
		gbp.add(new ALabel(i18n.getString("CommenTransForm.LabelRefNo")),47,2,1,1,0,0);
		
		gbp.add(jtId,54,0,5,1,0,0);
		gbp.add(jdcDate,54,1,5,1,0,0);
		gbp.add(jtrefNum,54,2,5,1,0,0);
	
	}

	public TransactionForm(String title, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable,String transTableName, String transTableIdColumnName) {
		this(title,resizable,closable,maximizable,iconifiable);
		this.transTableName=transTableName;
		this.transTableIdColumnName=transTableIdColumnName;
		//setIcon(Toolkit.getDefaultToolkit().getImage(CommonCons.FORM_ICON_INTERNEL));
	}

	public void addPanel(PanelGridBag pb){
		gbp.add(pb, 0, 3, 60, 60, 0, 1);
		add(gbp);
		pack();
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae){
//		if (proConst.isUserLogging.equals("TRUE")){
//			if (ae.getActionCommand().equals(CommonCons.SAVE)){
//				if (editMode){
//					if (LogConst.currentUser.edit(title))
//						saveData();
//					else
//						JOptionPane.showMessageDialog(this, "You are not authorized");
//				}else{
//					if (LogConst.currentUser.add(title))
//						saveData();
//					else
//						JOptionPane.showMessageDialog(this, "You are not authorized");
//				}
//			}else if(ae.getActionCommand().equals(CommonCons.CANCEL)){
//				cancel();
//			}else if(ae.getActionCommand().equals(CommonCons.TRASH)){
//				if (LogConst.currentUser.delete(title))
//					trashData();
//				else
//					JOptionPane.showMessageDialog(this, "You are not authorized");
//			}else if(ae.getActionCommand().equals(CommonCons.BACK)){
//				dispose();
//			}else{
//				super.actionPerformed(ae);
//			}
//		}else{
			if (ae.getActionCommand().equals(i18n.getString("BasicToolBar.SAVE"))){
				saveData();
			}else if(ae.getActionCommand().equals(i18n.getString("BasicToolBar.CANCEL"))){
				cancel();
			}else if(ae.getActionCommand().equals(i18n.getString("BasicToolBar.TRASH"))){
				trashData();
			}else if(ae.getActionCommand().equals(i18n.getString("BasicToolBar.BACK"))){
				dispose();
			}
//		}
	}		
	public void clearForm(){
		ControlComponent.clearAllJTextField(this);
		currentID=0;
		cannotEdit=false;
		jdcDate.setDate(new Date());
		editMode=false;
		if ((transTableName==null)||(transTableName.equals("")))return;
		if ((transTableIdColumnName==null)||(transTableIdColumnName.equals("")))return;
		DB db =new DB();
		int a=db.getNewNo(transTableName,transTableIdColumnName);
		jtId.setText((new Integer(a)).toString());
		
		if (db.isTableAvailabal("tbl_MCalendar")){
			String sql="SELECT  CDate FROM tbl_MCalendar " +
			"where Today=1 and  Bankid=" + TBSConst.BankID + " and workday='yes'";
			try {
				ResultSet rs = db.readData(new Envilop(sql));
				if(rs.next())
					jdcDate.setDate(ctrlData.getDate(rs.getString("CDate")));
//System.out.println(jdcDate.getDate());
			} catch (SQLException e) {
				System.out.println("Date format error in tbl_MCalendar");
				e.printStackTrace();
			}
		}
	
	}
	//abstract public void dispose();
	abstract public void saveData();
	abstract public void cancel();
	abstract public void trashData();
	abstract public void printTransaction();



}
