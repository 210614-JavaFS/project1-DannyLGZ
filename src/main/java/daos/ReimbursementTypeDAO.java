package daos;

import models.ReimbursementType;

public interface ReimbursementTypeDAO {

	public ReimbursementType findByTypeId(int reimTypeId);
	
}
