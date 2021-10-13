package sw.ui;


import amino.data.ReadWrite;
import amino.ui.ExternelForm;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import amino.ui.PanelGridBag;
import amino.ui.util.LabledComponent;
import amino.ui.util.TextBox;
import amino.ui.util.i18n;

import java.awt.event.ActionEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import sw.CommonCons;
import sw.EConst;
import sw.proConst;
//import ui.proConst;

public class EmailSettings extends ExternelForm {

	private static final long serialVersionUID = 200906290309L;
	PanelGridBag pgb;
	TextBox txtsmtpserv,txtuser,txtfrom,txtto,txtsubject,txtbody,txtattachPath,txtReportPathAcc,txtglPath,txttrnsbckpath,txtretrvbckpath;
	//JTextArea txtbody;
    JPasswordField txtPassword;

	public EmailSettings(){
		super("Email Setting",250,400);

		pgb=new PanelGridBag();
		txtsmtpserv=new TextBox();
		txtuser=new TextBox();
		txtfrom=new TextBox();
		txtPassword=new JPasswordField();
		txtto=new TextBox();
		txtsubject=new TextBox();
        txtbody=new TextBox();
        txtattachPath=new TextBox();

		pgb.add(new JLabel("SMTP Server"),0,1,5,1);
		pgb.add(new JLabel("User Name"),0,3,5,1);
        pgb.add(new JLabel("Password"),0,5,5,1);
		pgb.add(new JLabel("From"),0,7,5,1);
		pgb.add(new JLabel("To"),0,9,5,1);
        pgb.add(new JLabel("Subject"),0,11,5,1);
		pgb.add(new JLabel("Message Body"),0,13,5,1);
        pgb.add(new JLabel("Attachment File Path"),0,15,5,1);

		pgb.add(txtsmtpserv,7,1,20,1,1,0);
		pgb.add(txtuser,7,3,20,1,1,0);
		pgb.add(txtPassword,7,5,20,1,1,0);
        pgb.add(txtfrom,7,7,20,1,1,0);
		pgb.add(txtto,7,9,20,1,1,0);
		pgb.add(txtsubject,7,11,25,1,1,0);
        pgb.add(txtbody,7,13,25,1,1,0);
        pgb.add(txtattachPath,7,15,25,1,1,0);
        //pgb.add(new LabledComponent("Message Body", txtbody),0,11,12,1);


        setToolBar(this,CommonCons.SAVEBTN);
		add(pgb);
		setVisible(true);
        loadData();
	}

  public void saveData() {
		String pw=new String (txtPassword.getPassword());
		String[] data={txtsmtpserv.getText(),txtuser.getText(),pw,txtfrom.getText(),txtto.getText(),txtsubject.getText(),txtbody.getText(),txtattachPath.getText()};
		writeFile(EConst.file, data);
  }

 public void writeFile(String filenama,String[] data){
		FileOutputStream fout;
		try{
		    fout = new FileOutputStream (filenama+".mail");
		    for (int x=0;x<data.length;x++)
		    	new PrintStream(fout).println (data[x]);
		    fout.close();
        JOptionPane.showMessageDialog(null, i18n.getString("msg.saveok"));
       // editfalse();
        this.dispose();

		}catch (IOException e){
			System.err.println ("Unable to write to file");
		}
	}

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
		txtsmtpserv.setText(EConst.smtpserv);
//		txtServerPort.setText(proConst.serverPort);
		txtuser.setText(EConst.usernm);
        txtPassword.setText(EConst.passwrd);
		txtfrom.setText(EConst.efrom);
		txtto.setText(EConst.eto);
		txtsubject.setText(EConst.esubject);
		txtbody.setText(EConst.ebody);
		txtattachPath.setText(EConst.eattachpath);
	}


}
