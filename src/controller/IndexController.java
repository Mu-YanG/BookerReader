package controller;

import java.awt.List;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modle.Book;
import modle.BookClass;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import DB.MenuList;
import DB.SelectList;
@Controller
public class IndexController  extends MultiActionController{
	
	@RequestMapping(value="index")
	public ModelAndView Index(HttpServletRequest request,HttpServletResponse response) throws SQLException{
		ModelAndView mv = new ModelAndView();
		 ArrayList<BookClass> menulist =MenuList.Booklist();//根据书类返回菜单
		  mv.addObject("menulist",menulist);
		   HttpSession s = request.getSession();
		    s.setAttribute("booklist", menulist);
		    
	    SessionBoolean.login_redirect(request, mv);//判定用户是否登录。如登录判断管理员还是普通用户，分别跳转的哦啊不同界面
	 
	//分类书籍列表----------------------------
	    
	    /**
	     * <div class="right_box">
        <div class="title"><span class="title_icon"><img src="images/bullet5.gif" alt="" title="" /></span>${m.book_class_name}</div>
        <ul class="list">
          <li><a href="#">accesories</a></li>
        </ul>

      </div>
	     * */
	    //NEW 推荐链表
	    ArrayList<Book> tjlist = new ArrayList<Book>();
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < menulist.size(); i++) {
			sb.append(" <div class=\"right_box\"> <div class=\"title\"><span class=\"title_icon\"><img src=\"images/bullet5.gif\"  /></span>"+menulist.get(i).getBook_class_name()+"</div><ul class=\"list\">");
	    	ArrayList<Book> b = SelectList.SelectClass(menulist.get(i).getBook_class_name());
	    	if(b.size()>0){
		    	int num = (int) (Math.random()*b.size());
		    	System.out.println(num);
		    	tjlist.add(b.get(num));
		    	}else{}
		    
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
	    mv.addObject("tjlist", tjlist);
	 //listclass--------------------------------------------------------------------------
	    //随机生成链表
	     SelectList.SelectBook("select * from book ");
	    
	    
	    
	    
	    
	    
	    
	    
	    
	   
	  
	    mv.setViewName("index");
		return mv;	
		
		
	}


	
	
		
}
