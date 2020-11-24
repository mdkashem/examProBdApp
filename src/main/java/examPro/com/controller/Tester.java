package examPro.com.controller;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.security.auth.Subject;

import java.sql.Date;
import java.sql.SQLException;

import examPro.com.dao.subject.Quiz;
import examPro.com.model.Account;
import examPro.com.model.AccountStatus;
import examPro.com.model.AccountType;
import examPro.com.model.Role;
import examPro.com.services.UserRoleService;
import examPro.com.utilities.DAOUtilities;
import org.apache.log4j.Logger;
public class Tester {
	private enum route  {
		apple;
}
	public static void main(String[] args) {
		//Subject sub = new Subject("Economy", "col1", "col2", "col3");
		//DAOUtilities.getTableDAO().addTable(sub);
		/*Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY:MM:dd");
       String result = sdf.format(cal.getTime());
        System.out.println( result);
		*/
		//DAOUtilities.getRoleDAO().addRole(new Role(1, "Admin"));
		//DAOUtilities.getAccountTypeDAO().addAccountType(new AccountType(1, "regular"));
	///	DAOUtilities.getStatusDAO().addStatus(new AccountStatus(1,"pending"));
		//Date date = new Date(System.currentTimeMillis());
		//DAOUtilities.getAccountDAO().addAccount(new Account(1,3.50,  date, 1,1));
	//Object sub =	DAOUtilities.getSubjectDAO().findSubjectByName("JAVA");
	
	//System.out.println(sub);
	//
		Logger logger = Logger.getLogger(Tester.class);
		logger.debug("Logger Works! (Log4j 1)");
		
		
	}

}
