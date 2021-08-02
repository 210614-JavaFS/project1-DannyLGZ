package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import daos.UserDAO;
import daos.UserDAOImpl;
import models.Reimbursement;
import models.User;
import utils.ConnectionUtil;

public class UserService {

	private static UserDAO userDao = new UserDAOImpl();
	
	
//	public User login(String name, String password) {
//		return userDao.login(name, password);
//	}
	
	
	public boolean approveReim(User user, int reimId) {
		return userDao.approveReimbursement(user, reimId);
	}
	
	public boolean denyReim(User user, int reimId) {
		return userDao.denyReimbursement(user, reimId);
	}
	
	public User getUserById(int user_id) {
		return userDao.findById(user_id);
	}
	
	public User getUserByUserName(String name) {
		return userDao.findUserByUserName(name);
	}
	
	
	
	public User userLogin(String name, String password) {
		User user = userDao.findUserByUserName(name);
		String encryptPassword = userDao.MD5(password);
		if(encryptPassword.equals(user.getPassword())){
			return user;
			
		}else {
			System.out.println("Invalid password!");
		}
		return null;
	}
}
