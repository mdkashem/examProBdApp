package examPro.com.dao.subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import examPro.com.model.subject.Question;
import examPro.com.utilities.DAOUtilities;

public class QuestionDAOImpl implements QuestionDAO {
	Connection connection = null;
	PreparedStatement stmt = null;

	public boolean addQuestion(Question ques) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO questions(question_id, question, topic_id) VALUES(default, ?, ?)";
			stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, ques.getQuestion());
			stmt.setInt(2, ques.getTopic_id());

			if (stmt.executeUpdate() != 0) {
				return true;
			} else
				return false;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {

			closeResources();
		}
	}

	public List<Question> getAllQuestions() {
		List<Question> questions = new ArrayList<Question>();

		try {
			connection = DAOUtilities.getConnection(); // Get our database connection from the manager
			String sql = "SELECT * FROM questions"; // Our SQL query
			// String sql = "SELECT * FROM Users"; // Our SQL query
			stmt = connection.prepareStatement(sql); // Creates the prepared statement from the query

			ResultSet rs = stmt.executeQuery(); // Queries the database

			// So long as the ResultSet actually contains results...
			while (rs.next()) {
				// We need to populate a User object with info for each row from our query
				// result
				Question topic = new Question(Integer.parseInt(rs.getString("question_id")), rs.getString("question"),
						Integer.parseInt(rs.getString("topic_id")));
				// Each variable in our User object maps to a column in a row from our results.

				// Finally we add it to the list of Book objects returned by this query.
				questions.add(topic);

			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// We need to make sure our statements and connections are closed,
			// or else we could wind up with a memory leak
			closeResources();
		}

		// return the list of questions objects populated by the DB.
		return questions;
	}

	public int findLastQuestionId(Question ques) {
		int ID = -1;

		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO questions(question_id, question, topic_id) VALUES(default, ?, ?) RETURNING question_id";
			stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, ques.getQuestion());
			stmt.setInt(2, ques.getTopic_id());
			int update = stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				ID = rs.getInt(1);
				return ID;
			} else {
				return ID;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ID;

		} finally {

			closeResources();
		}
	}

	public Question getQuestionById(int id) {
		Question ques = null;

		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM questions WHERE question_id = ?";
			stmt = connection.prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				ques = new Question(rs.getInt("question_id"), rs.getString("question"), rs.getInt("topic_id"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}

		return ques;
	}
	public Question getQuestionByQuestion(String question) {
		Question ques = null;

		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM questions WHERE question = ?";
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, question);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				ques = new Question(rs.getInt("question_id"), rs.getString("question"), rs.getInt("topic_id"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}

		return ques;
	}

	public boolean updateQuestion(Question ques) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE questions SET question=?, topic_id=?";
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, ques.getQuestion());
			stmt.setInt(2, ques.getTopic_id());
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}

	public boolean deleteQuestionByID(int id) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE FROM questions WHERE question_id = ?";
			stmt = connection.prepareStatement(sql);

			stmt.setInt(1, id);
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			// We need to make sure our statements and connections are closed,
			// or else we could wind up with a memory leak
			closeResources();
		}
	}

	

	public List<Question> getAllQuestionByTopicId(int sub_topic_id) {
		List<Question> questions = new ArrayList<Question>();
		try {
			connection = DAOUtilities.getConnection(); // Get our database connection from the manager
			String sql = "SELECT * FROM questions where topic_id=?"; // Our SQL query
			// String sql = "SELECT * FROM Users"; // Our SQL query
			stmt = connection.prepareStatement(sql); // Creates the prepared statement from the query
			stmt.setInt(1, sub_topic_id);
			ResultSet rs = stmt.executeQuery(); // Queries the database

			// So long as the ResultSet actually contains results...
			while (rs.next()) {
				// We need to populate a User object with info for each row from our query
				// result
				Question ques = new Question(Integer.parseInt(rs.getString("question_id")), rs.getString("question"),
						Integer.parseInt(rs.getString("topic_id")));
				// Each variable in our User object maps to a column in a row from our results.

				// Finally we add it to the list of Book objects returned by this query.
				questions.add(ques);

			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// We need to make sure our statements and connections are closed,
			// or else we could wind up with a memory leak
			closeResources();
		}

		// return the list of questions objects populated by the DB.
		return questions;
	}
	// Closing all resources to prevent memory leaks.
		// Ideally, you really want to close them in the reverse-order you open them
		private void closeResources() {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				System.out.println("Could not close statement!");
				e.printStackTrace();
			}

			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.out.println("Could not close connection!");
				e.printStackTrace();
			}
		}

}
