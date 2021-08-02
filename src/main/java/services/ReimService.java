package services;

import java.util.List;

import daos.ReimDAO;
import daos.ReimDAOImpl;
import models.Reimbursement;

public class ReimService {
	
	private static ReimDAO reimDao = new ReimDAOImpl();
	
	public List<Reimbursement> getAllReim() {
		return reimDao.findAllReimbursement();
	}
	
	
	public List<Reimbursement> getPendingReim() {
		return reimDao.findAllPendingReimbursement();
	}
	
	
	public List<Reimbursement> getReimByAutor(int user_id){
		return reimDao.findAllReimbursementByAuthor(user_id);
			
	}
	
	
	public Reimbursement getReimById(int reim_id) {
		return reimDao.findReimbursementById(reim_id);
	}
	
	public boolean addReim(Reimbursement reimbursement) {
		return reimDao.addReimbursement(reimbursement);
	}
	
}
