package amino.ui.util;

import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JLabel;

public class ALabel extends JLabel {

	
	private static final long serialVersionUID = 201004051000L;

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
	
	@ Deprecated
	public ALabel() {
		setupFont();
	}
	
	public ALabel(boolean isdbfont) {
		if (isdbfont)
			setupDBFont();
		else
			setupFont();
	}
	@ Deprecated
	public ALabel(String arg0) {
		super(arg0);
		setupFont();
	}
	public ALabel(String arg0,boolean isdbfont) {
		super(arg0);
		if (isdbfont)
			setupDBFont();
		else
			setupFont();
	}
	@ Deprecated
	public ALabel(Icon arg0) {
		super(arg0);
		setupFont();
	}
	public ALabel(Icon arg0,boolean isdbfont) {
		super(arg0);
		if (isdbfont)
			setupDBFont();
		else
			setupFont();
	}
	@ Deprecated
	public ALabel(String arg0, int arg1) {
		super(arg0, arg1);
		setupFont();
	}
	public ALabel(String arg0, int arg1,boolean isdbfont) {
		super(arg0, arg1);
		if (isdbfont)
			setupDBFont();
		else
			setupFont();
	}
	@ Deprecated
	public ALabel(Icon arg0, int arg1) {
		super(arg0, arg1);
		setupFont();
	}
	public ALabel(Icon arg0, int arg1,boolean isdbfont) {
		super(arg0, arg1);
		if (isdbfont)
			setupDBFont();
		else
			setupFont();
	}
	@ Deprecated
	public ALabel(String arg0, Icon arg1, int arg2) {
		super(arg0, arg1, arg2);
		setupFont();
	}
	public ALabel(String arg0, Icon arg1, int arg2,boolean isdbfont) {
		super(arg0, arg1, arg2);
		if (isdbfont)
			setupDBFont();
		else
			setupFont();
	}
	

}
