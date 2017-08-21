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
		//Ŀ¼��ȡ
		ArrayList<BookClass> menulist =MenuList.Booklist();
	     SessionBoolean.login_redirect(request, mv);
	     mv.addObject("menulist",menulist);
	     String bookid = request.getParameter("bookid");
	    //��ȡ�ļ�
	     String path = request.getRealPath("//bookfiles//"+bookid+"//"+bookid+".txt");
		File file = new File(path);
		 //���ļ�
		
		BufferedReader r = new BufferedReader(new FileReader(file));
		String line=null ;
		//���ж��ļ�

		//�ַ�ƥ��
		String regex= "\\��+[\u4e00-\u9fa5]+\\��+\\s+";
		String regex2= "\\��+[\u4e00-\u9fa5]+\\��+\\s+[\u4e00-\u9fa5]+";
		String regex3= "\\��+[0-9]+\\��+[\u4e00-\u9fa5]+";
		String regex4= "\\��+[\u4e00-\u9fa5]+\\��+\\s+";
		String regex5= "\\��+[\u4e00-\u9fa5]+\\��+\\s+[\u4e00-\u9fa5]+";
		String regex6= "\\��+[0-9]+\\��+[\u4e00-\u9fa5]+";
		String regex7= "\\����+";
		
		//Ŀ¼����
		ArrayList<Mulu> listmulu = new ArrayList<Mulu>();
		//��������
		
		 String textid = request.getParameter("textid");
		 String text = request.getParameter("text");
		 int a = 0;
		
		
			//����һ
			/*while((line=r.readLine())!=null){
					//Ϊ�˽���Ŀ¼��ʾ��û4����ʾһ�Σ��˲������html�ı�������������
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
			//������
		 	StringBuilder bf= new StringBuilder();
		 	Map<String, StringBuilder> map = new HashMap<String, StringBuilder>();
				while((line=r.readLine())!=null){
					//������ΪĿ¼�ǽ���Ŀ¼
					if(line.matches(regex2)||line.matches(regex)||line.matches(regex3)||line.matches(regex4)||line.matches(regex5)||line.matches(regex6)||line.matches(regex7)){
						a++;
						Mulu m = new Mulu();
						m.setText(line);
						m.setArr(a);
						//Ŀ¼������
						listmulu.add(m);
						//���ݵ�bf
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
							//�������ֵΪС���½ںžͰ��½ںű�ɶ����ڴ˲�ѯ�ͷ��ص�һҳ
							if(cc-1<0){
								cc=2;
							}
							//ͬ�������½ںţ�������һҳ
							if(cc+1>listmulu.size()){
								cc=listmulu.size();
								mv.addObject("timu","ȫ����");
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


