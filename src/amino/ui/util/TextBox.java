package amino.ui.util;
//
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.text.AttributeSet;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
/**
 * @author amila giragama
 *
 */
public class TextBox extends JTextField implements KeyListener, PropertyChangeListener{//, TableCellRenderer {

	JPopupMenu jpm;
	FoundItem fi[];
	FindTree ft;
	String showList,getList;//show in F1 list ,  show in text box
	String checkFor;
	
	FoundItem selectedItem;
	int selectedId;
	String selectedCode,selectedName;
	
	private static final long serialVersionUID = 1L;
	
	public TextBox(){
		super();
		addKeyListener(this);
	}
	public TextBox(int x){
		super(x);
		addKeyListener(this);
	}
	public TextBox(String x){
		super(x);
		addKeyListener(this);
	}
	public TextBox(String x,int y){
		super(x,y);
		addKeyListener(this);
	}
	public TextBox(Document d,String s,int i){
		super(d,s,i);
		addKeyListener(this);
	}
	public void setFoundItemList(FoundItem fi[],String showList,String getList){
		this.fi=fi;
		this.showList=showList; ///show in list
		this.getList=getList;   ///show in text box
	}
	
	public void setFoundItemList(FoundItem fi[]){
		this.fi=fi;
	}
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_F1){
			if ((fi!=null)&&(fi.length>0)){ 
				setText("");
				jpm=new JPopupMenu();
				ft=new FindTree(fi);
				ft.addPropertyChangeListener(this);
				jpm.add(ft);
				//jpm.show(this,this.getX(),this.getY());
				jpm.show(getParent(),0,0);
				jpm.requestFocus();
				jpm.requestFocusInWindow();
			}
		}
	}	
	
	public void setText(String t) {
		super.setText(t);
		if (fi==null) return;
		//System.out.println(t);
		if (t==null) return;
		if (t.trim().equals("")) return;
		for(int x=0;x<fi.length;x++){
			if ((fi[x].showingData.equals(FoundItem.showCode)) && (fi[x].getCode().equals(getText()))){
				setSelectedItem(fi[x]);
			}else if((fi[x].showingData.equals(FoundItem.showName)) && (fi[x].getName().equals(getText()))){
				setSelectedItem(fi[x]);
			}
		}
	}
	public void setSelectedID(int id){
		if (fi==null) return;
		for(int x=0;x<fi.length;x++){
			if (id==fi[x].getID()){
				setSelectedItem(fi[x]);
				if (fi[x].showingData.equals(FoundItem.showName)){
					super.setText(fi[x].name);
				}else if (fi[x].showingData.equals(FoundItem.showCode)){
					super.setText(fi[x].code);
				}
			}
		}
	}
	public void setInt(int i){
		String si=(new Integer(i)).toString();
		setText(si);
	}
	
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(FindTree.selectedItemProperty)){
			if(evt.getNewValue() instanceof String){
				setText((String) evt.getNewValue());
			}else if (evt.getNewValue() instanceof FoundItem) {
				selectedItem=ft.getSelectedItem();
				updateSelectedData();
			}else{
				//System.out.println("propertyChange@TextBox " +   evt.getNewValue());
			}
			jpm.setVisible(false);
		}
	}
	
	private void updateSelectedData(){
		if (!(selectedItem==null)){
			//System.out.println("selected item updated " + getName() );
			selectedCode=selectedItem.getCode();
			selectedId=selectedItem.getID();
			selectedName=selectedItem.getName();
		}
	}
	
	public void setSelectedItem(FoundItem selectedItem){
		this.selectedItem=selectedItem;
		updateSelectedData();
	}
	public int getSelectedId(){
		return selectedId;
	}
	public String getSelectedCode(){
		return selectedCode;
	}
	public String getSelectedName(){
		return selectedName;
	}
	public FoundItem getSelectedItem(){
		return selectedItem;
	}
	
/*	public void setCheckFor(String checkFor){
		//if ((checkfor.equals(CHECK_FOR_UPPERCASE))||(checkfor.equals(CHECK_FOR_INTIGER))||(checkfor.equals(CHECK_FOR_LOWERCASE))){
			this.checkFor=checkFor;
			System.out.print("@setCheckFor " +checkFor);
			//return true;
		//}else{
			//return false;
		//}
	}*/
	
	protected Document createDefaultModel() {
/*		System.out.println("@createDefaultModel " +  checkFor);
		if (checkFor!=null){
			if (checkFor.equals(CHECK_FOR_UPPERCASE))
				return new CheckForUpperCase();
			else if (checkFor.equals(CHECK_FOR_LOWERCASE))
				return new CheckForLowerCase();
			else if (checkFor.equals(CHECK_FOR_INTIGER))
				return new CheckForIntiger();
		}else{
			return new PlainDocument();
		}*/
		return new PlainDocument();
	}
	
	//static class CheckedDocument extends PlainDocument {
	static class CheckForUpperCase extends PlainDocument {
		
		private static final long serialVersionUID = 200905060958L;
		
		public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				if (str == null) {
					return;
				}
				char[] upper = str.toCharArray();
				for (int i = 0; i < upper.length; i++) {
					upper[i] = Character.toUpperCase(upper[i]);
				}
				super.insertString(offs, new String(upper),  a);
 		}		
	}

	static class CheckForLowerCase extends PlainDocument {
		
		private static final long serialVersionUID = 200905060958L;
		
		public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
			if (str == null) {
				return;
			}
			char[] upper = str.toCharArray();
			for (int i = 0; i < upper.length; i++) {
				upper[i] = Character.toUpperCase(upper[i]);
			}
			super.insertString(offs, new String(upper),  a);
 		}		
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		setBorder(null);
		return this;
	}
		
}

/*jpm=new JPopupMenu(){
	private static final long serialVersionUID = 1L;
	FoundItem fi;
	public void setFoundItem(FoundItem fi){
		this.fi=fi;
	}
	public FoundItem getFoundItem(){
		return fi;
	}
};
*/