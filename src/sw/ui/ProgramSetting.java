package sw.ui;

import amino.data.ReadWrite;
import amino.ui.ExternelForm;
import java.awt.event.ComponentEvent;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import amino.ui.MasterForm;
import amino.ui.PanelGridBag;
import amino.ui.util.ALabel;
import amino.ui.util.TextBox;
import amino.ui.util.TextBoxIntiger;
import amino.ui.util.TextBoxNumeric;
import amino.ui.util.i18n;


import java.awt.event.ActionEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import javax.swing.JOptionPane;
import sw.CommonCons;
import sw.RegQuery;
import sw.proConst;
//import ui.proConst;

public class ProgramSetting extends ExternelForm {

	private static final long serialVersionUID = 200906290309L;

	PanelGridBag pgb;

	TextBox txtDatabaseName,txtServerIp,txtServerPort,txtUser,txtBackupPath,
	txtReportPath,txtTmpOutputReportPath,txtReportPathAcc,txtglPath,txttrnsbckpath,txtretrvbckpath,txtPropertyFile;
    TextBoxNumeric withholdrate;
	JPasswordField txtPassword;

	public ProgramSetting(){
		super("Program Setting",250,400);

		pgb=new PanelGridBag();
		txtServerIp=new TextBox();

		txtDatabaseName=new TextBox();
		txtUser=new TextBox();
		txtPassword=new JPasswordField();
		txtBackupPath=new TextBox();
		txtReportPath=new TextBox();
		txtServerPort=new TextBox();
		txtPropertyFile=new TextBox();
		txtTmpOutputReportPath=new TextBox();
    withholdrate=new TextBoxNumeric();

		pgb.add(new JLabel(i18n.getString("PrgSettings.ServerIp")),0,1,5,1);
		pgb.add(new JLabel(i18n.getString("PrgSettings.ServerPort")),0,2,5,1);
		pgb.add(new JLabel(i18n.getString("PrgSettings.Database")),0,3,5,1);
		pgb.add(new JLabel(i18n.getString("PrgSettings.UserName")),0,5,5,1);
		pgb.add(new JLabel(i18n.getString("PrgSettings.Password")),0,7,5,1);
		pgb.add(new JLabel(i18n.getString("PrgSettings.ReportPath")),0,9,5,1);
		pgb.add(new JLabel(i18n.getString("PrgSettings.PropertyFile")),0,11,5,1);
		pgb.add(new JLabel(i18n.getString("PrgSettings.tmpOutPutReport")),0,13,5,1);
		pgb.add(new JLabel(i18n.getString("PrgSettings.LetterPath")),0,15,5,1);

		pgb.add(txtServerIp,7,1,20,1,1,0);
		pgb.add(txtServerPort,7,2,20,1,1,0);
		pgb.add(txtDatabaseName,7,3,20,1,1,0);
		pgb.add(txtUser,7,5,20,1,1,0);
		pgb.add(txtPassword,7,7,20,1,1,0);
		pgb.add(txtReportPath,7,9,25,1,1,0);
		pgb.add(txtPropertyFile,7,11,25,1,1,0);
    pgb.add(txtTmpOutputReportPath,7,13,25,1,1,0);
    pgb.add(withholdrate,7,15,25,1,1,0);
		
		setToolBar(this,CommonCons.SAVEBTN);
		add(pgb);
		setVisible(true);
    loadData();
	}

  public void saveData() {
		String pw=new String (txtPassword.getPassword());
		String[] data={txtServerIp.getText(),txtDatabaseName.getText(),txtUser.getText(),pw,txtReportPath.getText(),txtServerPort.getText(),txtPropertyFile.getText(),txtTmpOutputReportPath.getText(),withholdrate.getText()};
		ReadWrite.writeFile(proConst.fln, data);
  }

// public void writeFile(String filenama,String[] data){
//		FileOutputStream fout;
//		try{
//		    fout = new FileOutputStream (RegQuery.SQLPATH+"/"+filenama+".ai");
//		    for (int x=0;x<data.length;x++)
//		    	new PrintStream(fout).println (data[x]);
//		    fout.close();
//        JOptionPane.showMessageDialog(null, "Succesfully Saved");
//       // editfalse();
//        this.dispose();
//
//		}catch (IOException e){
//			System.err.println ("Unable to write to file");
//		}
//	}

//	public void componentMoved(ComponentEvent ce) {
//		txtServerIp.setText(proConst.serverIp);
//		txtDatabaseName.setText(proConst.dbName);
//		txtUser.setText(proConst.user);
//		txtPassword.setText(proConst.passwd);
//		txtReportPath.setText(proConst.reportPath);
//
//
//	}
	public void actionPerformed(ActionEvent ae) {
		super.actionPerformed(ae);
		saveData();
	}

  	private void loadData(){
		txtServerIp.setText(proConst.serverIp);
//		txtServerPort.setText(proConst.serverPort);
		txtDatabaseName.setText(proConst.dbName);
		txtUser.setText(proConst.user);
		txtPassword.setText(proConst.passwd);
		txtServerPort.setText(proConst.serverPort);
		txtPropertyFile.setText(proConst.l10n);

//		txtBackupPath.setText(proConst.backupPath);
		txtReportPath.setText(proConst.reportPath);
//		txtReportPathAcc.setText(proConst.reportPathAcc);
		txtTmpOutputReportPath.setText(proConst.tmpOutputPath);
        withholdrate.setText(proConst.withholdrate);
	}

//    @Override
//    public void trashData() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public void validateTextBox(JTextField arg0) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
}
