package amino.ui.util;
//
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
/**
 * @author amila giragama
 *
 */
public class TextBoxIntiger extends TextBox {

	private static final long serialVersionUID = 200905061104L;

	public TextBoxIntiger(){
		super();
		addKeyListener(this);
		setHorizontalAlignment(TextBoxNumeric.RIGHT);
	}
	public TextBoxIntiger(int x){
		super(x);
		addKeyListener(this);
		setHorizontalAlignment(TextBoxNumeric.RIGHT);
	}
	public TextBoxIntiger(String x){
		super(x);
		addKeyListener(this);
		setHorizontalAlignment(TextBoxNumeric.RIGHT);
	}
	public TextBoxIntiger(String x,int y){
		super(x,y);
		addKeyListener(this);
		setHorizontalAlignment(TextBoxNumeric.RIGHT);
	}
	public TextBoxIntiger(Document d,String s,int i){
		super(d,s,i);
		addKeyListener(this);
		setHorizontalAlignment(TextBoxNumeric.RIGHT);
	}
	
	protected Document createDefaultModel() {
		return new CheckForIntiger();
	}
	
	public int getInt(){
		int i=0;
		try{
			i=new Integer(getText());
			return i;
		}catch(Exception e){
			return 0;
		}
	}
	public Integer getInteger(){
		return new Integer(getText());
	}
	
	static class CheckForIntiger extends PlainDocument {
		private static final long serialVersionUID = 200905061034L;

		public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
	    if (str == null)
	      return;
	    String oldString = getText(0, getLength());
	    String newString = oldString.substring(0, offs) + str + oldString.substring(offs);
	    try {
	      Integer.parseInt(newString + "0");
	      super.insertString(offs, str, a);
	    } catch (NumberFormatException e) {
	    }
	  }
	}
	Double dd;
}
