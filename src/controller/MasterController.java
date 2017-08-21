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
		
		//管理员主页
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
		
		////添加用户
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
			//产生数据转换异常，如果用string —— int 报错空指针异常 
			//String filepath = request.getParameter("filepath");
			
			//最外层判断输入完整
			if(userid != " " && username != null && password != null && password2 != null  && email != null && !file[0].isEmpty() ){
				//判断密码输入是否一直
				if(!password.equals(password2)){
					mv.addObject("tishi2", "密码输入不一致");
				} 
				//判断插入数据是否存在与数据库，判断主键
				if(!SelectList.SelectUser(userid)){
					mv.addObject("tishi", "用户名重复");	
				 } 
				//判断邮箱是否正确
				if(!Regex.Emaile(email)){
					mv.addObject("tishi3", "邮箱格式错误");
				 }
				
				if( password.equals(password2) && SelectList.SelectUser(userid) && Regex.Emaile(email)){
					
					//数据插入
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
			//最外层输入不完整，返回提示
			else{
				mv.addObject("tishi1", "重新检查保证输入完整");
				mv.setViewName("/master/useradd");
			}
			
			return mv;
			
		}
		//用户数据信息查询方法
		@RequestMapping(value="message")
		public ModelAndView userMessageSelect(HttpServletRequest request) throws SQLException{
			ModelAndView mv = new ModelAndView();
			//判断传入参数是否为空。不为空继续
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
				//查询方式判断
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
							mv.addObject("tishi", "按照类别查找管理员为（0） 普通用户为 （1）");
							mv.setViewName("/master/usermessage");
							return mv;
						}
					sql="select * from ruser where user_class = "+su+"";
					break;
				}
			
				
					
				    List<User> newlist =SelectList.SelectUserList(sql);
				    System.out.println("adsadas");
					if(!newlist.isEmpty()){//如果链表中有确定的值，链表不为空
						mv.addObject("mess", "找到了");
						mv.addObject("text",text);
						if(a<-1){//如果返回上一页小于页号——1，那么返回的为0页
							
							a=0;
							
						}else if(a>(newlist.size()/8)){//如果返回上一页大于页号max，那么返回的为max页
							
							a=(newlist.size()/8);
						}
						mv.addObject("a", a);
						int arr[] =PageGroup.Page(newlist,8);//页号每页8条记录
						for (int i = 0; i < arr.length; i++) {
							System.out.println(arr[i]);
						}
						mv.addObject("arr",arr);
						System.out.println(a);
						List<User>  userlist = PageGroup.PageGroup(newlist,a,8);
						mv.addObject("userlist", userlist);
					
					}else{
						
						mv.addObject("mess2", "米有你要找的信息");
						
					}
				
				
			}else{
				//如果没有信息返回空
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
				//判断是否可以得到传过来的用户id ，如果没有，显示查找，跳转到查找页面
				if(userid==null){
					mv.addObject("emid", "to select ");
					
				}
				//用户id不为空，行数据显示功能
				else  if(userid!=null){
					
					mv.addObject("emid",null);
					mv.addObject("emid1","数据显示");
					List<User>	list=SelectList.SelectUserlist(userid);
					mv.addObject("userlist",list);
					s.setAttribute("up_userid", userid);
				
				}
				//如果编辑不为空，那么显示 
				if(write!=null){
					String uid = s.getAttribute("up_userid").toString();
					List<User>	list=SelectList.SelectUserlist(uid);
					mv.addObject("userlist",list);
					mv.addObject("emid1",null);
					mv.addObject("emid",null);
					mv.addObject("emid2","不用查询");
					
					Delete.UserDelete(uid);
				
				
				}
				mv.setViewName("/master/userupdate");
				return mv;
				
			}
		
			
		
		
}
