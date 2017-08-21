package data;



public class Regex {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Emaile("11_2312@qq.asqqweqww");
		
	}
	//一个用户注册邮箱的判定方法
	/**
	 * @param String email
	 */
	public static boolean Emaile(String email) {
		//可输入\w + @+{大小写字母} + . + [小写字母] + 结束符
		String regex = "\\w+\\@+[a-zA-Z]+\\.+[a-z]+\\b";
		boolean a= email.matches(regex);
		System.out.println(a);
		return a;
	}
	
	
}
