package services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import models.Reimbursement;
import models.ReimbursementType;
import models.User;

class ReimServiceTest {
	
	public static ReimService reimService;
	public static List<Reimbursement> result;
	
	
	@BeforeAll
	public static void setReimService() {
		reimService = new ReimService();
		System.out.println("Unit test begin!");
	}
	
	@AfterAll
	public static void clearReimService() {
		System.out.println("Unit test done!");
		reimService = null;
	}
	
	@Test
	void testGetAllReim() {
		System.out.println("Testing get all reimbursements");
		result = reimService.getAllReim();
		assertNotNull(result);
		System.out.println("==============================================================");
	}

	@Test
	void testGetPendingReim() {
		System.out.println("Testing get all pending reimbursements");
		result = reimService.getPendingReim();
		assertNotNull(result);
		System.out.println("==============================================================");
	}

	@Test
	void testGetReimByAutor() {
		System.out.println("Testing get reimbursements by author");
		int user_id = 1;
		result = reimService.getReimByAutor(user_id);
		assertNotNull(result);
		System.out.println("==============================================================");
	}

	@Test
	void testGetReimById() {
		System.out.println("Testing get reimbursements by reim_id");
		int reim_id = 43198;
		assertNotNull(reimService.getReimById(reim_id));
		System.out.println("==============================================================");
	}

	@Test
	void testAddReim() {
		System.out.println("Testing add reimbursement");
		Reimbursement reimbursement = new Reimbursement();
		User user = new User();
		ReimbursementType reim_type = new ReimbursementType();
		reimbursement.setReimAuthorId(user);	
		reimbursement.setReimTypeId(reim_type);
		reimService.addReim(reimbursement);
		assertTrue(true);
		System.out.println("==============================================================");
	}

}
