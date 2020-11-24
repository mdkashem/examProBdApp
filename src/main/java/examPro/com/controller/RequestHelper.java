package examPro.com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.sql.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import examPro.com.dao.subject.Quiz;
import examPro.com.model.AccountStatus;
import examPro.com.model.Role;
import examPro.com.model.User;
import examPro.com.services.AdminRoleService;
import examPro.com.services.SubjectServices;
import examPro.com.services.UserRoleService;
import examPro.com.services.UserService;
import examPro.com.utilities.DAOUtilities;

public class RequestHelper {
	static HttpSession session;
	public static AdminRoleService adminRoleService = new AdminRoleService();
	public static UserRoleService userRoleService = new UserRoleService();

	public static Object processGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		final String URI = request.getRequestURI().replace("/examPro/examApi", "");
		System.out.println(URI);

		if (session == null) {
			return "Please login";
		} else {
			String role = (String) session.getAttribute("role");
			if (role.equalsIgnoreCase("user")) {
				switch (URI) {
				case "/all":
					// handleRequest(request, response);

					return "To be implemented";
				case "/take/quiz":
					// take subject name and sub topic name from user and generate quiz.

					return userRoleService.generateQuiz("JAVA", "Core Java", 3);

				case "/user/all":
					// response.getWriter().write("To be implemented");
					return "To Be Implemented";
				case "/logout":
					// logout working successfully. so it is done
					request.getSession(false);

					if (session != null) {
						session.invalidate();
					}
					session = null;
					return "You Logout";

				default:
					return "No Such Endpoint";
				}

			} else {
				switch (URI) {
				case "/adim":
					// handleRequest(request, response);

					return "To be implemented";// new SuperPowerService().findAllSuperPowers();
				case "/InsertQuiz":
					// handleRequest(request, response);

					return "Insert Quiz to be implemented";

				case "/user/all":

					return new UserService().allUsers(); // return all users as json
				case "/subject/all":

					return new SubjectServices().getAllSubject();
				case "/logout":
					// logout working successfully. so it is done
					request.getSession(false);

					if (session != null) {
						session.invalidate();
					}
					session = null;
					return "You Logout";

				default:
					return "No Such Endpoint";
				}
			}
		}
	}

	public static void processPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		final String URI = request.getRequestURI().replace("/examPro/examApi", "");
		System.out.println(URI);
		if (URI.equalsIgnoreCase("/login")) { // login is done
			String userName = request.getParameter("email");
			String pass = request.getParameter("password");
			User user = DAOUtilities.getUserDAO().getUserByName(userName);
			if (user != null && user.getPassword().equals(pass)) {
				Role role = DAOUtilities.getRoleDAO().getRoleById(user.getRoleId());
				System.out.println(user);
				System.out.println(role);
				session = request.getSession();
				// We can also set session attributes!
				session.setAttribute("username", userName);
				session.setAttribute("role", role.getRole());
			} else {
				response.getWriter().write("Invalid username");
			}
		}
		if (session != null && ((String) session.getAttribute("role")).equalsIgnoreCase("admin")) {
			switch (URI) {
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
				boolean isCreated = adminRoleService.createNewUser(email, password, fName, lName, DOB, phone, date);
				if (isCreated == true) {
					response.getWriter().write("New user has been created successfully");
				} else {
					response.getWriter().write("operation was not completed!!!");
				}

				break;
			case "/update":
				
				String email_update = request.getParameter("email");
				String password_update = request.getParameter("password");
				String fName_update = request.getParameter("fName");
				String lName_update = request.getParameter("lName");
				String DOB_update = request.getParameter("date_of_birth");
				String phone_update = request.getParameter("phone");
				Date date_update = new Date(System.currentTimeMillis());
				String isCreated_update = adminRoleService.updateUser(email_update, password_update, fName_update,
						lName_update, DOB_update, phone_update, date_update);
				response.getWriter().write(isCreated_update);
				break;
			case "/quiz/create":
				//
				String subject = request.getParameter("subject");
				String sub_topic = request.getParameter("sub_topic");
				String question = request.getParameter("question");
				String choice = request.getParameter("choice");
				String option_1 = request.getParameter("option_1");
				String option_2 = request.getParameter("option_2");
				String option_3 = request.getParameter("option_3");
				adminRoleService = new AdminRoleService();
				boolean check = adminRoleService.inserQuiz(subject, sub_topic, question, choice, option_1, option_2,
						option_3);
				if (check == false) {
					response.getWriter().write("Your data was not entered. Make sure, you are logged in as a admin");
				} else {
					response.getWriter().write(" Data has been entered succfully");
				}
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
		if (session != null && ((String) session.getAttribute("role")).equalsIgnoreCase("user")) {
			// this is where regular users activity

			switch (URI) {
			case "/submit/quiz":
				// take subject name and sub topic name from user and generate quiz.
				BufferedReader reader = request.getReader();

				StringBuilder sb = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}

				String jsonString = sb.toString();
				ObjectMapper oMapper = new ObjectMapper();
				Quiz quiz = oMapper.readValue(jsonString, Quiz.class);

				response.getWriter().write(oMapper.writeValueAsString(quiz));
				response.getWriter().write(" Submit quiz to be implemented!");
				response.setContentType("application/json");
				response.setStatus(201);
				break;

			}
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
			// out.write(paramName);
			// out.write("n");
			String[] paramValues = req.getParameterValues(paramName);
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
