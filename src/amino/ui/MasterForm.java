package amino.ui;

//import amino.ui.InternalForm;

//
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import amino.ui.util.i18n;

import sw.CommonCons;
import sw.LogConst;
import sw.proConst;
/**
 * @author amila giragama
 *
 */
public abstract class MasterForm extends InternalForm implements KeyListener {
	
	public  MasterForm(String title, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable) {
		super(title,resizable,closable,maximizable,iconifiable);
		setToolBar(this,CommonCons.SAVEBTN+CommonCons.CANCELBTN+CommonCons.TRASHBTN+CommonCons.BACKBTN);
	}
	public void actionPerformed(ActionEvent ae){
//		if (proConst.isUserLogging.equals("TRUE")){
//			if (ae.getActionCommand().equals(CommonCons.SAVE)){
//				if (editMode){
//					if (LogConst.currentUser.edit(title))
//						saveData();
//					else
//						JOptionPane.showMessageDialog(this, "You are not authorized");
//				}else{
//					if (LogConst.currentUser.add(title))
//						saveData();
//					else
//						JOptionPane.showMessageDialog(this, "You are not authorized");
//				}
//			}else if(ae.getActionCommand().equals(CommonCons.CANCEL)){
//				clearForm();
//			}else if(ae.getActionCommand().equals(CommonCons.TRASH)){
//				if (LogConst.currentUser.delete(title))
//					trashData();
//				else
//					JOptionPane.showMessageDialog(this, "You are not authorized");
//			}else if(ae.getActionCommand().equals(CommonCons.BACK)){
//				dispose();
//			}else{
//				super.actionPerformed(ae);
//			}
//		}else{
			if (ae.getActionCommand().equals(i18n.getString("BasicToolBar.SAVE") )){
				saveData();
			}else if(ae.getActionCommand().equals(i18n.getString("BasicToolBar.CANCEL") )){
				clearForm();
			}else if(ae.getActionCommand().equals(i18n.getString("BasicToolBar.TRASH") )){
				trashData();
			}else if(ae.getActionCommand().equals(i18n.getString("BasicToolBar.BACK"))){
				dispose();
			}
//		}
	}
	public void clearForm(){
		ControlComponent.clearAllJTextField(this);
		//newId=0;
		currentID=0;
		editMode=false;
	}
	
	abstract public void saveData();
	abstract public void trashData();
	
	public void keyReleased(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}
