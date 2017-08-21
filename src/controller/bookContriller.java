package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import modle.Book;
import modle.BookClass;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import data.PageGroup;

import DB.Delete;
import DB.Insert;
import DB.MenuList;
import DB.SelectList;
import DB.updata;

@Controller
public class bookContriller {

	
	 
	//ͼ���ϼ�
	@RequestMapping(value="bookadd")
	public ModelAndView bookadd() throws SQLException{
		ModelAndView mv = new ModelAndView();
		 ArrayList<BookClass> list =MenuList.Booklist();//�������෵�ز˵�
		mv.addObject("bookclasslist",list);
		mv.addObject("sumbmit", "����ϼ�");
		mv.setViewName("master/bookadd");
		return mv;
		
		
		
		
	}
	//ͼ���ϼܣ��ļ��ϴ��������أ�
	@RequestMapping(value="master/bookaddfile")
	public ModelAndView bookadd(@RequestParam("file")CommonsMultipartFile file[],HttpServletRequest request) throws SQLException, IOException{
		ModelAndView mv = new ModelAndView();
		ArrayList<BookClass> list =MenuList.Booklist();//�������෵�ز˵�
		mv.addObject("bookclasslist",list);
		HttpSession session = request.getSession();
		String  userid =(String) session.getAttribute("id");

		String name = request.getParameter("bookname").trim();
		String author = request.getParameter("author");
		String bookclass = request.getParameter("bookclass").trim();
		String cf = request.getParameter("cf").trim();
		System.out.println(userid);
		String bta =request.getParameter("sub");
		System.out.println(bta);
 		//�û���¼�ж�
		System.out.println("baneme:"+name);
		System.out.println(bta);
		//����ĳЩԭ����ֵ��֪����ʲô���˲�����jspҳ�渳ֵ" "
	
		if(userid!=null ){
			if(!" ".equals(name)&& !" ".equals(author)&& !" ".equals(cf) &&!" ".equals(file)){
				
				
		SimpleDateFormat date = new SimpleDateFormat("yyhhmmss");
		Date a = new Date();
		//�������ж��ļ��д���#�ŵĿ��ܱ����������أ���Ҫע�⣬��ʱ����ȣ��Ҿ͸���
		String id = userid+"k"+date.format(a);
		Insert.Insert(request, id, name, author, cf,  bookclass, file);
		System.out.println("plese");
		mv.addObject("message","�ϴ��ɹ������������ϴ�");
		mv.addObject("sumbmit", "�����ϴ�");
			}
			else{
				
				mv.addObject("message","����Ϊ�գ������º˶�");
				mv.addObject("sumbmit", "�����ϴ�");
			}
			
		
		}else{
			
			mv.addObject("message","û�е�¼�޷��ϴ��ļ�");
			mv.addObject("sumbmit", "����ϼ�");
		}
		
		mv.setViewName("master/bookadd");
		return mv;
		
		
		
		
	}
	//�鼮��ѯ
	@RequestMapping(value="master/bookmessage")
	public ModelAndView bookmessage (HttpServletRequest request) throws SQLException{
		ModelAndView mv = new ModelAndView();
		//�жϴ�������Ƿ�Ϊ�ա���Ϊ�ռ���
		String text = request.getParameter("text");
		String search = request.getParameter("search");
		String aa = request.getParameter("a");
		String del= request.getParameter("delete");
		String id= request.getParameter("bookid");
		int a =0;
		System.out.println(id);
		//del����ɾ�������Ϊ1��ô��ɾ��������
		if("1".equals(del)){
			Delete.BookDelete(id);
			
		}
		//d������ҳ��Ϊ�գ�������������ת����string -int
		if(aa!=null){
			try{
				
				a =Integer.valueOf(aa);
			}catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		try {
			//�׳����쳣
			byte b[] =text.getBytes("ISO-8859-1");
			text = new String(b);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//�������
		int su =0;
		 int intsearch = 0;
		//��������������Ͳ�Ϊ�գ�ִ������
		if(search!=null){
			//��ֵ����ҳ�棬����ҳ�Ų�ѯ
			mv.addObject("search", search);
			try{
				intsearch = Integer.valueOf(search);}
				catch(Exception e){
					
				}	
			//��������ֵ����
				List<Book> newlist = null;
			//��ѯ��ʽ�ж�
			
			
			 
			switch (intsearch) {
			//��ѯ����
			case 1:
			String	sql="select * from book ";
				newlist = SelectList.SelectBook(sql);
				break;
				//������Ų�ѯ
			case 2:
				
				newlist =SelectList.SelectId(text);
				break;
				//����������ѯ
			case 3:
				
				newlist = SelectList.SelectName(text);
				break;
			//����������ѯ
			case 4:
				newlist = SelectList.SelectClass(text);
				break;
			case 5:
				newlist = SelectList.SelectBookauthor(text);
				break;
			}
		
				if(!newlist.isEmpty()){//�����������ȷ����ֵ������Ϊ��
					mv.addObject("mess", "�ҵ���");
					mv.addObject("text",text);
					if(a<-1){//���������һҳС��ҳ�š���1����ô���ص�Ϊ0ҳ
						
						a=0;
						
					}else if(a>(newlist.size()/8)){//���������һҳ����ҳ��max����ô���ص�Ϊmaxҳ
						
						a=(newlist.size()/8);
					}
					mv.addObject("a", a);
					int arr[] =PageGroup.page(newlist,8);//ҳ��ÿҳ8����¼
					for (int i = 0; i < arr.length; i++) {
						System.out.println(arr[i]);
					}
					mv.addObject("arr",arr);
					System.out.println(a);
					List<Book>  booklist = PageGroup.Pagegroup(newlist,a,8);
				
					mv.addObject("booklist", booklist);
				
				}else{
					
					mv.addObject("mess2", "������Ҫ�ҵ���Ϣ");
					
				}
			
			
		}else{
			//���û����Ϣ���ؿ�
			mv.addObject("mess", null);
		}
		
		
		
		
		
		mv.setViewName("master/bookmessage");
		return mv;
	}
	/**�鼮�޸�ps���ڱ���������Ƚ��������Բ�����ɾ��ԭ�������ϴ���������������߼�ѧ����Ϊ���ã��ڴ˿�����ȡ��ѵ����ʱ��ȥ��д��ϣ��������õĽ��
	*
	*��һ�������ݻ�ȡ
	*
	*/
	@RequestMapping(value="master/bookupdate")
	public ModelAndView Bookshow(HttpServletRequest request) throws SQLException{
		
		ModelAndView mv = new ModelAndView();
		String bookid = request.getParameter("bookid");
		String sub = request.getParameter("sub");
	
		//�ж�id�Ƿ�Ϊ�գ��մ��ز�ѯ����ʾ�ֶ�
		if(bookid==null || "".equals(bookid)){
			mv.addObject("bookidboolean", "bookid no empty");
			
		}else {
			HttpSession session = request.getSession();
			System.out.println(bookid);
			session.setAttribute("bookid", bookid);
			ArrayList<Book> booklist =SelectList.SelectId(bookid);
			mv.addObject("booklist", booklist);
			mv.addObject("bookidboolean", null);
			mv.addObject("bookshow", "�ɽ���������ʾ");
		
		}
		try {
			if(sub!=null){
			byte b[]= sub.getBytes("iso-8859-1");
			sub = new String(b);}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if("�༭".equals(sub) ){
		
			mv.addObject("bookidboolean", null);
			mv.addObject("bookshow", null);
			mv.addObject("bookupdate", "�ɽ��������޸�");
			HttpSession session = request.getSession();
			ArrayList<Book> booklist =SelectList.SelectId((String)session.getAttribute("bookid"));
			System.out.println((String)session.getAttribute("bookid"));
			mv.addObject("booklist", booklist);
	        ArrayList<BookClass>  bookclasslist =	MenuList.Booklist();
			mv.addObject("bookclasslist",bookclasslist);
			
		}
		
		
		
		
		return mv;
		
		
		
		
	}
	//--------------------��һ���ֽ���--------------------//
	/**
	 * �ڶ����֡���ʹ��ȡ�����ϴ����������������鼮�޸�
	 * @throws Exception 
	 * */
	@RequestMapping(value="master/bookupdatefile")
	public ModelAndView Bookupdate(@RequestParam("file")CommonsMultipartFile file[],HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		String bookid =(String)session.getAttribute("bookid");
		String name = request.getParameter("bookname");
		String author = request.getParameter("author");
		String bookclass = request.getParameter("bookclass");
		String cf = request.getParameter("cf");
		String bta =request.getParameter("sub");
		//���ļ��ж��Ƿ��ļ������Ƿ�Ϊ��
		if(!file[0].isEmpty() && !file[1].isEmpty()){
			
			//�����Ϊ�գ����������޸�
			
			updata.BookUpdate(request, bookid, name, author, bookclass, cf, file);
			
			mv.setViewName("redirect:bookmessage?search=2&text="+bookid);  
			
			
			
			
			
		   
	   }else{	
		   	mv.addObject("message","Ϊ����ϴ��ļ�" );
		   	mv.setViewName("master/bookupdate");
			mv.addObject("bookupdate", "�ɽ��������޸�");
			
			ArrayList<Book> booklist =SelectList.SelectId((String)session.getAttribute("bookid"));
			System.out.println((String)session.getAttribute("bookid"));
			mv.addObject("booklist", booklist);
	        ArrayList<BookClass>  bookclasslist =	MenuList.Booklist();
			mv.addObject("bookclasslist",bookclasslist);
	   }
		
		
		
		return mv;
	}
	
	
}




