package amino.ui.util;

import java.awt.Component;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class AOptionPane extends JOptionPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 20100506L;

//	public AOptionPane() {
//		setButtonText(null);
//		setupFont();	}
//
//	public AOptionPane(Object arg0) {
//		super(arg0);
//		setButtonText(null);
//		setupFont();
//	}
//
//	public AOptionPane(Object arg0, int arg1) {
//		super(arg0, arg1);
//		setButtonText(null);
//		setupFont();
//	}
//
//	public AOptionPane(Object arg0, int arg1, int arg2) {
//		super(arg0, arg1, arg2);
//		setButtonText(null);
//		setupFont();
//	}
//
//	public AOptionPane(Object arg0, int arg1, int arg2, Icon arg3) {
//		super(arg0, arg1, arg2, arg3);
//		setButtonText(null);
//		setupFont();
//	}
//
//	public AOptionPane(Object arg0, int arg1, int arg2, Icon arg3, Object[] arg4) {
//		super(arg0, arg1, arg2, arg3, arg4);
//		setButtonText(null);
//		setupFont();
//	}
//
//	public AOptionPane(Object arg0, int arg1, int arg2, Icon arg3, Object[] arg4,	Object arg5) {
//		super(arg0, arg1, arg2, arg3, arg4, arg5);
//		setButtonText(null);
//		setupFont();
//		
//	}
	
	
  public static void showMessageDialog(Component parentComponent,   Object message) throws HeadlessException {
    showMessageDialog(parentComponent, message, "Message", INFORMATION_MESSAGE);
    setButtonText(null);
    setupFont();
  }

  public static void showMessageDialog(Component parentComponent, Object message, String title, int messageType)  throws HeadlessException {
    showMessageDialog(parentComponent, message, title, messageType, null);
    setButtonText(null);
    setupFont();
  }

  public static void showMessageDialog(Component parentComponent,Object message, String title, int messageType, Icon icon)  throws HeadlessException {
  	showOptionDialog(parentComponent, message, title, DEFAULT_OPTION, messageType, icon, null, null);
    setButtonText(null);
    setupFont();
  }
	
	
//	public static void showMessageDialog(Component com,Object obj){
//		
//		//setButtonText(null);
//		
//	}
	
	private static  void setButtonText(String[] givenOption){
//		System.out.println("call to setbutton text " + optionType);
//		if(!(i18n.getString("PrgCommen.DefaultMsgFontStyle").equals("null"))){
//			if(givenOption==null){
//				String msgOption[]=null;
//				if(optionType==YES_NO_CANCEL_OPTION){
//					msgOption=new String[]{i18n.getString("PrgCommen.DefaultMsgOptionYes"), i18n.getString("PrgCommen.DefaultMsgOptionNo"), i18n.getString("PrgCommen.DefaultMsgOptionCancel") };
//				}else if(optionType==YES_NO_OPTION){
//					msgOption=new String[]{i18n.getString("PrgCommen.DefaultMsgOptionYes"), i18n.getString("PrgCommen.DefaultMsgOptionNo") };
//				}else if(optionType==OK_CANCEL_OPTION){    
//					msgOption=new String[]{i18n.getString("PrgCommen.DefaultMsgOptionOk"), i18n.getString("PrgCommen.DefaultMsgOptionNo") };
//				}else if(optionType==OK_OPTION){
//					msgOption=new String[]{i18n.getString("PrgCommen.DefaultMsgOptionOk")};					
//				}else{
//					System.out.println("option type " + optionType);
//				}
//
//				setOptions(msgOption);
//			}else{
//				setOptions(givenOption);
//			}
//		}else{
//			setOptions(givenOption);
//		}
		
	}
	
	private static void setupFont(){
//		System.out.println("set font 11");
//		if(!(i18n.getString("PrgCommen.DefaultMsgFontStyle").equals("null"))){
//			String fntStyle=i18n.getString("PrgCommen.DefaultMsgFontStyle");
//			int fntSize=i18n.getInt("PrgCommen.DefaultMsgFontSize");
//			int fntType=i18n.getInt("PrgCommen.DefaultMsgFontType");
//			System.out.println("set font 12");
//			Component fc[]=getComponents();
//      for (int i = 0; i<fc.length; i++) {
//      	System.out.println("set font 13");
//      	if (fc[i] instanceof JPanel){
//					Component fcb[]=((JPanel)fc[i]).getComponents();
//      		fc[i].setFont(new Font(fntStyle,fntType,fntSize));
//      		for (int j = 0; j<fcb.length; j++) {
//      			System.out.println("set font 14 " + fntStyle);
//      			
//      			fcb[j].setFont(new Font(fntStyle,fntType,fntSize));
//      		}
//			
//      	}
//      }
//		}
	}
	
//  JOptionPane pane = new JOptionPane("So it's a date?", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_CANCEL_OPTION, 
//  		UIManager.getIcon("OptionPane.questionIcon"),
//      new String[]{"Okey-dokey", "Not on your life!", "Let me think about it" }, null);


}
