package examPro.com.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import examPro.com.dao.AccountDAO;
import examPro.com.dao.AccountDAOImpl;
import examPro.com.dao.AccountTypeDAO;
import examPro.com.dao.AccountTypeImplements;
import examPro.com.dao.RoleDAO;
import examPro.com.dao.RoleDAOImpl;
import examPro.com.dao.StatusDAO;
import examPro.com.dao.StatusDAOImplements;
import examPro.com.dao.userDAO;
import examPro.com.dao.userDAOImplement;
import examPro.com.dao.subject.AnswerDAO;
import examPro.com.dao.subject.AnswerDAOImpl;
import examPro.com.dao.subject.ChoiceDAO;
import examPro.com.dao.subject.ChoiceDAOImpl;
import examPro.com.dao.subject.QuestionDAO;
import examPro.com.dao.subject.QuestionDAOImpl;
import examPro.com.dao.subject.SubjectDAO;
import examPro.com.dao.subject.SubjectDAOImpl;
import examPro.com.dao.subject.TopicDAO;
import examPro.com.dao.subject.TopicDAOImpl;
import examPro.com.dao.subject.choose_optionDAO;
import examPro.com.dao.subject.choose_optionDAOImpl;

public class DAOUtilities {
	private static final String CONNECTION_USERNAME = System.getenv("dbUserName"); //accessing the system environment variable for user Name
	private static final String CONNECTION_PASSWORD =System.getenv("dbPassword");  //accessing the system environment variable for password
	private static final String URL = System.getenv("dbConnectionString_for_AWS") ;//System.getenv("dbConnectionString"); //accessing the system environment variable for  url
	/*
	 *the 3 lines bellow for mySQL connection testing purpose from Maruf web site  
	 * */
	   // private static final String CONNECTION_USERNAME = "mtestbd"; //accessing the system environment variable for user Name
		//private static final String CONNECTION_PASSWORD = "MarufSheikh";  //accessing the system environment variable for password
		//private static final String URL = "jdbc:mysql://23.29.122.186:3306/m0dELtesT"; 
	
	private static Connection connection;
	
	public static synchronized Connection getConnection() throws SQLException {
		if (connection == null) {
			try {
				Class.forName("org.postgresql.Driver");
				//Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("Could not register driver!");
				e.printStackTrace();
			}
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
		}
		
		//If connection was closed then retrieve a new connection
		if (connection.isClosed()){
			System.out.println("Opening new connection...");
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
		}
		return connection;
	}
	

	public static userDAO getUserDAO() {
		return new userDAOImplement();
	
	}
	
	public static RoleDAO getRoleDAO() {
		return new RoleDAOImpl();
	
	}
	public static StatusDAO getStatusDAO() {
		return new StatusDAOImplements();
	
	}
	
	public static AccountTypeDAO getAccountTypeDAO() {
		return new AccountTypeImplements();
	}
	public static AccountDAO getAccountDAO() {
		return new AccountDAOImpl();
	}
	public static SubjectDAO getSubjectDAO() {
		return new SubjectDAOImpl();
	}
	public static TopicDAO getTopicDAO() {
		return new TopicDAOImpl();
	}
	public static QuestionDAO getQuestionDAO() {
		return new QuestionDAOImpl();
	}
	public static ChoiceDAO getChoiceDAO() {
		return new ChoiceDAOImpl();
	}
	public static AnswerDAO getAnswerDAO() {
		return new AnswerDAOImpl();
	}
	public static choose_optionDAO getOptionDAO() {
		return new choose_optionDAOImpl();
	}
}
