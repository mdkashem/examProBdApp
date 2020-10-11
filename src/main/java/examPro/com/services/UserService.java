package examPro.com.services;

import java.util.List;

import examPro.com.dao.userDAO;
import examPro.com.dao.userDAOImplement;
import examPro.com.model.User;

public class UserService {
	userDAO UserDAO;
	public UserService() {
		this.UserDAO = new userDAOImplement();
	}
	
	public List<User> allUsers(){
		return this.UserDAO.getAllUser();
		
	}

}
