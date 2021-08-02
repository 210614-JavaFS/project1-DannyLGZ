package daos;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import models.Reimbursement;
import models.ReimbursementStatus;
import models.ReimbursementType;
import models.User;
import utils.ConnectionUtil;

public class ReimDAOImpl implements ReimDAO{
	
	UserDAO userDao = new UserDAOImpl();
	ReimbursementStatusDAO reimbursementStatusDAO = new ReimbursementStatusDAOImpl();
	ReimbursementTypeDAO reimbursementTypeDAO = new ReimbursementTypeDAOImpl();
	public static Random rd = new Random();
	private static Logger log = LoggerFactory.getLogger(ReimDAOImpl.class);
	
	//-----------------------------------------------------------------------------------
	@Override
	public List<Reimbursement> findAllReimbursement() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String allReim = "SELECT * FROM ers_reimbursement;";
//			String allReim = "SELECT reim_id, reim_amount, reim_submitted, reim_resolved, reim_description, user_first_name, user_last_name, reim_status, reim_type FROM (SELECT * FROM \r\n"
//					+ "	(SELECT * FROM ers_reimbursement LEFT JOIN ers_reimbursement_type ON ers_reimbursement.reim_type_id  = ers_reimbursement_type.reim_type_id) AS a\r\n"
//					+ "		LEFT JOIN ers_reimbursement_status ON a.reim_status_id = ers_reimbursement_status.reim_status_id) AS b\r\n"
//					+ "		LEFT JOIN ers_users ON b.reim_author = ers_users.ers_users_id;";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(allReim);
			List<Reimbursement> allReimList = new ArrayList<>();
			
			while(result.next()) {
				Reimbursement reimbursement = new Reimbursement();
				reimbursement.setReimId(result.getInt("reim_id"));
				reimbursement.setReimAmount(result.getDouble("reim_amount"));
				reimbursement.setReimSubmitted(result.getTimestamp("reim_submitted"));
				reimbursement.setReimResolved(result.getTimestamp("reim_resolved"));
				reimbursement.setReimDescription(result.getString("reim_description"));
//				reimbursement.setReimReceipt(result.getString("reim_receipt"));
				int reimAuthorId = result.getInt("reim_author");
				User userAuthor = userDao.findById(reimAuthorId);
				reimbursement.setReimAuthorId(userAuthor);
				int reimResolverId = result.getInt("reim_resolver");
				User userResolver = userDao.findById(reimResolverId);
				reimbursement.setReimResolverId(userResolver);
				int reimStatusId = result.getInt("reim_status_id");
				ReimbursementStatus reimbursementStatus = reimbursementStatusDAO.findByStatusId(reimStatusId);
				reimbursement.setReimStatusId(reimbursementStatus);
				int reimTypeId = result.getInt("reim_type_id");
				ReimbursementType reimbursementType = reimbursementTypeDAO.findByTypeId(reimTypeId);
				reimbursement.setReimTypeId(reimbursementType);
				allReimList.add(reimbursement);
			}
			log.info("Retrieve all reimbursement tickets");
			return allReimList;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	//-----------------------------------------------------------------------------------
	@Override
	public List<Reimbursement> findAllPendingReimbursement() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String allPendingReim = "SELECT * FROM ers_reimbursement WHERE reim_status_id = ?;";
			PreparedStatement statement = conn.prepareStatement(allPendingReim);
			statement.setInt(1, 1);
			ResultSet result = statement.executeQuery();
			List<Reimbursement> allPendingList = new ArrayList<>();
			
			while(result.next()) {
				Reimbursement reimbursement = new Reimbursement();
				reimbursement.setReimId(result.getInt("reim_id"));
				reimbursement.setReimAmount(result.getDouble("reim_amount"));
				reimbursement.setReimSubmitted(result.getTimestamp("reim_submitted"));
				reimbursement.setReimResolved(result.getTimestamp("reim_resolved"));
				reimbursement.setReimDescription(result.getString("reim_description"));
//				reimbursement.setReimReceipt(result.getString("reim_receipt"));
				int reimAuthorId = result.getInt("reim_author");
				User userAuthor = userDao.findById(reimAuthorId);
				reimbursement.setReimAuthorId(userAuthor);
				int reimResolverId = result.getInt("reim_resolver");
				User userResolver = userDao.findById(reimResolverId);
				reimbursement.setReimResolverId(userResolver);
				int reimStatusId = result.getInt("reim_status_id");
				ReimbursementStatus reimbursementStatus = reimbursementStatusDAO.findByStatusId(reimStatusId);
				reimbursement.setReimStatusId(reimbursementStatus);
				int reimTypeId = result.getInt("reim_type_id");
				ReimbursementType reimbursementType = reimbursementTypeDAO.findByTypeId(reimTypeId);
				reimbursement.setReimTypeId(reimbursementType);
				allPendingList.add(reimbursement);
			}
			log.info("Retrieve all pending reimbursement tickets");
			return allPendingList;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	//-----------------------------------------------------------------------------------
	@Override
	public List<Reimbursement> findAllReimbursementByAuthor(int userId) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String userReim = "SELECT * FROM ers_reimbursement WHERE reim_author = ?;";
			PreparedStatement statement = conn.prepareStatement(userReim);
			statement.setInt(1, userId);
			
			ResultSet result = statement.executeQuery();
			List<Reimbursement> listUserReim = new ArrayList<>();
			
			while(result.next()) {
				Reimbursement reimbursement = new Reimbursement();
				reimbursement.setReimId(result.getInt("reim_id"));
				reimbursement.setReimAmount(result.getDouble("reim_amount"));
				reimbursement.setReimSubmitted(result.getTimestamp("reim_submitted"));
				reimbursement.setReimResolved(result.getTimestamp("reim_resolved"));
				reimbursement.setReimDescription(result.getString("reim_description"));
//				reimbursement.setReimReceipt(result.getString("reim_receipt"));
				int reimAuthorId = result.getInt("reim_author");
				User userAuthor = userDao.findById(reimAuthorId);
				reimbursement.setReimAuthorId(userAuthor);
				int reimResolverId = result.getInt("reim_resolver");
				User userResolver = userDao.findById(reimResolverId);
				reimbursement.setReimResolverId(userResolver);
				int reimStatusId = result.getInt("reim_status_id");
				ReimbursementStatus reimbursementStatus = reimbursementStatusDAO.findByStatusId(reimStatusId);
				reimbursement.setReimStatusId(reimbursementStatus);
				int reimTypeId = result.getInt("reim_type_id");
				ReimbursementType reimbursementType = reimbursementTypeDAO.findByTypeId(reimTypeId);
				reimbursement.setReimTypeId(reimbursementType);
				listUserReim.add(reimbursement);
			}
			log.info("Retrieve past reimbursement tickets");
			return listUserReim;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	//-----------------------------------------------------------------------------------
	@Override
	public List<Reimbursement> findAllReimbursementByType() {
		// TODO Auto-generated method stub
		return null;
	}
	//-----------------------------------------------------------------------------------
	@Override
	public Reimbursement findReimbursementById(int reim_id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String findReim = "SELECT * FROM ers_reimbursement WHERE reim_id = ?;";
			PreparedStatement statement = conn.prepareStatement(findReim);
			statement.setInt(1, reim_id);
			ResultSet result = statement.executeQuery();
			Reimbursement reimbursement = new Reimbursement();
			while(result.next()) {
				reimbursement.setReimId(result.getInt("reim_id"));
				reimbursement.setReimAmount(result.getDouble("reim_amount"));
				reimbursement.setReimSubmitted(result.getTimestamp("reim_submitted"));
				reimbursement.setReimResolved(result.getTimestamp("reim_resolved"));
				reimbursement.setReimDescription(result.getString("reim_description"));
//				reimbursement.setReimReceipt(result.getString("reim_receipt"));
				int reimAuthorId = result.getInt("reim_author");
				User userAuthor = userDao.findById(reimAuthorId);
				reimbursement.setReimAuthorId(userAuthor);
				int reimResolverId = result.getInt("reim_resolver");
				User userResolver = userDao.findById(reimResolverId);
				reimbursement.setReimResolverId(userResolver);
				int reimStatusId = result.getInt("reim_status_id");
				ReimbursementStatus reimbursementStatus = reimbursementStatusDAO.findByStatusId(reimStatusId);
				reimbursement.setReimStatusId(reimbursementStatus);
				int reimTypeId = result.getInt("reim_type_id");
				ReimbursementType reimbursementType = reimbursementTypeDAO.findByTypeId(reimTypeId);
				reimbursement.setReimTypeId(reimbursementType);
			}
			log.info("Retrieve reimbursement tickets" + reim_id);
			return reimbursement;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//-----------------------------------------------------------------------------------
	@Override
	public boolean addReimbursement(Reimbursement reimbursement) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String newReim = "INSERT INTO ers_reimbursement "
					+ "(reim_id, reim_amount, reim_submitted, reim_resolved, reim_description, reim_author, reim_resolver, reim_status_id, reim_type_id)"
					+ "VALUES (?,?,?,?,?,?,?,?,?);";
			Timestamp timestamp = new Timestamp(new java.util.Date().getTime());
			PreparedStatement statement = conn.prepareStatement(newReim);
			int reimId = 10000 + rd.nextInt(89999);
			int index = 0;
			statement.setInt(++index, reimId);
			statement.setDouble(++index, reimbursement.getReimAmount());
			statement.setTimestamp(++index, timestamp);
			statement.setTimestamp(++index, null);
			statement.setString(++index, reimbursement.getReimDescription());
			statement.setInt(++index, reimbursement.getReimAuthorId().getUserId());
			statement.setObject(++index,null);
			statement.setInt(++index, 1);
			statement.setInt(++index, reimbursement.getReimTypeId().getTypeId());
			statement.execute();
			
			log.info("Submit new reimbursement ticket");
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//-----------------------------------------------------------------------------------
//	@Override
//	public boolean updateReimbursement(Reimbursement reimbursement) {
//
//		
//		return false;
//	}

}
