package examPro.com.model.subject;

public class Question {
	private int question_id;
	private String question;
	private int topic_id;
	public Question(int question_id, String question, int topic_id) {
		super();
		this.question_id = question_id;
		this.question = question;
		this.topic_id = topic_id;
	}
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public int getTopic_id() {
		return topic_id;
	}
	public void setTopic_id(int topic_id) {
		this.topic_id = topic_id;
	}
	@Override
	public String toString() {
		return "Question [question_id=" + question_id + ", question=" + question + ", topic_id=" + topic_id + "]";
	}
	

}
