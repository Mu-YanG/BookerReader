package controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import modle.Talk;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import DB.TalkServcieImpl;
/**
 * @author lvyang
 * 评论消息的controllear
 * 
 * */
@Controller
public class TalkControllear {
	/**
	 * 用户插入评论的handle
	 * @return 
	 * @throws UnsupportedEncodingException 
	 * 
	 * */
	@RequestMapping("user/inTalk")
	public String insertTalk(@RequestParam("bookId")String bookId,@RequestParam("message")String message,HttpServletRequest request) throws UnsupportedEncodingException{
		Talk talk = new Talk();
		talk.setBookId(bookId);
		talk.setMessage(new String(message.getBytes("ISO-8859-1"),"GBK"));
		talk.setCreateTime(new Date());
		talk.setUserId((String)request.getSession().getAttribute("id"));
		TalkServcieImpl ts = new TalkServcieImpl();
		
		ts.insertTalk(talk);	
		return "redirect:/user/bookmessage?bookid="+bookId;
	}
	/**
	 * 评论删除
	 * */
	@RequestMapping("user/deTalk")
	public String deTalk(@RequestParam("talkId")String talkId,@RequestParam("bookId")String bookId,HttpServletRequest request){
		TalkServcieImpl ts = new TalkServcieImpl();
		ts.deleteTalk(talkId);	
		return "redirect:/user/bookmessage?bookid="+bookId;
	}
	
}
