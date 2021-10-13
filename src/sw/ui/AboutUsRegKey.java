package sw.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import amino.ui.ExternelForm;
import amino.ui.PanelGridBag;

public class AboutUsRegKey extends ExternelForm {
	private static final long serialVersionUID = 20100106L;
	
	
	PanelGridBag pgb;
	JPanel borderPanel;
	
	public AboutUsRegKey() {
		super("About Info Finance", 450, 400);
		initComponent();
		add(borderPanel);
		setVisible(true);
	}
	
	
	private void initComponent(){
		borderPanel=new JPanel();
		borderPanel.setLayout(new BorderLayout());
		pgb=new PanelGridBag();

		
		JLabel lbPic,lbCompanyName,lbHO,lbBr;	

		Font fnt25=new Font(null,Font.BOLD,25);
		Font fnt15=new Font(null,Font.BOLD,15);

		
		ImageIcon img=new ImageIcon("pic/b3.jpg");
		lbPic=new JLabel(img);
		lbPic.setSize(new Dimension(10,100));
		lbCompanyName=new JLabel("Soft-Watch Infosys");
		lbCompanyName.setFont(fnt25);
		lbHO=new JLabel("HEAD OFFICE");
		lbHO.setFont(fnt15);
		lbBr=new JLabel("BRANCH");
		lbBr.setFont(fnt15);
		
		
		pgb.add(lbCompanyName, 0, 0,50,5);
		pgb.add(new JLabel("========================="), 0, 6,50,1);
		pgb.add(lbHO, 0, 8,50,1);
		pgb.add(new JLabel("127:1/1,"), 0, 10,50,1);
		pgb.add(new JLabel("Thimbirigasyaya Road,"), 0, 12,50,1);
		pgb.add(new JLabel("Colombo 05."), 0, 14,50,1);
		pgb.add(new JLabel("Tel: +94 112 500 424"), 0, 16,50,1);
		pgb.add(new JLabel("Fax: +94 112 500 141"), 0, 18,50,1);
		
		pgb.add(lbBr, 0, 25,50,1);
		pgb.add(new JLabel("835/3-1"), 0, 27,50,1);		
		pgb.add(new JLabel("Peradeniya Road"), 0, 29,50,1);
		pgb.add(new JLabel("Kandy"), 0, 31,50,1);
		pgb.add(new JLabel("Tel: +94 812 205 154"), 0, 33,50,1);
		
		//pgb.add(lbPic,50,0,10,60);
		
		
		borderPanel.add(lbPic,BorderLayout.EAST);
		
		borderPanel.add(pgb, BorderLayout.CENTER);
		
	}
	



}
