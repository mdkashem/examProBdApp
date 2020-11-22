package examPro.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import examPro.com.model.AccountStatus;
import examPro.com.utilities.DAOUtilities;

public class StatusDAOImplements implements StatusDAO {

	Connection connection = null;
	PreparedStatement stmt = null;

	public boolean addStatus(AccountStatus status) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO account_status(statusid, status) VALUES(default, ?)";
			stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, status.getStatus());

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

	public List<AccountStatus> getAllStatus() {
		List<AccountStatus> statusList = new ArrayList<AccountStatus>();

		try {
			connection = DAOUtilities.getConnection(); // Get our database connection from the manager
			String sql = "SELECT * FROM account_status"; // Our SQL query
			// String sql = "SELECT * FROM Users"; // Our SQL query
			stmt = connection.prepareStatement(sql); // Creates the prepared statement from the query

			ResultSet rs = stmt.executeQuery(); // Queries the database

			// So long as the ResultSet actually contains results...
			while (rs.next()) {
				// We need to populate a User object with info for each row from our query
				// result
				AccountStatus status = new AccountStatus();
				// Each variable in our User object maps to a column in a row from our results.
				status.setStatusId(Integer.parseInt(rs.getString("statusid")));
				status.setStatus(rs.getString("status"));

				// Finally we add it to the list of Book objects returned by this query.
				statusList.add(status);

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
		return statusList;
	}

	public AccountStatus getStatusByName(String name) {
		AccountStatus status = null;

		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM account_status WHERE status LIKE ?";
			stmt = connection.prepareStatement(sql);
			// This command populate the 1st '?' with the title and wildcards, between ' '
			stmt.setString(1, "%" + name + "%");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				status = new AccountStatus();
				status.setStatusId(Integer.parseInt(rs.getString("statusid")));
				status.setStatus(rs.getString("status"));
			}

			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// We need to make sure our statements and connections are closed,
			// or else we could wind up with a memory leak
			closeResources();
		}

		return status;
	}

	public List<AccountStatus> getStatusById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean updateStatus(AccountStatus status) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteStausByID(int id) {
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
