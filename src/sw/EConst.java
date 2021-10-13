package sw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EConst {

	public static boolean foundUser;
	public static final String file="mailreg";
	public static String smtpserv="smtp";
    public static String usernm="samurdhi";
    public static String passwrd="abcd1234";
	public static String efrom="ratnapura@mail.com";
	public static String eto="sethsiripaya@mail.com";
	public static String esubject="reports";
	public static String ebody="hi";
	public static String eattachpath="C:/attach";
//	public static String slipOutPath="";
//	public static String tmpOutputPath="";
//	public static String reportPath="";
//  public static boolean dateeditable=true;
//
	public static void getOpenID() {
        String line[]=readFile(file,12);
			smtpserv=line[0];
            usernm=line[1];
            passwrd=line[2];
            efrom=line[3];
			eto=line[4];
            esubject=line[5];
			ebody=line[6];
            eattachpath=line[7];
	}

	public static String[] readFile(String filename,int length){
		File file = new File(filename +".mail");
		//StringBuffer contents = new StringBuffer();
		BufferedReader reader = null;
		String[] line=new String[length];

		try{
			reader = new BufferedReader(new FileReader(file));
//   ################  DO NOT REMOVE STUDY THIS 3 LINES

		  String text="";
		  int x=0;
		  while ((text = reader.readLine()) != null){
		  	//contents.append(text).append(System.getProperty("line.separator"));
		  	line[x]=text;
		  	x++;
		  }

		}catch (FileNotFoundException e){
		  	e.printStackTrace();
		}catch (IOException e){
		    e.printStackTrace();
		}finally{
		  try{
		    if (reader != null){
		    	reader.close();
		    }
		  }catch (IOException e){
		    e.printStackTrace();
		  }
	  }
		return line;
	}
}
