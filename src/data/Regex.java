package data;



public class Regex {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Emaile("11_2312@qq.asqqweqww");
		
	}
	//һ���û�ע��������ж�����
	/**
	 * @param String email
	 */
	public static boolean Emaile(String email) {
		//������\w + @+{��Сд��ĸ} + . + [Сд��ĸ] + ������
		String regex = "\\w+\\@+[a-zA-Z]+\\.+[a-z]+\\b";
		boolean a= email.matches(regex);
		System.out.println(a);
		return a;
	}
	
	
}
