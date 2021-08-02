package daos;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import models.Reimbursement;
import models.User;
import models.UserRole;
import utils.ConnectionUtil;

public class UserDAOImpl implements UserDAO{
	
	public Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	UserRoleDAO userRoleDAO = new UserRoleDAOImpl();
	private static Logger log = LoggerFactory.getLogger(UserDAOImpl.class);
	

//	@Override
//	public User login(String name, String password) {
//		try(Connection conn = ConnectionUtil.getConnection()){
//			String check_userName = "SELECT * FROM ers_users WHERE ers_username = ?;";
//			PreparedStatement statement = conn.prepareStatement(check_userName);
//			statement.setString(1, name);
//			ResultSet result = statement.executeQuery();
//			if (result.next()) {
////				boolean password_verified = false;
////				password_verified = BCrypt.checkpw(inputPassword, retrievedHash);
//				
//				String userPassword = result.getString("ers_password");
//				if (password.equals(userPassword)) {
//					User user = new User(name,password);
//					user.setUserId(result.getInt("ers_users_id"));
//					user.setFirstName(result.getString("user_first_name"));
//					user.setLastName(result.getString("user_last_name"));
//					user.setEmail(result.getString("user_email"));
//					int userRoleId = result.getInt("user_role_id");
//					UserRole userRole = userRoleDAO.findById(userRoleId);
//					user.setRoleId(userRole);
//			
//					log.info(user.getUserId()+ " "+user.getRoleId().getRole()+": "+user.getFirstName()+" "+user.getLastName()
//							+ " logged in.");
//					return user;
//				}else {
//					System.out.println("Invalid Password");
////					login(name, password);	
//				}
//			}else {
//				System.out.println("Invalid Username.");
////				login(name, password);
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}
//	
//		return null;
//	}

	@Override
	public boolean approveReimbursement(User user, int reimId) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String update = "UPDATE ers_reimbursement SET reim_resolved = ?, reim_status_id = ?, reim_resolver = ?"
					+ "WHERE reim_id = ?;";
			PreparedStatement statement = conn.prepareStatement(update);
			statement.setTimestamp(1, timestamp);
			statement.setInt(2, 2);
			statement.setInt(3, user.getUserId());
			statement.setInt(4, reimId);
			statement.execute();
			log.info(user.getRoleId().getRole()+" :"+user.getFirstName()+user.getLastName()+" approved "
					+reimId);
			return true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean denyReimbursement(User user, int reimId) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String update = "UPDATE ers_reimbursement SET reim_resolved = ?, reim_status_id = ?, reim_resolver = ?"
					+ "WHERE reim_id = ?;";
			PreparedStatement statement = conn.prepareStatement(update);
			statement.setTimestamp(1, timestamp);
			statement.setInt(2, 3);
			statement.setInt(3, user.getUserId());
			statement.setInt(4, reimId);
			statement.execute();
			log.info(user.getRoleId().getRole()+" :"+user.getFirstName()+user.getLastName()+" rejected "
					+reimId);
			return true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User findById(int userId) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM ers_users WHERE ers_users_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, userId);
			ResultSet result = statement.executeQuery();
			User user = new User();
			while(result.next()) {
				user.setUserId(result.getInt("ers_users_id"));
				user.setFirstName(result.getString("user_first_name"));
				user.setLastName(result.getString("user_last_name"));
				user.setEmail(result.getString("user_email"));
				int userRoleId = result.getInt("user_role_id");
				UserRole userRole = userRoleDAO.findById(userRoleId);
				user.setRoleId(userRole);
			}
			return user;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User findUserByUserName(String userName) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM ers_users WHERE ers_username = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, userName);
			ResultSet result = statement.executeQuery();
			User user = new User();
			if(result.next()) {
				user.setUserName(result.getString("ers_username"));
				user.setPassword(result.getString("ers_password"));
				user.setUserId(result.getInt("ers_users_id"));
				user.setFirstName(result.getString("user_first_name"));
				user.setLastName(result.getString("user_last_name"));
				user.setEmail(result.getString("user_email"));
				int userRoleId = result.getInt("user_role_id");
				UserRole userRole = userRoleDAO.findById(userRoleId);
				user.setRoleId(userRole);
				
				return user;
			}else {
				System.out.println("Invalid Username. Please try again.");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String MD5(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(password.getBytes());
			BigInteger number = new BigInteger(1,messageDigest);
			String hashtext = number.toString(16);
			while (hashtext.length()<32) {
				hashtext = "0"+ hashtext;
			}
			return hashtext;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

}
