package examPro.com.dao.subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import examPro.com.model.subject.Sub_Topic;
import examPro.com.utilities.DAOUtilities;

public class TopicDAOImpl implements TopicDAO {

	Connection connection = null;
	PreparedStatement stmt = null;

	@Override
	public boolean addTopic(Sub_Topic topic) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO sub_topics(topic_id, topic, sub_id) VALUES(default, ?, ?)";
			stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, topic.getTopic());
			stmt.setInt(2, topic.getSub_id());

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

	@Override
	public List<Sub_Topic> getAllTopic() {
		List<Sub_Topic> topics = new ArrayList<Sub_Topic>();

		try {
			connection = DAOUtilities.getConnection(); // Get our database connection from the manager
			String sql = "SELECT * FROM sub_topics"; // Our SQL query
			// String sql = "SELECT * FROM Users"; // Our SQL query
			stmt = connection.prepareStatement(sql); // Creates the prepared statement from the query

			ResultSet rs = stmt.executeQuery(); // Queries the database

			// So long as the ResultSet actually contains results...
			while (rs.next()) {
				// We need to populate a User object with info for each row from our query
				// result
				Sub_Topic topic = new Sub_Topic(Integer.parseInt(rs.getString("topic_id")), rs.getString("topic"),
						Integer.parseInt(rs.getString("sub_id")));
				// Each variable in our User object maps to a column in a row from our results.

				// Finally we add it to the list of Book objects returned by this query.
				topics.add(topic);

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
		return topics;
	}

	@Override
	public int findLastTopicId(Sub_Topic topic) {
		int ID = -1;

		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO sub_topics(topic_id, topic, sub_id) VALUES(default, ?, ?) RETURNING topic_id";
			stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, topic.getTopic());
			stmt.setInt(2, topic.getSub_id());
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

	@Override
	public Sub_Topic getTopicById(int id) {
		Sub_Topic topic = null;

		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM sub_topics WHERE topic_id = ?";
			stmt = connection.prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				topic = new Sub_Topic(rs.getInt("topic_id"), rs.getString("topic"), rs.getInt("sub_id"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}

		return topic;
	}
	@Override
	public Sub_Topic getTopicByName(String sub_topic) {
		Sub_Topic topic = null;

		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM sub_topics WHERE topic = ?";
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, sub_topic);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				topic = new Sub_Topic(rs.getInt("topic_id"), rs.getString("topic"), rs.getInt("sub_id"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}

		return topic;
	}

	@Override
	public boolean updateTopic(Sub_Topic topic) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE sub_topics SET topic=?, sub_id=?";
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, topic.getTopic());
			stmt.setInt(2, topic.getSub_id());
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
	public boolean deleteTopicByID(int id) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE FROM sub_topics WHERE topic_id = ?";
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
