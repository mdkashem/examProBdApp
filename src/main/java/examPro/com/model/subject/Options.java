package examPro.com.model.subject;

public class Options {
private int option_id;
private String option_one;
private String option_two;
private String option_three;
private int question_id;
public Options(int option_id, String option_one, String option_two, String option_three, int question_id) {
	super();
	this.option_id = option_id;
	this.option_one = option_one;
	this.option_two = option_two;
	this.option_three = option_three;
	this.question_id = question_id;
}
public int getOption_id() {
	return option_id;
}
public void setOption_id(int option_id) {
	this.option_id = option_id;
}
public String getOption_one() {
	return option_one;
}
public void setOption_one(String option_one) {
	this.option_one = option_one;
}
public String getOption_two() {
	return option_two;
}
public void setOption_two(String option_two) {
	this.option_two = option_two;
}
public String getOption_three() {
	return option_three;
}
public void setOption_three(String option_three) {
	this.option_three = option_three;
}
public int getQuestion_id() {
	return question_id;
}
public void setQuestion_id(int question_id) {
	this.question_id = question_id;
}
@Override
public String toString() {
	return "Options [option_id=" + option_id + ", option_one=" + option_one + ", option_two=" + option_two
			+ ", option_three=" + option_three + ", question_id=" + question_id + "]";
}


}
