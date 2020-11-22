package examPro.com.dao.subject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import examPro.com.model.subject.Answer;
import examPro.com.utilities.DAOUtilities;

public class AnswerDAOImpl implements AnswerDAO{
	Connection connection = null;
	PreparedStatement stmt = null;
	@Override
	public boolean addAnswer(Answer answer) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO answers(ans_id, choice_id, question_id) VALUES(default, ?, ?)";
			stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS );
			stmt.setInt(1, answer.getChoice_id());
			stmt.setInt(2, answer.getQuestion_id());
			
			if(stmt.executeUpdate()!=0) {
				return true;
			}else
				return false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			
			closeResources();
		}
	}

	@Override
	public List<Answer> getAllAnswer() {
		List<Answer> answers = new ArrayList<Answer>();

		try {
			connection = DAOUtilities.getConnection();	// Get our database connection from the manager
			String sql = "SELECT * FROM Answers";			// Our SQL query
			//String sql = "SELECT * FROM Users";			// Our SQL query
			stmt = connection.prepareStatement(sql);	// Creates the prepared statement from the query
			
			ResultSet rs = stmt.executeQuery();			// Queries the database

			// So long as the ResultSet actually contains results...
			while (rs.next()) {
				// We need to populate a User object with info for each row from our query result
				Answer ans = new Answer(Integer.parseInt(rs.getString("answer_id")), Integer.parseInt(rs.getString("choice_id")), Integer.parseInt(rs.getString("question_id")));
				// Each variable in our User object maps to a column in a row from our results.
			
				// Finally we add it to the list of Book objects returned by this query.
				answers.add(ans);
				
			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// We need to make sure our statements and connections are closed, 
			// or else we could wind up with a memory leak
			closeResources();
		}
		
		// return the list of users objects populated by the DB.
		return answers;
	}

	@Override
	public int findLastAnswerId(Answer answer) {
		 int ID = -1;
			
			try {
				connection = DAOUtilities.getConnection();
				String sql = "INSERT INTO answers(ans_id, choice_id, question_id) VALUES(default, ?, ?) RETURNING ans_id";
				stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS );
				
				stmt.setInt(1, answer.getChoice_id());
				stmt.setInt(2, answer.getQuestion_id());
				int update = stmt.executeUpdate();
						 ResultSet rs = stmt.getGeneratedKeys();
						 if (rs != null && rs.next()) {
						  ID = rs.getInt(1);
						  return ID;
						 }else {
							 return ID;
						 }		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return ID;
				
			}finally {
				
				closeResources();
			}
	}

	@Override
	public Answer getAnswerById(int id) {
		Answer answer = null;

		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM answers WHERE ans_id = ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				answer = new Answer(rs.getInt("ans_id"), rs.getInt("choice_id"), rs.getInt("question_id"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return answer;
	}

	@Override
	public boolean updateAnswer(Answer answer) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE answers SET choice_id=?, question_id=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, answer.getChoice_id());
			stmt.setInt(2, answer.getQuestion_id());
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

	@Override
	public boolean deleteAnswerByID(int id) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE FROM answers WHERE ans_id = ?";
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
		}finally {
			// We need to make sure our statements and connections are closed, 
			// or else we could wind up with a memory leak
			closeResources();
		}
	}
	
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

	@Override
	public Answer getAnswerByQuestionId(int question_id) {
		Answer answer = null;

		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM answers WHERE question_id = ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, question_id);
			
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				answer = new Answer(rs.getInt("ans_id"), rs.getInt("choice_id"), rs.getInt("question_id"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return answer;
	}

}
