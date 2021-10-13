package sw.ui;
//
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;

import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import sw.CommonCons;
import sw.db.DB;
import sw.db.Envilop;

import amino.ui.InternalForm;
import amino.ui.util.i18n;

import java.awt.event.ActionEvent;

public class optQuaryRunner extends InternalForm {

	private static final long serialVersionUID = 200905290430L;

	JTextArea jtfQuary,jtfAnswer;
	JScrollPane jspQuary,jspAnswer;
	
	public optQuaryRunner(){
		super(i18n.getString("QuaryRunner.title"),false,true,true,false);
		
		
		
		setToolBar(this,CommonCons.SAVEBTN+CommonCons.CANCELBTN+CommonCons.BACKBTN);
		setBounds(0, 0, 400, 300);
		jtfQuary=new JTextArea();
		jtfAnswer=new JTextArea();
		jtfQuary.addFocusListener(this);
		jspQuary=new JScrollPane(jtfQuary);
		jspAnswer=new JScrollPane(jtfAnswer);
		add(jspQuary);
		add(jspAnswer);
    setComponent();
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae){
    super.actionPerformed(ae);
    if(ae.getActionCommand().equals(i18n.getString("BasicToolBar.SAVE"))){
      try {
        String sql = jtfQuary.getText().trim();
        Envilop env = new Envilop(sql);
        DB db = new DB();
        db.updateData(env);
				//db.commitclose();
        jtfAnswer.setText("Data Updated");
      } catch (SQLException ex) {
        //Logger.getLogger(optQuaryRunner.class.getName()).log(Level.SEVERE, null, ex);
        jtfAnswer.setText(ex.toString());
      }
    }
    setComponent();
  }
	public void validateTextBox(JTextField jtf) {
		
	}

	public void componentMoved(ComponentEvent e) {
//		setBounds(0, 0, 400, 300);
		setSize(400, 300);
    setComponent();
    jtfQuary.requestFocus();
	}

  public void setComponent(){
		jspQuary.setBounds(10, 45, 370, 120);
		jspAnswer.setBounds(10, 170, 370, 60);
  }
  public void focusGained(FocusEvent e) {
  	jtfAnswer.setText("");
  }

}
