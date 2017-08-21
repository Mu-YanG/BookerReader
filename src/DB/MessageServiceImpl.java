package DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



import data.GetData;

import modle.Message;

/**
 * @author lvyang
 * 关于发布新消息数据库Dao
 * */
public class MessageServiceImpl {
	private DB db=null; 
	
	/**
	 * 获取消息列表
	 * */
	public ArrayList<Message> getMessages(Integer status){
		
		db=new DB();
		Statement statement=null;
		ResultSet rs=null;
		ArrayList<Message> messages=null;
		try {
		statement=(Statement) db.con.createStatement();
		String sql ="select * from message where message_status="+status.intValue();
		rs = statement.executeQuery(sql);
		messages = new ArrayList<Message>();
		while(rs.next()){
			
			Message message = new Message(rs.getInt(1)+"", rs.getString(2),new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate(3)), rs.getString(4), rs.getString(5)+"",rs.getString(6),rs.getString(7));
		    messages.add(message);
		}
		
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return messages;
		
	}
	/**
	 * @author lvyang
	 * 插入消息
	 * */
	public boolean insertMessage(Message message){
		db= new DB();Statement statement=null;
		try {
			 statement = (Statement) db.con.createStatement();
			String sql = "insert into message(" +
					"message_title,message_create_time,message,message_status,user,url)" +
					"values('" +
					 message.getMessageTitle()+"','" +
					GetData.newDate(new Date())+"','" +
					 message.getMessage()+"',0,'"+message.getUser()+"','"+message.getUrl()+"')";
			int executeUpdate = statement.executeUpdate(sql);
			return executeUpdate==1;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if (statement!=null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		return false;
	}
	/**
	 * 删除消息的方法
	 * */
	public boolean  deleteMessage(int messageId){
		db= new DB();Statement statement=null;
		try {
			 statement = (Statement) db.con.createStatement();
			String sql = "delete from message where message_id="+messageId;
			int executeUpdate = statement.executeUpdate(sql);
			return executeUpdate==1;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if (statement!=null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		return false;
		
		
		
	}
	/**
	 * 更改消息状态，以发布和未发布2种
	 * 
	 * */
	public boolean showMessage(Integer messageId,Integer status) {
		db= new DB();Statement statement=null;
		try {
			 statement = (Statement) db.con.createStatement();
			String sql ="update message set message_status="+status+" where message_id="+messageId;
			int executeUpdate = statement.executeUpdate(sql);
			return executeUpdate==1;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if (statement!=null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		return false;
		
	}
	/**
	 * 根据id查询数据
	 * */
	
	public Message getMessageById(String messageId) {
		db=new DB();
		Statement statement=null;
		ResultSet rs=null;
		Message messages=null;
		try {
		statement=(Statement) db.con.createStatement();
		String sql ="select * from message where message_id="+messageId;
		rs = statement.executeQuery(sql);
		while(rs.next()){
			
		messages = new Message(rs.getInt(1)+"", rs.getString(2),new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate(3)), rs.getString(4), rs.getString(5)+"",rs.getString(6),rs.getString(7));
		}
		
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return messages;
	}
	

}
