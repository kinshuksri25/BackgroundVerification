package main.java.com.BGV.DAO;

import org.hibernate.SessionFactory;

import main.java.com.BGV.Model.EmployeeDetails;
import main.java.com.BGV.Model.ViewObject;

public interface EmployeeDetailsCommonDAO extends CommonDAO
{
	SessionFactory sessionFactory = CommonDAO.sessionFactory;
	
	public ViewObject getEmployeeDetails(EmployeeDetails employeeDetails);
}
