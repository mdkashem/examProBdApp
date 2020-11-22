package examPro.com.services;

import java.sql.Date;

import examPro.com.model.Account;
import examPro.com.model.User;
import examPro.com.model.subject.Answer;
import examPro.com.model.subject.Choice;
import examPro.com.model.subject.Options;
import examPro.com.model.subject.Question;
import examPro.com.model.subject.Sub_Topic;
import examPro.com.model.subject.Subject;
import examPro.com.utilities.DAOUtilities;

public class AdminRoleService {
	public AdminRoleService() {

	}

	/**
	 * This method is used to create quiz into database It creates the given subject
	 * if it does not exist. If the subject already exist, then use its id for
	 * reference to the sub topic of the subject. Similarly, it does not create sub
	 * topic, if it exist.
	 * 
	 * @param subject
	 * @param sub_topic
	 * @param question
	 * @param choice
	 * @param option_1
	 * @param option_2
	 * @param option_3
	 * @return boolean
	 */

	public boolean inserQuiz(String subject, String sub_topic, String question, String choice, String option_1,
			String option_2, String option_3) {
		/*
		 * insert subject into database, if it does not exist check if the subject
		 * already exist, then use the existing subject id
		 */
		Subject exist_sub = DAOUtilities.getSubjectDAO().findSubjectByName(subject);
		int sub_id;
		if (exist_sub == null) {
			sub_id = DAOUtilities.getSubjectDAO().findLastSubjectId(new Subject(1, subject));
		} else {
			sub_id = exist_sub.getSub_id();
		}
		Sub_Topic exist_sub_topic = DAOUtilities.getTopicDAO().getTopicByName(sub_topic);
		int topic_id;
		if (exist_sub_topic == null) {
			topic_id = DAOUtilities.getTopicDAO().findLastTopicId(new Sub_Topic(1, sub_topic, sub_id));
		} else {
			topic_id = exist_sub_topic.getTopic_id();
		}

		int question_id = DAOUtilities.getQuestionDAO().findLastQuestionId(new Question(1, question, topic_id));
		int choice_id = DAOUtilities.getChoiceDAO().findLastChoiceId(new Choice(1, choice));
		int ans_id = DAOUtilities.getAnswerDAO().findLastAnswerId(new Answer(1, choice_id, question_id));
		int op_id = DAOUtilities.getOptionDAO()
				.findLastOptionId(new Options(1, option_1, option_2, option_3, question_id));

		if (op_id < 0) {
			return false;
		} else {
			return true;
		}

	}// end

	/**
	 * this method is used to create new user in database. By default, all the new
	 * user's role is user role.
	 * 
	 * @param email
	 * @param password
	 * @param fName
	 * @param lName
	 * @param DOB
	 * @param phone
	 * @param date
	 * @return boolean
	 */
	public boolean createNewUser(String email, String password, String fName, String lName, String DOB, String phone,
			Date date) {
		if (DAOUtilities.getUserDAO().getUserByEmail("kasem_sea@yahoo.com") == null) {

			int newAccount_number = DAOUtilities.getAccountDAO().findLastAccountId(new Account(1, 0.00, date, 1, 1));
			boolean result = DAOUtilities.getUserDAO()
					.addUser(new User(1, email, password, fName, lName, DOB, phone, newAccount_number, 2));
			return result;
		}
		return false;
	}
}
