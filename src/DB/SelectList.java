package DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modle.Book;
import modle.Collection;
import modle.User;

public class SelectList {

	private static ArrayList<User> list;
	private static Statement stat;
	private static ResultSet rs;


	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println(SelectUser("1qwewqe"));
	}
	
	/**
	 * 现用于用户登陆时返回arrylist的一个链表，即用户信息返回
	 * 查项 id，匹配项 password    如想项查找可重写 SelectUserlogin 方法
	 * @param String id  String password
	 * */
	public static List<User>  SelectUserlogin(String id , String password ) throws SQLException{
		DB db = new DB();
		stat = DB.con.createStatement();
		
		
		String sql="select * from ruser where user_id = '"+id+"'";
		
		
		rs = stat.executeQuery(sql);
		
		User user =new User();
		List<User> list = new ArrayList<User>();
	
		while(rs.next()){
				user.setId(rs.getString(1));
				user.setName(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setSex(rs.getInt(4));
				user.setBirthday(rs.getDate(5));
				user.setPhoto(rs.getString(6));
				user.setEmai(rs.getString(7));
				user.setUserClass(rs.getInt(8));
				user.setUserlv(rs.getInt(9));
				list.add(user);
				
		}
		DB.con.close();
		System.out.println("数据库关闭");
		return list;
	}
	public static List<User>  SelectUserlist(String id) throws SQLException{
		DB db = new DB();
		stat = DB.con.createStatement();
		
		
		String sql="select * from ruser where user_id = '"+id+"'";
		
		
		rs = stat.executeQuery(sql);
		
		User user =new User();
		List<User> list = new ArrayList<User>();
	
		while(rs.next()){
				user.setId(rs.getString(1));
				user.setName(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setSex(rs.getInt(4));
				user.setBirthday(rs.getDate(5));
				user.setPhoto(rs.getString(6));
				user.setEmai(rs.getString(7));
				user.setUserClass(rs.getInt(8));
				user.setUserlv(rs.getInt(9));
				list.add(user);
				
		}
		DB.con.close();
		System.out.println("数据库关闭");
		return list;
	}
	//用户查询返回链表
	public static List<User>  SelectUserList(String sql) throws SQLException{
		DB db = new DB();
		stat = DB.con.createStatement();
		
		
		//String sql="select * from ruser where user_id = '"+id+"'";
		
		
		rs = stat.executeQuery(sql);
		
	
		ArrayList<User> list = new ArrayList<User>();
		
		while(rs.next()){
			User user =new User();//发生错误：原因对象先声明，放入list中的对象是一个
				user.setId(rs.getString(1));
				user.setName(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setSex(rs.getInt(4));
				user.setBirthday(rs.getDate(5));
				user.setPhoto(rs.getString(6));
				user.setEmai(rs.getString(7));
				user.setUserClass(rs.getInt(8));
				user.setUserlv(rs.getInt(9));
				list.add(user);
				
		}
		DB.con.close();
		System.out.println("数据库关闭");
		
		return list;
	}
	
	public static ArrayList<User>  SelectUser(String id ,String name, String password ,String SEX ,String birthday,String userclass, Statement stat) throws SQLException{
		DB db = new DB();
		stat = DB.con.createStatement();
		
		
		
		
		
		
		return list;
	}
	//用户id查询方法二
	public static boolean  SelectUser(String id) throws SQLException{
		DB db = new DB();
		stat = DB.con.createStatement();
        String sql="select * from ruser where user_id = '"+id+"'";
		rs = stat.executeQuery(sql);
		if(rs.next()){
			return false;//数据判断。如果有id = 传入参数的时候，返回  false 调用时 if（）{}else{：目前为该用户已存在，请重新输入用户}
				
		}
		DB.con.close();
		System.out.println("数据库关闭");
		return true;
	}
	
	//书籍查询bookmessage
	/**
	 *开始查找--------------------------------------------------------------
	 * 
	 *按照id查询-------------
	 * 
	 * */
	public static ArrayList<Book> SelectId(String bookid) throws SQLException{
		ArrayList<Book> list = new ArrayList<Book>();
		String sql = "select * from book where book_id ='"+bookid+"'";
		 DB db = new DB();
		 stat = db.con.createStatement();
		rs = stat.executeQuery(sql);
		while(rs.next()){
			Book book = new Book();
			book.setBookId(rs.getString(1));
			book.setBookName(rs.getString(2));
			book.setBookAuthor(rs.getString(3));
			book.setBookClass(rs.getString(4));
			book.setBookCf(rs.getString(5));
			book.setBookPhoto(rs.getString(6));
			list.add(book);
			
		}
		
		
		return list;
	} 
	/**
	 * 按照书名进行查询
	 * */
	public static ArrayList<Book> SelectName(String name) throws SQLException{
		ArrayList<Book> list = new ArrayList<Book>();
		String sql = "select * from book where book_name ='"+name+"'";
		 DB db = new DB();
		 stat = db.con.createStatement();
		rs = stat.executeQuery(sql);
		while(rs.next()){
			Book book = new Book();
			book.setBookId(rs.getString(1));
			book.setBookName(rs.getString(2));
			book.setBookAuthor(rs.getString(3));
			book.setBookClass(rs.getString(4));
			book.setBookCf(rs.getString(5));
			book.setBookPhoto(rs.getString(6));
			list.add(book);
			
		}
		
		
		return list;
	} 
	/**
	 * 按照书的类别进行查询
	 * */
	
	public static ArrayList<Book> SelectClass(String bookclass) throws SQLException{
		ArrayList<Book> list = new ArrayList<Book>();
		String sql = "select * from book where book_class ='"+bookclass+"'";
		 DB db = new DB();
		 stat = db.con.createStatement();
		rs = stat.executeQuery(sql);
		while(rs.next()){
			Book book = new Book();
			book.setBookId(rs.getString(1));
			book.setBookName(rs.getString(2));
			book.setBookAuthor(rs.getString(3));
			book.setBookClass(rs.getString(4));
			book.setBookCf(rs.getString(5));
			book.setBookPhoto(rs.getString(6));
			list.add(book);
			
		}
		
		
		return list;
	} 
	
	
	public static ArrayList<Book> SelectBook(String sql) throws SQLException{
		ArrayList<Book> list = new ArrayList<Book>();
	//	String sql = "select * from book where book_id ='"+bookid+"'";
		 DB db = new DB();
		 stat = db.con.createStatement();
		rs = stat.executeQuery(sql);
		while(rs.next()){
			Book book = new Book();
			book.setBookId(rs.getString(1));
			book.setBookName(rs.getString(2));
			book.setBookAuthor(rs.getString(3));
			book.setBookClass(rs.getString(4));
			book.setBookCf(rs.getString(5));
			book.setBookPhoto(rs.getString(6));
			list.add(book);
			
		}
		
		
		return list;
	} 
	public static ArrayList<Book> SelectBookauthor(String author) throws SQLException{
		ArrayList<Book> list = new ArrayList<Book>();
		String sql = "select * from book where book_author ='"+author+"'";
		 DB db = new DB();
		 stat = db.con.createStatement();
		rs = stat.executeQuery(sql);
		while(rs.next()){
			Book book = new Book();
			book.setBookId(rs.getString(1));
			book.setBookName(rs.getString(2));
			book.setBookAuthor(rs.getString(3));
			book.setBookClass(rs.getString(4));
			book.setBookCf(rs.getString(5));
			book.setBookPhoto(rs.getString(6));
			list.add(book);
			
		}
		
		
		return list;
	} 
	
	/**
	 * 该方法用于用户返回收藏的图书id
	 * @throws SQLException 
	 * 
	 * 
	 * */
	public static ArrayList<Collection> SelectUserBook(String userid) throws SQLException{
		ArrayList<Collection> list = new ArrayList<Collection>();
		String sql = "select * from collection where user_id='"+userid+"'";
		DB db = new DB();
		stat = db.con.createStatement();
		rs=stat.executeQuery(sql);
		while(rs.next()){
			
			Collection Collection = new Collection();
			Collection.setBookId(rs.getString(1));
			Collection.setUserId(rs.getString(2));
	
			
			list.add(Collection);
			
		}
		
		
		
		
		
		return list;
		
	}
	
	
	
	
	
	
}
