package amino.data;
import java.io.*;

///
public class Converter{
	
	static BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
	final static String arr1to20[]={"one","two","three","four","five","six","seven","eight","nine","ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen"};
	final static String arr20to100[]={"twenty","thirty","forty","fifty","sixty","seventy","eighty","ninety"};
	final static String arr10toThePower[]={" hundred"," thousand", " million"," billion"," trillion"," quadrillion"};
	
	public static String printValue(Long numbr){
		
		long number=numbr.longValue();
		String st=numbr.toString();
		int length=st.length();
		
		if (number==0){
			
		}	else if(number<20 && number>0){
			int num=numbr.intValue();
			return arr1to20[num-1];
		}else if(number>=20&&number<100){	
			String str=Long.toString(number);
			Integer index1= new Integer(""+str.charAt(0));
			Integer index2=new Integer(""+str.charAt(1));
		
			if(index2>=1)
				return (arr20to100[index1-2]+" "+arr1to20[index2-1]);
			else
				return (arr20to100[index1-2]);
		}else if(number>=100 && number<1000){	
			String str=st.charAt(0)+"";
			if(new Long(st.substring(1))==0)
				return (printValue(str)+""+arr10toThePower[0]);
			else	
				return (printValue(str)+""+arr10toThePower[0]+ " and "+ printValue(st.substring(1)));
		}else	{
			int arrindex=length/3;					//length of the string representation of number/3 
			int remain=length%3;					//according to value of remain the substring boundaries change
			
			if(remain>0)
				return (printValue(new Long(st.substring(0,(remain))))+""+arr10toThePower[arrindex]+" "+printValue(new Long(st.substring(remain))));
			else
			 	return (printValue(new Long(st.substring(0,3)))+""+arr10toThePower[arrindex-1]+" "+printValue(new Long(st.substring(3))));
		}
		return "";
	}
	
	public static String printValue(String s){	
		Long l=new Long(s);
		if(l>0)
			return Converter.printValue(l);	
		else
			return"please enter a number greater than zero";
	}
	
//public static void main(String args[]){
//System.out.println("Enter the number you want to print");
//try{
//	String s=bf.readLine();
//	
//	if(new Long(s)==0)
//		System.out.println("zero");					//to avoid printing zero to enter the loop and print at the end of numbers >0
//	else
//		System.out.println(printValue(s));
//}catch(IOException io){
//	System.out.println("IO exception");
//	Converter.main(new String[1]);
//}catch(NumberFormatException nf){
//	System.out.println("Not a valid number. Please enter a valid number");
//	Converter.main(new String[1]);
//}catch(ArrayIndexOutOfBoundsException aiobEx){
//	System.out.println("Number is too large");
//	Converter.main(new String[1]);
//}catch (RuntimeException re){
//	System.err.print(re);
//	Converter.main(new String[1]);
//}
//
//
//}
	
}