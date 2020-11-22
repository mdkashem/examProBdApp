package examPro.com.services;

import java.util.ArrayList;
import java.util.List;

import examPro.com.dao.subject.Quiz;
import examPro.com.model.subject.Answer;
import examPro.com.model.subject.Choice;
import examPro.com.model.subject.Options;
import examPro.com.model.subject.Question;
import examPro.com.utilities.DAOUtilities;

public class UserRoleService {
	public UserRoleService() {

	}

	public List<Quiz> generateQuiz(String subject, String sub_topic, int num_of_quiz) {
		List<Quiz> quizs = new ArrayList<Quiz>();
		// find subject id from database and find sub_topic id
		// find all question and question id by using the sub_topic id
		// find all options and answer id by using the question id
		// generate list of quiz and return
		int sub_id = DAOUtilities.getSubjectDAO().findSubjectByName(subject).getSub_id();
		int topic_id = DAOUtilities.getTopicDAO().getTopicByName(sub_topic).getTopic_id();
		List<Question> questions = DAOUtilities.getQuestionDAO().getAllQuestionByTopicId(topic_id);
		int count = 0;
		if (questions.size() < num_of_quiz) {
			while (count < questions.size()) {
				Options option = DAOUtilities.getOptionDAO()
						.getOptionByQuestionId(questions.get(count).getQuestion_id());
				Answer ans = DAOUtilities.getAnswerDAO().getAnswerByQuestionId(questions.get(count).getQuestion_id());
				Choice rightAnswer = DAOUtilities.getChoiceDAO().getChoiceById(ans.getChoice_id());
				quizs.add(new Quiz(questions.get(count).getQuestion(), option.getOption_one(), option.getOption_two(),
						option.getOption_three(), rightAnswer.getChoice()));
				count++;
			}
		} else {
			while (count < num_of_quiz) {
				Options option = DAOUtilities.getOptionDAO()
						.getOptionByQuestionId(questions.get(count).getQuestion_id());
				Answer ans = DAOUtilities.getAnswerDAO().getAnswerByQuestionId(questions.get(count).getQuestion_id());
				Choice rightAnswer = DAOUtilities.getChoiceDAO().getChoiceById(ans.getChoice_id());
				quizs.add(new Quiz(questions.get(count).getQuestion(), option.getOption_one(), option.getOption_two(),
						option.getOption_three(), rightAnswer.getChoice()));
				count++;
			}
		}
		return quizs;

	}
}
