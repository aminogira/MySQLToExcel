package amino.ui.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import sw.proConst;

public class i18n {
	private static ResourceBundle bundle = null;
	
	private static void init(){
		bundle = ResourceBundle.getBundle(proConst.l10n);
	}
	public static String getString(String key){
		if (bundle==null) init();
		return bundle.getString(key).trim(); 
	}
	public static int getInt(String key){
		int ans=0;
		if (bundle==null) init();
		ans=new Integer(bundle.getString(key).trim());
		return ans;
	}

  public static String[] getStringArray(String key){
		if (bundle==null) init();
    String a=bundle.getString(key);
    System.out.println(a);
		String aa[]=bundle.getStringArray(key);
    
    return bundle.getStringArray(key);

	}

	
}
