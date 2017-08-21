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
		if(SessionBoolean.Seionboolean(request)){//sessionΪ�� û�е�¼
		
		
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			if(id != null && password != null){//�ж������Ƿ�Ϊ�ղ�Ϊ�ռ���
				
				List<User> lis =SelectList.SelectUserlogin(id, password);
			
				if(!lis.isEmpty()){
				    User user =lis.get(0);
					int a =user.getUserClass();
					String na = user.getName();
					String getid = user.getId();
					
					String pa =user.getPassword(); 
					
					
					if(pa.equals(password)){//�û�������ȷ
						
						sessoin.setAttribute("id", id);
						sessoin.setAttribute("username", na);
						sessoin.setAttribute("userclass",a);
						if(a==0){//��ת����Ա����
							mv.addObject("index", "master");
							mv.setViewName("master/mindex");
							mv.addObject("message", na);
							mv.addObject("tishi","master/mindex");
						}else {
							//��ת�û�index
							mv.addObject("message", na);
							mv.addObject("tishi","#");
							
							return new ModelAndView("redirect:/index");
							
						}
						
					}else{
						mv.addObject("message", "��¼");
						mv.addObject("tishi", "���벻����������");
						
						mv.setViewName("user/login");
					}
				}else{
					   mv.addObject("tishi", "û�и��û��������º˶�");
					   mv.addObject("message", "��¼");
					   mv.setViewName("user/login");
				}
				
				
				
				
				
			}else{
				mv.addObject("message", "��¼");
				
				mv.setViewName("user/login");
			}
			
			
	  }else{//session ��Ϊ���Ե�¼
		  	
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
		    		book.setBookName("�鼮δ�ϼܾ����ڴ�");
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
