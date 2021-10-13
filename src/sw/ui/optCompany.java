package sw.ui;

//

import java.awt.event.ComponentEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JTextField;

import sw.db.DB;
import sw.db.Envilop;


import amino.ui.MasterForm;
import amino.ui.PanelGridBag;

public class optCompany extends MasterForm {

	private static final long serialVersionUID = 2009041510;

	JTextField jtCmpName,jtCmpAddr01,jtCmpAddr02,jtCmpAddr03,jtCmpAddr04,jtCmpEmail,jtCmpTelNo,jtFaxNo;
		//Button jbSave,jbCancel;
	
	PanelGridBag pgb;
	
	public optCompany() {
		
		super("Company Information",true,true,false,true);
		
		pgb=new PanelGridBag();
		
		jtCmpName=new JTextField();
		jtCmpAddr01=new JTextField();
    jtCmpAddr02=new JTextField();
    jtCmpAddr03=new JTextField();
    jtCmpAddr04=new JTextField();
		jtCmpEmail=new JTextField();
		jtCmpTelNo=new JTextField();
		jtFaxNo=new JTextField();
//		jtBackupPath=new JTextField();
//		jtReportPath=new JTextField();
		
		
		//ResultSet rs=ctrlmAccType.retrieveData("SELECT * FROM macccat order by accCatId");
		//Array aa=rs.getArray("name");
		//{"a","b","c"}
		//jcAccCatId=new JComboBox();
		
				
		pgb.add(new JLabel("Company Name"),0,1,10,1);
		pgb.add(new JLabel("Company Address"),0,2,10,1);
		pgb.add(new JLabel("E-mail "),0,6,10,1);
		pgb.add(new JLabel("Telephon No"),0,7,10,1);
		pgb.add(new JLabel("Fax No"),0,8,10,1);
//		pgb.add(new JLabel("Backup Path"),0,8,10,1);
//		pgb.add(new JLabel("Report Path"),0,9,10,1);
		
		pgb.add(jtCmpName,12,1,10,1,1,0);
		pgb.add(jtCmpAddr01,12,2,10,1,1,0);
    pgb.add(jtCmpAddr02,0,3,25,1,1,0);
    pgb.add(jtCmpAddr03,0,4,25,1,1,0);
    pgb.add(jtCmpAddr04,0,5,25,1,1,0);
        
    pgb.add(jtCmpEmail,12,6,10,1,1,0);
		pgb.add(jtCmpTelNo,12,7,10,1,1,0);
		pgb.add(jtFaxNo,12,8,10,1,1,0);
//		pgb.add(jtBackupPath,12,8,10,1,1,0);
//		pgb.add(jtReportPath,12,9,10,1,1,0);
		//pgb.add(jtOpDate,12,6,10,1,1,0);
		
		add(pgb);
		
		pack();
		setVisible(true);
	}

	@Override
	public void saveData() {
		DB db=new DB();
//		String sql="UPDATE optCompany SET " +
//		           "comId=0, comName='"+ jtCmpName.getText() +"', comAddr='" + jtCmpAddr01.getText() + "', " +
//		           "comEmail='" + jtCmpEmail.getText() + "', comTelNo='" + jtCmpTelNo.getText() + "', " +
//		           "comFaxNo='" + jtFaxNo.getText() + "', comBackupPath='" + jtBackupPath.getText() + "', " +
//		           "comReportPath='" + jtReportPath.getText() + "'";

//        String sql="Insert into optCompany(comId,comName, comAddr01, comAddr02, comAddr03, comEmail, comTelNo ,comFaxNo, comBackupPath,comReportPath) " +
//                    " values(0,'"+ jtCmpName.getText() +"','" + jtCmpAddr01.getText() + "','" + jtCmpAddr02.getText() + "','" + jtCmpAddr03.getText() + "','" + jtCmpEmail.getText() + "','" + jtCmpTelNo.getText() + "','" + jtFaxNo.getText() + "','" + jtBackupPath.getText() + "','" + jtReportPath.getText() + "')";
																			  		    
		String sql="Insert into optcompany(comid , comname, comaddr1 , comaddr2 , comaddr3 , comaddr4 , comemail , comtelno , comfaxno) " +
			    						" values(0,'"+ jtCmpName.getText() +"','" + jtCmpAddr01.getText() + "','" + jtCmpAddr02.getText() + "','" + jtCmpAddr03.getText() + "','" + jtCmpAddr04.getText() + "','" + jtCmpEmail.getText() + "','" + jtCmpTelNo.getText() + "','" + jtFaxNo.getText() + "')";
    Envilop env=new Envilop(sql);
		try {
			db.updateData(new Envilop("delete from optcompany"));
      db.updateData(env);
      //db.commitclose();
		} catch (SQLException e) {
			//db.rollbackclose();
			e.printStackTrace();
		}
		
		
		
		
		//UPDATE optcompany o SET comId=0, comName='Coop', comAddr='Colombo', comEmail='admin@cop.lk', comTelNo='011022033044', comFaxNo='', comBackupPath='D:\sw\backup',comReportPath='D:\sw\rpt'
		
	}

	@Override
	public void trashData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateTextBox(JTextField jtf) {
		// TODO Auto-generated method stub
		
	}

	public void componentMoved(ComponentEvent e) {
		jtCmpName.requestFocus();
		
		DB db=new DB();
		Envilop env=new Envilop("SELECT * FROM optcompany");
		ResultSet rs=null;
		try {
			rs = db.readData(env);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		 
		try{
			if (rs.next()){
				currentID=rs.getInt("comId");
				editMode=true;
				 
				jtCmpName.setText(rs.getString("comName"));
				jtCmpAddr01.setText(rs.getString("comAddr01"));
        jtCmpAddr02.setText(rs.getString("comAddr02"));
        jtCmpAddr03.setText(rs.getString("comAddr03"));
        jtCmpAddr04.setText(rs.getString("comAddr04"));
				jtCmpEmail.setText(rs.getString("comEmail"));
				jtCmpTelNo.setText(rs.getString("comTelNo"));
				jtFaxNo.setText(rs.getString("comFaxNo"));
//				jtBackupPath.setText(rs.getString("comBackupPath"));
//				jtReportPath.setText(rs.getString("comReportPath"));
			}
		}catch(SQLException sqle){
			  sqle.printStackTrace();
		}

	}

}
