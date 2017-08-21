package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
@Controller
public class SessionBoolean extends MultiActionController {
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return super.handleRequest(request, response);
	}
	
     public static  boolean  Seionboolean(HttpServletRequest request){
		HttpSession session = request.getSession();
		String s =(String)session.getAttribute("id");
		if(s==null){
			return true;           //���δ��¼����ture
		}
		return false;//�ѵ�¼
		
     }
  
	/**
	 * �����жϵ�¼��ע���Ĺ�ϵ
	 * 
	 * 
	 * */
	public static  void  login_redirect(HttpServletRequest request,ModelAndView mv){
		 if(SessionBoolean.Seionboolean(request)){
		    	
		    	mv.addObject("message", "��¼");
		    	mv.addObject("tishi","login/");
		    }else{
		    	
		    	mv.addObject("zhuxiao","ע��");
		    	mv.addObject("zhuxiaolink","sessionzhuxiao");
		    	HttpSession s =request.getSession();
		    	Object ss =s.getAttribute("userclass");
		    System.out.println(ss);
		    	mv.addObject("message", s.getAttribute("username"));
		    	if("0".equals(ss.toString())){
		    		mv.addObject("tishi","mindex/");
		    	
		    	
		    	}else {
		    		mv.addObject("tishi","usermessage/");
		    	
		    		
		    		
		    	}
		    	
		    }
		
		
		
	}
	@RequestMapping(value="sessionzhuxiao")
	public ModelAndView zhuxiao(HttpServletRequest request,HttpServletResponse response) {
		
		HttpSession s =request.getSession();
		s.setAttribute("id", null);
		System.out.println(s.getAttribute("id"));
		return  new ModelAndView("redirect:/index");
	
	}
	
	
}
