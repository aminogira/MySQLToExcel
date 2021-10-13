/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package amino.data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author amila giragama
 */
public class DateObservation {
  private static final int[] DAYS = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
  static DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
  // FORMAT  YYYY-MM-DD
  public static boolean isValid(String gDate) {
    gDate=gDate.trim();
    if(gDate.length()>19)
      return false;
    if(gDate.length()>10)
      gDate=gDate.substring(0,10);
    int iY=0,iM=0,iD=0;
    boolean patternError=false;
    try{
      iY=new Integer(gDate.substring(0,4));
      iM=new Integer(gDate.substring(5,7));
      iD=new Integer(gDate.substring(8));
    }catch(NumberFormatException nfe){
      patternError = false;
    }
    if(patternError)
      return false;
    else
      if ((iY==0)||(iM==0)||(iD==0))
        return false;
      else
        return isValid(iM,iD,iY);
  }


  public static boolean isValid(int m, int d, int y) {
    if (y<1900) return false; //no need actueal date
    //but this use for database date
    if (m < 1 || m > 12)      return false;
    if (d < 1 || d > DAYS[m]) return false;
    if (m == 2 && d == 29 && !isLeapYear(y)) return false;
    return true;
  }

  public static boolean isLeapYear(int y) {
    if (y % 400 == 0) return true;
    if (y % 100 == 0) return false;
    return (y % 4 == 0);
  }
  
  
  public static int getDateDiffDays(Date fday, Date lday){
    Calendar calf = Calendar.getInstance();
    Calendar call = Calendar.getInstance();
    calf.setTime(fday);
    call.setTime(lday);
    long diffm=call.getTimeInMillis()-calf.getTimeInMillis();
    /*System.out.println("Minits "+diffm/(1000*60));
    System.out.println("Hours "+diffm/(1000*60*60));
    System.out.println("Day "+diffm/(1000*60*60*24));
    System.out.println("Months "+diffm/(1000*60*60*24*30));*/
    int diff= (int) Math.abs(Math.floor(diffm/(1000*60*60*24)));
    return diff;
}
  
  
  
}


/**
 *
 *
 * //        if(isValid(iM,iD,iY)){
//          //aditional testing
//          boolean ptrnPass=true;
//          try {
//            Date cDate = (Date)sdf.parse(iY + "-" + iM + "-" + iD);
//            System.out.println(cDate);
//          } catch (ParseException e) {
//            ptrnPass=false;
//          }
//          return ptrnPass;
//        }
//        return false;

  public static Date getDate(String date) {
		//DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

		Date cDate=null;
		try {
			cDate = (Date)sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return cDate;
	}
 *
 *
 */