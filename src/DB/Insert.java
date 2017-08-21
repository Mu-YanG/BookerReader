package DB;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.DefaultEditorKit.InsertContentAction;

import org.omg.CORBA.ULongLongSeqHelper;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import upload.Upload;

public class Insert {

	static Statement stat;
	 static ResultSet rs;
	private static Object flie;
	static CommonsMultipartFile file[];
	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws SQLException, ParseException {
		// TODO Auto-generated method stub
	System.out.println(selectCollection("1001", "1002"));
	System.out.println(selectCount("1001"));
		//InsertCollection("1001", "1003");
		
		
		//UserInsert("1002","吕洋","1","1","1990","01","02","wqe","1",file);
	}
	/*
	        String userid = request.getParameter("userid");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String password2 = request.getParameter("password2");
		
			String sex =request.getParameter("sex");
		
			String year =request.getParameter("year");
			String mouth =request.getParameter("mouth");
			String day =request.getParameter("day");
			String email = request.getParameter("email");
			String userclass = request.getParameter("userclass");
			userphotos  -- userlv
	 */
	public static boolean Insert(HttpServletRequest request,String bookid,String bookname,String author,String cf,String bookclass,CommonsMultipartFile file[]) throws SQLException, IOException{
		
		
		
		DB db = new DB();
		
		/*byte b[] =file[0].getOriginalFilename().getBytes("GBK");
		String filename1 = new String(b);
		byte b2[] =file[1].getOriginalFilename().getBytes("GBK");
		String filename = new String(b2);
		System.out.println(filename1+filename);*/
		Upload.Upfile(request, file, bookid);
		String p = Upload.fn;
		String sql="insert into book values('"+bookid+"','"+bookname+"','"+author+"','"+bookclass+"','"+cf+"','"+p+"')";
		
	
		stat = db.con.createStatement();
	    int a = stat.executeUpdate(sql);
	   
	
		return false;
	}
	
	
	
	
	
	
	
	
@SuppressWarnings("static-access")
public static boolean UserInsert(HttpServletRequest request,String userid,String username,String password,String sex,String year,String mouth,String day,String email,String userclass,CommonsMultipartFile file[]) throws SQLException, IOException{
		
		String date = year+"-"+mouth+"-"+day;
		SimpleDateFormat d = new SimpleDateFormat("yyyy-mm-dd");
		int uc = 0 ;
		int usex = 1 ;
		Date birthday =new Date();
		try {
			//把生日转化为date格式
			 birthday= d.parse(date);
			 
			 uc = Integer.valueOf(userclass);
			 usex = Integer.valueOf(sex);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();System.out.println("41转换异常");
			
		}
		System.out.println(uc+" "+usex+" "+ date);
		DB db = new DB();
		byte b[] =file[0].getOriginalFilename().getBytes("GBK");
		String filename = new String(b);
		System.out.println(filename);
		Upload upload = new Upload();
		upload.UpImg(request, file, userid, "photos\\userphoto");
		 
		String sql="insert into ruser values('"+userid+"','"+username+"','"+password+"',"+usex+",'"+date+"','"+upload.getFn()+"','"+email+"',"+userclass+",1)";
		
	
		stat = db.con.createStatement();
	    int a = stat.executeUpdate(sql);
	   
		System.out.println(a);
		return false;
	}
	
	/**
	 * 该方法用于书籍类别的插入
	 * @param int (书类序号) String （书类名）
	 * 返回值：无
	 * @throws SQLException 
	 * */
	public static void InsertBookClass(String bookClassname, int bookClassId) throws SQLException{
		
		String sql = "insert into book_class values("+bookClassId+",'"+bookClassname+"')";
		DB db = new DB();
		stat = db.con.createStatement();
		stat.execute(sql);
		
	}
	
	//------------书籍类别的插入-----end----------------------------------------------
	/**
	 * 收藏插入语句
	 * @throws SQLException 	  
	 * 
	 * */
	public static  void InsertCollection(String bookid ,String userid) throws SQLException, ParseException{
		
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date =new Date();
	    String sql = "insert into collection values('"+bookid+"','"+userid+"','"+d.format(date)+"')";
		DB db = new DB();
		stat = db.con.createStatement();
		stat.execute(sql);
		
		
	}
	/**
	 * 收藏查询语句，为数据查询
	 * @throws SQLException 
	 * 
	 * */
	public static boolean selectCollection(String bookid ,String userid) throws SQLException{
		
		String sql = "select * from collection where book_id='"+bookid+"' and user_id='"+userid+"'";
		DB db = new DB();
		stat = db.con.createStatement();
		rs=stat.executeQuery(sql);
		if(rs.next()){
			return true;
			
		}

		
		return false;
	}
	/**
	 * 收藏量统计方法
	 * 
	 * */
	public static long selectCount(String bookid) throws SQLException{
			int a = 0;
			String sql = "select * from collection where book_id='"+bookid+"'";
			DB db = new DB();
			stat = db.con.createStatement();
			rs=stat.executeQuery(sql);
			while(rs.next()){
				a++;
				
			}
	
			
			return a;
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
