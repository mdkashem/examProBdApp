package examPro.com.dao.subject;

import java.util.List;

import examPro.com.model.subject.Sub_Topic;

public interface TopicDAO {
	    public boolean addTopic(Sub_Topic topic);    
	    public List<Sub_Topic> getAllTopic();
	    public int findLastTopicId(Sub_Topic topic);
		public Sub_Topic getTopicById(int id);
		public boolean updateTopic(Sub_Topic topic);
		public boolean deleteTopicByID(int id);
		public Sub_Topic getTopicByName(String sub_topic);
}
