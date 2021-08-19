# Employee Reimbursment System (ERS)

## Executive Summary
The Expense Reimbursement System (ERS) will manage the process of reimbursing employees for expenses incurred while on company time. All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests. Finance managers can log in and view all reimbursement requests and past history for all employees in the company. Finance managers are authorized to approve and deny requests for expense reimbursement.


## Project Objectives
* Design a single page application.
* Create a SQL databese using AWS RDS.
* Connect back-end system to database with JDBC
* User credential shall be encrypted and store in database.
* Application shall deploy onto a Tomcat Server.
* The middle tier shall use Servlet technology for dynamic Web application development.
* Implement Logback for appropriate logging.
* Test coverage of the service layer.


## Design Requirement
* Employee
1. Log in with existing user credential.
2. View history reimbursement ticket(s).
3. Submit new reimbursement ticket(s).

* Finance Manager
1. Approve/Reject pending reimbursement ticket(s).
2. View all reimbursement ticket(s).
3. View all pending reimbursement ticket(s).
4. View self history reimbursement ticket(s).
5. Submit new reimbursement ticket(s).


## Use Case Diagram

![](./imgs/use-case.jpg)


## Activity Diagram

![](./imgs/activity.jpg)



## PostgreSQL 3rd normal form table construction

![image](https://user-images.githubusercontent.com/67383232/129967495-efcb4e62-4526-4d14-aa3d-a229099d50c8.png)


## User table with encrypted credential
![image](https://user-images.githubusercontent.com/67383232/129971352-ec99cdbb-4273-4952-a5c0-0e86d0961436.png)

## Welcome page after sucessful login
![image](https://user-images.githubusercontent.com/67383232/129996526-96b2d4e1-cf58-4a67-88a3-234478df614a.png)


## Reimbursement Types
Employees must select the type of reimbursement as: LODGING, TRAVEL, FOOD, or OTHER.
![image](https://user-images.githubusercontent.com/67383232/129996670-6f52cb24-08cf-4f27-b628-def7e8f82b46.png)






## Features Todo for Future Stretch Goals
* Create a single page application. 
* Replace HTML/JavaScript with a React application.
* Users can upload a document or image of their receipt when submitting reimbursements which can stored in the database and reviewed by a financial manager.
* Application shall be hosted remotely on an EC2.
* Static files (webpages) shall be hosted on an S3 bucket. 
