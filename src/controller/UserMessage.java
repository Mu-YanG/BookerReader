package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modle.Book;
import modle.BookClass;
import modle.Collection;
import modle.User;

import org.omg.CORBA_2_3.portable.InputStream;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import DB.Delete;
import DB.MenuList;
import DB.SelectList;
@Controller
public class UserMessage extends MultiActionController {
	@RequestMapping(value="usermessage")
	public ModelAndView handleRequestInternal(HttpServletRequest request,HttpServletResponse response)throws SQLException{
				ModelAndView mv = new ModelAndView();
				ArrayList<BookClass> menulist =MenuList.Booklist();
				mv.addObject("menulist",menulist);
				SessionBoolean.login_redirect(request, mv);//判定用户是否登录。如登录判断管理员还是普通用户，分别跳转的哦啊不同界面
				 
				String userid =(String) request.getSession().getAttribute("id");
				List<User> userlist = SelectList.SelectUserlist(userid);
				mv.addObject("user",userlist);
				String sc = request.getParameter("sc");
				String dw = request.getParameter("dw");
			
				String bookid = request.getParameter("bookid");
				//取消收藏
				if(sc!=null && bookid!=null){
					Delete.DeleteColection(bookid, userid);
					
				}
				//文件下载
				if(dw!=null && bookid!=null){
					//response.setContentType("text/plain");
					try {
						ArrayList<Book>	book1=SelectList.SelectId(bookid);
						response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(book1.get(0).getBookName()+".txt", "UTF-8"));
						String path = request.getRealPath("//bookfiles//"+bookid+"//"+bookid+".txt");
						System.out.println(path);
						File file = new File(path);
						FileInputStream in = new FileInputStream(file);
						OutputStream out = response.getOutputStream();
						byte b[] = new byte[500];
						int r;
						while((r=in.read())!=-1){
							out.write(r);
							
						}
						in.close();
						out.close();
					
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
				}
				
				
				
				ArrayList<Collection> booklist = SelectList.SelectUserBook(userid.toString().trim());
				ArrayList<Book> bookcollection = new ArrayList<Book>();
				for (int i = 0; i <booklist.size(); i++) {
					ArrayList<Book>	book=SelectList.SelectId(booklist.get(i).getBookId());
					bookcollection.addAll(book);
					
				}
				mv.addObject("bookcollection", bookcollection);
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				mv.setViewName("user/usermessage");
				return mv;
				
				
			}
}
