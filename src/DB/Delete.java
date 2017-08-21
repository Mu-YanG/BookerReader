package DB;

import java.sql.SQLException;
import java.sql.Statement;

public class Delete {

	private static Statement stat;
	private static int      rs;
	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		//UserDelete("1");
		
		
	}
	public static void  UserDelete(String id) throws SQLException{
		DB db = new DB();
		String sql = "delete from ruser where user_id='"+id+"'";
		stat =DB.con.createStatement();
	     rs =	stat.executeUpdate(sql);
		System.out.println("delect sucess");
		
		
	}
	public static void  BookDelete(String id) throws SQLException{
		DB db = new DB();
		String sql = "delete from book where book_id='"+id+"'";
		stat =DB.con.createStatement();
	     rs =	stat.executeUpdate(sql);
		System.out.println("delect sucess");
		
		
	}
	
	/**
	 * 该方法用于书籍类别的删除
	 * @param int (书类序号) String （书类名）
	 * 返回值：无
	 * @throws SQLException 
	 * */
	public static void DeleteBookClass( int bookClassId) throws SQLException{
		
		String sql = "delete from book_class where book_class_id ="+bookClassId+" ";
		DB db = new DB();
		stat = db.con.createStatement();
		stat.execute(sql);
		
	}
	public static void DeleteColection(String bookid ,String userid) throws SQLException{
		
		String sql = "delete from collection where book_id ='"+bookid+"' and user_id = '"+userid+"' ";
		DB db = new DB();
		stat = db.con.createStatement();
		stat.execute(sql);
		
		
		
	}
	
	
	
	
	
}
