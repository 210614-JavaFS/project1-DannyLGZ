package daos;

import models.Reimbursement;
import models.User;

public interface UserDAO {
	//public boolean register(User user);
//	public User login(String name, String password);
	public User findById(int userId);
	public User findUserByUserName(String userName);
	public boolean approveReimbursement(User user, int reimId);
	public boolean denyReimbursement(User user, int reimId);
	public String MD5(String password);
}
