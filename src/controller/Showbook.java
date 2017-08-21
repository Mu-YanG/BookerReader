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
		  ModelAndView mv=new ModelAndView();//���ò˵�
		
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
		    
		    //�鼮չʾ��ҳ
		    String zzz= c.trim();
		    ArrayList<Book> list =bookClassList.BookClassSelectAllShow(zzz);
		    ArrayList<Book> list2 = new ArrayList<>();
		   
		    if (num<0) {
				num=0;
			}
		    //����
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
		    		//��ʼ��
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
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    mv.setViewName("user/bookshow");
		    return mv;
		
		
	
	}
	/**
	 * ͼ����ʾ
	 * */
	@RequestMapping(value="user/bookmessage")
	public ModelAndView BookMessage(HttpServletRequest request) throws SQLException, ParseException{
		String sc = request.getParameter("sc");
		String userid =(String) request.getSession().getAttribute("id");
		String bookid = request.getParameter("bookid");
		TalkServcieImpl impl = new TalkServcieImpl();
		
		
		
		ModelAndView mv = new ModelAndView();
		//Ŀ¼����-------------
		     ArrayList<BookClass> menulist =MenuList.Booklist();
		     SessionBoolean.login_redirect(request, mv);
		     mv.addObject("menulist",menulist);
		 //�û���¼�ж�    
		     List<Talk> talkList = impl.getTalkList(bookid);
		     
		     mv.addObject("talkList",talkList);
		     if(bookid!=null){
				     request.getSession().setAttribute("bookmessageid", bookid);
		     }   
				     bookid =(String) request.getSession().getAttribute("bookmessageid");
				     ArrayList<Book>  bookshow = SelectList.SelectId(bookid);
				   
				     mv.addObject("bookshow",bookshow);
				     mv.addObject("bookid2",bookid);
				   
				     //�ղش�������ֵ
				    long count= Insert.selectCount(bookid);
				    mv.addObject("count",count);
				  //�ղ��ж�-----����
				    
				    
				    if(Insert.selectCollection(bookid, userid)){
				    	 mv.addObject("collection", "ych.jpg");
				    	 //���ղ��Ƿ������ӿ�ȡ���ղأ�1����ȡ���ղ�
				    	 mv.addObject("collectionurl", "user/bookmessage?sc=1");
				     }else{
				    	//δ�ղ��Ƿ������ӿ��ղأ�0�����ղ�
				    	 mv.addObject("collectionurl", "user/bookmessage?sc=0");
				    	 mv.addObject("collection", "djsc.jpg");
				 	 
				     }
				     //�ղ��ڴ��ж�
				    if(sc!=null){
				    	if(userid!=null){
					    if("1".equals(sc)){
					    	
					    	Delete.DeleteColection(bookid, userid);
					    	//δ�ղ��Ƿ������ӿ��ղأ�0�����ղ�
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
					   
					    	 //���ղ��Ƿ������ӿ�ȡ���ղأ�1����ȡ���ղ�
					    	 mv.addObject("collectionurl", "user/bookmessage?sc=1");
					    }
					    }else{
					    	
					    	 mv.addObject("message1", "�û�δ��¼���¼������ղ�");
					    	
					    	
					    }
				    	
				    	
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
				     
		     
		     
		     
		     
		    
		    
		    
		    
		    
		
		mv.setViewName("user/bookmessage");
		return mv;
	}
	//�鼮����

	@RequestMapping(value="user/SelectBook")
	public ModelAndView SelectBook(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		  ModelAndView mv=new ModelAndView();//���ò˵�
		  
		  ArrayList<BookClass> menulist =MenuList.Booklist();
		  mv.addObject("menulist",menulist);
		  HttpSession session = request.getSession();
		  SessionBoolean.login_redirect(request, mv);
		    
		    //�鼮��ѯ���
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
		    	 mv.addObject("b", "����ʧ��");}else{
		    	 mv.addObject("selectbook", c);
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
		    
		    
		    
		    
		    
		    
		    
		    
		    mv.setViewName("user/SelectBook");
		    return mv;
		
		
	
	}
	
	
	
}
