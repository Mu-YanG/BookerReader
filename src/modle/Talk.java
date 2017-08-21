package modle;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 书籍评论
 * */
public class Talk {

	private String talkId;
	private String bookId;
	private String userId;
	private String message;
	private Date createTime;
	
	
	
	@Override
	public String toString() {
		return "Talk [talkId=" + talkId + ", bookId=" + bookId + ", userId=" + userId + ", message=" + message
				+ ", createTime=" + createTime + "]";
	}
	public Talk() {
		
	}
	public Talk(String talkId, String bookId, String userId, String message,
			Date createTime) {
		super();
		this.talkId = talkId;
		this.bookId = bookId;
		this.userId = userId;
		this.message = message;
		this.createTime = createTime;
	}
	public String getCreateTimeshow() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月DD日   hh分");
		String format = dateFormat.format(createTime);
		return format;
	}

	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		
		
		
		this.createTime = createTime;
	}
	public String getTalkId() {
		return talkId;
	}
	public void setTalkId(String talkId) {
		this.talkId = talkId;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		String fuckMessage[]={
				"草你妈","你麻痹","习近平","妈比","操","妈","fuck","共产"
		};
		for (int i = 0; i < fuckMessage.length; i++) {
			if(message.contains(fuckMessage[i])){
				message.replace(fuckMessage[i], "***");
			}
		}
		
		this.message = message;
	}
	
}
