package services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import models.User;
import models.UserRole;

class UserServiceTest {
	
	public static UserService userService;
	
	@BeforeAll
	public static void setUserService() {
		userService = new UserService();
		System.out.println("Unit test begin!");
	}
	
	@AfterAll
	public static void clearUserService() {
		System.out.println("Unit test done!");
		userService = null;
	}
	

	@Test
	void testApproveReim() {
		System.out.println("Testing approve reim.");
		User user = new User();
		UserRole user_role = new UserRole();
		user.setRoleId(user_role);
		int reimId = 12322;
		userService.approveReim(user, reimId);
		assertTrue(true);
		System.out.println("===========================================================");
		
	}

	@Test
	void testDenyReim() {
		System.out.println("Testing deny reim.");
		User user = new User();
		UserRole user_role = new UserRole();
		user.setRoleId(user_role);
		int reimId = 12322;
		userService.denyReim(user, reimId);
		assertTrue(true);
		System.out.println("===========================================================");
	}

	@Test
	void testGetUserById() {
		System.out.println("Testing Get User by userID.");
		User user = new User();
		int userId = user.getUserId();
		assertNotNull(userService.getUserById(userId));
		System.out.println("===========================================================");
	}

	@Test
	void testGetUserByUserName() {
		System.out.println("Testing Get User by userName.");
		String userName = "tester1";
		assertNotNull(userService.getUserByUserName(userName));
		System.out.println("===========================================================");
	}

	@Test
	void testUserLogin() {
		System.out.println("Testing user login.");
		String name = "tester1";
		String password = "password";
		assertNotNull(userService.userLogin(name, password));
		System.out.println("===========================================================");
	}

}
