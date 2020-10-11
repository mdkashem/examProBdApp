package examPro.com.dao.subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import examPro.com.model.subject.Subject;
import examPro.com.utilities.DAOUtilities;

public class SubjectDAOImpl implements SubjectDAO{

	Connection connection = null;
	PreparedStatement stmt = null;
	@Override
	public boolean addSubject(Subject sub) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO subjects(sub_id, sub_name) VALUES(default, ?)";
			stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS );
			stmt.setString(1, sub.getSubject());
			
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
	public List<Subject> getAllSubject() {
		List<Subject> subjects = new ArrayList<Subject>();

		try {
			connection = DAOUtilities.getConnection();	// Get our database connection from the manager
			String sql = "SELECT * FROM subjects";			// Our SQL query
			//String sql = "SELECT * FROM Users";			// Our SQL query
			stmt = connection.prepareStatement(sql);	// Creates the prepared statement from the query
			
			ResultSet rs = stmt.executeQuery();			// Queries the database

			// So long as the ResultSet actually contains results...
			while (rs.next()) {
				// We need to populate a User object with info for each row from our query result
				Subject sub = new Subject(Integer.parseInt(rs.getString("sub_id")), rs.getString("sub_name"));
				// Each variable in our User object maps to a column in a row from our results.
			
				// Finally we add it to the list of Book objects returned by this query.
				subjects.add(sub);
				
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
		return subjects;
	}
	/****
	 * the findLastSubjectId method add a subject and return the id of the inserted subject.
	 */
	
	@Override
	public int findLastSubjectId(Subject sub) {
          int ID = -1;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO subjects(sub_id, sub_name) VALUES(default, ?) RETURNING sub_id";
			stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS );
			
			stmt.setString(1, sub.getSubject());
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
	public Subject getSubjectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateSubject(Subject sub) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteSubjectByID(int id) {
		// TODO Auto-generated method stub
		return false;
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
