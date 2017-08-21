package test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;



import modle.Message;
import modle.Talk;
import DB.DB;
import DB.MessageServiceImpl;
import DB.TalkServcieImpl;

public class testDB {
	public static void main(String[] args) {
		DB db = new DB();
	}//,"+new Date()+"
	@Test
	public void testinsertTalk() throws ParseException{
		TalkServcieImpl impl =  new TalkServcieImpl();
		//impl.insertTalk(new Talk(null,"1","1","1",null));
		/*List<Talk> talkList = impl.getTalkList("1");
		for (Talk talk : talkList) {
			System.out.println(talk.getTalkId());
		}*/
		impl.deleteTalk("3");
	}
	@Test
	public void testmESSAGE(){
		MessageServiceImpl impl = new MessageServiceImpl();
	//	impl.insertMessage(new Message(null, "12", null, "1222222222222222222222", null));
	//	impl.deleteMessage(1);
//		ArrayList<Message> messages = impl.getMessages(0);
//		for (Message message : messages) {
//			message.getMessageId();
//			System.out.println(message.getMessageId());
//		}
		Message messageById = impl.getMessageById("21");
		System.out.println(messageById);
	}
}
