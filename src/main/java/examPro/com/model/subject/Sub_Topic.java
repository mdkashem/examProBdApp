package examPro.com.model.subject;

public class Sub_Topic {
	private int topic_id;
	private String topic;
	private int sub_id;
	public Sub_Topic(int topic_id, String topic, int sub_id) {
		super();
		this.topic_id = topic_id;
		this.topic = topic;
		this.sub_id = sub_id;
	}
	public int getTopic_id() {
		return topic_id;
	}
	public void setTopic_id(int topic_id) {
		this.topic_id = topic_id;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public int getSub_id() {
		return sub_id;
	}
	public void setSub_id(int sub_id) {
		this.sub_id = sub_id;
	}
	@Override
	public String toString() {
		return "Sub_Topic [topic_id=" + topic_id + ", topic=" + topic + ", sub_id=" + sub_id + "]";
	}
	
	
}
