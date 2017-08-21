package data;

import java.text.SimpleDateFormat;
import java.util.*;

public class GetData {
	public static void main(String args[]){
		
		System.out.println(getTime());
	}
	public static long getTime() {
		Date a =new Date();
		
		return a.getTime();
		
	}
	/**
	 * 数据库时间插入转换加‘’
	 * */
	public static String newDate(Date date){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = dateFormat.format(date);
		return format;
	}
}
