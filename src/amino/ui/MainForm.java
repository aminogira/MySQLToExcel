package amino.ui;

import javax.swing.JFrame;
import javax.swing.JToolBar;
//
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;


//import de.javasoft.plaf.synthetica.SyntheticaStandardLookAndFeel;


import java.awt.Toolkit;

import java.awt.BorderLayout;
import java.awt.Dimension;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;

//import javax.swing.JFrame;
//import javax.swing.JComponent;
//import javax.swing.UnsupportedLookAndFeelException;
//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
//import java.awt.BorderLayout;
//import java.awt.FlowLayout;
//import java.awt.Dimension;
//import java.awt.Toolkit;

import sw.CommonCons;
import amino.ui.util.FindLilst;
import amino.ui.util.i18n;

/**
 * @author amila giragama
 *
 */
public abstract class MainForm extends JFrame implements ActionListener {
	JToolBar jtbMain;
	JPanel formPanel,findPanel;
	/**
	 * Method MainForm
	 *
	 *
	 */
	protected String mainMenuBar[][];
	
	private MainForm(String title) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setExtendedState(MAXIMIZED_BOTH);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int w=screenSize.width;
		int h=screenSize.height;
		
		setBounds(screenSize.width/2 - w/2, screenSize.height/2 - h/2,w,h);		
		
		//formPanel=new PanelFlow(PanelFlow.CENTER);

		//getContentPane().add(formPanel,BorderLayout.CENTER);
		setIconImage(Toolkit.getDefaultToolkit().getImage(CommonCons.FORM_ICON_MAIN));
	}
	
	public MainForm(String title,String BackgroundFile) {
		this(title);
		
		if ((BackgroundFile==null)||(BackgroundFile.length()==0)){
			formPanel=new PanelFlow(PanelFlow.CENTER);
		}else{
			formPanel=new PanelFlow(PanelFlow.CENTER,BackgroundFile);
		}

		getContentPane().add(formPanel,BorderLayout.CENTER);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(CommonCons.FORM_ICON_MAIN));
	}
	
	public void setMainToolBar(String toolBar[],ActionListener al){
		add(UIToolCreator.createToolBar("Main Tool Bar",toolBar,al,null),BorderLayout.NORTH);
	}
	public void setMainMenuBar(){
		setMainMenuBar(this);
	}
	public void setMainMenuBar(ActionListener al){
		setJMenuBar(UIToolCreator.createMenuBar(mainMenuBar,al));
	}
	/*
	public void addForm(InternalForm iform){
		getContentPane().add(iform,BorderLayout.CENTER);
		setVisible(true);
	}
	*/
	public void addForm(InternalForm iform){
		formPanel.add(iform);
		setVisible(true);
	}
	

	
	public void showFindList(String tbl,String colName){
		findPanel=new FindLilst();
		getContentPane().add(findPanel,BorderLayout.EAST);
		((FindLilst) findPanel).setGrid(tbl,colName);
		setVisible(true);
	}
	public void showFindTree(){
		
		//findPanel=new FindTree();
		//getContentPane().add(findPanel,BorderLayout.EAST);
		//setVisible(true);
	}
	public void clearFindGrid(){
		remove(findPanel);
		findPanel=null;
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae){
		String ac=ae.getActionCommand();
		if(ac.equals(i18n.getString("Menu.Theam0"))){
			changeUI(CommonCons.WINDOWS_LF);
		}else if(ac.equals(i18n.getString("Menu.Theam1"))){
			changeUI(CommonCons.METAL_LF);
		}else if(ac.equals(i18n.getString("Menu.Theam2"))){
			changeUI(CommonCons.MOTIF_LF);
		}else if(ac.equals(i18n.getString("Menu.Theam3"))){
			changeUI(CommonCons.SYNTH_LF);
		}else if(ac.equals(i18n.getString("Menu.Theam4"))){
			changeUISynthetica();
		}else if(ac.equals(i18n.getString("Menu.Theam5"))){
			changeUI(CommonCons.GTK_LF);
	
//		}else if(ac.equals(CommonCons.MNU_CMD_SETTING)) {
//			showProgramSettingForm();
		}else{
			System.out.println (ac);
		}
	}
	

	public abstract void showProgramSettingForm();
	


	
	public void changeUI(String ui) {
		try {
			UIManager.setLookAndFeel(ui);
			
			SwingUtilities.updateComponentTreeUI(this);
		}catch (ClassNotFoundException exc) {
			System.err.println("Could not load LookAndFeel: ");
		}catch (InstantiationException exc) {
		}catch (IllegalAccessException exc) {
		}catch (UnsupportedLookAndFeelException exc) {
			System.err.println("Not Suppported OS to the LookAndFeel: ");
		}
	}
	public void changeUISynthetica() {
			//try {
//				try {
//					UIManager.setLookAndFeel(new SyntheticaStandardLookAndFeel());
//				} catch (UnsupportedLookAndFeelException e) {
//					e.printStackTrace();
//				}catch (ParseException e) {
//
//				}
////			} catch (ParseException e) {
////				e.printStackTrace();
////			}
			SwingUtilities.updateComponentTreeUI(this);
	}
	

/**

 	public Object getValue(String key) {
 		return new Object();
 	}
          //Gets one of this object's properties using the associated key.
 	public boolean isEnabled() {
 		return true;
 	}
          //Returns the enabled state of the Action.
 	public void putValue(String key, Object value) {}
          //Sets one of this object's properties using the associated key.
 	public void removePropertyChangeListener(PropertyChangeListener listener) {}
   public void addPropertyChangeListener(PropertyChangeListener listener) {}
          //Removes a PropertyChange listener.
 	public void setEnabled(boolean b) {}
          //Sets the enabled state of the Action.

*/




}

//if(ac.equals(CommonCons.WINDOWSLOOKANDFEEL)){
//changeUI(CommonCons.WINDOWS_LF);
//}else if(ac.equals(CommonCons.METTLELOOKANDFEEL)){
//changeUI(CommonCons.METAL_LF);
//}else if(ac.equals(CommonCons.MOTIFLOOKANDFEEL)){
//changeUI(CommonCons.MOTIF_LF);
//}else if(ac.equals(CommonCons.SYNTHLOOKANDFEEL)){
//changeUI(CommonCons.SYNTH_LF);
//}else if(ac.equals(CommonCons.SYNTHETICALOOKANDFEEL)){
//changeUISynthetica();
//}else if(ac.equals(CommonCons.GTKLOOKANDFEEL)){
//changeUI(CommonCons.GTK_LF);


//JFormattedTextField