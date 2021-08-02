DROP TABLE IF EXISTS ers_reimbursement;  
DROP TABLE IF EXISTS ers_reimbursement_status;  
DROP TABLE IF EXISTS ers_reimbursement_type;  
DROP TABLE IF EXISTS ers_users;  
DROP TABLE IF EXISTS ers_user_roles;  


-- Current reimbursement statuses: APPROVED, PENDING and DENIED
CREATE TABLE ers_reimbursement_status (
  reim_status_id INTEGER PRIMARY KEY,
  reim_status    VARCHAR(10) NOT NULL
);

-- Current reimbursement types: LODGING, TRAVEL, FOOD or OTHER
CREATE TABLE ers_reimbursement_type (
  reim_type_id INTEGER PRIMARY KEY,
  reim_type    VARCHAR(10) NOT NULL
);

CREATE TABLE ers_user_roles (
  ers_user_role_id INTEGER PRIMARY KEY,
  user_role        VARCHAR(20) NOT NULL
);

CREATE TABLE ers_users (
  ers_users_id    INTEGER PRIMARY KEY,
  ers_username    VARCHAR(50)  NOT NULL UNIQUE,
  ers_password    VARCHAR(100)  NOT NULL,
  user_first_name VARCHAR(100) NOT NULL,
  user_last_name  VARCHAR(100) NOT NULL,
  user_email      VARCHAR(150) NOT NULL UNIQUE,
  user_role_id    INTEGER        NOT NULL,
  FOREIGN KEY (user_role_id) REFERENCES ers_user_roles(ers_user_role_id)
);


CREATE TABLE ers_reimbursement (
  reim_id          INTEGER    PRIMARY KEY,
  reim_amount      NUMERIC    NOT NULL,
  reim_submitted   TIMESTAMP NOT NULL,
  reim_resolved    TIMESTAMP,
  reim_description VARCHAR(250),
  reim_receipt     BYTEA,
  reim_author      INTEGER    NOT NULL,
  reim_resolver    INTEGER,
  reim_status_id   INTEGER    NOT NULL,
  reim_type_id     INTEGER    NOT NULL,
  FOREIGN KEY (reim_author) REFERENCES ers_users(ers_users_id),
  FOREIGN KEY (reim_resolver) REFERENCES ers_users(ers_users_id),
  FOREIGN KEY (reim_status_id) REFERENCES ers_reimbursement_status(reim_status_id),
  FOREIGN KEY (reim_type_id) REFERENCES ers_reimbursement_type(reim_type_id)
);


-- ====================================== Mock Data =============================================


--Populating ERS_REIMBURSEMENT_STATUS
INSERT INTO ers_reimbursement_status 
	VALUES  (1, 'Pending'),
			(2, 'Approved'),
			(3, 'Denied');

--Populating ERS_REIMBURSEMENT_TYPE
INSERT INTO ers_reimbursement_type
	VALUES  (1, 'Lodging'),
			(2, 'Travel'),
			(3, 'Food'),
			(4, 'Other');

--Populating ERS_USER_ROLES	
INSERT INTO ers_user_roles (ers_user_role_id, user_role) 
	VALUES  (1, 'Finance Manager'),
 			(2, 'Employee');
			

--Populating ERS_USERS		
INSERT INTO ers_users (ers_users_id, ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id)
	VALUES  (1, 'tester1', '5f4dcc3b5aa765d61d8327deb882cf99', 'Steven', 'Hamilton', 'shamilton0@list-manage.com', 1),
 			(2, 'tester2', 'dc647eb65e6711e155375218212b3964', 'Earl', 'Davis', 'edavis1@wiley.com', 1),
 			(3, 'tester3', 'f5bb0c8de146c67b44babbf4e6584cc0', 'Sharon', 'Baker', 'sbaker2@mtv.com', 2),
 			(4, 'tester4', 'a1fa59e79bba1a38bb0684d3298c9ddd', 'Janice', 'Gardner', 'jgardner3@indiegogo.com', 2);
 		
 		
 		
--Populating ERS_REIMBURSEMENT
INSERT INTO ers_reimbursement (reim_id, reim_amount, reim_submitted, reim_resolved, reim_description, reim_receipt, reim_author, reim_resolver, reim_status_id, reim_type_id)
	VALUES  (28873, 100, '07/25/2021 07:29:46',NULL,NULL,NULL,1,NULL,1,3 ),
			(14965,50,'07/28/2021 19:42:21',NULL,'Taxi transportation',NULL,2,NULL,1,2),
			(95473,500,'08/01/2021 09:14:46','08/01/2021 09:20:15','Conference Admission',NULL,3,1,2,4),
			(52109,1000,'08/01/2021 20:43:16','08/01/2021 23:18:32','Entertainment Cost',NULL,4,2,3,3);










