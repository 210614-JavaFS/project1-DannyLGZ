package daos;

import models.UserRole;

public interface UserRoleDAO {
	
	public UserRole findById(int userRoleId);
}
