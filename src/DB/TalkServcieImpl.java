package DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modle.Talk;

public class TalkServcieImpl {
	
	private DB db = null;
	Statement createStatement=null;
	ResultSet executeQuery=null;
	/**
	 * @author lvyang
	 * 用于插入评论的service
	 * */
	public Boolean insertTalk(Talk talk){
		
	try {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = dateFormat.format(new Date());
		db=new DB();
		createStatement = db.con.createStatement();
		String sql="insert into book_talk(book_id,message,user_id,create_time) values('"+
					talk.getBookId()+"','"
					+talk.getMessage()+"','"
					+talk.getUserId()+"','"
					+format
					+"')";
		int execute = createStatement.executeUpdate(sql);
		return execute==1;
	} catch (SQLException e) {
		e.printStackTrace();
	}finally{
		try {
			if(createStatement!=null){
					createStatement.close();
			}
		} catch (SQLException e) {
			
		}
	}
		return false;
	}
	
	public List<Talk> getTalkList(String bookid){
		db=new DB();
		List<Talk> talkList=null;
		try {
			talkList = new ArrayList<Talk>();
			createStatement = db.con.createStatement();
		    executeQuery = createStatement.executeQuery("select * from book_talk where book_id='"+bookid+"'");
			while(executeQuery.next()){
				Talk talk = new Talk();
				talk.setTalkId(executeQuery.getString(1));
				talk.setBookId(executeQuery.getString(2));
				talk.setMessage(executeQuery.getString(3));
				talk.setUserId(executeQuery.getString(4));
				talk.setCreateTime(executeQuery.getDate(5));
				talkList.add(talk);
				System.out.println(talk);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(createStatement!=null){
						createStatement.close();
				}
				if(executeQuery!=null){
					executeQuery.close();
				}
			} catch (SQLException e) {
				
			}
		}	
		return talkList;
	}
	public Boolean deleteTalk(String talkid){
		try {
			db=new DB();
			createStatement = db.con.createStatement();
			int execute = createStatement.executeUpdate("delete from book_talk where talk_id="+talkid);
			return execute==1;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(createStatement!=null){
						createStatement.close();
				}
			} catch (SQLException e) {
				
			}
		}
			return false;
		
	}

}
