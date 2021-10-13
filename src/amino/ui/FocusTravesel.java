package amino.ui;
//
import java.awt.FocusTraversalPolicy;
import java.awt.KeyboardFocusManager;
import java.awt.Component;
import java.awt.Container;
import java.util.Vector;
import java.util.HashSet;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.util.Set;

/**
 * @author amila giragama
 *
 */
public class FocusTravesel extends FocusTraversalPolicy {
	
	Vector<Component> order;
		
  public FocusTravesel(Vector<Component> order,Container baseOn){

	  this.order = new Vector<Component>(order.size());
	  this.order.addAll(order);
	      
	  Set forwardKeys= baseOn.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS);
		Set<KeyStroke> newForwardKeys = new HashSet<KeyStroke>(forwardKeys);
		newForwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
//		newForwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0));
//		newForwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0));
		baseOn.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,newForwardKeys);
	
	  Set backwordKeys= baseOn.getFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS);
		Set newBackwordKeys = new HashSet(backwordKeys);
		newBackwordKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0));
//		newBackwordKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
//		baseOn.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS,newBackwordKeys);
//	
    Set downwordKeys= baseOn.getFocusTraversalKeys(KeyboardFocusManager.DOWN_CYCLE_TRAVERSAL_KEYS);
		Set newDownwordKeys = new HashSet(downwordKeys);
		newDownwordKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0));
//		baseOn.setFocusTraversalKeys(KeyboardFocusManager.DOWN_CYCLE_TRAVERSAL_KEYS,newDownwordKeys);
//		
	  Set upwordKeys= baseOn.getFocusTraversalKeys(KeyboardFocusManager.UP_CYCLE_TRAVERSAL_KEYS);
		Set newUpwordKeys = new HashSet(upwordKeys);
		newUpwordKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0));
//		baseOn.setFocusTraversalKeys(KeyboardFocusManager.UP_CYCLE_TRAVERSAL_KEYS,newUpwordKeys );
		
		baseOn.setFocusTraversalPolicy(this);
  }
		
	public Component getComponentAfter(Container aContainer, Component aComponent) {
		return order.get((order.indexOf(aComponent) + 1) % order.size());
	}

	public Component getComponentBefore(Container aContainer, Component aComponent) {
        int idx = order.indexOf(aComponent) - 1;
        if (idx < 0) idx = order.size() - 1;
        return order.get(idx);
	}

	public Component getFirstComponent(Container aContainer) {
		return order.firstElement();
	}

	public Component getLastComponent(Container aContainer) {
		return order.lastElement();
	}

	public Component getDefaultComponent(Container aContainer) {
		return order.get(0);
	}	
}
