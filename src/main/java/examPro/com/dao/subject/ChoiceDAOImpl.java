package examPro.com.dao.subject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import examPro.com.model.subject.Choice;
import examPro.com.utilities.DAOUtilities;
public class ChoiceDAOImpl implements ChoiceDAO{
	Connection connection = null;
	PreparedStatement stmt = null;
	@Override
	public boolean addChoise(Choice choice) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO choices(choice_id, choice) VALUES(default, ?)";
			stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS );
			stmt.setString(1, choice.getChoice());
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
	public List<Choice> getAllChoice() {
		List<Choice> choices = new ArrayList<Choice>();

		try {
			connection = DAOUtilities.getConnection();	// Get our database connection from the manager
			String sql = "SELECT * FROM choices";			// Our SQL query
			//String sql = "SELECT * FROM Users";			// Our SQL query
			stmt = connection.prepareStatement(sql);	// Creates the prepared statement from the query
			
			ResultSet rs = stmt.executeQuery();			// Queries the database

			// So long as the ResultSet actually contains results...
			while (rs.next()) {
				// We need to populate a User object with info for each row from our query result
				Choice choice = new Choice(Integer.parseInt(rs.getString("choice_id")), rs.getString("choice"));
				// Each variable in our User object maps to a column in a row from our results.
			
				// Finally we add it to the list of Book objects returned by this query.
				choices.add(choice);
				
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
		return choices;
	}

	@Override
	public int findLastChoiceId(Choice choice) {
		 int ID = -1;
			
			try {
				connection = DAOUtilities.getConnection();
				String sql = "INSERT INTO choices(choice_id, choice) VALUES(default, ?) RETURNING choice_id";
				stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS );
				
				stmt.setString(1, choice.getChoice());
				
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
	public Choice getChoiceById(int id) {
		Choice choice = null;

		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM choices WHERE choice_id = ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				choice = new Choice(rs.getInt("choice_id"), rs.getString("choice"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return choice;
	}

	@Override
	public boolean updateChoice(Choice choice) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE choices SET choice=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, choice.getChoice());
			
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
	public boolean deleteChoiceByID(int id) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE FROM choices WHERE choice_id = ?";
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
