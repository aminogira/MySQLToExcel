package amino.ui.util;
//
import java.awt.*;
import java.util.*;
import java.awt.geom.*;
import java.applet.*;

import javax.swing.JPanel;

/*
<applet code="AminoClock" height = 180 width = 180>
</applet>
*/


public class AminoClock extends JPanel implements Runnable {

final static int centreX = 90;
final static int centreY = 90;
final static int radius = 70;

Thread t = null;
Hand sec,min,hour;
boolean shouldRun = true;
Calendar time;
String strTime;
String date;

	public AminoClock(){
		init();
	}

 public void init() {
  setBackground(Color.black);
  setForeground(Color.red);
  time = Calendar.getInstance();
 }

 public void start() {
  shouldRun = true;
  t = new Thread(this);
  sec = new Hand(time.get(Calendar.SECOND),radius-15,15,Color.red,0);    //  value,length,neglen,Color,thick,
  min = new Hand(time.get(Calendar.MINUTE),radius*5/7,Color.green,9);
  hour = new Hand(time.get(Calendar.HOUR_OF_DAY),radius/2,Color.blue,14);
  date = new String(time.get(Calendar.DAY_OF_MONTH)+"");
  t.start();
 }

 public void stop() {
 t = null;
 shouldRun = false;
 strTime+="stopped";
 }

 public void run() {
  repaint();
  while(shouldRun) {
   repaint(centreX-radius-5,centreY-radius-5,centreX+radius+5,centreY+radius+5);
   try{
	   Thread.sleep(1000);
   }catch(InterruptedException e){}
   sec.val++;
   if(sec.val == 60) {
	  sec.val = 0;
	  min.val++;
   }
   if(min.val == 60) {
	  min.val = 0;
	  hour.val++;
   }
   if(hour.val == 13)
      hour.val=1;
   strTime = (int)hour.val+":"+(int)min.val+":"+(int)sec.val;
  }
}

private void setAllAngles() {
 sec.angle = (sec.val)*Math.PI/30;
 min.angle = (min.val)*Math.PI/30 + (sec.val)*Math.PI/1800;
 hour.angle =(hour.val)*Math.PI/6 + (min.val)*Math.PI/360;
}

public void paint(Graphics g) {
update(g);
}

public void update(Graphics g) {
Graphics2D g2 = (Graphics2D)g;

g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
g.setColor(Color.black);
g.fillOval(centreX-radius,centreX-radius,2*radius,2*radius);

//----->>border
g.setColor(Color.lightGray);
g.drawOval(centreX-radius,centreX-radius,2*radius,2*radius);
g.drawOval(centreX-radius-1,centreX-radius-1,2*radius+2,2*radius+2);

g.setColor(Color.gray);
g.drawOval(centreX-radius-2,centreX-radius-2,2*radius+4,2*radius+4);
g.drawOval(centreX-radius-3,centreX-radius-3,2*radius+6,2*radius+6);
//<<------------


g.setColor(Color.white);
g.fillRect(centreX+radius/2,centreY-7,20,12);     //116,73
g.setColor(Color.black);
g.drawString(date,centreX+radius/2+5,centreY+4);
for(int i=0;i<60;i++) {
  if(i%5==0) {
    g.setColor(Color.gray);
    g.drawLine(centreX+(int)((radius*6/7)*Math.sin(Math.toRadians(i*6))),centreY-(int)((radius*6/7)*Math.cos(Math.toRadians(i*6))),centreX+(int)(radius*Math.sin(Math.toRadians(i*6))),centreY-(int)(radius*Math.cos(Math.toRadians(i*6))));
  }
  else {
    g.setColor(Color.white);
    g.drawLine(centreX+(int)((radius*6.5/7)*Math.sin(Math.toRadians(i*6))),centreY-(int)((radius*6.5/7)*Math.cos(Math.toRadians(i*6))),centreX+(int)(radius*Math.sin(Math.toRadians(i*6))),centreY-(int)(radius*Math.cos(Math.toRadians(i*6))));
  }
}
 //showStatus(strTime);
 setAllAngles();

 hour.display(g);
 min.display(g);
 sec.display(g);

 g.setColor(Color.red);
 g.fillOval(centreX-3,centreY-3,6,6);
 g.setColor(Color.yellow);
 g.fillOval(centreX-1,centreY-1,2,2);
}
};




class Hand{
	 private int len;
	 private int negLen;
	 private Color handColor;
	 private Shape hand;
	 private double rectifier;
	 double angle;
	 double val;
	 AffineTransform af;

Hand(double value,Color col,int rec) {
	  len = 100;
	  val = value;
	  negLen = 0;
	  handColor = col;
	  rectifier = Math.PI*rec/180;
	  initialize();
}

Hand(double value, int len,Color col,int rec) {
 this.len = len;
 this.val = value;
 negLen = 0;
 handColor = col;
 rectifier = Math.PI*rec/180;
 initialize();
}

Hand(double value, int len,int neg,Color col,int rec) {
 this.len = len;
 this.val = value;
 negLen = neg;
 handColor = col;
 rectifier = Math.PI*rec/180;
 initialize();
}

void initialize() {
 int x[] = new int[4];
 int y[] = new int[4];

 x[0] = AminoClock.centreX - negLen;
 y[0] = AminoClock.centreY;

 x[1] = AminoClock.centreX + (int)((len/2)*Math.cos(rectifier));
 y[1] = AminoClock.centreY - (int)((len/2)*Math.sin(rectifier));

 x[2] = AminoClock.centreX + len;
 y[2] = AminoClock.centreY;

 x[3] = AminoClock.centreX + (int)((len/2)*Math.cos(rectifier));
 y[3] = AminoClock.centreY + (int)((len/2)*Math.sin(rectifier));

 hand = new Polygon(x,y,4);
}

void display(Graphics g) {
Graphics2D g1 = (Graphics2D)g;
g.setColor(handColor);
af = AffineTransform.getRotateInstance(angle-Math.PI/2,AminoClock.centreX,AminoClock.centreY);
g1.fill(af.createTransformedShape(hand));
g1.draw(af.createTransformedShape(hand));
}
};
