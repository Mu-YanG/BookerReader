package modle;

public class Answer {
	private String answerId;
	private String takeId;
	private String answerMessage;
	private String userId;
	
	public Answer() {
	}
	public Answer(String answerId, String takeId, String answerMessage,
			String userId) {
		this.answerId = answerId;
		this.takeId = takeId;
		this.answerMessage = answerMessage;
		this.userId = userId;
	}
	public String getAnswerId() {
		return answerId;
	}
	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}
	public String getTakeId() {
		return takeId;
	}
	public void setTakeId(String takeId) {
		this.takeId = takeId;
	}
	public String getAnswerMessage() {
		return answerMessage;
	}
	public void setAnswerMessage(String answerMessage) {
		this.answerMessage = answerMessage;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
