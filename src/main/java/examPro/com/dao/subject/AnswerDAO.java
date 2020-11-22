package examPro.com.dao.subject;
import java.util.List;

import examPro.com.model.subject.Answer;
public interface AnswerDAO {
	    public boolean addAnswer(Answer answer);    
	    public List<Answer> getAllAnswer();
	    public int findLastAnswerId(Answer answer);
		public Answer getAnswerById(int id);
		public boolean updateAnswer(Answer answer);
		public boolean deleteAnswerByID(int id);
		public Answer getAnswerByQuestionId(int question_id);
}
