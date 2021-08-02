package daos;

import models.ReimbursementStatus;

public interface ReimbursementStatusDAO {

	public ReimbursementStatus findByStatusId(int reimStatusId);
}
