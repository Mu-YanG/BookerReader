package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import modle.BookClass;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import DB.Delete;
import DB.Insert;
import DB.MenuList;
import DB.updata;

@Controller
public class BookClassContriller {
	
	private int id = 0;

	@RequestMapping(value="master/bookclass")
	public ModelAndView Bookclass(HttpServletRequest request) throws SQLException{
		int num=0;
		ModelAndView mv = new ModelAndView();
		ArrayList<BookClass>  list	=MenuList.Booklist();
		mv.addObject("bookclass",list);
		
		String classid = request.getParameter("classid");
		String classname = request.getParameter("classname");
		String sub = request.getParameter("sub");
	
		if(sub!=null && !"".equals(sub)){
			 num =	list.get(list.size()-1).getBook_class_id();
			try {
				byte[] b;
				b = sub.getBytes("iso-8859-1");
				sub = new String(b);
				
					
				
				
			} catch ( Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//���
			if("add".equals(sub)){
				mv.addObject("add", "tianja");
			
			
				mv.addObject("classid", num+=1);
			}else{
				mv.addObject("add", null);
				
			}
			//��ӵı���
			if("����".equals(sub)){
				try {
						byte[] b;
						b = classname.getBytes("iso-8859-1");
						classname = new String(b);
						
							
						
						
					} catch ( Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				Insert.InsertBookClass(classname, num+1);
				ArrayList<BookClass>  list2	=MenuList.Booklist();
				mv.addObject("bookclass",list2);
			}
			//ɾ��
			if("dd".equals(sub)){
				id =Integer.parseInt(classid); 
				Delete.DeleteBookClass(id);
				ArrayList<BookClass>  list2	=MenuList.Booklist();
				mv.addObject("bookclass",list2);
			}
			//�޸�
			if("xx".equals(sub)){
				try {
					byte[] b;
					b = classname.getBytes("iso-8859-1");
					classname = new String(b);
					
						
					
					
				} catch ( Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				id =Integer.parseInt(classid);
				mv.addObject("classid", id);
				mv.addObject("classname", classname);
				mv.addObject("xxx","qweqweqw");
			
				
			}//�޸ĵı���
			if("ȷ���޸�".equals(sub)){
				try {
					byte[] b;
					b = classname.getBytes("iso-8859-1");
					classname = new String(b);
					
						
					
					
				} catch ( Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
				
				
				mv.addObject("xxx",null);
	
				updata.Bookclassupdate(id, classname);
				ArrayList<BookClass>  list2	=MenuList.Booklist();
				mv.addObject("bookclass",list2);
				
			}
		}
		mv.setViewName("master/bookClass");
		return mv ;
	}

}
