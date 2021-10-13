package amino.data;


///
import java.util.regex.Pattern;
import java.util.Date;


public class NID {
   
	public static String getGender(String NIDno){
		String gender="";
		boolean b = Pattern.matches("[0-9]{9}[a-zA-Z]", NIDno);
		if(b){
			int days=new Integer(NIDno.substring(2,5));
			if (days>500)
				gender="Female";
			else
				gender="Male";
		}
		return gender;
	}
 
	public static Date getDOB(String NIDno){
		Date dob=null;
    boolean b = Pattern.matches("[0-9]{9}[a-zA-Z]", NIDno);
    if(b){
      String year="";
      int month=0;
      int days=0,date=0;

      year=NIDno.substring(0,2);
      days=new Integer(NIDno.substring(2,5));

      if(days>500)
      	days=days-500;
      
      if(days<31){
        date=days;
        month=0;
      }else if(days<60){
        date=days-31;
        month=1;
      }else if(days<91){
        date=days-60;
        month=2;
      }else if(days<121){
        date=days-91;
        month=3;
      }else if(days<152){
        date=days-121;
        month=4;
      }else if(days<182){
        date=days-152;
        month=5;
      }else if(days<213){
        date=days-182;
        month=6;
      }else if(days<244){
        date=days-213;
        month=7;
      }else if(days<274){
        date=days-213;
        month=8;
      }else if(days<305){
        date=days-274;
        month=9;
      }else if(days<335){
        date=days-305;
        month=10;
      }else{
      	date=days-335;
        month=11;
      }
      dob=new Date(new Integer(year),new Integer(month),date);
      
//        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
//        String s = df.format(dob);

     // System.out.println("year: "+year+" month:"+month+" date:"+date);

    }
		
	  return dob;  
	}

}
