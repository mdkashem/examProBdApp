package examPro.com.dao.subject;

public class Quiz {
	String question;
	String option_1;
	String option_2;
	String option_3;
	String right_ans;
	/**
	 * 
	 * @param question
	 * @param option_1
	 * @param option_2
	 * @param option_3
	 * @param right_ans
	 */
	public Quiz(String question, String option_1, String option_2, String option_3, String right_ans) {
		super();
		this.question = question;
		this.option_1 = option_1;
		this.option_2 = option_2;
		this.option_3 = option_3;
		this.right_ans = right_ans;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getOption_1() {
		return option_1;
	}
	public void setOption_1(String option_1) {
		this.option_1 = option_1;
	}
	public String getOption_2() {
		return option_2;
	}
	public void setOption_2(String option_2) {
		this.option_2 = option_2;
	}
	public String getOption_3() {
		return option_3;
	}
	public void setOption_3(String option_3) {
		this.option_3 = option_3;
	}
	public String getRight_ans() {
		return right_ans;
	}
	public void setRight_ans(String right_ans) {
		this.right_ans = right_ans;
	}
	@Override
	public String toString() {
		return "Quiz [question=" + question + ", option_1=" + option_1 + ", option_2=" + option_2 + ", option_3="
				+ option_3 + ", right_ans=" + right_ans + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((option_1 == null) ? 0 : option_1.hashCode());
		result = prime * result + ((option_2 == null) ? 0 : option_2.hashCode());
		result = prime * result + ((option_3 == null) ? 0 : option_3.hashCode());
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		result = prime * result + ((right_ans == null) ? 0 : right_ans.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quiz other = (Quiz) obj;
		if (option_1 == null) {
			if (other.option_1 != null)
				return false;
		} else if (!option_1.equals(other.option_1))
			return false;
		if (option_2 == null) {
			if (other.option_2 != null)
				return false;
		} else if (!option_2.equals(other.option_2))
			return false;
		if (option_3 == null) {
			if (other.option_3 != null)
				return false;
		} else if (!option_3.equals(other.option_3))
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (right_ans == null) {
			if (other.right_ans != null)
				return false;
		} else if (!right_ans.equals(other.right_ans))
			return false;
		return true;
	}
	

}
