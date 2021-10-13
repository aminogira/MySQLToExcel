package sw.db;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import amino.print.PrintRow;
import java.util.logging.Level;
import java.util.logging.Logger;
//import sw.ctrl.DataRow;
//import sw.ctrl.DataSet;

public class ctrlData {
	
	public static DecimalFormat deciformat = new DecimalFormat( "#,###,##0.00" );
	public static DecimalFormat numformat = new DecimalFormat( "####0.00" );
//lblLoanAmount.setText(ctrlData.deciformat.format(rs.getDouble("loanAmount")));
	
	
//	public static DataSet[] toDataSetArray(Object obj[]){
//		DataSet[] ds=new DataSet[obj.length];
//		for(int x=0;x<obj.length;x++)
//			ds[x]=(DataSet) obj[x];
//		return ds;
//	}
//	public static DataRow[] toDataRowArray(Object obj[]){
//		DataRow[] dr=new DataRow[obj.length];
//		for (int x=0;x<obj.length;x++)
//			dr[x]=(DataRow) obj[x];
//		return dr;
//	}
  public static double roundTwoDecimals(double d) {
    DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Double.valueOf(twoDForm.format(d));
  }
  public static double roundNoDecimals(double d) {
    DecimalFormat twoDForm = new DecimalFormat("###");
		return Double.valueOf(twoDForm.format(d));
  }


	public static PrintRow[] toPrintRowArray(Object obj[]){
		PrintRow[] dr=new PrintRow[obj.length];
		for (int x=0;x<obj.length;x++)
			dr[x]=(PrintRow) obj[x];
		return dr;
	}
	public static int[] tointArray(Object obj[]){
		int tmp[]=new int[obj.length];
		for (int x=0;x<obj.length;x++)
			tmp[x]=(Integer)obj[x];
		return tmp;
	}
	public static boolean[] tobooleanArray(Object obj[]){
		boolean tmp[]=new boolean[obj.length];
		for (int x=0;x<obj.length;x++)
			tmp[x]=(Boolean)obj[x];
		return tmp;
	}
	
	public static Integer[] toIntegerArray(Object obj[]){
		Integer tmp[]=new Integer[obj.length];
		for (int x=0;x<obj.length;x++)
			tmp[x]=(Integer)obj[x];
		return tmp;
	}
	
	public static String[] toStringArray(Object obj[]){
		String str[]=new String[obj.length];
		for(int x=0;x<obj.length;x++)
			str[x]=(String)obj[x];
		return str;
	}
	public static double[] todoubleArray(Object obj[]){
		double dbl[]=new double[obj.length];
		for(int x=0;x<obj.length;x++)
			dbl[x]=(Double)obj[x];
		return dbl;
	}
	public static Double[] toDoubleArray(Object obj[]){
		Double dbl[]=new Double[obj.length];
		for(int x=0;x<obj.length;x++)
			dbl[x]=(Double)obj[x];
		return dbl;
	}
	public static Date[] toDateArray(Object obj[]){
		Date dt[]=new Date[obj.length];
		for(int x=0;x<obj.length;x++)
			dt[x]=(Date)obj[x];
		return dt;
	}
	public static String getDateString(Date date){
		if (date==null)
			return "2000-01-01";
		else{
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			return sdf.format(date);
		}
	}
	
	public static String getDateStringForFileName(Date date){
		if (date==null)
			return "2000-01-01";
		else{
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd_hhmmss");
			return sdf.format(date);
		}
	}
	public static String getDateString(String gDate){
		DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = null;
		try {
			date = (Date)sdf.parse(gDate);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return sdf.format(date);
	}
	public static Date getDate(String date){
		DateFormat sdfLong=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		DateFormat sdfSort=new SimpleDateFormat("yyyy-MM-dd");
		Date cDate=null;
		if(date.length()>10){
			try {
				cDate = (Date)sdfLong.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				cDate = (Date)sdfSort.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cDate;
	}	
	public static int getDateDef(Date dtStart,Date dtEnd){
		int ans=0;
		ans= (int) ((dtEnd.getTime()- dtStart.getTime())/(24*60*60*1000));
		
		
		
		
		return ans;
	}
	
	public static final int MONTH=GregorianCalendar.MONTH;
	public static final int YEAR=GregorianCalendar.YEAR;
	public static final int DATE=GregorianCalendar.DATE;
	
	public static Date getNextDate(Date startDate,int field,int amount){
		GregorianCalendar gc=new GregorianCalendar();
		gc.setTime(startDate);
		gc.add(field, amount);
		return gc.getTime();
	}
	
}
