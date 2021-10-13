package amino.print;

import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.util.Date;
////
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import sw.db.DB;


import com.toedter.calendar.JDateChooser;

import amino.ui.InternalForm;
import amino.ui.PanelGridBag;
import amino.ui.util.ALabel;
import amino.ui.util.i18n;

public abstract class PrintReport extends InternalForm {
	private static final long serialVersionUID = 200907140142L;
	
	protected PanelGridBag pgb;
	protected JDateChooser dcFrom,dcTo;
	JButton jb[];
	
	public PrintReport(String title,String reportName[]) {
		super(title, false, true, false, false);
		pgb=new PanelGridBag();
		
		dcFrom=new JDateChooser();
		dcTo=new JDateChooser();
		dcFrom.setDate(new Date());
		dcTo.setDate(new Date());
				
		pgb.add(new ALabel(i18n.getString("PrintReport.From")),0,2,10,1);
		pgb.add(dcFrom,12,2,10,1);
		pgb.add(new ALabel(i18n.getString("PrintReport.To")),0,5,10,1);
		pgb.add(dcTo,12,5,10,1);
		
		int top = 10;
		jb=new JButton[reportName.length];
		for (int x=0;x<reportName.length;x++){
			jb[x]=new JButton(reportName[x].substring(0, (reportName[x].length()-7)));
			pgb.add(jb[x],0,top,25,1);
			jb[x].addActionListener(this);
			top+=2;
		}
		add(pgb);
//		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae){
		super.actionPerformed(ae);
		showReport(ae.getActionCommand());
		
		showReport(ae.getActionCommand(),new DB());
	}
	
	public abstract void showReport(String reportName);
	public abstract void showReport(String reportName,DB db);

	public void validateTextBox(JTextField jtf) {
	}

	public void componentMoved(ComponentEvent e) {
	}

}
