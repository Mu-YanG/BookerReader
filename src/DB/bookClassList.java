package DB;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



import modle.Book;

public class bookClassList{
	
	/**
	 *第一部分按照书籍类别分类
	 * @throws SQLException 
	 * 
	 * 
	 * */
	
	
	
	public static ArrayList<Book> BookClassSelectAllShow(String bookclass) throws SQLException{
		ArrayList<Book> list = new ArrayList<Book>();
		String sql = "select * from book where  book_class='"+bookclass+"'"; 
		DB db = new DB();
		Statement	stat= db.con.createStatement();
		ResultSet rs = stat.executeQuery(sql);
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
	public ArrayList<Book> newboolist(){
		
		ArrayList<Book> list = new ArrayList<Book>();
		
		
		
		
		
		return list;
	}
	
	
	
	
	
	
	
}
