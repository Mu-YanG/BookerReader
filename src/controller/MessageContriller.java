package controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import modle.Book;
import modle.BookClass;
import modle.Message;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import DB.MenuList;
import DB.MessageServiceImpl;
import DB.SelectList;
/**
 * ��Ϣ
 * 
 * */
@Controller
public class MessageContriller {

	/**
	 * ��ȡ������Ϣ
	 * @throws UnsupportedEncodingException 
	 * */
	@RequestMapping(value="user/getMessageForJson")
	@ResponseBody
	public String getJsonForMessage() throws UnsupportedEncodingException{
		MessageServiceImpl impl = new MessageServiceImpl();
		ArrayList<Message> messages = impl.getMessages(0);
		Gson gson = new Gson();
		String json = gson.toJson(messages);
		//�����б�gbk���룩�����ñ���
		return new String(json.getBytes("gbk"),"ISO-8859-1");
	}
	/**
	 * ��ȡ������Ϣ
	 * */
	@RequestMapping("user/getMessagelist")
	public String getMessage(Message message,HttpSession httpSession,Map<String, Object> map){
		MessageServiceImpl impl = new MessageServiceImpl();
		ArrayList<Message> messages=null;
		if (message.getMessageStatus()==null || "10".equals(message.getMessageStatus())) {
			 messages = impl.getMessages(0);
			 messages.addAll(impl.getMessages(1));
		}else{
			messages = impl.getMessages(Integer.parseInt(message.getMessageStatus()));
		}
		map.put("messagelist", messages);
		return "/master/message";
	}
	/**
	 * �����Ϣ
	 * */
	@RequestMapping("master/insertMessage")
	public String insertJsonForMessage(Message message,HttpSession httpSession,Map<String, Object> map){
		MessageServiceImpl impl = new MessageServiceImpl();

		message.setUser((String)httpSession.getAttribute("username"));
		impl.insertMessage(message);
		return "redirect:/user/getMessagelist";
	}
	/**
	 * ɾ��
	 * */
	@RequestMapping("master/deketeMessage")
	public String deleteJsonForMessage(Integer messageId,HttpSession httpSession,Map<String, Object> map){
		MessageServiceImpl impl = new MessageServiceImpl();
		impl.deleteMessage(messageId);
		return "redirect:/user/getMessagelist";
	}
	
	/**
	 * ������Ϣ/ȡ������
	 * */
	@RequestMapping("master/exchangeMessage")
	public String showMessage(Integer messageId,Integer status){
		MessageServiceImpl impl = new MessageServiceImpl();
		impl.showMessage(messageId,status);
		return "redirect:/user/getMessagelist";
	}
	/**
	 * ǰ̨��ȡ��Ϣ
	 * @throws SQLException 
	 * */
	@RequestMapping(value="user/{id}")
	public ModelAndView getOneMessageById(@PathVariable("id")Integer messageId,HttpServletRequest request) throws SQLException{
		ModelAndView mv = new ModelAndView("/user/showMessage");
		MessageServiceImpl impl = new MessageServiceImpl();
		Message messageById = impl.getMessageById(messageId.toString());
		mv.addObject("ms", messageById);
		
		 ArrayList<BookClass> menulist =MenuList.Booklist();//�������෵�ز˵�
		  mv.addObject("menulist",menulist);
		   HttpSession s = request.getSession();
		    s.setAttribute("booklist", menulist);
		    
	    SessionBoolean.login_redirect(request, mv);//�ж��û��Ƿ��¼�����¼�жϹ���Ա������ͨ�û����ֱ���ת��Ŷ����ͬ����
	 
	//�����鼮�б�----------------------------
	    
	    /**
	     * <div class="right_box">
       <div class="title"><span class="title_icon"><img src="images/bullet5.gif" alt="" title="" /></span>${m.book_class_name}</div>
       <ul class="list">
         <li><a href="#">accesories</a></li>
       </ul>

     </div>
	     * */
	    //NEW �Ƽ�����
	    ArrayList<Book> tjlist = new ArrayList<Book>();
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < menulist.size(); i++) {
			sb.append(" <div class=\"right_box\"> <div class=\"title\"><span class=\"title_icon\"><img src=\"images/bullet5.gif\"  /></span>"+menulist.get(i).getBook_class_name()+"</div><ul class=\"list\">");
	    	ArrayList<Book> b = SelectList.SelectClass(menulist.get(i).getBook_class_name());
	    	if(b.size()>0){
		    	int num = (int) (Math.random()*b.size());
		    	System.out.println(num);
		    	tjlist.add(b.get(num));
		    	}else{}
		    
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
	    mv.addObject("tjlist", tjlist);
	 //listclass--------------------------------------------------------------------------
	    //�����������
	     SelectList.SelectBook("select * from book ");
	    
	    
	    
	    
	    
	    
	    
	    
		    
		return mv;
	}
	/**
	 * �޸���Ϣ
	 * */
	@RequestMapping("master/updateMessage")
	public String updateMessage(Message message,Map<String, Object> map){
		MessageServiceImpl impl = new MessageServiceImpl();
		if (message.getMessage()==null) {
			message =impl.getMessageById(message.getMessageId());
			map.put("m", message);
			return "master/messageadd";
		}else{
			
			impl.deleteMessage(Integer.parseInt(message.getMessageId()));
			impl.insertMessage(message);
		}
		
		return "redirect:/user/getMessagelist";
	
	}
}
