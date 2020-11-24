package examPro.com.dao.subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import examPro.com.model.subject.Options;
import examPro.com.model.subject.Sub_Topic;
import examPro.com.utilities.DAOUtilities;

public class choose_optionDAOImpl implements choose_optionDAO {
	Connection connection = null;
	PreparedStatement stmt = null;

	public boolean addOption(Options option) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO choose_option(option_id, option_one, option_two, option_three, question_id) VALUES(default, ?,?,?, ?)";
			stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, option.getOption_one());
			stmt.setString(2, option.getOption_two());
			stmt.setString(3, option.getOption_three());
			stmt.setInt(4, option.getQuestion_id());

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

	public List<Options> getAllOption() {
		List<Options> options = new ArrayList<Options>();

		try {
			connection = DAOUtilities.getConnection(); // Get our database connection from the manager
			String sql = "SELECT * FROM choose_options"; // Our SQL query
			// String sql = "SELECT * FROM Users"; // Our SQL query
			stmt = connection.prepareStatement(sql); // Creates the prepared statement from the query

			ResultSet rs = stmt.executeQuery(); // Queries the database

			// So long as the ResultSet actually contains results...
			while (rs.next()) {
				// We need to populate a User object with info for each row from our query
				// result
				Options option = new Options(Integer.parseInt(rs.getString("option_id")), rs.getString("option_one"),
						rs.getString("option_two"), rs.getString("option_three"),
						Integer.parseInt(rs.getString("question_id")));
				// Each variable in our User object maps to a column in a row from our results.

				// Finally we add it to the list of Book objects returned by this query.
				options.add(option);

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
		return options;
	}

	public int findLastOptionId(Options option) {
		int ID = -1;

		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO choose_options(option_id, option_one, option_two, option_three, question_id) VALUES(default, ?,?,?, ?) RETURNING option_id";
			stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, option.getOption_one());
			stmt.setString(2, option.getOption_two());
			stmt.setString(3, option.getOption_three());
			stmt.setInt(4, option.getQuestion_id());
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

	public Options getOptionById(int id) {
		Options option = null;

		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM choose_options WHERE option_id = ?";
			stmt = connection.prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				option = new Options(Integer.parseInt(rs.getString("option_id")), rs.getString("option_one"),
						rs.getString("option_two"), rs.getString("option_three"),
						Integer.parseInt(rs.getString("question_id")));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}

		return option;
	}

	public boolean updateOption(Options option) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE choose_options SET option_one=?, option_two, option_three , question_id=?";
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, option.getOption_one());
			stmt.setString(2, option.getOption_two());
			stmt.setString(3, option.getOption_three());
			stmt.setInt(4, option.getQuestion_id());
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

	public boolean deleteOptionByID(int id) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE FROM choose_options WHERE option_id = ?";
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

	public Options getOptionByQuestionId(int question_id) {
		Options option = null;

		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM choose_options WHERE question_id = ?";
			stmt = connection.prepareStatement(sql);

			stmt.setInt(1, question_id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				option = new Options(Integer.parseInt(rs.getString("option_id")), rs.getString("option_one"),
						rs.getString("option_two"), rs.getString("option_three"),
						Integer.parseInt(rs.getString("question_id")));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}

		return option;
	}

}
