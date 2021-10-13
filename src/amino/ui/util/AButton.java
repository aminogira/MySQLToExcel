
package amino.ui.util;

import java.awt.Font;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 *
 * @author Amila Giragama
 */
public class AButton extends JButton {

  public AButton() {
    super();
    setupFont();
  }
  public AButton(Action a) {
    super(a);
    setupFont();
  }
  public AButton(Icon icon) {
    super(icon);
    setupFont();
  }
  public AButton(String text) {
    super(text);
    setupFont();
  }
  public AButton(String text, Icon icon) {
    super(text,icon);
    setupFont();
  }


  public AButton(boolean isdbfont) {
    super();
		if (isdbfont)
			setupDBFont();
		else
			setupFont();
  }
  public AButton(Action a,boolean isdbfont) {
    super(a);
		if (isdbfont)
			setupDBFont();
		else
			setupFont();
  }
  public AButton(Icon icon,boolean isdbfont) {
    super(icon);
		if (isdbfont)
			setupDBFont();
		else
			setupFont();
  }
  public AButton(String text,boolean isdbfont) {
    super(text);
		if (isdbfont)
			setupDBFont();
		else
			setupFont();
  }
  public AButton(String text, Icon icon,boolean isdbfont) {
    super(text,icon);
		if (isdbfont)
			setupDBFont();
		else
			setupFont();
  }

  private void setupFont(){
		if(!(i18n.getString("PrgCommen.DefaultFontStyle").equals("null"))){
			String fntStyle=i18n.getString("PrgCommen.DefaultFontStyle");
			int fntSize=i18n.getInt("PrgCommen.DefaultFontSize");
			int fntType=i18n.getInt("PrgCommen.DefaultFontType");
			setFont(new Font(fntStyle,fntType,fntSize));
		}
	}

	private void setupDBFont(){
		if(!(i18n.getString("PrgCommen.DefaultDbFontStyle").equals("null"))){
			String fntStyle=i18n.getString("PrgCommen.DefaultDbFontStyle");
			int fntSize=i18n.getInt("PrgCommen.DefaultDbFontSize");
			int fntType=i18n.getInt("PrgCommen.DefaultDbFontType");
			setFont(new Font(fntStyle,fntType,fntSize));
		}
	}

}
