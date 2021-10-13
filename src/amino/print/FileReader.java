package amino.print;
///
import java.io.File;
import java.io.FilenameFilter;

//import sw.proConst;
/**
 * @author amila giragama
 *
 */
public class FileReader {
	
	static File dir;
	
//	public static void main(String args[]){
//		proConst.getOpenID();
//		String s[]=getFileList();
//		for (int x=0;x<s.length;x++){
//			String fn=s[x].substring(0, (s[x].length()-7));
//			System.out.println(fn);
//		}
//			
//	}
	
	public static String[] getFileList(String reportPath){
		String fileList[]=null;
		
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				//return !name.startsWith(".");
				return (name.endsWith(".jasper") && !name.startsWith("."));
			}
		}; 
		//
		dir=new File(reportPath);
		fileList=dir.list(filter);
		return fileList; 
	}
	
	
	
}
