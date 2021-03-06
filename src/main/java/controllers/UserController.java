package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.User;
import services.UserService;

public class UserController extends HttpServlet{

//	private static Logger log = LoggerFactory.getLogger(UserController.class);
	private static UserService userService = new UserService();
	private ObjectMapper objectMapper = new ObjectMapper();
	
	
	public boolean validateUser(HttpServletRequest request, HttpServletResponse response) 
			throws IOException {

		BufferedReader reader = request.getReader();
		StringBuilder stringBuilder = new StringBuilder();
		String line = reader.readLine();
		while (line != null) {
			stringBuilder.append(line);
			line = reader.readLine();
		}
		
		String body = new String(stringBuilder);
		System.out.println(body);
		User user = objectMapper.readValue(body, User.class);
		String name = user.getUserName();
		String password = user.getPassword();
		
		if(userService.userLogin(name, password)!=null) {
			String json = objectMapper.writeValueAsString(userService.getUserByUserName(name));
			response.getWriter().print(json);
			System.out.println("Login Successful!");
			response.setStatus(201);
			HttpSession session = request.getSession();
			
//			session.setAttribute("user", user);
			session.setAttribute("userId", user.getUserId());
			session.setAttribute("userName", user.getUserName());
			session.setAttribute("password", user.getPassword());
			session.setAttribute("firstName", user.getFirstName());
			session.setAttribute("lastName", user.getLastName());
			session.setAttribute("email", user.getEmail());
			session.setAttribute("roleId", user.getRoleId());
			
			return true;
		}else {
			System.out.println("Login Failed.");
			response.setStatus(406);
			return false;
		}
	}
	
	
	public void approveReim(HttpServletResponse response, User user, int reimId) 
			throws IOException {
		if(userService.approveReim(user, reimId)) {
			System.out.println("The reimbursement has been approved");
			response.setStatus(201);
		}else {
			System.out.println("Something is wrong.");
			response.setStatus(406);
		}
	}
	
	
	public void denyReim(HttpServletResponse response, User user, int reimId) 
			throws IOException {
		if(userService.denyReim(user, reimId)) {
			System.out.println("The reimbursement has been rejected");
			response.setStatus(201);
		}else {
			System.out.println("Something is wrong.");
			response.setStatus(406);
		}
	}
	
	
	
	public void getUserById(HttpServletResponse response, int userId) throws IOException{
		User user = userService.getUserById(userId);
		String json = objectMapper.writeValueAsString(user);
		response.getWriter().print(json);
		response.setStatus(200);
	}
}
