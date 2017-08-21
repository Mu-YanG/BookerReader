package DB;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class BirthdayChange {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		tobairthday("1993-12-11");

	}
	//1994.12.21


	public static Date tobairthday(String s) throws ParseException{
	//String regex = {}
	String[] a=s.split("\\.");//ÕýÔò·Ö¸î
	

	SimpleDateFormat data =new SimpleDateFormat("yyyy-mm-dd");
	Date d = data.parse(s);
	
	
	
	System.out.println(s);
		
		
		return null;
		
	}
	

}
