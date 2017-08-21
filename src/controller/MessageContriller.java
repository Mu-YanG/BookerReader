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
 * 消息
 * 
 * */
@Controller
public class MessageContriller {

	/**
	 * 获取所有消息
	 * @throws UnsupportedEncodingException 
	 * */
	@RequestMapping(value="user/getMessageForJson")
	@ResponseBody
	public String getJsonForMessage() throws UnsupportedEncodingException{
		MessageServiceImpl impl = new MessageServiceImpl();
		ArrayList<Message> messages = impl.getMessages(0);
		Gson gson = new Gson();
		String json = gson.toJson(messages);
		//返回列表（gbk解码），公用编码
		return new String(json.getBytes("gbk"),"ISO-8859-1");
	}
	/**
	 * 获取所有消息
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
	 * 添加消息
	 * */
	@RequestMapping("master/insertMessage")
	public String insertJsonForMessage(Message message,HttpSession httpSession,Map<String, Object> map){
		MessageServiceImpl impl = new MessageServiceImpl();

		message.setUser((String)httpSession.getAttribute("username"));
		impl.insertMessage(message);
		return "redirect:/user/getMessagelist";
	}
	/**
	 * 删除
	 * */
	@RequestMapping("master/deketeMessage")
	public String deleteJsonForMessage(Integer messageId,HttpSession httpSession,Map<String, Object> map){
		MessageServiceImpl impl = new MessageServiceImpl();
		impl.deleteMessage(messageId);
		return "redirect:/user/getMessagelist";
	}
	
	/**
	 * 发布消息/取消发布
	 * */
	@RequestMapping("master/exchangeMessage")
	public String showMessage(Integer messageId,Integer status){
		MessageServiceImpl impl = new MessageServiceImpl();
		impl.showMessage(messageId,status);
		return "redirect:/user/getMessagelist";
	}
	/**
	 * 前台获取消息
	 * @throws SQLException 
	 * */
	@RequestMapping(value="user/{id}")
	public ModelAndView getOneMessageById(@PathVariable("id")Integer messageId,HttpServletRequest request) throws SQLException{
		ModelAndView mv = new ModelAndView("/user/showMessage");
		MessageServiceImpl impl = new MessageServiceImpl();
		Message messageById = impl.getMessageById(messageId.toString());
		mv.addObject("ms", messageById);
		
		 ArrayList<BookClass> menulist =MenuList.Booklist();//根据书类返回菜单
		  mv.addObject("menulist",menulist);
		   HttpSession s = request.getSession();
		    s.setAttribute("booklist", menulist);
		    
	    SessionBoolean.login_redirect(request, mv);//判定用户是否登录。如登录判断管理员还是普通用户，分别跳转的哦啊不同界面
	 
	//分类书籍列表----------------------------
	    
	    /**
	     * <div class="right_box">
       <div class="title"><span class="title_icon"><img src="images/bullet5.gif" alt="" title="" /></span>${m.book_class_name}</div>
       <ul class="list">
         <li><a href="#">accesories</a></li>
       </ul>

     </div>
	     * */
	    //NEW 推荐链表
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
	    		book.setBookName("书籍未上架敬请期待");
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
	    //随机生成链表
	     SelectList.SelectBook("select * from book ");
	    
	    
	    
	    
	    
	    
	    
	    
		    
		return mv;
	}
	/**
	 * 修改消息
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
