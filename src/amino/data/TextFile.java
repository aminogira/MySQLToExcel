package amino.data;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.JOptionPane;

import amino.ui.util.i18n;

public class TextFile {
	
 public static void fileAppend(String filename,String[] data){
  BufferedWriter out;
  try {
   out = new BufferedWriter(new FileWriter(filename, true));
   for (int x=0;x<data.length;x++)
    out.write(data[x] );
   out.close();
  } catch (IOException e) {
   e.printStackTrace();
  }
 }

// public static void fileAppend(String filename,String data){
//	  BufferedWriter out;
//	  try {
//	   out = new BufferedWriter(new FileWriter(filename, true));
//	   out.write(data);
//	   out.close();
//	  } catch (IOException e) {
//	   e.printStackTrace();
//	  }
//	 }

	 
// public static void fileWrite(String filenama,String[] data){
//  FileOutputStream fout;  
//  try{
//  fout = new FileOutputStream (filenama);
//  if (data!=null)
//   for (int x=0;x<data.length;x++)
//    new PrintStream(fout).println (data[x]);
//  fout.close();  
//  }catch (IOException e){
//   System.err.println ("Unable to write to file");
//  }
//  }
 public static boolean fileAppend(String filename,String data){
	 boolean ans=true;
	  BufferedWriter out;
	  try {
	   out = new BufferedWriter(new FileWriter(filename, true));
	   out.write(data);
	   out.close();
	  } catch (IOException e) {
	  	JOptionPane.showMessageDialog(null, i18n.getString("msg.chkDeskSpace"));
	  	ans=false;
	  }
	  return ans;
	 }
	
	public static boolean fileWrite(String filenama,String[] data){
 		boolean ans=true;
	  FileOutputStream fout=null;  
	  //try{
	  try {
			fout = new FileOutputStream (filenama);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, i18n.getString("msg.bkupFileNotFound"));
			ans=false;
		}
	  if (data!=null)
	  	for (int x=0;x<data.length;x++)
	  		new PrintStream(fout).println (data[x]);
	  try {
			fout.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,i18n.getString("msg.bkupFileWriting") );
			ans=false;
		}
		return ans; 
  }
}
