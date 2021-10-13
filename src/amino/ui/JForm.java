package amino.ui;

import javax.swing.JFrame;
////
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
//import java.awt.Component;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

/**
 * @author amila giragama
 *
 */
public class JForm extends JFrame implements WindowListener {
	public static final long serialVersionUID=20060907;
	private static GridBagLayout gbl;
	private static GridBagConstraints gbc;
	private static BorderLayout bl;
	
	public final static String SEPARATOR="S e p e r at o r  S t r i n g";
	public final static String JRADIO="J r a d i o b u t t o n";
	public final static String JCHECK="J c h e c k b o x";

	public static final int GBL=10,BL=11,FL=12,GL=13;
	public static final String CENTER=BorderLayout.CENTER,EAST=BorderLayout.EAST,
	NORTH=BorderLayout.NORTH,SOUTH=BorderLayout.SOUTH,WEST=BorderLayout.WEST;

	JPanel formPanel;
	
	public static final String WINDOWS_LF="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public static final String MOTIF_LF="com.sun.java.swing.plaf.motif.MotifLookAndFeel";
	public static final String METAL_LF="javax.swing.plaf.metal.MetalLookAndFeel";
	public static final String GTK_LF="com.sun.java.swing.plaf.gtk.GTKLookAndFeel";

/*	public JForm(String title,int width,int height,int layout)	{
		super(title);
		formPanel=new JPanel();
		formPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		add(formPanel);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(screenSize.width/2 - width/2, screenSize.height/2 - height/2,width,height);
		layoutModification(layout);
		addWindowListener(this);
	}
*/

	public JForm(String title,int presentage,int layout) {
//if we send 1 as presentage the screen size will be 1/12
		super(title);
		int fix=12;
		if (presentage==100){
			setExtendedState(MAXIMIZED_BOTH);
		}else{
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int w=presentage*screenSize.width/fix;
			int h=presentage*screenSize.height/fix;
			setBounds(screenSize.width/2 - w/2, screenSize.height/2 - h/2,w,h);
		}
		formPanel=new JPanel();
		formPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		add(formPanel,CENTER);
		layoutModification(layout);
		addWindowListener(this);
	}
	
	private void layoutModification(int layout) {
		if (layout==GBL) {
			gbl=new GridBagLayout();
			getContentPane().setLayout(gbl);
			gbc=new GridBagConstraints();
		}else if(layout==BL) {
			bl=new BorderLayout();
			getContentPane().setLayout(bl);
		}else {
			System.out.println("no any layout init");
		}
	}
	
	public void addForm(InternalForm iform){
		System.out.println("jform addForm begin");
		formPanel.add(iform);
		setVisible(true);
		System.out.println("jform addForm end");
	}
	
	public void setMainMenuBar(String menu[][],ActionListener al){
		setJMenuBar(createMenuBar(menu,al));
	}

/*	public JForm(String title,int w,int h,int layout,boolean isMain) {
		this(title,w,h,layout);
		if(isMain)setDefaultCloseOperation(EXIT_ON_CLOSE);
		else setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	public JForm(String title,int layout,boolean isMain) {
		this(title,100,layout);
		//setExtendedState(MAXIMIZED_BOTH);
		if(isMain)setDefaultCloseOperation(EXIT_ON_CLOSE);
		else setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}


	public JForm(String title,int presentage,int layout,boolean isMain) {
		this(title,presentage,layout);
		if(isMain)setDefaultCloseOperation(EXIT_ON_CLOSE);
		else setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
*/
//########################################################
//method for BorderLayout
	
	public void setHVgap(int hgap,int vgap) {
		try {
			bl.setHgap(hgap);
			bl.setVgap(vgap);
		}catch(Exception e) {
			System.out.println("the boarder layout is not init");
		}
	}
	public void add(JComponent jc,String pos) {
		try {
			getContentPane().add(jc,pos);
		}catch(Exception e) {
			System.out.println("the boarder layout is not init");
		}
	}

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
//########################################################
//method for gridbaglayout
	public void add(JComponent jc,int gridX,int gridY) {
		try {
			gbc.gridx=gridX;
			gbc.gridy=gridY;
			gbl.setConstraints(jc,gbc);
			getContentPane().add(jc,gbc);
		}catch(Exception e) {
			System.out.println("Grid Bag Layout is null you may init another layout");
		}
	}

	public void add( JComponent jc,int gridx,int gridy,int gridwidth,int gridheight)	{
		try {
			gbc=new GridBagConstraints();
			gbc.gridx=gridx;
			gbc.gridy=gridy;
			gbc.gridwidth=gridwidth;
			gbc.gridheight=gridheight;
			gbl.setConstraints(jc,gbc);
			getContentPane().add(jc,gbc);
		}catch(Exception e) {
			System.out.println("Grid Bag Layout is null you may init another layout");
		}
	}
	
	public void add(JComponent jc,int gridX,int gridY,
			int gridWidth,int gridHeight,int weightX,int weightY) {
		try {
			gbc.gridx=gridX;
			gbc.gridy=gridY;
			gbc.gridwidth=gridWidth;
			gbc.gridheight=gridHeight;
			gbc.weightx=weightX;
			gbc.weighty=weightY;
			gbl.setConstraints(jc,gbc);
			getContentPane().add(jc,gbc);
		}catch(Exception e) {
				System.out.println("Grid Bag Layout is null you may init another layout");
		}
	}

	public JMenuBar createMenuBar(String menu[][],ActionListener al) {
		JMenuBar jmb=null;
		if (jmb == null){
			jmb=new JMenuBar();
		}
		for(int x=0;x<menu.length;x++) {
			JMenu	jm=new JMenu(menu[x][0]);
			ButtonGroup bg=new ButtonGroup();
			for(int y=1;y<menu[x].length;y++) {
				if(menu[x][y].equals(SEPARATOR)){
					jm.addSeparator();
				}else if(menu[x][y].equals(JRADIO)){
					y++;
					JRadioButtonMenuItem jrbmi=new JRadioButtonMenuItem(menu[x][y]);
					bg.add(jrbmi);
					jrbmi.addActionListener(al);
					jm.add(jrbmi);
				}else if(menu[x][y].equals(JCHECK)){
					y++;
					JCheckBoxMenuItem jcbmi=new JCheckBoxMenuItem(menu[x][y]);
					jcbmi.addActionListener(al);
					jm.add(jcbmi);
				}else{
					JMenuItem jmi=new JMenuItem(menu[x][y]);
					jmi.addActionListener(al);
					jm.add(jmi);
				}
			}
			jmb.add(jm);
		}
		return jmb;
	}	
	
	public void windowActivated(WindowEvent e){}
	public void windowClosed(WindowEvent e){
 		Runtime.getRuntime().gc();
	}
	public void windowClosing(WindowEvent e){}
	public void windowDeactivated(WindowEvent e){}
	public void windowDeiconified(WindowEvent e){}
	public void windowIconified(WindowEvent e) {}
	public void windowOpened(WindowEvent e) {}
}

