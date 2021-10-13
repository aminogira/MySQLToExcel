package sw;


public class CommonCons {
//	public static final String WINDOWSLOOKANDFEEL="Theam 01";//"Windows Look & Feel";
//	public static final String MOTIFLOOKANDFEEL="Theam 02";//"Motif Look & Feel";
//	public static final String METTLELOOKANDFEEL="Theam 03";//"Mettle Look & Feel";
//	public static final String SYNTHLOOKANDFEEL="Theam 04";//"Synth Look & Feel";
//	public static final String SYNTHETICALOOKANDFEEL="Theam 05";//"Synthetica Look & Feel";
//	public static final String GTKLOOKANDFEEL="Theam 06";//"Synthetica Look & Feel";
	
	public static final String WINDOWS_LF="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public static final String MOTIF_LF="com.sun.java.swing.plaf.motif.MotifLookAndFeel";
	public static final String METAL_LF="javax.swing.plaf.metal.MetalLookAndFeel";
	public static final String SYNTH_LF="javax.swing.plaf.synth.SynthLookAndFeel";
	public static final String GTK_LF="com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
	
	
//	public static final String MNU_CMD_OPTION="Option";
//	public static final String MNU_CMD_CUSTOMIZED="Customized";
//	public static final String MNU_CMD_SETTING="Setting";
	
	public static final int SAVEBTN=128,PRINTBTN=256,CANCELBTN=512,TRASHBTN=1024,BACKBTN=2048,SHOWDOCBTN=4096;
//	public static final String SAVE="Save <F8>",PRINT="Print <F4>",CANCEL="Refresh <F5>",TRASH="Trush <F12>",BACK="Back <Esc>",SHOWDOC="Show <F9>";
//	public static final String SAVE="Ok",PRINT="Print",CANCEL="Refresh",TRASH="Trush",BACK="Back",SHOWDOC="Show";
	public static final String SAVEICO="Save",PRINTICO="Print",CANCELICO="Cancel",TRASHICO="Trash",BACKICO="Undo",SHOWDOCICO="Doc";
	
	public static final String PROPATH=System.getProperty("user.dir");

/*	public static final String ICOPATH=PROPATH + "/pic/blue/", ICOTYPE=".png";
	public static final String ROLLOVERICOPATH=PROPATH + "/pic/green/",
	                           DISABLEDICOPATH=PROPATH + "/pic/smoke/",
	                           PRESSEDICOPATH =PROPATH + "/pic/red/";
*/
	public static final String ICOPATH=PROPATH + "/ico/normal/", ICOTYPE=".png";
	public static final String ROLLOVERICOPATH=PROPATH + "/ico/rollOver/",
	                           DISABLEDICOPATH=PROPATH + "/pic/smoke/",
	                           PRESSEDICOPATH =PROPATH + "/ico/press/";

	public static final String FORM_ICON_LOGGING="pic/icon.gif";
	public static final String FORM_ICON_MAIN="pic/icon.gif";
	public static final String FORM_ICON_INTERNEL="pic/icon.gif";
	
	//public static final String sinFont="Amila";
        public static final String sinFont="CHAMARA";
        
	
	public static String backgroundFile;
	
//8192

/**
 *not work
	public static final String BASIC_LF="javax.swing.plaf.basic.BasicLookAndFeel";
	public static final String MULTI_LF="javax.swing.plaf.multi.MultiLookAndFeel";
	public static final String GTK_LF="com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
*/

}
//WindowsLookAndFeel