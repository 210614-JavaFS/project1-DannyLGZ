package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.ReimbursementStatus;
import utils.ConnectionUtil;

public class ReimbursementStatusDAOImpl implements ReimbursementStatusDAO{

	@Override
	public ReimbursementStatus findByStatusId(int reimStatusId) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM ers_reimbursement_status WHERE reim_status_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, reimStatusId);
			ResultSet result = statement.executeQuery();
			ReimbursementStatus reimbursementStatus = new ReimbursementStatus();
			while(result.next()) {
				reimbursementStatus.setStatusId(result.getInt("reim_status_id"));
				reimbursementStatus.setStatus(result.getString("reim_status"));
			}
			return reimbursementStatus;
			
		}catch(SQLException e) {
				e.printStackTrace();
			}
		return null;
	}

}
