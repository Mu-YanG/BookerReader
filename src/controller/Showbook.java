package controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modle.Book;
import modle.BookClass;
import modle.Talk;
import modle.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import DB.Delete;
import DB.Insert;
import DB.MenuList;
import DB.SelectList;
import DB.TalkServcieImpl;
import DB.bookClassList;
import data.Page;
import data.PageGroup;
@Controller
public class Showbook extends MultiActionController {
	
	private String c;
	int number=9;
	@RequestMapping(value="show")
	public ModelAndView handleRequestInternal(HttpServletRequest request,
		HttpServletResponse response,@RequestParam("id")int book_class_id,@RequestParam(defaultValue="0")Integer num) throws Exception {
		  ModelAndView mv=new ModelAndView();//调用菜单
		
		  ArrayList<BookClass> menulist =MenuList.Booklist();
		  mv.addObject("menulist",menulist);
		  for (int i = 0; i < menulist.size(); i++) {
			if(menulist.get(i).getBook_class_id() == book_class_id){
				
				 c = menulist.get(i).getBook_class_name();
			}
		}
		  mv.addObject("bc",c);
		  
		  HttpSession session = request.getSession();
		    session.setAttribute("booklist", menulist);
		    SessionBoolean.login_redirect(request, mv);
		    
		    //书籍展示分页
		    String zzz= c.trim();
		    ArrayList<Book> list =bookClassList.BookClassSelectAllShow(zzz);
		    ArrayList<Book> list2 = new ArrayList<>();
		   
		    if (num<0) {
				num=0;
			}
		    //正好
		    if ((list.size()%number)==0) {
		    	
				if (num>=list.size()/number) {
					
					num=list.size()/number;
				}
			
		    }else{
		    	if (num>=(list.size()/number)) {
					num=(list.size()/number);
				}
		    }
		   
		    if (num!=null) {
		    	for (int i = num*number; i < num*number+number; i++) {
		    		if (i>=list.size()) {
		    			break;
		    		}
		    		//初始化
		    		list2.add(list.get(i));
		    	}
		    }
		    
		    mv.addObject("book_class_id",book_class_id);
		    mv.addObject("num",num);
		    
		    
		    mv.addObject("bookshowlist", list2);
		    StringBuilder sb = new StringBuilder();
		    for (int i = 0; i < menulist.size(); i++) {
				sb.append(" <div class=\"right_box\"> <div class=\"title\"><span class=\"title_icon\"><img src=\"images/bullet5.gif\"  /></span>"+menulist.get(i).getBook_class_name()+"</div><ul class=\"list\">");
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
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    mv.setViewName("user/bookshow");
		    return mv;
		
		
	
	}
	/**
	 * 图书显示
	 * */
	@RequestMapping(value="user/bookmessage")
	public ModelAndView BookMessage(HttpServletRequest request) throws SQLException, ParseException{
		String sc = request.getParameter("sc");
		String userid =(String) request.getSession().getAttribute("id");
		String bookid = request.getParameter("bookid");
		TalkServcieImpl impl = new TalkServcieImpl();
		
		
		
		ModelAndView mv = new ModelAndView();
		//目录返回-------------
		     ArrayList<BookClass> menulist =MenuList.Booklist();
		     SessionBoolean.login_redirect(request, mv);
		     mv.addObject("menulist",menulist);
		 //用户登录判定    
		     List<Talk> talkList = impl.getTalkList(bookid);
		     
		     mv.addObject("talkList",talkList);
		     if(bookid!=null){
				     request.getSession().setAttribute("bookmessageid", bookid);
		     }   
				     bookid =(String) request.getSession().getAttribute("bookmessageid");
				     ArrayList<Book>  bookshow = SelectList.SelectId(bookid);
				   
				     mv.addObject("bookshow",bookshow);
				     mv.addObject("bookid2",bookid);
				   
				     //收藏次数返回值
				    long count= Insert.selectCount(bookid);
				    mv.addObject("count",count);
				  //收藏判断-----初次
				    
				    
				    if(Insert.selectCollection(bookid, userid)){
				    	 mv.addObject("collection", "ych.jpg");
				    	 //以收藏是返回链接可取消收藏（1）是取消收藏
				    	 mv.addObject("collectionurl", "user/bookmessage?sc=1");
				     }else{
				    	//未收藏是返回链接可收藏（0）是收藏
				    	 mv.addObject("collectionurl", "user/bookmessage?sc=0");
				    	 mv.addObject("collection", "djsc.jpg");
				 	 
				     }
				     //收藏在此判断
				    if(sc!=null){
				    	if(userid!=null){
					    if("1".equals(sc)){
					    	
					    	Delete.DeleteColection(bookid, userid);
					    	//未收藏是返回链接可收藏（0）是收藏
					    	 mv.addObject("collectionurl", "user/bookmessage?sc=0");
					    	 mv.addObject("collection", "djsc.jpg");
					    	 long count2= Insert.selectCount(bookid);
							    mv.addObject("count",count2);
					    	
					    }
					    if("0".equals(sc)){
					    
					    	Insert.InsertCollection(bookid, userid);
					    	 mv.addObject("collection", "ych.jpg");
					    	 long count3= Insert.selectCount(bookid);
							    mv.addObject("count",count3);
					   
					    	 //以收藏是返回链接可取消收藏（1）是取消收藏
					    	 mv.addObject("collectionurl", "user/bookmessage?sc=1");
					    }
					    }else{
					    	
					    	 mv.addObject("message1", "用户未登录请登录后进行收藏");
					    	
					    	
					    }
				    	
				    	
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
				     
		     
		     
		     
		     
		    
		    
		    
		    
		    
		
		mv.setViewName("user/bookmessage");
		return mv;
	}
	//书籍搜索

	@RequestMapping(value="user/SelectBook")
	public ModelAndView SelectBook(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		  ModelAndView mv=new ModelAndView();//调用菜单
		  
		  ArrayList<BookClass> menulist =MenuList.Booklist();
		  mv.addObject("menulist",menulist);
		  HttpSession session = request.getSession();
		  SessionBoolean.login_redirect(request, mv);
		    
		    //书籍查询结果
		   String key = request.getParameter("key");
		   try{
		   byte[] b = key.getBytes("iso-8859-1");
		   key = new String(b);}catch (Exception e) {
			// TODO: handle exception
		}
		   String sql = "select * from book where book_name  like '%"+key+"%'";
		   String sql1 = "select * from book where book_id  like '%"+key+"%'";
		     ArrayList<Book> by =SelectList.SelectBook(sql);
		     ArrayList<Book> c =SelectList.SelectBook(sql1);
		     c.addAll(by);
		     if(c.isEmpty()){
		    	 mv.addObject("b", "查找失败");}else{
		    	 mv.addObject("selectbook", c);
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
		    
		    
		    
		    
		    
		    
		    
		    
		    mv.setViewName("user/SelectBook");
		    return mv;
		
		
	
	}
	
	
	
}
