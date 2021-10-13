package sw;

//import sw.ui.optProgramSetting;
import java.io.FileNotFoundException;

import amino.data.ReadWrite;
import sw.ui.ProgramSetting;

public class proConst {	


	public static boolean dateeditable=false;

	//public static final String fln=RegQuery.getRegfilePath() + "regTBSMCCN";
    public static final String fln="regTBSMCCN";
	
	public static String serverIp="localhost";
	public static String dbName="gltmp";
	public static String user="root";
	public static String passwd="";
	public static String serverPort="";
//	public static String backupPath="";
	public static String reportPath="";
  public static String withholdrate="";
	public static String l10n="";
//	public static String reportPathAcc="";
//	public static String reportPathLoan="";
	public static String tmpOutputPath="";
//	public static String isUserLogging="";
  public static String slipPath="";
  public static String slipOutPath="";
//	
	public static int branchID=1;
	public static String tmpTbllist[]={"tloansheduletmp","tmpprinttrans","tmpprintreport" };
	
	public static void getOpenID(){
		String line[]=null;
        System.out.println(fln);

		try{
			line=ReadWrite.readFile(fln,13);
		}catch(FileNotFoundException fnfe){
			new ProgramSetting();
		}
		serverIp=line[0];
		dbName=line[1];
		user=line[2];
		passwd=line[3];
		reportPath=line[4];
		serverPort=line[5];
		l10n=line[6];
		tmpOutputPath=line[7];
		withholdrate=line[8];
    
//		reportPathLoan=line[9];
//		isUserLogging=line[10];
//    slipPath=line[11];
//    slipOutPath=line[12];
	}
	
	
	
}






