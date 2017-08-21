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

	
	 
	//图书上架
	@RequestMapping(value="bookadd")
	public ModelAndView bookadd() throws SQLException{
		ModelAndView mv = new ModelAndView();
		 ArrayList<BookClass> list =MenuList.Booklist();//根据书类返回菜单
		mv.addObject("bookclasslist",list);
		mv.addObject("sumbmit", "点击上架");
		mv.setViewName("master/bookadd");
		return mv;
		
		
		
		
	}
	//图书上架（文件上传方法重载）
	@RequestMapping(value="master/bookaddfile")
	public ModelAndView bookadd(@RequestParam("file")CommonsMultipartFile file[],HttpServletRequest request) throws SQLException, IOException{
		ModelAndView mv = new ModelAndView();
		ArrayList<BookClass> list =MenuList.Booklist();//根据书类返回菜单
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
 		//用户登录判断
		System.out.println("baneme:"+name);
		System.out.println(bta);
		//由于某些原因传入值不知道是什么，顾采用在jsp页面赋值" "
	
		if(userid!=null ){
			if(!" ".equals(name)&& !" ".equals(author)&& !" ".equals(cf) &&!" ".equals(file)){
				
				
		SimpleDateFormat date = new SimpleDateFormat("yyhhmmss");
		Date a = new Date();
		//经本人判断文件中带有#号的可能被拦截器拦截，需要注意，因时间紧迫，我就改了
		String id = userid+"k"+date.format(a);
		Insert.Insert(request, id, name, author, cf,  bookclass, file);
		System.out.println("plese");
		mv.addObject("message","上传成功，请点击继续上传");
		mv.addObject("sumbmit", "继续上传");
			}
			else{
				
				mv.addObject("message","传入为空，请重新核对");
				mv.addObject("sumbmit", "重新上传");
			}
			
		
		}else{
			
			mv.addObject("message","没有登录无法上传文件");
			mv.addObject("sumbmit", "点击上架");
		}
		
		mv.setViewName("master/bookadd");
		return mv;
		
		
		
		
	}
	//书籍查询
	@RequestMapping(value="master/bookmessage")
	public ModelAndView bookmessage (HttpServletRequest request) throws SQLException{
		ModelAndView mv = new ModelAndView();
		//判断传入参数是否为空。不为空继续
		String text = request.getParameter("text");
		String search = request.getParameter("search");
		String aa = request.getParameter("a");
		String del= request.getParameter("delete");
		String id= request.getParameter("bookid");
		int a =0;
		System.out.println(id);
		//del传入删除，如果为1那么就删除该数据
		if("1".equals(del)){
			Delete.BookDelete(id);
			
		}
		//d当传入页吗不为空，进行数据类型转换，string -int
		if(aa!=null){
			try{
				
				a =Integer.valueOf(aa);
			}catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		try {
			//抛出空异常
			byte b[] =text.getBytes("ISO-8859-1");
			text = new String(b);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//定义变量
		int su =0;
		 int intsearch = 0;
		//如果传入搜索类型不为空，执行搜索
		if(search!=null){
			//把值带回页面，方便页号查询
			mv.addObject("search", search);
			try{
				intsearch = Integer.valueOf(search);}
				catch(Exception e){
					
				}	
			//创建返回值链表
				List<Book> newlist = null;
			//查询方式判断
			
			
			 
			switch (intsearch) {
			//查询所有
			case 1:
			String	sql="select * from book ";
				newlist = SelectList.SelectBook(sql);
				break;
				//按照书号查询
			case 2:
				
				newlist =SelectList.SelectId(text);
				break;
				//按照书名查询
			case 3:
				
				newlist = SelectList.SelectName(text);
				break;
			//按照书分类查询
			case 4:
				newlist = SelectList.SelectClass(text);
				break;
			case 5:
				newlist = SelectList.SelectBookauthor(text);
				break;
			}
		
				if(!newlist.isEmpty()){//如果链表中有确定的值，链表不为空
					mv.addObject("mess", "找到了");
					mv.addObject("text",text);
					if(a<-1){//如果返回上一页小于页号——1，那么返回的为0页
						
						a=0;
						
					}else if(a>(newlist.size()/8)){//如果返回上一页大于页号max，那么返回的为max页
						
						a=(newlist.size()/8);
					}
					mv.addObject("a", a);
					int arr[] =PageGroup.page(newlist,8);//页号每页8条记录
					for (int i = 0; i < arr.length; i++) {
						System.out.println(arr[i]);
					}
					mv.addObject("arr",arr);
					System.out.println(a);
					List<Book>  booklist = PageGroup.Pagegroup(newlist,a,8);
				
					mv.addObject("booklist", booklist);
				
				}else{
					
					mv.addObject("mess2", "米有你要找的信息");
					
				}
			
			
		}else{
			//如果没有信息返回空
			mv.addObject("mess", null);
		}
		
		
		
		
		
		mv.setViewName("master/bookmessage");
		return mv;
	}
	/**书籍修改ps由于本人在上面比较懒，所以采用在删除原数据在上传，这个反人类与逻辑学的行为不好，在此刻我吸取教训，花时间去重写，希望会产生好的结果
	*
	*第一步分数据获取
	*
	*/
	@RequestMapping(value="master/bookupdate")
	public ModelAndView Bookshow(HttpServletRequest request) throws SQLException{
		
		ModelAndView mv = new ModelAndView();
		String bookid = request.getParameter("bookid");
		String sub = request.getParameter("sub");
	
		//判断id是否为空，空传回查询表单显示字段
		if(bookid==null || "".equals(bookid)){
			mv.addObject("bookidboolean", "bookid no empty");
			
		}else {
			HttpSession session = request.getSession();
			System.out.println(bookid);
			session.setAttribute("bookid", bookid);
			ArrayList<Book> booklist =SelectList.SelectId(bookid);
			mv.addObject("booklist", booklist);
			mv.addObject("bookidboolean", null);
			mv.addObject("bookshow", "可进行数据显示");
		
		}
		try {
			if(sub!=null){
			byte b[]= sub.getBytes("iso-8859-1");
			sub = new String(b);}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if("编辑".equals(sub) ){
		
			mv.addObject("bookidboolean", null);
			mv.addObject("bookshow", null);
			mv.addObject("bookupdate", "可进行数据修改");
			HttpSession session = request.getSession();
			ArrayList<Book> booklist =SelectList.SelectId((String)session.getAttribute("bookid"));
			System.out.println((String)session.getAttribute("bookid"));
			mv.addObject("booklist", booklist);
	        ArrayList<BookClass>  bookclasslist =	MenuList.Booklist();
			mv.addObject("bookclasslist",bookclasslist);
			
		}
		
		
		
		
		return mv;
		
		
		
		
	}
	//--------------------第一部分结束--------------------//
	/**
	 * 第二部分。促使获取内容上传到服务器，进行书籍修改
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
		//用文件判断是否文件内容是否为空
		if(!file[0].isEmpty() && !file[1].isEmpty()){
			
			//如果不为空，进行数据修改
			
			updata.BookUpdate(request, bookid, name, author, bookclass, cf, file);
			
			mv.setViewName("redirect:bookmessage?search=2&text="+bookid);  
			
			
			
			
			
		   
	   }else{	
		   	mv.addObject("message","为添加上传文件" );
		   	mv.setViewName("master/bookupdate");
			mv.addObject("bookupdate", "可进行数据修改");
			
			ArrayList<Book> booklist =SelectList.SelectId((String)session.getAttribute("bookid"));
			System.out.println((String)session.getAttribute("bookid"));
			mv.addObject("booklist", booklist);
	        ArrayList<BookClass>  bookclasslist =	MenuList.Booklist();
			mv.addObject("bookclasslist",bookclasslist);
	   }
		
		
		
		return mv;
	}
	
	
}




