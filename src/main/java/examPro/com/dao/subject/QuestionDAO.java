package examPro.com.dao.subject;

import java.util.List;

import examPro.com.model.subject.Question;

public interface QuestionDAO {
	 public boolean addQuestion(Question ques);    
	    public List<Question> getAllQuestions();
	    public int findLastQuestionId(Question ques);
		public Question getQuestionById(int id);
		public boolean updateQuestion(Question ques);
		public boolean deleteQuestionByID(int id);
		public List<Question> getAllQuestionByTopicId(int sub_topic_id);
		public Question getQuestionByQuestion(String question);

}
