package amino.ui;
//
import javax.swing.JPanel;
import javax.swing.JComponent;

import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Vector;
import java.awt.Component;
/**
 * @author amila giragama
 *
 */
public class PanelGridBag extends JPanel {

	private static final long serialVersionUID = 200904280149L;
	GridBagLayout gbl;
	GridBagConstraints gbc;
	
	protected Vector<Component> focusOrder;
	protected FocusTravesel ft;

	
	//public Vector<Component> order;
	
	public PanelGridBag() {
		gbl=new GridBagLayout();
		
		setLayout(gbl);
		focusOrder=new Vector<Component>();
//		order=new Vector<Component>();
		gbc=new GridBagConstraints();
	}
	
	protected void setFocusOrder(Component[] compList,Container baseOn){
		for (int x=0;x<compList.length;x++){
			focusOrder.add(compList[x]);
		}
		ft=new FocusTravesel(focusOrder, baseOn);
	}
	
	public void setTravasalPolicy(Vector<Component> order){
		ft=new FocusTravesel(order, this);		
	}
	
	public void add(JComponent jc,int gridx,int gridy){
		gbc.gridx=gridx;
		gbc.gridy=gridy;
		gbc.fill=GridBagConstraints.BOTH;
//		gbc.ipady=10;
//		gbc.ipadx=10;
		gbc.insets=new Insets(2, 5, 2, 5);	
		add(jc,gbc);
	}
	public void add(JComponent jc,int gridx,int gridy,int gridwidth,int gridheight){
		gbc.gridwidth=gridwidth;
		gbc.gridheight=gridheight;
		add(jc,gridx,gridy);
	}
	public void add(JComponent jc,int gridx,int gridy,int gridwidth,int gridheight,double weightx,double weighty){
		gbc.weightx=weightx;
		gbc.weighty=weighty;		
		add(jc,gridx,gridy,gridwidth,gridheight);
	}
	
	
	public void clearForm(){
		ControlComponent.clearAllJTextField(this);
	}
	
}