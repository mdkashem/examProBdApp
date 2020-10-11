package examPro.com.model.subject;

public class Answer {
	private int ans_id;
	private int choice_id;
	private int question_id;
	public Answer(int ans_id, int choice_id, int question_id) {
		super();
		this.ans_id = ans_id;
		this.choice_id = choice_id;
		this.question_id = question_id;
	}
	public int getAns_id() {
		return ans_id;
	}
	public void setAns_id(int ans_id) {
		this.ans_id = ans_id;
	}
	public int getChoice_id() {
		return choice_id;
	}
	public void setChoice_id(int choice_id) {
		this.choice_id = choice_id;
	}
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	@Override
	public String toString() {
		return "Answer [ans_id=" + ans_id + ", choice_id=" + choice_id + ", question_id=" + question_id + "]";
	}
	

}
