//import darrylbu.util.SwingUtils;
import java.awt.Component;
import java.awt.Font;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;


 
public class OptionPaneFonts {
 
//   public static void main(String[] args) {
//  	 System.out.println("start");
//      UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(
//              "Verdana", Font.BOLD, 32)));
//      SwingUtilities.invokeLater(new Runnable() {
//
//         @Override
//         public void run() {
//            new OptionPaneFonts().makeUI();
//         }
//      });
//   }
// 
   public void makeUI() {
//      JOptionPane pane = new JOptionPane("So it's a date?",
//              JOptionPane.QUESTION_MESSAGE,
//              JOptionPane.YES_NO_CANCEL_OPTION,
//              UIManager.getIcon("OptionPane.questionIcon"),
//              new String[]{"Okey-dokey", "Not on your life!",
//                 "Let me think about it"
//              }, null);
//      for (JButton button : SwingUtils.getDescendantsOfType(JButton.class, pane)) {
//         button.setFont(new Font("Tahoma", Font.ITALIC, 18));
//      }
      
//  		if(!(i18n.getString("PrgCommen.DefaultMsgFontStyle").equals("null"))){
//  			String fntStyle=i18n.getString("PrgCommen.DefaultMsgFontStyle");
//  			int fntSize=i18n.getInt("PrgCommen.DefaultMsgFontSize");
//  			int fntType=i18n.getInt("PrgCommen.DefaultMsgFontType");     
//  			Component fc[]=pane.getComponents();
//        for (int i = 0; i<fc.length; i++) {
//        	if (fc[i] instanceof JPanel){
//  					Component fcb[]=((JPanel)fc[i]).getComponents();
//        		fc[i].setFont(new Font("chamara",0,25));
//        		for (int j = 0; j<fcb.length; j++) {
//        			fcb[j].setFont(new Font("chamara",0,25));
//        		}
//
//        	}
//        }
//  		}
      
      
      JDialog dialog = new JDialog((JWindow) null);
      dialog.setModal(true);
    //  dialog.add(pane);
      dialog.pack();
 
      dialog.setLocationRelativeTo(null);
      dialog.setVisible(true);
   }
   
   
   
   
// 	private void setupFont(){
//		if(!(i18n.getString("PrgCommen.DefaultMsgFontStyle").equals("null"))){
//			String fntStyle=i18n.getString("PrgCommen.DefaultMsgFontStyle");
//			int fntSize=i18n.getInt("PrgCommen.DefaultMsgFontSize");
//			int fntType=i18n.getInt("PrgCommen.DefaultMsgFontType");
//			
//			Component fc[]=getComponents();
//      for (int i = 0; i<fc.length; i++) {
//      	if (fc[i] instanceof JPanel){
//					Component fcb[]=((JPanel)fc[i]).getComponents();
//      		fc[i].setFont(new Font(fntStyle,fntType,fntSize));
//      		for (int j = 0; j<fcb.length; j++) {
//      			fcb[j].setFont(new Font(fntStyle,fntType,fntSize));
//      		}
//			
//      	}
//      }
//		}
//	}
   
}