package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modle.Book;
import modle.BookClass;
import modle.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import DB.MenuList;
import DB.SelectList;

@Controller
public class LoginController extends MultiActionController{

	
	
	
	
	
	
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return super.handleRequest(request, response);
	}
	@RequestMapping(value="login")
	public ModelAndView Loginb(HttpServletRequest request,HttpServletResponse response) throws SQLException {
		HttpSession sessoin = request.getSession();
		ModelAndView mv = new ModelAndView();
		 ArrayList<BookClass> menulist =MenuList.Booklist();
		 mv.addObject("menulist",menulist);
		 sessoin.setAttribute("booklist", menulist);
		if(SessionBoolean.Seionboolean(request)){//session为空 没有登录
		
		
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			if(id != null && password != null){//判断输入是否为空不为空继续
				
				List<User> lis =SelectList.SelectUserlogin(id, password);
			
				if(!lis.isEmpty()){
				    User user =lis.get(0);
					int a =user.getUserClass();
					String na = user.getName();
					String getid = user.getId();
					
					String pa =user.getPassword(); 
					
					
					if(pa.equals(password)){//用户密码正确
						
						sessoin.setAttribute("id", id);
						sessoin.setAttribute("username", na);
						sessoin.setAttribute("userclass",a);
						if(a==0){//跳转管理员界面
							mv.addObject("index", "master");
							mv.setViewName("master/mindex");
							mv.addObject("message", na);
							mv.addObject("tishi","master/mindex");
						}else {
							//跳转用户index
							mv.addObject("message", na);
							mv.addObject("tishi","#");
							
							return new ModelAndView("redirect:/index");
							
						}
						
					}else{
						mv.addObject("message", "登录");
						mv.addObject("tishi", "密码不对重新输入");
						
						mv.setViewName("user/login");
					}
				}else{
					   mv.addObject("tishi", "没有改用户，请重新核对");
					   mv.addObject("message", "登录");
					   mv.setViewName("user/login");
				}
				
				
				
				
				
			}else{
				mv.addObject("message", "登录");
				
				mv.setViewName("user/login");
			}
			
			
	  }else{//session 不为空以登录
		  	
			String s =(String)sessoin.getAttribute("username");
			mv.addObject("message", s);
			return new ModelAndView("redirect:/index");
	  }
		
		
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
		    
		    
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		return mv;
	}
	
	
	

}
