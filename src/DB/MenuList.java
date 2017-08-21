package DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import modle.BookClass;

public class MenuList {

	public static Statement stat = null;
	public static ResultSet rs;
	/**
	 * @param args
	 * @throws SQLException 
	 */
	/*public static void main(String[] args) throws SQLException {
		ArrayList<BookClass> list = MenuList.Booklist(); 
		for (int i = 0; i < list.size(); i++) {
			BookClass b = list.get(i);
			System.out.println(b.getBook_class_id()+" "+b.getBook_class_name());
			
		}
	}*/
	//书类的查询
	public static ArrayList<BookClass> Booklist() throws SQLException{
		ArrayList<BookClass> list = new ArrayList<BookClass>();
		
		String sql = "select * from book_class "; 
		DB db = new DB();
		System.out.println();
		stat = db.con.createStatement();
		rs =stat.executeQuery(sql);
		while(rs.next()) {
			BookClass bc = new BookClass();
			bc.setBook_class_id(rs.getInt(1));
			bc.setBook_class_name(rs.getString(2));
			list.add(bc);
			
			
		}
		DB.con.close();
		return list; 
	}
}
