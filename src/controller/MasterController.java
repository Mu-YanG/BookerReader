package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modle.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import data.PageGroup;
import data.Regex;

import upload.Upload;

import DB.Delete;
import DB.Insert;
import DB.SelectList;
@Controller
public class MasterController extends MultiActionController{

	/**
	 * @param args
	 */
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
*/
		@Override
		public ModelAndView handleRequest(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			// TODO Auto-generated method stub
			return super.handleRequest(request, response);
		}
		
		//����Ա��ҳ
		@RequestMapping(value="mindex")
		public  ModelAndView index(){
			ModelAndView mv = new ModelAndView();
			mv.setViewName("/master/mindex");
			
			
			
			return mv;
			
		}
	/*	private String id;
		private String name;
		private String password;
		private int sex;
		private Date birthday;
		private String photo;
		private String emai;
		private int   userClass;*/
		
		////����û�
		@RequestMapping(value="master/useradd")
		public  ModelAndView add(@RequestParam("file")CommonsMultipartFile file[],HttpServletRequest request) throws IOException, ParseException, SQLException{
			ModelAndView mv = new ModelAndView();	
			String userid = request.getParameter("userid");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String password2 = request.getParameter("password2");
			
			String sex =request.getParameter("sex");
		
			String year =request.getParameter("year");
			String mouth =request.getParameter("mouth");
			String day =request.getParameter("day");
			String email = request.getParameter("email");
			String userclass = request.getParameter("userclass");
			//��������ת���쳣�������string ���� int �����ָ���쳣 
			//String filepath = request.getParameter("filepath");
			
			//������ж���������
			if(userid != " " && username != null && password != null && password2 != null  && email != null && !file[0].isEmpty() ){
				//�ж����������Ƿ�һֱ
				if(!password.equals(password2)){
					mv.addObject("tishi2", "�������벻һ��");
				} 
				//�жϲ��������Ƿ���������ݿ⣬�ж�����
				if(!SelectList.SelectUser(userid)){
					mv.addObject("tishi", "�û����ظ�");	
				 } 
				//�ж������Ƿ���ȷ
				if(!Regex.Emaile(email)){
					mv.addObject("tishi3", "�����ʽ����");
				 }
				
				if( password.equals(password2) && SelectList.SelectUser(userid) && Regex.Emaile(email)){
					
					//���ݲ���
					Insert.UserInsert(request,userid, username, password2, sex, year, mouth, day, email, userclass, file);
					
					
				}	
				mv.setViewName("/master/mindex");
				/*
					//int sexi = Integer.valueOf(sex); 
					//int userclassi = Integer.valueOf(userclass); 
					String birthday = year+"-"+mouth+"-"+day;
					SimpleDateFormat a = new SimpleDateFormat("yyyy-mm-dd");
					Date date =a.parse(birthday);
					Upload.UpImg(request,file,userid, "photos");
					*/					

			}
			//��������벻������������ʾ
			else{
				mv.addObject("tishi1", "���¼�鱣֤��������");
				mv.setViewName("/master/useradd");
			}
			
			return mv;
			
		}
		//�û�������Ϣ��ѯ����
		@RequestMapping(value="message")
		public ModelAndView userMessageSelect(HttpServletRequest request) throws SQLException{
			ModelAndView mv = new ModelAndView();
			//�жϴ�������Ƿ�Ϊ�ա���Ϊ�ռ���
			String text = request.getParameter("text");
			String search = request.getParameter("search");
			String aa = request.getParameter("a");
			String del= request.getParameter("delete");
			String id= request.getParameter("userid");
			int a =0;
			if("1".equals(del)){
				Delete.UserDelete(id);
				
			}
			if(aa!=null){
				try{
					
					a =Integer.valueOf(aa);
				}catch (Exception e) {
					// TODO: handle exception
				}
				
			}
			int su =0;
			 int intsearch = 0;
			
			if(search!=null){
				mv.addObject("search", search);
				try{
					intsearch = Integer.valueOf(search);}
					catch(Exception e){
						
					}	
				
				String sql="";
				//��ѯ��ʽ�ж�
				switch (intsearch) {
				case 1:
					sql="select * from ruser ";
					break;

				case 2:
					
					sql="select * from ruser where user_id = '"+text+"'";
				
					break;
				case 3:
					try {
						byte b[] =text.getBytes("ISO-8859-1");
						text = new String(b);
					
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					sql="select * from ruser where user_name = '"+text+"'";
					break;
				case 4:
					try{
						  su = Integer.valueOf(text);}
						catch(Exception e){
							mv.addObject("tishi", "���������ҹ���ԱΪ��0�� ��ͨ�û�Ϊ ��1��");
							mv.setViewName("/master/usermessage");
							return mv;
						}
					sql="select * from ruser where user_class = "+su+"";
					break;
				}
			
				
					
				    List<User> newlist =SelectList.SelectUserList(sql);
				    System.out.println("adsadas");
					if(!newlist.isEmpty()){//�����������ȷ����ֵ������Ϊ��
						mv.addObject("mess", "�ҵ���");
						mv.addObject("text",text);
						if(a<-1){//���������һҳС��ҳ�š���1����ô���ص�Ϊ0ҳ
							
							a=0;
							
						}else if(a>(newlist.size()/8)){//���������һҳ����ҳ��max����ô���ص�Ϊmaxҳ
							
							a=(newlist.size()/8);
						}
						mv.addObject("a", a);
						int arr[] =PageGroup.Page(newlist,8);//ҳ��ÿҳ8����¼
						for (int i = 0; i < arr.length; i++) {
							System.out.println(arr[i]);
						}
						mv.addObject("arr",arr);
						System.out.println(a);
						List<User>  userlist = PageGroup.PageGroup(newlist,a,8);
						mv.addObject("userlist", userlist);
					
					}else{
						
						mv.addObject("mess2", "������Ҫ�ҵ���Ϣ");
						
					}
				
				
			}else{
				//���û����Ϣ���ؿ�
				mv.addObject("mess", null);
			}
			
			
			
			mv.setViewName("/master/usermessage");
			return mv;
		}
		
		@RequestMapping("master/userupdate")
			public  ModelAndView UserUpdate(HttpServletRequest request) throws IOException, ParseException, SQLException{
				ModelAndView mv = new ModelAndView();	
				
				String userid = request.getParameter("userid");
				HttpSession s= request.getSession();
				
				String write = request.getParameter("write");
				//�ж��Ƿ���Եõ����������û�id �����û�У���ʾ���ң���ת������ҳ��
				if(userid==null){
					mv.addObject("emid", "to select ");
					
				}
				//�û�id��Ϊ�գ���������ʾ����
				else  if(userid!=null){
					
					mv.addObject("emid",null);
					mv.addObject("emid1","������ʾ");
					List<User>	list=SelectList.SelectUserlist(userid);
					mv.addObject("userlist",list);
					s.setAttribute("up_userid", userid);
				
				}
				//����༭��Ϊ�գ���ô��ʾ 
				if(write!=null){
					String uid = s.getAttribute("up_userid").toString();
					List<User>	list=SelectList.SelectUserlist(uid);
					mv.addObject("userlist",list);
					mv.addObject("emid1",null);
					mv.addObject("emid",null);
					mv.addObject("emid2","���ò�ѯ");
					
					Delete.UserDelete(uid);
				
				
				}
				mv.setViewName("/master/userupdate");
				return mv;
				
			}
		
			
		
		
}
