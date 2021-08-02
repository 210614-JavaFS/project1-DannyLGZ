package web;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import controllers.ReimController;
import controllers.UserController;
import daos.UserDAO;
import daos.UserDAOImpl;
import models.User;

public class FrontControllerServlet extends HttpServlet {

	private static Logger log = LoggerFactory.getLogger(FrontControllerServlet.class);
	private ReimController reimController = new ReimController();
	private UserDAO userDAO = new UserDAOImpl();
	private UserController userController = new UserController(); 
	ObjectMapper objectMapper = new ObjectMapper();
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		response.setContentType("application/json");
		response.setStatus(404);
		
		final String URL = request.getRequestURI().replace("/ers/", "");
		
		System.out.println(URL);
		
		String[] UrlSections = URL.split("/"); 
		
		switch(UrlSections[0]) {
			case "login":
				if (request.getMethod().equals("POST")) {
					if(userController.validateUser(request, response)) {
						System.out.println("User Login is successful");
						log.info("");
					}else {
						System.out.println("Invalid Login Credential.");
					}
				}
				break;
				
				
			case "employee":
				if(session == null) {
					System.out.println("You are not logged in!");
				}else if (session != null) {
					response.setStatus(201);
				
				
				if (UrlSections.length == 2) {
					switch(UrlSections[1]) {
						case "check":
							String userName = session.getAttribute("userName").toString();
							User user = userDAO.findUserByUserName(userName);
							System.out.println(user);
							String json = objectMapper.writeValueAsString(user);
							response.getWriter().print(json);
							log.info(user.getUserId()+ " "+user.getRoleId().getRole()+": "+user.getFirstName()+" "+user.getLastName()
							+ " logged in.");
							break;
					
						case "allreim":
							reimController.getAllReim(response);
							break;
						case "pendingreim":
							reimController.getPendingReim(response);
							break;
						case "myreim":
							String usertest = session.getAttribute("userName").toString();
							User newUser = userDAO.findUserByUserName(usertest);
							int nameId = newUser.getUserId();
							reimController.getReimByAuthor(response, nameId);
							break;
						case "addnewreim":
							reimController.addReim(request, response);
							break;
						case "logout":
							session.invalidate();	
							break;
					}
				}else if (UrlSections.length == 3) {
					switch(UrlSections[1]) {
						case "reim":
							reimController.getReimById(response, Integer.parseInt(UrlSections[2]));
							break;
						case "approve":
							String userName = session.getAttribute("userName").toString();
							User manager = userDAO.findUserByUserName(userName);
							System.out.println(manager);
							userController.approveReim(response, manager, Integer.parseInt(UrlSections[2]));
							break;
						case "deny":
							String userName1 = session.getAttribute("userName").toString();
							User manager1 = userDAO.findUserByUserName(userName1);
							userController.denyReim(response, manager1, Integer.parseInt(UrlSections[2]));
							break;
					default:
						break;
					}
				}
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		doGet(request,response);
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request,response);
	}
	
	
}
