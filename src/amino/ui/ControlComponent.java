package amino.ui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
////
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.TableColumn;

import amino.ui.util.i18n;
/**
 * @author amila giragama
 *
 */
public class ControlComponent {
	
	public static void clearAllJTextField(Container c){
		Component fc[]=c.getComponents();
		for (int i = 0; i<fc.length; i++) {
			if (fc[i] instanceof JTextField){
				((JTextField)fc[i]).setText("");
			}else if (fc[i] instanceof Container)  {
				clearAllJTextField((Container)fc[i]);
			}
		}
	}	
  public static void setEnabledAll(Container c,boolean isEnabled){
    Component fc[]=c.getComponents();
    for (int i = 0; i<fc.length; i++) {
      fc[i].setEnabled(isEnabled);
    }
  }
	public static void setTableColumnWidth(JTable jt,int colWidth[]){
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumn col=null;
		for(int x=0;x<colWidth.length;x++){
			col = jt.getColumnModel().getColumn(x);
			if (colWidth[x]==0){
				col.setMaxWidth(0);
				//col.setMinWidth(0);
				col.setPreferredWidth(0);
				//col.setResizable(false);
			}else{
				col.setPreferredWidth(colWidth[x]);
			}
		}
	}
	public static void setMessageFont(){
		if(!(i18n.getString("PrgCommen.DefaultMsgFontStyle").equals("null"))){
			String fntStyle=i18n.getString("PrgCommen.DefaultMsgFontStyle");
			int fntSize=i18n.getInt("PrgCommen.DefaultMsgFontSize");
			int fntType=i18n.getInt("PrgCommen.DefaultMsgFontType");
			UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(fntStyle,fntType,fntSize)));
		}
	}
}
