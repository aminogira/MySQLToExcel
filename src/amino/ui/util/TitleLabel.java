package amino.ui.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class TitleLabel extends ALabel {
	private static final long serialVersionUID = 20100401L;

	public TitleLabel(String title){
		super(title);
		setForeground(Color.BLUE);
		//setFont(new Font(null,Font.BOLD,15));
	}
	
	public void paint(Graphics g){
		super.paint(g);
		g.drawLine(0, this.getHeight()-1, this.getWidth(), this.getHeight()-1);
		g.drawLine(0, this.getHeight()-2, this.getWidth(), this.getHeight()-2);
	}
	
	
}
