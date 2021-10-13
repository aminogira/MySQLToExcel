package amino.ui;
//
import java.awt.Component;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToolBar;
import javax.swing.ButtonGroup;
//import javax.swing.Icon;
import javax.swing.ImageIcon;

import amino.ui.util.i18n;

import sw.CommonCons;
import sw.LogConst;
import sw.proConst;
//import amino.ui.util.ImageIconAmi;

import java.awt.event.ActionListener;
/**
 * @author amila giragama
 *   mfile.setMnemonic('f')
 *    jf1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,ActionEvent.CTRL_MASK));
 */
public class UIToolCreator {
	//UIToolCreator.SEPARATOR
	public final static String SEPARATOR="S e p e r at o r  S t r i n g";
	public final static String JRADIO="J r a d i o b u t t o n";
	public final static String JCHECK="J c h e c k b o x";

	static JMenuBar jmb;
	static String mainMenuItems[][];

	public static JMenuBar createMenuBar(String menu[][],ActionListener al) {
		if (jmb == null){
			jmb=new JMenuBar();
		}
		for(int x=0;x<menu.length;x++) {
			
			JMenu	jm=new JMenu(i18n.getString(menu[x][0]));
			setupMenuFont(jm);
			ButtonGroup bg=new ButtonGroup();
			for(int y=1;y<menu[x].length;y++) {
				if(menu[x][y].equals(SEPARATOR)){
					jm.addSeparator();
				}else if(menu[x][y].equals(JRADIO)){
					y++;
					JRadioButtonMenuItem jrbmi=new JRadioButtonMenuItem(i18n.getString(menu[x][y]));
					setupMenuFont(jrbmi);
					bg.add(jrbmi);
					jrbmi.addActionListener(al);
					jm.add(jrbmi);
				}else if(menu[x][y].equals(JCHECK)){
					y++;
					JCheckBoxMenuItem jcbmi=new JCheckBoxMenuItem(i18n.getString(menu[x][y]));
					setupMenuFont(jcbmi);
					jcbmi.addActionListener(al);
					jm.add(jcbmi);
				}else if(menu[x][y].equals("")){
					
				}else{
					String menuLabel="";
//					if (proConst.isUserLogging.equals("TRUE")){
//						menuLabel = LogConst.currentUser.show(menu[x][y]);
//					}else
					menuLabel = menu[x][y];
					if (!(menuLabel.trim().equals(""))){
						JMenuItem jmi=new JMenuItem(i18n.getString(menu[x][y]));
						setupMenuFont(jmi);
						jmi.addActionListener(al);
            //FontSize
            //jmi.setFont(new Font("",25,18));
						jm.add(jmi);
					}
				}
			}
      //jm.setFont(new Font("",25,25));
			jmb.add(jm);
		}
		return jmb;
	}

	private static void setupMenuFont(Component com){
		if(!(i18n.getString("Menu.FontStyle").equals("null"))){
			String fntStyle=i18n.getString("Menu.FontStyle");
			int fntSize=i18n.getInt("Menu.FontSize");
			int fntType=i18n.getInt("Menu.FontType");
			com.setFont(new Font(fntStyle,fntType,fntSize));
		}
	}
	
	private static void setupFont(String tagPropertySet,Component com){
		if(!(i18n.getString(tagPropertySet + ".FontStyle").equals("null"))){
			String fntStyle=i18n.getString(tagPropertySet +".FontStyle");
			int fntSize=i18n.getInt(tagPropertySet +".FontSize");
			int fntType=i18n.getInt(tagPropertySet +".FontType");
			com.setFont(new Font(fntStyle,fntType,fntSize));
		}
	}
	
	
	
	public static JToolBar createBasicToolBar(String toolBarName,ActionListener al,int buttons){
		JToolBar jtb=new JToolBar(toolBarName);
		JButton jb;
		if((buttons & CommonCons.SAVEBTN) == CommonCons.SAVEBTN){
			jb=new JButton(i18n.getString("BasicToolBar.SAVE"),new ImageIcon(CommonCons.ICOPATH+CommonCons.SAVEICO+CommonCons.ICOTYPE));
			setupFont("BasicToolBar",jb);
			jb.setRolloverIcon(new ImageIcon(CommonCons.ROLLOVERICOPATH+CommonCons.SAVEICO+CommonCons.ICOTYPE));
			jb.setDisabledIcon(new ImageIcon(CommonCons.DISABLEDICOPATH+CommonCons.SAVEICO+CommonCons.ICOTYPE));
			jb.setPressedIcon(new ImageIcon(CommonCons.PRESSEDICOPATH+CommonCons.SAVEICO+CommonCons.ICOTYPE));
			jb.addActionListener(al);
			jtb.add(jb);
		}
		if((buttons & CommonCons.PRINTBTN) == CommonCons.PRINTBTN){
			jb=new JButton(i18n.getString("BasicToolBar.PRINT"),new ImageIcon(CommonCons.ICOPATH+CommonCons.PRINTICO+CommonCons.ICOTYPE));
			setupFont("BasicToolBar",jb);
			jb.setRolloverIcon(new ImageIcon(CommonCons.ROLLOVERICOPATH+CommonCons.PRINTICO+CommonCons.ICOTYPE));
			jb.setDisabledIcon(new ImageIcon(CommonCons.DISABLEDICOPATH+CommonCons.PRINTICO+CommonCons.ICOTYPE));
			jb.setPressedIcon(new ImageIcon(CommonCons.PRESSEDICOPATH+CommonCons.PRINTICO+CommonCons.ICOTYPE));
			jb.addActionListener(al);
			jtb.add(jb);
		}
		if((buttons & CommonCons.CANCELBTN) == CommonCons.CANCELBTN){
			jb=new JButton(i18n.getString("BasicToolBar.CANCEL") ,new ImageIcon(CommonCons.ICOPATH+CommonCons.CANCELICO+CommonCons.ICOTYPE));
			setupFont("BasicToolBar",jb);
			jb.setRolloverIcon(new ImageIcon(CommonCons.ROLLOVERICOPATH+CommonCons.CANCELICO+CommonCons.ICOTYPE));
			jb.setDisabledIcon(new ImageIcon(CommonCons.DISABLEDICOPATH+CommonCons.CANCELICO+CommonCons.ICOTYPE));
			jb.setPressedIcon(new ImageIcon(CommonCons.PRESSEDICOPATH+CommonCons.CANCELICO+CommonCons.ICOTYPE));
			jb.addActionListener(al);
			jtb.add(jb);
		}
		if((buttons & CommonCons.TRASHBTN) == CommonCons.TRASHBTN){
			jb=new JButton(i18n.getString("BasicToolBar.TRASH") ,new ImageIcon(CommonCons.ICOPATH+CommonCons.TRASHICO+CommonCons.ICOTYPE));
			setupFont("BasicToolBar",jb);
			jb.setRolloverIcon(new ImageIcon(CommonCons.ROLLOVERICOPATH+CommonCons.TRASHICO+CommonCons.ICOTYPE));
			jb.setDisabledIcon(new ImageIcon(CommonCons.DISABLEDICOPATH+CommonCons.TRASHICO+CommonCons.ICOTYPE));
			jb.setPressedIcon(new ImageIcon(CommonCons.PRESSEDICOPATH+CommonCons.TRASHICO+CommonCons.ICOTYPE));
			jb.addActionListener(al);
			jtb.add(jb);
		}
		if((buttons & CommonCons.SHOWDOCBTN) == CommonCons.SHOWDOCBTN){
			jb=new JButton(i18n.getString("BasicToolBar.SHOWDOC") ,new ImageIcon(CommonCons.ICOPATH+CommonCons.SHOWDOCICO+CommonCons.ICOTYPE));
			setupFont("BasicToolBar",jb);
			jb.setRolloverIcon(new ImageIcon(CommonCons.ROLLOVERICOPATH+CommonCons.SHOWDOCICO+CommonCons.ICOTYPE));
			jb.setDisabledIcon(new ImageIcon(CommonCons.DISABLEDICOPATH+CommonCons.SHOWDOCICO+CommonCons.ICOTYPE));
			jb.setPressedIcon(new ImageIcon(CommonCons.PRESSEDICOPATH+CommonCons.SHOWDOCICO+CommonCons.ICOTYPE));
			jb.addActionListener(al);
			jtb.add(jb);
		}
		if((buttons & CommonCons.BACKBTN) == CommonCons.BACKBTN){
			jb=new JButton(i18n.getString("BasicToolBar.BACK") ,new ImageIcon(CommonCons.ICOPATH+CommonCons.BACKICO+CommonCons.ICOTYPE));
			setupFont("BasicToolBar",jb);
			jb.setRolloverIcon(new ImageIcon(CommonCons.ROLLOVERICOPATH+CommonCons.BACKICO+CommonCons.ICOTYPE));
			jb.setDisabledIcon(new ImageIcon(CommonCons.DISABLEDICOPATH+CommonCons.BACKICO+CommonCons.ICOTYPE));
			jb.setPressedIcon(new ImageIcon(CommonCons.PRESSEDICOPATH+CommonCons.BACKICO+CommonCons.ICOTYPE));
			jb.addActionListener(al);
			jtb.add(jb);
		}
		return jtb;
	}

	
	public static JToolBar createToolBar(String toolBarName,String toolButton[],ActionListener al,ImageIcon ii[]){
		JToolBar jtb=new JToolBar(toolBarName);
		for(int x=0;x<toolButton.length;x++){
			JButton jb;
			if (ii == null){
				jb=new JButton(toolButton[x]);
			}	else	{
				jb=new JButton(toolButton[x],ii[x]);
			}
			jb.addActionListener(al);
			jtb.add(jb);
		}
		return jtb;
	}
}



