package DB;

import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import upload.Upload;

public class updata {


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/**
	 * 
	 * 书籍修改方法，调用文件上传方法upload.upfile
	 * 引用了一个值，该值为fn，实际意义为上传文件第一个即（图片）的文件名
	 * */
	public static void BookUpdate(HttpServletRequest request,String bookid,String bookname,String bookAuthor,String bookClass,String bookcf,CommonsMultipartFile f[]) throws Exception{
		
		DB db = new DB();
		Upload upload = new Upload();
		upload.Upfile(request, f, bookid);
		String bookphoto =	upload.getFn();
		String sql = "update book set book_name='"+bookname+"' ,book_cf='"+bookcf+"' ,book_author='"+bookAuthor+"' ,book_class='"+bookClass+"', book_photo='"+bookphoto+"' where book_id='"+bookid+"' ";
		Statement stat =db.con.createStatement();
		stat.execute(sql);

	}
	public static void Bookclassupdate(int id ,String name) throws SQLException{
		DB db = new DB();
		String sql = "update book_class set book_class_name='"+name+"' where book_class_id="+id+"";
		Statement stat =db.con.createStatement();
		stat.execute(sql);
	}
	
	
	
}
