package main.java.com.BGV.DAO;

import org.hibernate.SessionFactory;

import main.java.com.BGV.Model.Employee;
import main.java.com.BGV.Model.ViewObject;

public interface EmployeeCommonDAO extends CommonDAO
{
	public static SessionFactory sessionFactory = CommonDAO.sessionFactory;
	public ViewObject getEmployeeDetails(Employee employee);
	public ViewObject getEmployeeDetailsAll();
}