package examPro.com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.sql.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import examPro.com.model.Account;
import examPro.com.model.AccountStatus;
import examPro.com.model.Role;
import examPro.com.model.User;
import examPro.com.services.UserService;
import examPro.com.utilities.DAOUtilities;

public class RequestHelper {
	static HttpSession session ;
	public static Object processGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {

			final String URI = request.getRequestURI().replace("/examPro/examApi", "");
			System.out.println(URI);
			
			if(session == null) {
				return "Please login";
			}else{
				String role = (String) session.getAttribute("role");
				if(role.equalsIgnoreCase("user")) {
					switch (URI) {
					case "/all":
						//handleRequest(request, response);
						
						return "To be implemented";

					case "/user/all":
						//response.getWriter().write("To be implemented");
						return "To Be Implemented";
					case "/logout":
						// logout working successfully. so it is done 
						request.getSession(false);

						if (session != null) {
							session.invalidate();
						}
						session=null;
						return "You Logout";

					default:
						return "No Such Endpoint";
					}
					
				}else {
					switch (URI) {
					case "/adim":
						//handleRequest(request, response);
						
						return "To be implemented";//new SuperPowerService().findAllSuperPowers();
					case "/InsertQuiz":
						//handleRequest(request, response);
						
						return "Insert Quiz to be implemented";

					case "/user/all":
						
						return new UserService().allUsers(); // return all users as json 
					case "/logout":
						// logout working successfully. so it is done 
						request.getSession(false);

						if (session != null) {
							session.invalidate();
						}
						session=null;
						return "You Logout";

					default:
						return "No Such Endpoint";
					}
				}
				
			}
			
		}

	public static void processPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        final String URI = request.getRequestURI().replace("/examPro/examApi", "");
        System.out.println(URI);

        if(URI.equalsIgnoreCase("/login")) { //login is done
        	String userName = request.getParameter("email");
	   		String pass = request.getParameter("password");
	   		User user =DAOUtilities.getUserDAO().getUserByName(userName);
	   		if(user !=null && user.getPassword().equals(pass) ) {
	   			Role role = DAOUtilities.getRoleDAO().getRoleById(user.getRoleId());
	   			System.out.println(user);
		   		System.out.println(role);
		   		session = request.getSession();
				//We can also set session attributes!
				session.setAttribute("username", userName);
				session.setAttribute("role", role.getRole());
	   		}else {
	   			response.getWriter().write("Invalid username");
	   		}
        }
		   		if(session!=null && ((String) session.getAttribute("role")).equalsIgnoreCase("admin")) {
		   			switch(URI) {
		   			case "/catagory/create": // pass test on postman 
		   				
		   				break;
		   		   	case "/user/create": 
		   		   		// passed the postman test
		   		   		String email = request.getParameter("email");
		   		   		String password = request.getParameter("password");
		   		   		String fName = request.getParameter("fName");
		   		   		String lName = request.getParameter("lName");
		   		   		String DOB = request.getParameter("date_of_birth");
		   		   		String phone = request.getParameter("phone");
		   		     	Date date = new Date(System.currentTimeMillis());
		   		   		
		   		   		int newAccount_number = DAOUtilities.getAccountDAO().findLastAccountId(new Account(1, 0.00,  date, 1,1));
		   		   		DAOUtilities.getUserDAO().addUser(new User(1,email, password,fName, lName, DOB, phone, newAccount_number, 2));
		   				break;
		   	         case "/user/create/status":
		   	        	 // passed test from postman 
		   	        	 String status = request.getParameter("status");
		   	        	 DAOUtilities.getStatusDAO().addStatus(new AccountStatus(1, status));
		   				
		   				break;
		   			
		   			 default:
		   				response.getWriter().write("Home Page goes here. Login success");
		   			} // end switch 
		   		}
		   		
		   		if(session!=null && ((String) session.getAttribute("role")).equalsIgnoreCase("user")) {
		   			// this is where regular users activity 
		   			
		   			response.getWriter().write(" You login as a regular user");
		   		}	
		
	}
	
	
	/*
	 * the handleRequest method return all the parametter
	 */
	
	public static ArrayList<String> handleRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
		 
        PrintWriter out = res.getWriter();
        res.setContentType("text/plain");
        
        Enumeration<String> parameterNames = req.getParameterNames();
        ArrayList<String> list = new ArrayList<>();
        while (parameterNames.hasMoreElements()) {
 
            String paramName = parameterNames.nextElement();
            //out.write(paramName);
            //out.write("n");
 
          String[]  paramValues = req.getParameterValues(paramName);
            for (int i = 0; i < paramValues.length; i++) {
                String paramValue = paramValues[i];
                list.add(paramValue);
               // out.write("\t" + paramValue);
               // out.write("\n");
            }
 
        }
 
        out.close();
		return list;
 
    }

}
