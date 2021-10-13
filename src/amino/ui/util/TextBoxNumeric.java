package amino.ui.util;
//
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import sw.db.ctrlData;

//import sw.ui.util.TextBoxIntiger.CheckForIntiger;
/**
 * @author amila giragama
 *
 */
public class TextBoxNumeric extends TextBox {

	private static final long serialVersionUID = 200905070919L;

	public TextBoxNumeric(){
		super();
		addKeyListener(this);
		setHorizontalAlignment(TextBoxNumeric.RIGHT);
	}
	public TextBoxNumeric(int x){
		super(x);
		addKeyListener(this);
		setHorizontalAlignment(TextBoxNumeric.RIGHT);
	}
	public TextBoxNumeric(String x){
		super(x);
		addKeyListener(this);
		setHorizontalAlignment(TextBoxNumeric.RIGHT);
	}
	public TextBoxNumeric(String x,int y){
		super(x,y);
		addKeyListener(this);
		setHorizontalAlignment(TextBoxNumeric.RIGHT);
	}
	public TextBoxNumeric(Document d,String s,int i){
		super(d,s,i);
		addKeyListener(this);
		setHorizontalAlignment(TextBoxNumeric.RIGHT);
	}
	
	public double getdouble(){
		double d=0;
		try{
			d=new Double(getText());
			return d;
		}catch(Exception e){
			return 0;
		}
	}
	public Double getDouble(){
		try{
			return new Double(getText());
		}catch(Exception e){
			return null;
		}
	}
	public void setDouble(double d){
		setText(ctrlData.numformat.format(d));
	}
	


/*      WE CAN USE THIS COMMENTED PART OR FALLOWING createDefaultModel() event
 * 
 * public TextBoxNumeric(int columns) {
  super(columns);
  setDocument(new NumberFilterDocument());
}  
*/
	protected Document createDefaultModel() {
		return new CheckFroNumeric();
	}
	
  public class CheckFroNumeric extends PlainDocument {

  	private static final long serialVersionUID = 200905070922L;
		private StringBuffer __scratchBuffer;

		

		
		
    public CheckFroNumeric() {
      __scratchBuffer = new StringBuffer();
    }

    public void insertString(int offset, String text, AttributeSet aset) throws BadLocationException {
      if(text == null)
        return;

      __scratchBuffer.setLength(0);

      // Reject all strings that cause the contents of the field not
      // to be a valid number (i.e., string representation of a double)
      try {
        __scratchBuffer.append(getText(0, getLength()));
        __scratchBuffer.insert(offset, text);
        // Kludge: Append a 0 so that leading decimal points
        // and signs will be accepted
        __scratchBuffer.append('0');
      } catch(BadLocationException ble) {
        ble.printStackTrace();
        return;
      } catch(StringIndexOutOfBoundsException sioobe) {
        sioobe.printStackTrace();
        return;
      }

      try {
        Double.parseDouble(__scratchBuffer.toString());
      } catch(NumberFormatException nfe) {
        // Resulting string will not be number, so reject it
        return;
      }

      super .insertString(offset, text, aset);
    }
  }


}
