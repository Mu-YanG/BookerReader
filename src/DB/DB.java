package DB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DB {
   
	
	public static Connection con;
	public static Statement stat;
	public static ResultSet rs;
	public  DB() {
	try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/webbk?rewriteBatchedStatements=true&characterEncoding=GBK";
		    con=DriverManager.getConnection(url,"root","ROOT");
		    System.out.println("------sucess join database------");
		
		
		
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println("Êý¾ÝÁ´½ÓÊ§°Ü");
	
	}	
		
	}
	/*public static ArrayList<ResultSet> Select(String sql) throws SQLException{
		   sate = con.createStatement();
		   rs = sate.executeQuery(sql);
		   ArrayList<ResultSet> list = new ArrayList<ResultSet>();
		   while(rs.next()) {
			   
			   list.add(rs);
			   
			   
		   }
		return list;
	}*/

	
	
	
}
