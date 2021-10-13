package amino.ui;
//
import javax.swing.JPanel;
import javax.swing.JComponent;

import sw.CommonCons;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
/**
 * @author amila giragama
 *
 */
public class PanelFlow extends JPanel {
	
	private static final long serialVersionUID = 200904280147L;
	
	public static final int LEFT = FlowLayout.LEFT;
	public static final int RIGHT = FlowLayout.RIGHT;
	public static final int CENTER = FlowLayout.CENTER;
	public static final int LEADING = FlowLayout.LEADING;
	public static final int TRAILING  = FlowLayout.TRAILING;
	
	private boolean drawBackgroundPic;
	
	public PanelFlow() {
		setLayout(new FlowLayout());
		drawBackgroundPic=false;
	}
	public PanelFlow(int align) {
		setLayout(new FlowLayout(align));
		drawBackgroundPic=false;
	}
	
	public PanelFlow(int align,String imageFileName) {
		setLayout(new FlowLayout(align));
		CommonCons.backgroundFile=imageFileName;
		drawBackgroundPic=true;
	}
	
	public PanelFlow(int align, int hgap, int vgap) {
		setLayout(new FlowLayout(align,hgap,vgap));
		drawBackgroundPic=false;
	}
	public void add(JComponent jc){
		super.add(jc);
	}

	public void paint(Graphics g){
		super.paint(g);
		if (drawBackgroundPic) {
			Image img=Toolkit.getDefaultToolkit().getImage(CommonCons.PROPATH + "/pic/" +  CommonCons.backgroundFile);
			g.drawImage(img, 0, 0, getWidth() , getHeight(), this);
			
			Component fc[]=getComponents();
			for (int x=0;x<fc.length;x++)
				fc[x].repaint();

		}
	}
}
