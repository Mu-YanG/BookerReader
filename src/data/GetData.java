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
	 * ���ݿ�ʱ�����ת���ӡ���
	 * */
	public static String newDate(Date date){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = dateFormat.format(date);
		return format;
	}
}
