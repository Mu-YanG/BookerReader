package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import modle.Book;
import modle.BookClass;
import modle.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import data.Regex;

import DB.Delete;
import DB.Insert;
import DB.MenuList;
import DB.SelectList;

@Controller
public class UserZcContriller {

	@RequestMapping(value="user/zc")
	public ModelAndView Zc(HttpServletRequest request) throws SQLException, IOException{
		ModelAndView mv = new ModelAndView();
		 ArrayList<BookClass> menulist =MenuList.Booklist();//根据书类返回菜单
		  mv.addObject("menulist",menulist);
		   HttpSession s = request.getSession();
		    s.setAttribute("booklist", menulist);
		    
	    SessionBoolean.login_redirect(request, mv);
	    
	    mv.addObject("title", "用户注册");
	    
	    //用户修改---------------------------------
	   String xg = request.getParameter("xg");
	   if(xg != null){
		   HttpSession session = request.getSession();
		   session.getAttribute("id");
		   mv.addObject("title", "修改");
		   mv.addObject("xg", "notnull");
		  List<User> userlist= SelectList.SelectUserlist((String)session.getAttribute("id"));
		  mv.addObject("userlist", userlist);
	   }

		   
	    
	    
	    //右侧书籍显示---------------------------------
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < menulist.size(); i++) {
			sb.append(" <div class=\"right_box\"> <div class=\"title\"><span class=\"title_icon\"><img src=\"../images/bullet5.gif\"  /></span>"+menulist.get(i).getBook_class_name()+"</div><ul class=\"list\">");
	    	ArrayList<Book> b = SelectList.SelectClass(menulist.get(i).getBook_class_name());
	    	for(int j=b.size();j<10;j++){
	    		Book book =new Book();
	    		book.setBookId("#");
	    		book.setBookName("书籍未上架敬请期待");
	    		b.add(book);
	    		
	    	}
	    	for (int j = 0; j < b.size(); j++) {
	    		if("#".equals(b.get(j).getBookId())){
	    		sb.append("<li><a href=\"#\">"+b.get(j).getBookName()+"</a></li>");
	    		}else{
	    			sb.append("<li><a href=\"/BookReader/user/bookmessage?bookid="+b.get(j).getBookId()+"\">"+b.get(j).getBookName()+"</a></li>");	
	    		}
			}
	    	sb.append("</ul></div>");
	    
		}
	    mv.addObject("sblist", sb);
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    mv.setViewName("user/zc");
	    return mv;
		
	}
	@RequestMapping(value="user/zcfile")
	public ModelAndView Zcfile(HttpServletRequest request,@RequestParam("file")CommonsMultipartFile file[]) throws SQLException, IOException{
		ModelAndView mv = new ModelAndView();
		 ArrayList<BookClass> menulist =MenuList.Booklist();//根据书类返回菜单
		  mv.addObject("menulist",menulist);
		   HttpSession s = request.getSession();
		    s.setAttribute("booklist", menulist);
		    
	    SessionBoolean.login_redirect(request, mv);
	    mv.addObject("title", "用户注册");
	    //用户注册开始---------------------------------
	    String userid = request.getParameter("userid");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		
		String sex =request.getParameter("sex");
	
		String year =request.getParameter("year");
		String mouth =request.getParameter("mouth");
		String day =request.getParameter("day");
		String email = request.getParameter("email");
		Delete.UserDelete(userid);
		
		
		//产生数据转换异常，如果用string —— int 报错空指针异常 
		//String filepath = request.getParameter("filepath");
		
		//最外层判断输入完整
		if(userid != " " && username != null && password != null && password2 != null  && email != null && !file[0].isEmpty() ){
			//判断密码输入是否一直
			if(!password.equals(password2)){
				mv.addObject("tishi2", "密码输入不一致");
			} 
			//判断插入数据是否存在与数据库，判断主键
			if(!SelectList.SelectUser(userid)){
				mv.addObject("tishi0", "用户名重复");	
			 } 
			//判断邮箱是否正确
			if(!Regex.Emaile(email)){
				mv.addObject("tishi3", "邮箱格式错误");
			 }
			
			if( password.equals(password2) && SelectList.SelectUser(userid) && Regex.Emaile(email)){
				
				//数据插入
				Insert.UserInsert(request,userid, username, password2, sex, year, mouth, day, email, "1", file);
				return new ModelAndView("redirect:/login/");
				
			}	

			/*
				//int sexi = Integer.valueOf(sex); 
				//int userclassi = Integer.valueOf(userclass); 
				String birthday = year+"-"+mouth+"-"+day;
				SimpleDateFormat a = new SimpleDateFormat("yyyy-mm-dd");
				Date date =a.parse(birthday);
				Upload.UpImg(request,file,userid, "photos");
				*/					

		}
		//最外层输入不完整，返回提示
		else{
			mv.addObject("tishi1", "重新检查保证输入完整");
			
		}
	    
	    
	    
	    
	    
	    
	    //右侧书籍显示---------------------------------
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < menulist.size(); i++) {
			sb.append(" <div class=\"right_box\"> <div class=\"title\"><span class=\"title_icon\"><img src=\"../images/bullet5.gif\"  /></span>"+menulist.get(i).getBook_class_name()+"</div><ul class=\"list\">");
	    	ArrayList<Book> b = SelectList.SelectClass(menulist.get(i).getBook_class_name());
	    	for(int j=b.size();j<10;j++){
	    		Book book =new Book();
	    		book.setBookId("#");
	    		book.setBookName("书籍未上架敬请期待");
	    		b.add(book);
	    		
	    	}
	    	for (int j = 0; j < b.size(); j++) {
	    		if("#".equals(b.get(j).getBookId())){
	    		sb.append("<li><a href=\"#\">"+b.get(j).getBookName()+"</a></li>");
	    		}else{
	    			sb.append("<li><a href=\"/BookReader/user/bookmessage?bookid="+b.get(j).getBookId()+"\">"+b.get(j).getBookName()+"</a></li>");	
	    		}
			}
	    	sb.append("</ul></div>");
	    
		}
	    mv.addObject("sblist", sb);
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    mv.setViewName("user/zc");
	    return mv;
		
	}
}
