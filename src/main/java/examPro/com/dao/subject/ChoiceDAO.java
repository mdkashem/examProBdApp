package examPro.com.dao.subject;
import java.util.List;

import examPro.com.model.subject.Choice;
public interface ChoiceDAO {
	    public boolean addChoise(Choice choice);    
	    public List<Choice> getAllChoice();
	    public int findLastChoiceId(Choice choice);
		public Choice getChoiceById(int id);
		public boolean updateChoice(Choice choice);
		public boolean deleteChoiceByID(int id);
}
