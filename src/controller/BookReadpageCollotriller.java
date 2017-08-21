package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modle.BookClass;
import modle.Mulu;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.omg.CORBA_2_3.portable.InputStream;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



import DB.MenuList;

@Controller
public class BookReadpageCollotriller {



	@RequestMapping(value="user/bookReadPage")
	public ModelAndView BookReadPage(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException{
		
		ModelAndView mv = new ModelAndView();
		//目录获取
		ArrayList<BookClass> menulist =MenuList.Booklist();
	     SessionBoolean.login_redirect(request, mv);
	     mv.addObject("menulist",menulist);
	     String bookid = request.getParameter("bookid");
	    //获取文件
	     String path = request.getRealPath("//bookfiles//"+bookid+"//"+bookid+".txt");
		File file = new File(path);
		 //读文件
		
		BufferedReader r = new BufferedReader(new FileReader(file));
		String line=null ;
		//安行读文件

		//字符匹配
		String regex= "\\第+[\u4e00-\u9fa5]+\\章+\\s+";
		String regex2= "\\第+[\u4e00-\u9fa5]+\\章+\\s+[\u4e00-\u9fa5]+";
		String regex3= "\\第+[0-9]+\\章+[\u4e00-\u9fa5]+";
		String regex4= "\\第+[\u4e00-\u9fa5]+\\部+\\s+";
		String regex5= "\\第+[\u4e00-\u9fa5]+\\部+\\s+[\u4e00-\u9fa5]+";
		String regex6= "\\第+[0-9]+\\部+[\u4e00-\u9fa5]+";
		String regex7= "\\契子+";
		
		//目录链表
		ArrayList<Mulu> listmulu = new ArrayList<Mulu>();
		//内容链表
		
		 String textid = request.getParameter("textid");
		 String text = request.getParameter("text");
		 int a = 0;
		
		
			//方法一
			/*while((line=r.readLine())!=null){
					//为了进行目录显示，没4个显示一次，顾采用输出html文本，这样做不好
					if(line.matches(regex2)||line.matches(regex)||line.matches(regex3)){
						listmulu.add("<td>");
						a++;
						listmulu.add(line);
						listmulu.add("</td>");
						if(a%4==0){
							listmulu.add("</tr>");
							
						}
						
					}
					listnerong.add(line);
					
				}*/
			//方法二
		 	StringBuilder bf= new StringBuilder();
		 	Map<String, StringBuilder> map = new HashMap<String, StringBuilder>();
				while((line=r.readLine())!=null){
					//当发现为目录是进入目录
					if(line.matches(regex2)||line.matches(regex)||line.matches(regex3)||line.matches(regex4)||line.matches(regex5)||line.matches(regex6)||line.matches(regex7)){
						a++;
						Mulu m = new Mulu();
						m.setText(line);
						m.setArr(a);
						//目录的链表
						listmulu.add(m);
						//内容的bf
						bf= new StringBuilder();
					}else{
						bf.append(line+"<br>");
					}
					Integer aa = a;
					map.put(aa.toString(), bf);
				}
				r.close();
			mv.addObject("mulu","mulu");
			
					if(textid!=null ){
						
							int cc = 0;
							StringBuilder b=map.get(textid);
							mv.addObject("nei",b);
							
							try{
								
								
								 cc =Integer.parseInt(textid); 
								 
							}catch(Exception e){
								
								
								
							}
						
							for (int  i = 0;  i < listmulu.size(); i++) {
								if(cc==listmulu.get(i).getArr()){
									text = listmulu.get(i).getText(); 
								
								}
								
								
							}
							mv.addObject("timu",text);
							//如果返回值为小于章节号就把章节号变成二，在此查询就返回第一页
							if(cc-1<0){
								cc=2;
							}
							//同理，大于章节号，最回最后一页
							if(cc+1>listmulu.size()){
								cc=listmulu.size();
								mv.addObject("timu","全书完");
							}

							mv.addObject("ar", cc);
							
							
							mv.addObject("mulu",null);
					}

				
			
		
			
			
			
		
		
		
		
		mv.addObject("bookid",bookid);
		mv.addObject("listmulu",listmulu);
		//mv.addObject("bf",bf);
		mv.setViewName("user/bookReadPage");
		return mv;
	}
	
}


