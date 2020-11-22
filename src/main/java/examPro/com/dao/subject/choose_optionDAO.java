package examPro.com.dao.subject;

import java.util.List;

import examPro.com.model.subject.Options;

public interface choose_optionDAO {
	public boolean addOption(Options option);    
    public List<Options> getAllOption();
    public int findLastOptionId(Options option);
	public Options getOptionById(int id);
	public Options getOptionByQuestionId(int question_id);
	public boolean updateOption(Options option);
	public boolean deleteOptionByID(int id);
}
