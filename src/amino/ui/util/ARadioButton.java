/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package amino.ui.util;

import java.awt.Font;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JRadioButton;


public class ARadioButton extends JRadioButton {
  @ Deprecated
  public ARadioButton() {
    setupFont();
  }
  @ Deprecated
  public ARadioButton(Action a) {
    super(a);
    setupFont();
  }
  @ Deprecated
  public ARadioButton(Icon icon) {
    super(icon);
    setupFont();
  }
  @ Deprecated
  public ARadioButton(Icon icon, boolean selected) {
    super(icon,selected);
    setupFont();
  }
  @ Deprecated
  public ARadioButton(String text) {
    super(text);
    setupFont();
  }
  @ Deprecated
  public ARadioButton(String text, boolean selected) {
    super(text,selected);
    setupFont();
  }
  @ Deprecated
  public ARadioButton(String text, Icon icon) {
    super(text,icon);
    setupFont();
  }
  @ Deprecated
  public ARadioButton(String text, Icon icon, boolean selected) {
    super(text,icon,selected);
    setupFont();
  }
  public ARadioButton(boolean isdbfont) {
    if (isdbfont)
			setupDBFont();
		else
			setupFont();
  }
  public ARadioButton(boolean isdbfont,Action a) {
    super(a);
    if (isdbfont)
			setupDBFont();
		else
			setupFont();
  }
  public ARadioButton(boolean isdbfont,Icon icon) {
    super(icon);
    if (isdbfont)
			setupDBFont();
		else
			setupFont();
  }
  public ARadioButton(boolean isdbfont,Icon icon, boolean selected) {
    super(icon,selected);
    if (isdbfont)
			setupDBFont();
		else
			setupFont();
  }
  public ARadioButton(boolean isdbfont,String text) {
    super(text);
    if (isdbfont)
			setupDBFont();
		else
			setupFont();
  }
  public ARadioButton(boolean isdbfont,String text, boolean selected) {
    super(text,selected);
    if (isdbfont)
			setupDBFont();
		else
			setupFont();
  }
  public ARadioButton(boolean isdbfont,String text, Icon icon) {
    super(text,icon);
    if (isdbfont)
			setupDBFont();
		else
			setupFont();
  }
  public ARadioButton(boolean isdbfont,String text, Icon icon, boolean selected) {
    super(text,icon,selected);
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
