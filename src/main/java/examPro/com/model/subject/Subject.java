package examPro.com.model.subject;

public class Subject {
	private int sub_id;
	private String subject;
	
	
	
	public Subject(int sub_id, String subject) {
		super();
		this.sub_id = sub_id;
		this.subject = subject;
	}
	
	@Override
	public String toString() {
		return "Subject [sub_id=" + sub_id + ", subject=" + subject + "]";
	}

	public int getSub_id() {
		return sub_id;
	}
	public void setSub_id(int sub_id) {
		this.sub_id = sub_id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	

}
