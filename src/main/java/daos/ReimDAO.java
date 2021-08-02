package daos;

import java.util.List;

import models.Reimbursement;
import models.User;

public interface ReimDAO {

	public List<Reimbursement> findAllReimbursement();
	public List<Reimbursement> findAllPendingReimbursement();
	public List<Reimbursement> findAllReimbursementByAuthor(int userId);
	public List<Reimbursement> findAllReimbursementByType();
	public Reimbursement findReimbursementById(int reim_id);
	public boolean addReimbursement(Reimbursement reimbursement);
//	public boolean updateReimbursement(Reimbursement reimbursement);
	
}
