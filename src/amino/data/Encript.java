package amino.data;

public class Encript {
///
	//please do not use \ " '
	
	private final static String sOri="`1234567890-=|+_)(*&^%$#@!~qwertyu iop[]}{POIUYTREWQasdfghjkl;:LKJHGFDSA>ZXCVBNM<>?/.,mnbvcxz<";
	private final static String sEnc="-=|+_)(*&^%$#@!~qwertyuiop[]}{POIUYTREWQasdfghjkl;:LKJHGFDSA>Z XCVBNM<>?/.,mnbvcxz<`1234567890";
	
	//┬┴(─§î♠üû
	
	public static String encript(String original){
		String encripted="";
		for (int x=0;x<original.length();x++){
			String oneChar=original.substring(x, x+1);
			int foundIn=sOri.indexOf(oneChar);
			String foundChar=sEnc.substring(foundIn, foundIn+1);
			//encripted.concat(foundChar);
			encripted += foundChar;
		}
		return encripted;
	}
	
	public static String decript(String encripted){
		String decripted="";
		for (int x=0;x<encripted.length();x++){
			String oneChar=encripted.substring(x, x+1);
			int foundIn=sEnc.indexOf(oneChar);
			String foundChar=sOri.substring(foundIn, foundIn+1);
			decripted+=foundChar;
		}
		return decripted;
	}

//	public static void main(String args[]){
//		String a="amila giragama";
//		System.out.println(a);
//		System.out.println(encript(a));
//		System.out.println(decript(encript(a)));
//	}

}
