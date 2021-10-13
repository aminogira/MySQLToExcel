package amino.ui.util;

//
import java.awt.BorderLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
/**
 * @author amila giragama
 *
 */
public class FindTree extends JPanel implements KeyListener,TreeSelectionListener,FocusListener,ComponentListener {

	private static final long serialVersionUID = 200904271714L;
	
	JTree tree;
	FoundItem[] fi;
	FoundItem selectedItem;
	TextBox tbFilter;
	public static String selectedItemProperty="s e l e c t e d   i t e m";
	
	public FindTree(FoundItem[] fi){
		super(new BorderLayout());
		tree=new JTree(fi);
		tree.addKeyListener(this);
		this.fi=fi;
		addKeyListener(this);
		addFocusListener(this);
		JScrollPane jsp=new JScrollPane(tree);
		//setLayout(arg0)
		add(jsp,BorderLayout.CENTER);
		tbFilter=new TextBox(15);
		tbFilter.addKeyListener(this);
		add(tbFilter,BorderLayout.SOUTH);
		//tree.setFocusCycleRoot(true);
		//requestFocus();
		//tree.requestFocus();
		//requestFocusInWindow();
		//tree.requestFocusInWindow();
		
		addComponentListener(this);
		
		setVisible(true);
	
	}
	public void valueChanged(TreeSelectionEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		if(e.getSource().equals(tree)){
			if	(e.getKeyCode()==KeyEvent.VK_ESCAPE)
				transferFocusUpCycle();
			else if (e.getKeyCode()==KeyEvent.VK_ENTER){
				//selectedItem=(FoundItem) tree.getLastSelectedPathComponent();
				//Object obj=(tree.getLastSelectedPathComponent());
				//selectedItem=(FoundItem) obj; 
				
	//			DefaultMutableTreeNode dmtn=(DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
	//			FoundItem tmpFi=(FoundItem) dmtn;
	//			System.out.print(tmpFi.getCode());
	//			firePropertyChange(selectedItemProperty, null, tree.getLastSelectedPathComponent());	
				
				firePropertyChange(selectedItemProperty, null, findSelectedItem(tree.getLastSelectedPathComponent().toString()));
				firePropertyChange(selectedItemProperty, null, tree.getLastSelectedPathComponent().toString());
				
				
				//Object obj=tree.getLastSelectedPathComponent();
	
				//firePropertyChange(selectedItemProperty, null, tree.getLastSelectedPathComponent());
				
				//firePropertyChange(selectedItemProperty, null, tmpFi);
				
			}else{

			}
		}else if(e.getSource().equals(tbFilter)){
			
		}
	}
	
	private FoundItem findSelectedItem(String find){
		FoundItem tmpfi=null;
		if (fi==null) return tmpfi;
		for(int x=0; x<fi.length; x++){
			if(fi[x].toString().equals(find)){
				tmpfi=fi[x];
			}
		}
		selectedItem=tmpfi;
		return tmpfi;
	}
	
	public FoundItem getSelectedItem(){
		return selectedItem;
	}
	

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
	public void focusGained(FocusEvent e) {
		tree.requestFocus();
		tree.requestFocusInWindow();
	}
	public void focusLost(FocusEvent e) {}
	public void componentHidden(ComponentEvent e) {}
	public void componentMoved(ComponentEvent e) {
		tree.requestFocus();
	}
	public void componentResized(ComponentEvent e) {}
	public void componentShown(ComponentEvent e) {}
}	
/*	
	class abc implements TreeModel{

		public void addTreeModelListener(TreeModelListener l) {
			// TODO Auto-generated method stub
			
		}

		public Object getChild(Object parent, int index) {
			// TODO Auto-generated method stub
			return null;
		}

		public int getChildCount(Object parent) {
			// TODO Auto-generated method stub
			return 0;
		}

		public int getIndexOfChild(Object parent, Object child) {
			// TODO Auto-generated method stub
			return 0;
		}

		public Object getRoot() {
			// TODO Auto-generated method stub
			return null;
		}

		public boolean isLeaf(Object node) {
			// TODO Auto-generated method stub
			return false;
		}

		public void removeTreeModelListener(TreeModelListener l) {
			// TODO Auto-generated method stub
			
		}

		public void valueForPathChanged(TreePath path, Object newValue) {
			// TODO Auto-generated method stub
			
		}
		
	}
	class tn implements TreeNode{

		public Enumeration children() {
			// TODO Auto-generated method stub
			return null;
		}

		public boolean getAllowsChildren() {
			// TODO Auto-generated method stub
			return false;
		}

		public TreeNode getChildAt(int childIndex) {
			// TODO Auto-generated method stub
			return null;
		}

		public int getChildCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		public int getIndex(TreeNode node) {
			// TODO Auto-generated method stub
			return 0;
		}

		public TreeNode getParent() {
			// TODO Auto-generated method stub
			return null;
		}

		public boolean isLeaf() {
			// TODO Auto-generated method stub
			return false;
		}
		
	}
*/	


