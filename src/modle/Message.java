package modle;
/**
 * 
 * 通知表（通知信息）
 * */
public class Message {

	private String messageId;
	private String messageTitle;
	private String messageCreateTime;
	private String message;
	private String messageStatus;
	private String user;
	private String url;
	
	
	public Message() {
	}
	


	public Message(String messageId, String messageTitle,
			String messageCreateTime, String message, String messageStatus,
			String user,String url) {
		super();
		this.messageId = messageId;
		this.messageTitle = messageTitle;
		this.messageCreateTime = messageCreateTime;
		this.message = message;
		this.messageStatus = messageStatus;
		this.user = user;
		this.url = url;
	}



	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getMessageTitle() {
		return messageTitle;
	}
	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}
	public String getMessageCreateTime() {
		return messageCreateTime;
	}
	public void setMessageCreateTime(String messageCreateTime) {
		this.messageCreateTime = messageCreateTime;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessageStatus() {
		return messageStatus;
	}
	public void setMessageStatus(String messageStatus) {
		this.messageStatus = messageStatus;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", messageTitle="
				+ messageTitle + ", messageCreateTime=" + messageCreateTime
				+ ", message=" + message + ", messageStatus=" + messageStatus
				+ ", user=" + user + ", url=" + url + "]";
	}
	
	
	
}
